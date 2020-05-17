package src.bankingApp.Bank;

import src.bankingApp.account.AccountTypes;
import src.bankingApp.account.BankAccount;
import src.bankingApp.account.CurrentAccount;
import src.bankingApp.account.SavingsAccount;
import src.bankingApp.account.UnsupportedAccountTypeException;
import src.bankingApp.constants.ExceptionMessages;
import src.bankingApp.customer.Customer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Branch {
    private static AtomicInteger globalBranchCount  = new AtomicInteger(0);//atomic because we want branchIds to be unique
    private static Integer DEFAULT_ANNUAL_INTEREST_RATE = 6;
    private static String BRANCH_PREFIX = "BRANCH-";

    final private String branchId;
    private Map<String, BankAccount> bankAccounts; // we want getAccountByAccountNumber to be O(1) operations
    private Map<String, Customer> customers;// we want getCustomersByPAN to be O(1) operations


    public Branch() {
        this.branchId = BRANCH_PREFIX + ((Integer)globalBranchCount.incrementAndGet()).toString();
        this.bankAccounts = new HashMap<>();//  replaced list with hashmap, keeping in mind use case
        this.customers = new HashMap<>();// replaced list with hashmap, keeping in mind use case
    }

    public void createBankAccount(String panNumber, AccountTypes accountType, Double minimumBalance)
            throws UnsupportedAccountTypeException{
        //Add customer to branch if not already present
        if(this.customers.get(panNumber) == null){
            this.customers.put(panNumber, new Customer(panNumber));
        }

        //create new bank account for customer
        BankAccount account;
        switch (accountType){
            case CURRENT:
                account = new CurrentAccount(minimumBalance, minimumBalance, DEFAULT_ANNUAL_INTEREST_RATE);
                break;
            case SAVINGS:
                account = new SavingsAccount(minimumBalance, minimumBalance, DEFAULT_ANNUAL_INTEREST_RATE);
                break;
            default:
                throw new UnsupportedAccountTypeException(ExceptionMessages.INVALID_WITHDRAW_MESSAGE);
        }
        this.bankAccounts.put(account.getAccountNumber(), account);

        //add account to customers accounts
        this.customers.get(panNumber).addBankAccount(account);
    }

    public Integer getBranchAccountsCount(){
        return this.bankAccounts.size();
    }

    /*
    O(1) time fetch
     */
    public Customer getCustomerByPan(String pan)
            throws BranchExecption{
        if(customers.get(pan) == null) {
            throw new BranchExecption(String.format(ExceptionMessages.CUSTOMER_PAN_NOT_FOUND, pan));
        }
        return customers.get(pan);
    }

    /*
   O(1) time fetch
    */
    public BankAccount getAccountByAccountNumber(String accNumber)
            throws BranchExecption{
        if(this.bankAccounts.get(accNumber) == null) {
            throw new BranchExecption(String.format(ExceptionMessages.CUSTOMER_ACCOUNT_NOT_FOUND, accNumber));
        }
        return bankAccounts.get(accNumber);
    }

    public String getBranchId(){
        return this.branchId;
    }

    public void printAllAccountNumbers(){
        System.out.println("Branch : " + this.branchId + ", Accounts: "+this.bankAccounts.size());
        for(Map.Entry<String, Customer> entry: this.customers.entrySet()){
            System.out.println("  Customer : " + entry.getKey());
            for(BankAccount account: entry.getValue().getBankAccountsList()){
                System.out.println("    ACCT NO: "+ account.getAccountNumber() + ", TYPE: " + account.getType() + ", CURRENT BALANCE: " + account.getCurrentBalance());
            }
        }
    }


}
