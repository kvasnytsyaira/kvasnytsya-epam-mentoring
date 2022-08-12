package mentoring.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class AdultUser implements User{
    long id;
    @NotBlank(message = "Name is mandatory")
    String name;
    @Email
    String email;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    public AdultUser() {
    }

    public AdultUser(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public AdultUser(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
