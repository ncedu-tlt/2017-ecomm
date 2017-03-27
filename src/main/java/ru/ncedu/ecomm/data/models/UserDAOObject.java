package ru.ncedu.ecomm.data.models;

import java.sql.Date;

public class UserDAOObject {
    private long id;
    private long roleId;
    private String firstName;
    private String lastName;
    private String password;
    private String phone;
    private String email;
    private Date registrationDate;
    private String recoveryHash;
    private String userAvatar;

    public UserDAOObject() {
    }

    public UserDAOObject(long id,
                         long roleId,
                         String firstName,
                         String lastName,
                         String password,
                         String phone,
                         String email,
                         Date registrationDate,
                         String recoveryHash,
                         String userAvatar) {

        this.id = id;
        this.roleId = roleId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.registrationDate = registrationDate;
        this.recoveryHash = recoveryHash;
        this.userAvatar = userAvatar;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getRecoveryHash() {
        return recoveryHash;
    }

    public void setRecoveryHash(String recoveryHash) {
        this.recoveryHash = recoveryHash;
    }
}
