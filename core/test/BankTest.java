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
}