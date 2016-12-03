package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.Role;

import java.util.List;

public interface RoleDAO {

    List<Role> getRoles();

    Role getRoleById(long id);

}
