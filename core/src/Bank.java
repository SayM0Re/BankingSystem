
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Bank {
    private List<Client> clients = new ArrayList<>();
    private List<Account> accounts = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(Bank.class);

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
        try {
            Account account = transaction.getAccount();
            switch (transaction.getType()) {
                case DEPOSIT:
                    account.deposit(transaction.getAmount());
                    break;
                case WITHDRAW:
                    if (!account.withdraw(transaction.getAmount())) {
                        System.out.println("Error: Not enough money on balance " + account.getAccountNumber());
                        return;
                    }
                    break;
            }
            account.addTransaction(transaction);
            logger.info("Processed transaction: {}", transaction);
        } catch (Exception e) {
            logger.error("Transaction failed: {}", e.getMessage());
        }
    }
}