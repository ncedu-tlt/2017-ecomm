package ru.ncedu.ecomm.data.models.builders;

import ru.ncedu.ecomm.data.models.UserDAOObject;

import java.sql.Date;

public class UserDAOObjectBuilder {
    private long userId;
    private long roleId;
    private String firstName;
    private String lastName;
    private String password;
    private String phone;
    private String email;
    private Date registrationDate;
    private String recoveryHash;
    private String userAvatar;

    public UserDAOObjectBuilder() {

    }

    public UserDAOObjectBuilder setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;

        return this;
    }

    public UserDAOObjectBuilder setUserId(long userId) {
        this.userId = userId;

        return this;
    }

    public UserDAOObjectBuilder setRoleId(long roleId) {
        this.roleId = roleId;

        return this;
    }

    public UserDAOObjectBuilder setFirstName(String firstName) {
        this.firstName = firstName;

        return this;
    }

    public UserDAOObjectBuilder setLastName(String lastName) {
        this.lastName = lastName;

        return this;
    }

    public UserDAOObjectBuilder setPassword(String password) {
        this.password = password;

        return this;
    }

    public UserDAOObjectBuilder setPhone(String phone) {
        this.phone = phone;

        return this;
    }

    public UserDAOObjectBuilder setEmail(String email) {
        this.email = email;

        return this;
    }

    public UserDAOObjectBuilder setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;

        return this;
    }

    public UserDAOObjectBuilder setRecoveryHash(String recoveryHash){
        this.recoveryHash = recoveryHash;

        return this;
    }

    public UserDAOObject build() {
        return new UserDAOObject(
                userId,
                roleId,
                firstName,
                lastName,
                password,
                phone,
                email,
                registrationDate,
                recoveryHash,
                userAvatar);
    }
}

