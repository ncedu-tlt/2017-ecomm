package ru.ncedu.ecomm.data.models.builders;

import ru.ncedu.ecomm.data.models.Property;

public class PropertyBuilder {
    private String propertyId;
    private String value;

    public PropertyBuilder(){

    }

    public PropertyBuilder setPropertyId(String propertyId) {
        this.propertyId = propertyId;

        return this;
    }

    public PropertyBuilder setValue(String value) {
        this.value = value;

        return this;
    }

    public Property build(){
        return new Property(
                propertyId,
                value
        );
    }
}
