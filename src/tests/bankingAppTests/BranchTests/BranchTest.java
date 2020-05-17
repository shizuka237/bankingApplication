package src.tests.bankingAppTests.BranchTests;

import org.junit.Test;
import org.junit.Before;

import src.bankingApp.Bank.Branch;
import src.bankingApp.Bank.BranchExecption;
import src.bankingApp.account.AccountTypes;
import src.bankingApp.account.UnsupportedAccountTypeException;

public class BranchTest {

    private Branch  bankBranch;

    @Before
    public void setUp() throws Exception {
        this.bankBranch = new Branch();
        this.bankBranch.createBankAccount("pan1", AccountTypes.CURRENT, 100.0);
        this.bankBranch.createBankAccount("pan2", AccountTypes.SAVINGS, 200.0);
        this.bankBranch.createBankAccount("pan1", AccountTypes.SAVINGS, 100.0);
    }

    @Test
    public void createBankAccount() throws UnsupportedAccountTypeException {
        this.bankBranch.createBankAccount("pan3", AccountTypes.SAVINGS, 100.0);
    }

    @Test
    public void getCustomerByPan() throws BranchExecption{
        this.bankBranch.getCustomerByPan("pan1");
    }

    @Test(expected = BranchExecption.class)
    public void getCustomerByPanException() throws BranchExecption{
        this.bankBranch.getCustomerByPan("pan5");

    }

    @Test
    public void getAccountByAccountNumber() throws BranchExecption{
        //this.bankBranch.printAllAccountNumbers();
        this.bankBranch.getAccountByAccountNumber("ACCT-1");
    }

    @Test(expected = BranchExecption.class)
    public void getAccountByAccountNumberException() throws BranchExecption {
        this.bankBranch.getAccountByAccountNumber("ACCT-10000");
    }

}