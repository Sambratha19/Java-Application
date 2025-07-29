public class mail {
    String email, sub, body;

    public String getEmail() {
        return email;
    }

    public String getSub() {
        return sub;
    }

    public String getBody() {
        return body;
    }

    mail(String email, String sub, String body){
        this.email=email;
        this.sub=sub;
        this.body=body;
    }
}
