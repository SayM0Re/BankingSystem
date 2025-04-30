import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Класс Клиент
class Client {
    private String id;
    private String name;
    
    public Client(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public String getId() { return id; }
    public String getName() { return name; }
    
    @Override
    public String toString() {
        return "\nClient: " + name + " (ID: " + id + ")";
    }
}

// Класс Банковский счет
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
    
    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }
    public List<Transaction> getTransactions() { return transactions; }
    
    @Override
    public String toString() {
        return "Account: " + accountNumber + " | Balance: $" + balance;
    }
}

class Transaction {
    public enum Type { DEPOSIT, WITHDRAW }
    
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
    
    @Override
    public String toString() {
        return date + " | " + type + " | Amount: $" + amount + " | Account: " + account.getAccountNumber();
    }
}

// Класс Банк
class Bank {
    private List<Client> clients = new ArrayList<>();
    private List<Account> accounts = new ArrayList<>();
    
    public Client createClient(String id, String name) {
        Client client = new Client(id, name);
        clients.add(client);
        return client;
    }
    
    public Account createAccount(String accountNumber, Client client) {
        Account account = new Account(accountNumber, client);
        accounts.add(account);
        return account;
    }
    
    public void processTransaction(Transaction transaction) {
        Account account = transaction.getAccount();
        switch(transaction.getType()) {
            case DEPOSIT:
                account.deposit(transaction.getAmount());
                break;
            case WITHDRAW:
                if(!account.withdraw(transaction.getAmount())) {
                    System.out.println("Error: Not enough money on balance " + account.getAccountNumber());
                    return;
                }
                break;
        }
        account.addTransaction(transaction);
    }
}

public class BankingSystem {
    public static void main(String[] args) {
        Bank bank = new Bank();
        
        // Создаем клиентов
        Client client1 = bank.createClient("C001", "Ivan Petrov");
        Client client2 = bank.createClient("C002", "Maria Ivanova");
        
        // Создаем счета
        Account account1 = bank.createAccount("ACC1001", client1);
        Account account2 = bank.createAccount("ACC1002", client2);
        
        // Выполняем транзакции
        bank.processTransaction(new Transaction(Transaction.Type.DEPOSIT, 1000, account1));
        bank.processTransaction(new Transaction(Transaction.Type.DEPOSIT, 1500, account2));
        bank.processTransaction(new Transaction(Transaction.Type.WITHDRAW, 300, account1));
        bank.processTransaction(new Transaction(Transaction.Type.WITHDRAW, 2000, account2)); 
        
        // Выводим информацию
        System.out.println(client1);
        System.out.println(account1);
        System.out.println("\nOperations log:");
        account1.getTransactions().forEach(System.out::println);
    }
}