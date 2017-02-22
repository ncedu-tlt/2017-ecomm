
package ru.ncedu.ecomm.servlets.models;


public enum EnumRoles {
    ADMINISTRATOR(1),
    MANAGER(2),
    USER(3);

    private long roleIdentifier;

    EnumRoles(long role) {
        this.roleIdentifier = role;
    }

    public long getRole() {
        return roleIdentifier;
    }
}
