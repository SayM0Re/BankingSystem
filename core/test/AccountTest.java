import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void testValidDeposit() {
        Client client = new Client("C001", "Ivan");
        Account account = new Account("ACC001", client);
        account.deposit(500);
        assertEquals(500, account.getBalance());
    }

    @Test
    void testNegativeDeposit() {
        Client client = new Client("C002", "Maria");
        Account account = new Account("ACC002", client);
        account.deposit(-100);
        assertEquals(0, account.getBalance()); // ожидаем, что отрицательный депозит игнорируется
    }

    @Test
    void testZeroDeposit() {
        Client client = new Client("C003", "Oleg");
        Account account = new Account("ACC003", client);
        account.deposit(0);
        assertEquals(0, account.getBalance()); // нулевой депозит — ничего не меняется
    }

    @Test
    void testWithdrawSuccess() {
        Client client = new Client("C004", "Anna");
        Account account = new Account("ACC004", client);
        account.deposit(1000);
        boolean result = account.withdraw(300);
        assertTrue(result);
        assertEquals(700, account.getBalance());
    }

    @Test
    void testWithdrawFailure() {
        Client client = new Client("C005", "Oksana");
        Account account = new Account("ACC005", client);
        account.deposit(200);
        boolean result = account.withdraw(500);
        assertFalse(result);
        assertEquals(200, account.getBalance()); // баланс не изменился
    }
}