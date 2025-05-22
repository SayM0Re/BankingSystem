import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {
    @Test
    void testTransactionCreation() {
        Account account = new Account("ACC001", new Client("C001", "Test"));
        Transaction transaction = new Transaction(
            Transaction.Type.DEPOSIT,
            1000.0,
            account
        );
        
        assertEquals(Transaction.Type.DEPOSIT, transaction.getType());
        assertEquals(1000.0, transaction.getAmount(), 0.01);
        assertTrue(new Date().getTime() - transaction.getDate().getTime() < 1000);
    }
}
