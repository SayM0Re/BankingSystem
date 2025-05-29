import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * БАНКОВСКАЯ СИСТЕМА
 * <p>
 * Задача: Отслеживание и обработка банковских операций.
 * Требуется обеспечить функционал:
 * 1. Создание клиентов и банковских счетов
 * 2. Выполнение операций пополнения и снятия средств
 * 3. Отслеживание истории транзакций
 * 4. Обработку ошибок при недостатке средств
 * 5. Вывод информации о клиентах и состоянии счетов
 */
public final class BankSystem {

    private static final Logger LOGGER = LoggerFactory.getLogger(BankSystem.class);

    public static void main(final String[] args) {
        LOGGER.info("App is started");
        final Bank bank = new Bank();

        // Создаем клиентов
        final Client client1 = bank.createClient("C001", "Ivan Petrov");
        final Client client2 = bank.createClient("C002", "Maria Ivanova");

        // Создаем счета
        final Account account1 = bank.createAccount("ACC1001", client1);
        final Account account2 = bank.createAccount("ACC1002", client2);

        // Выполняем транзакции
        bank.processTransaction(new Transaction(Transaction.Type.DEPOSIT, 1000, account1));
        bank.processTransaction(new Transaction(Transaction.Type.DEPOSIT, 1500, account2));
        bank.processTransaction(new Transaction(Transaction.Type.WITHDRAW, 300, account1));
        bank.processTransaction(new Transaction(Transaction.Type.WITHDRAW, 2000, account2));
        bank.processTransaction(new Transaction(Transaction.Type.DEPOSIT, -100, account1));

        // Выводим информацию
        LOGGER.info("{}", client1);
        LOGGER.info("{}", account1);
        LOGGER.info("{}", client2);
        LOGGER.info("{}", account2);

        LOGGER.info("Operations log for account {}:", account1.getAccountNumber());
        final List<Transaction> transactions = account1.getTransactions();
        for (final Transaction transaction : transactions) {
            LOGGER.info(transaction.toString());
        }
    }
}
