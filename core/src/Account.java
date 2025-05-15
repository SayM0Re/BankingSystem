import java.util.ArrayList;
import java.util.List;

class Account {
    private String accountNumber;
    private Client client;
    private double balance;
    private List<Transaction> transactions = new ArrayList<>();

    public Account(String accountNumber, Client client) {
        this.accountNumber = accountNumber;
        this.client = client;
        this.balance = 0.0;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public String toString() {
        return "Account: " + accountNumber + " | Balance: $" + balance;
    }
}