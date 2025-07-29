public class User {
    String email;

    String pass;
    String hashedPass;

    public String getEmail() {
        return email;
    }

    User(String email, String pass){
        this.email=email;
        this.pass=pass;
        Function function=new Function();
        this.hashedPass=function.hashedPassword(pass);

    }

    public String getHashedPass() {
        return hashedPass;
    }
}
