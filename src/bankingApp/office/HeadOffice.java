package src.bankingApp.office;

import src.bankingApp.Bank.Branch;
import src.bankingApp.constants.ExceptionMessages;

import java.util.ArrayList;
import java.util.List;

public class HeadOffice {
    List<Branch> branches;

    public HeadOffice(){
        this.branches = new ArrayList<>();
    }

    public void createBranch(){
        this.branches.add(new Branch());
    }

    public Branch getBranchById(String branchId)
            throws BranchNotFoundException {
        return this.branches.stream().filter(branch -> branch.getBranchId().equals(branchId)).findFirst().orElseThrow(()->
                new BranchNotFoundException(String.format(ExceptionMessages.BRANCH_NOT_FOUND_EXCEPTION, branchId)));
    }

    public void displayAllBranch(){
        for(Branch branch: this.branches){
            branch.printAllAccountNumbers();
            System.out.println();
        }
    }
}
