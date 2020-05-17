package src.bankingApp.main.java;

import src.bankingApp.account.AccountTypes;
import src.bankingApp.office.HeadOffice;

public class MainTests {

    public static void main(String args[] ) throws Exception{

        HeadOffice office = new HeadOffice();
        office.createBranch();
        office.createBranch();

        office.displayAllBranch();
        office.getBranchById("BRANCH-1").createBankAccount("PAN-1", AccountTypes.CURRENT, 100.0);
        office.getBranchById("BRANCH-1").createBankAccount("PAN-1", AccountTypes.CURRENT, 100.0);
        office.getBranchById("BRANCH-2").createBankAccount("PAN-1", AccountTypes.SAVINGS, 100.0);
        office.getBranchById("BRANCH-1").createBankAccount("PAN-1", AccountTypes.CURRENT, 100.0);
        office.getBranchById("BRANCH-2").createBankAccount("PAN-2", AccountTypes.CURRENT, 100.0);
        office.getBranchById("BRANCH-1").createBankAccount("PAN-3", AccountTypes.SAVINGS, 100.0);


        office.displayAllBranch();


        System.out.println("PAN-1 deposits 100 rs into BRANCH-1, ACCT-1 account");
        System.out.println("PAN-2 deposits 100 rs into BRANCH-2, ACCT-5 account");
        office.getBranchById("BRANCH-1").getAccountByAccountNumber("ACCT-1").deposit(100);
        office.getBranchById("BRANCH-2").getAccountByAccountNumber("ACCT-5").deposit(100);

        office.displayAllBranch();


        System.out.println("PAN-1 withdraws 10 rs into BRANCH-1, ACCT-2 account");
        try {
            office.getBranchById("BRANCH-1").getAccountByAccountNumber("ACCT-2").withDraw(10);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }


        System.out.println("PAN-1 withdraws 10 rs into BRANCH-1, ACCT-1 account");
        office.getBranchById("BRANCH-1").getAccountByAccountNumber("ACCT-1").withDraw(10);
        office.getBranchById("BRANCH-1").printAllAccountNumbers();




    }
}
