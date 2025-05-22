import java.util.Date;

class Transaction {
    public enum Type {
        DEPOSIT, WITHDRAW
    }

    private Type type;
    private double amount;
    private Date date;
    private Account account;

    public Transaction(Type type, double amount, Account account) {
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
        return date;
    }
    
    @Override
    public String toString() {
        return date + " | " + type + " | Amount: $" + amount + " | Account: " + account.getAccountNumber();
    }
}