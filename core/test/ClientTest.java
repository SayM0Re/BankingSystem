import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    @Test
    void testClientGetters() {
        Client client = new Client("ID123", "Ivan Ivanov");
        assertEquals("ID123", client.getId());
        assertEquals("Ivan Ivanov", client.getName());
    }
}
