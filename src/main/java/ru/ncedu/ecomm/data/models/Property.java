package ru.ncedu.ecomm.data.models;

public class Property {

    private String propertyId;
    private String value;

    public Property() {
    }

    public Property(String id, String value) {
        this.propertyId = id;
        this.value = value;
    }

    public String getId() {
        return propertyId;
    }

    public void setId(String id) {
        this.propertyId = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
