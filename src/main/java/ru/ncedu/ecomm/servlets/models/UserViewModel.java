package ru.ncedu.ecomm.servlets.models;

import java.util.Date;

public class UserViewModel {

    private long id;
    private String role;
    private String email;
    private Date registrationDate;
    private String fio;

    public UserViewModel() {
    }


    public UserViewModel(long id, String fio, String role, String email, Date registrationDate) {
        this.fio = fio;
        this.role = role;
        this.email = email;
        this.registrationDate = registrationDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public UserViewModel build() {
        return new UserViewModel(id, fio, role, email, registrationDate);
    }
}
