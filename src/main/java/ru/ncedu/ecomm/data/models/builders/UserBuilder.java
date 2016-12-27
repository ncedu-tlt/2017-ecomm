package ru.ncedu.ecomm.data.models.builders;

import ru.ncedu.ecomm.data.models.User;

import java.sql.Date;

public class UserBuilder {
    private long userId;
    private long roleId;
    private String firstName;
    private String lastName;
    private String password;
    private String phone;
    private String email;
    private Date registrationDate;
    private String recoveryHash;

    public UserBuilder() {

    }

    public UserBuilder setUserId(long userId) {
        this.userId = userId;

        return this;
    }

    public UserBuilder setRoleId(long roleId) {
        this.roleId = roleId;

        return this;
    }

    public UserBuilder setFirstName(String firstName) {
        this.firstName = firstName;

        return this;
    }

    public UserBuilder setLastName(String lastName) {
        this.lastName = lastName;

        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;

        return this;
    }

    public UserBuilder setPhone(String phone) {
        this.phone = phone;

        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;

        return this;
    }

    public UserBuilder setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;

        return this;
    }

    public UserBuilder setRecoveryHash(String recoveryHash){
        this.recoveryHash = recoveryHash;

        return this;
    }

    public User build() {
        return new User(
                userId,
                roleId,
                firstName,
                lastName,
                password,
                phone,
                email,
                registrationDate,
                recoveryHash);
    }
}

