package ru.ncedu.ecomm.servlets.models.builders;

import ru.ncedu.ecomm.servlets.models.UserViewModel;

import java.util.Date;

public class UserViewModelBuilder {
    private long id;
    private String role;
    private String email;
    private Date registrationDate;
    private String fio;

    public UserViewModelBuilder() {

    }

    public UserViewModelBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public UserViewModelBuilder setRole(String role) {
        this.role = role;
        return this;
    }

    public UserViewModelBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserViewModelBuilder setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }

    public UserViewModelBuilder setFio(String fio) {
        this.fio = fio;
        return this;
    }

    public UserViewModel build() {
        return new UserViewModel(id, fio, role, email, registrationDate);
    }
}
