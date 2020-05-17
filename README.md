
** Banking Application

  This is sample Java apllication for a banking application, it takes care of various operations like
    * createBankAccount
    * deposit into bank account
    * withdraw from bank account
    * Concurreny handling for multiple transactions using synchornization and atomic types


** Project staructure:

```
src/
|-- bankingApp
|   |-- Bank
|   |   |-- Branch.java
|   |   `-- BranchException.java
|   |-- account
|   |   |-- AccountTypes.java
|   |   |-- BankAccount.java
|   |   |-- CurrentAccount.java
|   |   |-- SavingsAccount.java
|   |   `-- UnsupportedAccountTypeException.java
|   |-- constants
|   |   |-- Constants.java
|   |   `-- ExceptionMessages.java
|   |-- customer
|   |   `-- Customer.java
|   |-- main
|   |   |-- java
|   |   |   `-- MainTests.java
|   |   `-- resources
|   |       `-- META-INF
|   |           `-- MANIFEST.MF
|   |-- office
|   |   |-- BranchNotFoundException.java
|   |   `-- HeadOffice.java
|   |-- src
|   `-- transactions
|       |-- Transaction.java
|       |-- TransactionException.java
|       `-- TransactionTypes.java
`-- tests
    |-- java.bankingAppTests
    |   |-- BranchTests
    |   |   `-- BranchTest.java
    |   `-- accountTests
    |       `-- BankAccountTest.java
    `-- tests.iml

```


** How to test

```
git clone https://github.com/shizuka237/bankingApplication.git

cd bankingApplication

mvn package

java -jar target/BankingApp-1.0-SNAPSHOT.jar

```



![SAMPLE RUN ](https://github.com/shizuka237/bankingApplication/blob/master/bakingSample.png)
