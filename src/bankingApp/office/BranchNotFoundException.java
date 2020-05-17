package src.bankingApp.office;

public class BranchNotFoundException extends Exception{
    public BranchNotFoundException(String s){
        super(s);
    }
}