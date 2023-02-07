package model;

import java.util.Objects;

public class User {

    private Integer id;
    private String firstName, lastName, createdOn;

    public User() {
    }

    public User(Integer id, String createdOn, String firstName, String lastName) {
        this.id = id;
        this.createdOn = createdOn;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return id +","+ firstName.toUpperCase() +","+ lastName.toUpperCase()+","+ createdOn+";";
    }


}
