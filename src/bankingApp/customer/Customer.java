package src.bankingApp.customer;

import src.bankingApp.account.BankAccount;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private List<BankAccount> bankAccountsList;
    final private String panNumber; // final because pan number must not change for a customer , it is his identity

    public Customer(String panNumber) {
        this.bankAccountsList = new ArrayList<>();
        this.panNumber = panNumber;
    }

    public List<BankAccount> getBankAccountsList() {
        return bankAccountsList;
    }

    public void setBankAccountsList(List<BankAccount> bankAccountsList) {
        this.bankAccountsList = bankAccountsList;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void addBankAccount(BankAccount account){
        this.bankAccountsList.add(account);
    }

}