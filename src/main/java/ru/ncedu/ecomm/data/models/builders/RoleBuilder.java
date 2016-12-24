package ru.ncedu.ecomm.data.models.builders;

import ru.ncedu.ecomm.data.models.Role;

public class RoleBuilder {
    private long id;
    private String name;

    public RoleBuilder() {
    }

    public RoleBuilder setId(long id) {
        this.id = id;
        
        return this;
    }

    public RoleBuilder setName(String name) {
        this.name = name;

        return this;
    }
    public Role build(){
        return new Role(
                id,
                name
        );
    }
}
