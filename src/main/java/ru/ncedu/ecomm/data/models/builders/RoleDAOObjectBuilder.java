package ru.ncedu.ecomm.data.models.builders;

import ru.ncedu.ecomm.data.models.RoleDAOObject;

public class RoleDAOObjectBuilder {
    private long id;
    private String name;

    public RoleDAOObjectBuilder() {
    }

    public RoleDAOObjectBuilder setId(long id) {
        this.id = id;
        
        return this;
    }

    public RoleDAOObjectBuilder setName(String name) {
        this.name = name;

        return this;
    }
    public RoleDAOObject build(){
        return new RoleDAOObject(
                id,
                name
        );
    }
}
