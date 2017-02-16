
package ru.ncedu.ecomm.servlets.models;


public enum EnumRoles {
    ADMINISTRATOR(1),
    MANAGER(2),
    USER(3);

    private int role;

    EnumRoles(int role) {
        this.role = role;
    }

    public int getRole() {
        return role;
    }
}
