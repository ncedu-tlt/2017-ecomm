
package ru.ncedu.ecomm.servlets.models;


public enum EnumRoles {
    ADMINISTRATOR(1),
    MANAGER(2),
    USER(3);

    private long role;

    EnumRoles(long role) {
        this.role = role;
    }

    public long getRole() {
        return role;
    }
}
