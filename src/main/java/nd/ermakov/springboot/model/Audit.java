package nd.ermakov.springboot.model;

public class Audit {

    private String name;
    private String password;
    private String status;

    public Audit(String name, String password, String status) {
        this.name = name;
        this.password = password;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
