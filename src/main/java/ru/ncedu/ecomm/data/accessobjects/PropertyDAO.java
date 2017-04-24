package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.dao.PropertyDAOObject;

import java.util.List;

public interface PropertyDAO {

    List<PropertyDAOObject> getProperties();

    PropertyDAOObject getPropertyById(String id);

    PropertyDAOObject addProperty(PropertyDAOObject property);

    void deleteProperty(PropertyDAOObject property);

    PropertyDAOObject updateProperty(PropertyDAOObject property);

}
