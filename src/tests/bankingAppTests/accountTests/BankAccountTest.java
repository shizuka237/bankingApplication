package src.tests.bankingAppTests.accountTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import src.bankingApp.account.BankAccount;
import src.bankingApp.account.SavingsAccount;
import src.bankingApp.transactions.TransactionException;

import static org.junit.Assert.*;

public class BankAccountTest {

    private BankAccount bankAccount;

    @Before
    public void setUp(){
        this.bankAccount = new SavingsAccount(100.0, 100.0, 6.0);
    }

    @Test
    public void getMinimumBalance() {
        Assert.assertEquals((int)(this.bankAccount.getMinimumBalance()-100), 0);
    }

    @Test
    public void getCurrentBalance() throws TransactionException {
        Assert.assertEquals((int)(this.bankAccount.getCurrentBalance()-100), 0);
        this.bankAccount.deposit(100);
        Assert.assertEquals((int)(this.bankAccount.getCurrentBalance()-200), 0);
    }

    @Test
    public void getInterestRate() {
        Assert.assertEquals((int)(this.bankAccount.getInterestRate()-6.0), 0);
    }

    @Test(expected = TransactionException.class)
    public void withDrawException() throws TransactionException{
        this.bankAccount.withDraw(50);
    }

    @Test
    public void withDrawSuccess() throws TransactionException{
        this.bankAccount.deposit(100);
        this.bankAccount.withDraw(50);
    }

    @Test
    public void depositSuccess() throws TransactionException{
        this.bankAccount.deposit(100);
    }

    @Test(expected = TransactionException.class)
    public void depositException() throws TransactionException{
        this.bankAccount.deposit(-1);
    }


    @Test
    public void getTransactionsHistory() throws TransactionException{
        this.bankAccount.deposit(100);
        this.bankAccount.deposit(50);
        this.bankAccount.deposit(100);
        this.bankAccount.withDraw(50);
        this.bankAccount.getTransactionsHistory(2);
    }

    @Test
    public void getTransactionsHistory1() throws TransactionException{
        this.bankAccount.deposit(100);
        this.bankAccount.deposit(50);
        this.bankAccount.deposit(100);
        this.bankAccount.withDraw(50);
        this.bankAccount.getTransactionsHistory();
    }

    @Test
    public void getMiniStatement() throws TransactionException{
        this.bankAccount.deposit(100);
        this.bankAccount.deposit(50);
        this.bankAccount.deposit(100);
        this.bankAccount.withDraw(50);
        this.bankAccount.getMiniStatement();
    }

}