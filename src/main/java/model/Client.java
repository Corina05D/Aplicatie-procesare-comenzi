package model;

public class Client {
    private int idClient;
    private String name;
    private String emailAddress;
    private String address;

    public Client() {
    }

    public Client(int idClient, String name, String emailAddress, String address) {
        super();
        this.idClient = idClient;
        this.name = name;
        this.emailAddress = emailAddress;
        this.address = address;
    }

    public Client(String name, String emailAddress, String address) {
        super();
        this.name = name;
        this.emailAddress = emailAddress;
        this.address = address;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString(){
        String result = "";
        result += "idClient: " + idClient;
        result += "\nName: " + name;
        result += "\nEmail: " + emailAddress;
        result += "\nAddress: " + address;
        return result;
    }
}
