package ru.ncedu.ecomm.data.models.builders;

import ru.ncedu.ecomm.data.models.PropertyDAOObject;

public class PropertyDAOObjectBuilder {
    private String propertyId;
    private String value;

    public PropertyDAOObjectBuilder(){

    }

    public PropertyDAOObjectBuilder setPropertyId(String propertyId) {
        this.propertyId = propertyId;

        return this;
    }

    public PropertyDAOObjectBuilder setValue(String value) {
        this.value = value;

        return this;
    }

    public PropertyDAOObject build(){
        return new PropertyDAOObject(
                propertyId,
                value
        );
    }
}
