package ru.ncedu.ecomm.rest;

import ru.ncedu.ecomm.data.models.Category;
import ru.ncedu.ecomm.data.models.Property;
import ru.ncedu.ecomm.data.models.Role;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

/**
 * Available by http://localhost:8080/rest/ecomm/roles/1
 */
@Path("/ecomm")
public class RestService {

    @GET
    @Path("/roles")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Role> getRoles() {
        return getDAOFactory().getRoleDAO().getRoles();
    }

    @GET
    @Path("/roles/{roleId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Role getRole(@PathParam("roleId") long roleId) {
        return getDAOFactory().getRoleDAO().getRoleById(roleId);
    }

    @GET
    @Path("/categories")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> getCategories() {
        return getDAOFactory().getCategoryDAO().getCategories();
    }

    @GET
    @Path("/categories/{categotyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Category getCategory(@PathParam("categotyId") long categotyId) {
        return getDAOFactory().getCategoryDAO().getCategoryById(categotyId);
    }

    @GET
    @Path("/categories/parent/{parentCategotyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> getCategoryByParent(@PathParam("parentCategotyId") long parentCategotyId) {
        return getDAOFactory().getCategoryDAO().getCategoriesByParentId(parentCategotyId);
    }

    @GET
    @Path("/properties")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Property> getProperties() {
        return getDAOFactory().getPropertyDAO().getProperties();
    }

    @GET
    @Path("/properties/{propertyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Property getProperties(@PathParam("propertyId") long propertyId) {
        return getDAOFactory().getPropertyDAO().getPropertyById(propertyId);
    }
}
