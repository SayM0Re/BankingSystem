
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bank {
    private final List<Client> clients = new ArrayList<>();
    private final List<Account> accounts = new ArrayList<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(Bank.class);

    public Client createClient(final String id, final String name) {
        final Client client = new Client(id, name);
        clients.add(client);
        return client;
    }

    public Account createAccount(final String accountNumber, final Client client) {
        final Account account = new Account(accountNumber, client);
        accounts.add(account);
        return account;
    }

    public void processTransaction(final Transaction transaction) {
        try {
            final Account account = transaction.getAccount();
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
            LOGGER.info("Processed transaction: {}", transaction);
        } catch (Exception e) {
            LOGGER.error("Transaction failed: {}", e.getMessage(), e);
        }
    }
}