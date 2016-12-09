package ru.ncedu.ecomm.rest;

import ru.ncedu.ecomm.data.models.Category;
import ru.ncedu.ecomm.data.models.Property;
import ru.ncedu.ecomm.data.models.Role;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    @Path("/categories/{categoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Category getCategory(@PathParam("categoryId") long categoryId) {
        return getDAOFactory().getCategoryDAO().getCategoryById(categoryId);
    }

    @DELETE
    @Path("/categories/{categoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCategory(@PathParam("categoryId") long categoryId) {
        getDAOFactory().getCategoryDAO().deleteCategory(getDAOFactory().getCategoryDAO().getCategoryById(categoryId));
        return Response.ok().build();
    }

    @POST
    @Path("/categories")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Category addCategory(Category category) {
        return getDAOFactory().getCategoryDAO().addCategory(category);
    }

    @GET
    @Path("/categories/parent/{parentCategoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> getCategoryByParent(@PathParam("parentCategoryId") long parentCategoryId) {
        return getDAOFactory().getCategoryDAO().getCategoriesByParentId(parentCategoryId);
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
    public Property getProperties(@PathParam("propertyId") String propertyId) {
        return getDAOFactory().getPropertyDAO().getPropertyById(propertyId);
    }
}
