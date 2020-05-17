package src.tests.bankingAppTests.BranchTests;

import org.junit.Test;
import org.junit.Before;

import src.bankingApp.Bank.Branch;
import src.bankingApp.Bank.BranchException;
import src.bankingApp.account.AccountTypes;
import src.bankingApp.account.UnsupportedAccountTypeException;

public class BranchTests {

    private Branch  bankBranch;

    @Before
    public void setUp() throws Exception {
        Branch.resetGlobalCount();
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
    public void getCustomerByPan() throws BranchException {
        this.bankBranch.getCustomerByPan("pan1");
    }

    @Test(expected = BranchException.class)
    public void getCustomerByPanException() throws BranchException {
        this.bankBranch.getCustomerByPan("pan5");

    }

    @Test(expected = BranchException.class)
    public void getAccountByAccountNumberException() throws BranchException {
        this.bankBranch.getAccountByAccountNumber("ACCT-10000");
    }

}