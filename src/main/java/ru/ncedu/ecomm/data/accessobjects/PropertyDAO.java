package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.Property;
import java.util.List;

public interface PropertyDAO {

    List<Property> getProperties();

    Property getPropertyById(String id);

    Property addProperty(Property property);

    void deleteProperty(Property property);

    Property updateProperty(Property property);



}
