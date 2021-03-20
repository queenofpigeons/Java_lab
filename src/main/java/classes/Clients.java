package classes;

import javax.persistence.*;

@Entity
@Table(name = "clients")
public class Clients {

    private int client_id;
    private String client_name;
    private String client_phone;

    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    @Column(name = "client_name")
    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    @Column(name = "client_phone")
    public String getClient_phone() {
        return client_phone;
    }

    public void setClient_phone(String client_phone) {
        this.client_phone = client_phone;
    }
}
