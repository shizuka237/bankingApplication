package  src.bankingApp.transactions;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

/*
Immutable class Transaction, because we dont want to allow
any update in transaction once the transactions is committed
 */

final public class Transaction {
    final private String transactionId;
    final private double amount;
    final private long epochTimeInSec;
    final private TransactionTypes type;


    public Transaction(String transactionId, double amount, TransactionTypes type) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.type = type;
        this.epochTimeInSec = Instant.now().getEpochSecond();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionTypes getType() {
        return type;
    }

    public String getLocalTime(){
        Date date = new Date(this.epochTimeInSec * 1000);
        DateFormat df = new SimpleDateFormat("dd MMM yyyy hh:mm:ss zzz");
        return df.format(date);
    }

}