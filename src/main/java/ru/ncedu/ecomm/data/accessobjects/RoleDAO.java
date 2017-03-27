package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.RoleDAOObject;

import java.util.List;

public interface RoleDAO {

    List<RoleDAOObject> getRoles();

    RoleDAOObject getRoleById(long id);

}
