package src.bankingApp.account;

public class UnsupportedAccountTypeException extends Exception{
    public UnsupportedAccountTypeException(String s){
        super(s);
    }
}
