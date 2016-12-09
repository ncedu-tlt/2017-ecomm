package ru.ncedu.ecomm.data.models;

public class Property {

    private long property_id;
    private String value;

    public Property() {
    }

    public Property(long id, String value) {
        this.property_id = id;
        this.value = value;
    }

    public long getId() {
        return property_id;
    }

    public void setId(long id) {
        this.property_id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
