package src.bankingApp.account;

public class CurrentAccount extends BankAccount {

    public CurrentAccount(double minimumBalance, double currentBalance, double interestRate) {
        super(minimumBalance, currentBalance, interestRate, AccountTypes.CURRENT);
    }

    @Override
    public AccountTypes getType(){
        return this.type;
    }
}
