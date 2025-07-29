import java.sql.Statement;

public class Passenger {
    String name;
    static int nextId=1;
    int id;
    Passenger(String name){
        this.name=name;
        this.id=nextId++;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
