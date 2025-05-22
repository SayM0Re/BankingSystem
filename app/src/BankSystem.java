import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * БАНКОВСКАЯ СИСТЕМА
 * 
 * Задача: Отслеживание и обработка банковских операций.
 * Требуется обеспечить функционал:
 * 1. Создание клиентов и банковских счетов
 * 2. Выполнение операций пополнения и снятия средств
 * 3. Отслеживание истории транзакций
 * 4. Обработку ошибок при недостатке средств
 * 5. Вывод информации о клиентах и состоянии счетов
 */
public class BankSystem {
    private static final Logger logger = LoggerFactory.getLogger(BankSystem.class);

    public static void main(String[] args) {
        logger.info("App is started");
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
        bank.processTransaction(new Transaction(Transaction.Type.DEPOSIT, -100, account1));

        // Выводим информацию
        logger.info("{}", client1);
        logger.info("{}", account1);
        logger.info("{}", client2);
        logger.info("{}", account2);
        logger.info("\nOperations log:");
        account1.getTransactions().forEach(t -> logger.info(t.toString()));
    }
}
