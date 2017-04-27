package ru.ncedu.ecomm.data.models.dto.builders;

import ru.ncedu.ecomm.data.models.dao.RoleDAOObject;
import ru.ncedu.ecomm.data.models.dto.UserDTOObject;

import java.sql.Date;

public class UserDTOObjectBuilder {
    private long id;
    private long roleId;
    private String roleName;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private Date registrationDate;
    private String userAvatar;
    private String password;
    private RoleDAOObject role;

    public UserDTOObjectBuilder() {
    }

    public UserDTOObjectBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public UserDTOObjectBuilder setRoleId(long roleId) {
        this.roleId = roleId;
        return this;
    }

    public UserDTOObjectBuilder setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public UserDTOObjectBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserDTOObjectBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserDTOObjectBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public UserDTOObjectBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserDTOObjectBuilder setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }

    public UserDTOObjectBuilder setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
        return this;
    }

    public UserDTOObjectBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserDTOObjectBuilder setRole(RoleDAOObject role) {
        this.role = role;
        return this;
    }

    public UserDTOObject build(){
        return new UserDTOObject(
                id,
                roleId,
                roleName,
                firstName,
                lastName,
                phone,
                email,
                registrationDate,
                userAvatar,
                password,
                role);
    }
}
