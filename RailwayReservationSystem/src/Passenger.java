public class Passenger {
    int age;
    String name;
    String gender;
    String berth;
    String allottedBerth;
    int ticketId;

    public Passenger(int age, String name, String gender, String berth, String allottedBerth,int ticketId) {
        this.age = age;
        this.name = name;
        this.gender = gender;
        this.berth = berth;
        this.allottedBerth = allottedBerth;
        this.ticketId = ticketId;
    }

    public String toString(){
        return "TicketId: "+ticketId+",Name: "+name+", Age: "+age+", Gender"+gender+", Berth: "+allottedBerth;
    }

}
