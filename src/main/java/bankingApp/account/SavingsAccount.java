package src.bankingApp.account;

public class SavingsAccount extends BankAccount {

    public SavingsAccount(double minimumBalance, double currentBalance, double interestRate) {
        super(minimumBalance, currentBalance, interestRate, AccountTypes.SAVINGS);
    }

    @Override
    public AccountTypes getType(){
        return this.type;
    }
}
