package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.Property;
import java.util.List;

public interface PropertyDAO {

    List<Property> getProperty();

    Property getPropertyById(long id);

    Property addProperty(Property property);

    void deleteProperty(Property property);

    //void deleteProperty(long id);

    Property updateProperty(Property property);



}
