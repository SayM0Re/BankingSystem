
public class Client {

    private final String id;
    private final String name;
    
    public Client(final String id, final String name) {
        this.id = id;
        this.name = name;
    }
    
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return "Client: " + name + " (ID: " + id + ")";
    }
}