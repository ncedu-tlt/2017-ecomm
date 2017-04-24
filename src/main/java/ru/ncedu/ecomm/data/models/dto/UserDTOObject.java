package ru.ncedu.ecomm.data.models.dto;

import java.sql.Date;

public class UserDTOObject {
    private long id;
    private long roleId;
    private String roleName;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private Date registrationDate;
    private String userAvatar;

    public UserDTOObject() {
    }

    public UserDTOObject(long id,
                         long roleId,
                         String roleName,
                         String firstName,
                         String lastName,
                         String phone,
                         String email,
                         Date registrationDate,
                         String userAvatar) {
        this.id = id;
        this.roleId = roleId;
        this.roleName = roleName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.registrationDate = registrationDate;
        this.userAvatar = userAvatar;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}
