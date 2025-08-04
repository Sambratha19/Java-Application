public class User {
    String email;
    String username;
    String pass;
    static int i=1;
    int id;
    char sellerORbuyer;

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPass() {
        return pass;
    }

    public int getId() {
        return id;
    }

    public char getSellerORbuyer() {
        return sellerORbuyer;
    }

    User(String email, String pass, String username, char sellerORbuyer){
        this.email=email;
        this.username=username;
        this.sellerORbuyer=sellerORbuyer;
        this.pass=pass;
        this.id=i++;
    }
}
