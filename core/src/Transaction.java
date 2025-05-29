import java.util.Date;

public class Transaction {
    public enum Type {
        DEPOSIT,
        WITHDRAW
    }

    private final Type type;
    private final double amount;
    private final Date date;
    private final Account account;

    public Transaction(final Type type, final double amount, final Account account) {
        this.type = type;
        this.amount = amount;
        this.account = account;
        this.date = new Date();
    }

    public Type getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public Account getAccount() {
        return account;
    }

    public Date getDate() {
        return new Date(date.getTime());
    }
    
    @Override
    public String toString() {
        return date + " | " + type + " | Amount: $" + amount + " | Account: " + account.getAccountNumber();
    }
}