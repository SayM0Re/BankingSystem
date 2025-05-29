import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

class BankTest {
    @Mock
    private Account mockAccount;

    @Test
    void testProcessDepositTransaction() {
        Bank bank = new Bank();
        Client client = bank.createClient("C100", "Test Client 1");
        Account account = bank.createAccount("ACC100", client);

        Transaction transaction = new Transaction(Transaction.Type.DEPOSIT, 500.0, account);
        bank.processTransaction(transaction);

        assertEquals(500.0, account.getBalance());
        assertTrue(account.getTransactions().contains(transaction));
    }

    @Test
    void testProcessInvalidTransaction() {
        Bank bank = new Bank();
        Client client = bank.createClient("C200", "Test Client 2");
        Account account = bank.createAccount("A200", client);

        Transaction transaction = new Transaction(Transaction.Type.DEPOSIT, -100, account);

        bank.processTransaction(transaction);
        assertEquals(0, account.getBalance());
    }

    @Test
    void testProcessWithdrawTransaction_Success() {
        Bank bank = new Bank();
        Client client = bank.createClient("C300", "Test Client 3");
        Account account = bank.createAccount("ACC300", client);

        // Пополнить счет сначала
        Transaction deposit = new Transaction(Transaction.Type.DEPOSIT, 1000.0, account);
        bank.processTransaction(deposit);

        // Снять деньги
        Transaction withdraw = new Transaction(Transaction.Type.WITHDRAW, 500.0, account);
        bank.processTransaction(withdraw);

        assertEquals(500.0, account.getBalance());
        assertTrue(account.getTransactions().contains(withdraw));
    }

    @Test
    void testProcessWithdrawTransaction_Failure() {
        Bank bank = new Bank();
        Client client = bank.createClient("C400", "Test Client 4");
        Account account = bank.createAccount("ACC400", client);

        // Попытка снять без депозита
        Transaction withdraw = new Transaction(Transaction.Type.WITHDRAW, 100.0, account);
        bank.processTransaction(withdraw);

        // Баланс должен остаться 0
        assertEquals(0.0, account.getBalance());
        assertFalse(account.getTransactions().contains(withdraw));
    }
}
