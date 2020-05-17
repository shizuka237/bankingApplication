package src.bankingApp.account;

import src.bankingApp.transactions.Transaction;
import src.bankingApp.transactions.TransactionTypes;
import src.bankingApp.transactions.TransactionException;
import src.bankingApp.constants.ExceptionMessages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/*
Abstract class to represent a bank account

 */
public abstract class BankAccount {

    private static AtomicInteger globalAccounts = new AtomicInteger(0);;
    final private String accountNumber;//final because account no cannot change once set, it is its unique identifier
    private double minimumBalance;
    private double currentBalance;
    private double interestRate;
    private List<Transaction> transactionList;
    protected AccountTypes type;

    private static int MINI_STATEMENT_SIZE = 10;
    private static String ACCT_NO_PREFIX = "ACCT-";


    public BankAccount(double minimumBalance, double currentBalance, double interestRate, AccountTypes type) {
        this.accountNumber = ACCT_NO_PREFIX + ((Integer) globalAccounts.incrementAndGet()).toString();
        this.minimumBalance = minimumBalance;
        this.currentBalance = currentBalance;
        this.interestRate = interestRate;
        this.transactionList =  new ArrayList<>();
        this.type = type;
    }

    public  static void resetGlobalCount(){
        globalAccounts = new AtomicInteger(0);
    }

    /*
    * synchronized because we dont want any two parallal transactions to have same transaction ids
    * */
    private synchronized String generateTransactionId(){
        return  ((Integer)(this.transactionList.size() + 1)).toString();
    }

    /*
    * we validate and Commit a new transaction atomically so as to remove any inconsistency
    * between the two concurrent transactions for current account
     */
    private synchronized double validateAndCommitTransaction(final Transaction transaction)
        throws TransactionException{
            switch (transaction.getType()){
                case WITHDRAW:
                    if(this.currentBalance - transaction.getAmount() < this.minimumBalance){
                        throw new TransactionException(ExceptionMessages.INVALID_WITHDRAW_MESSAGE);
                    } else {
                        this.currentBalance -= transaction.getAmount();
                    }
                    this.transactionList.add(transaction);
                    break;

                case DEPOSIT:
                    if(transaction.getAmount() < 0){
                        throw new TransactionException(ExceptionMessages.INVALID_DEPOSIT);
                    } else {
                        this.currentBalance += transaction.getAmount();
                    }
                    this.transactionList.add(transaction);
                    break;
            }
            return this.currentBalance;
    }

    public double getMinimumBalance(){
        return this.minimumBalance;
    }

    public double getCurrentBalance(){
        return this.currentBalance;
    }

    public double getInterestRate(){
        return this.interestRate;
    }


    public abstract AccountTypes getType();

    public double withDraw(Integer amount)
            throws TransactionException{
        Transaction transaction = new Transaction(this.generateTransactionId(), amount, TransactionTypes.WITHDRAW);
        return this.validateAndCommitTransaction(transaction);
    }

    public double deposit(Integer amount)
            throws TransactionException{
        Transaction transaction = new Transaction(this.generateTransactionId(), amount, TransactionTypes.DEPOSIT);
        return this.validateAndCommitTransaction(transaction);
    }

    public void getTransactionsHistory(Integer limit){
        // Print the list objects in tabular format.
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%12s %30s %20s %35s %8s %8s", "TRANSACTION ID", "AMOUNT", "TYPE", "TIME", "OLD BAL", "NEW BAL");
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        Double currentAmount = this.currentBalance;
        List<Transaction> reverseList = this.transactionList;
        Collections.reverse(reverseList);
        for(Transaction transaction: reverseList){
            if(limit==0) break;
            Double oldAmount = transaction.getType() ==
                    TransactionTypes.DEPOSIT ? currentAmount - transaction.getAmount(): currentAmount + transaction.getAmount();
            System.out.format("%12s %30s %20s %35s %8s %8s",
                    transaction.getTransactionId(), transaction.getAmount(), transaction.getType(), transaction.getLocalTime(),
                    oldAmount.toString(), currentAmount.toString());
            System.out.println();
            currentAmount = oldAmount;
            limit-=1;
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------");
    }

    public void getTransactionsHistory(){
        this.getTransactionsHistory(this.transactionList.size());
    }

    public void getMiniStatement(){
        this.getTransactionsHistory(MINI_STATEMENT_SIZE);
    }

    public String getAccountNumber(){return this.accountNumber;}

}