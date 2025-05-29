import java.util.ArrayList;
import java.util.List;

public class Account {
    private final String accountNumber;
    private final Client client;
    private double balance;
    private final List<Transaction> transactions = new ArrayList<>();

    public Account(final String accountNumber, final Client client) {
        this.accountNumber = accountNumber;
        this.client = client;
        this.balance = 0.0;
    }

    public void deposit(final double amount) {
        if (amount <= 0) {
            System.out.println("Error: Deposit must be positive.");
        } else {
            balance += amount;
        }
    }

    public boolean withdraw(final double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void addTransaction(final Transaction transaction) {
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