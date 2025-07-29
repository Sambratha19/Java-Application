public class Passenger {
    String name;
    static int nextId=1;
    static int id;
    String coach;
    Passenger(String name, String coach){
        this.name=name;
        this.coach=coach;
        this.id=nextId++;
    }

    public int getId() {
        return id;
    }
}
