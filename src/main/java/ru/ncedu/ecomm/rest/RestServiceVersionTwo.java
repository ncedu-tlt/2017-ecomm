package ru.ncedu.ecomm.rest;

import ru.ncedu.ecomm.data.models.dao.ProductDAOObject;
import ru.ncedu.ecomm.data.models.dao.UserDAOObject;
import ru.ncedu.ecomm.data.models.dto.UserDTOObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

/**
 * Available by http://localhost:8080/rest/ecomm/v2/
 */

@Path("/ecomm/v2")
public class RestServiceVersionTwo {
    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDTOObject> getUsersManagement() {return getDAOFactory().getUserDAO().getUsersForManagement();
    }

    @GET
    @Path("/users/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTOObject getUserManagementById(@PathParam("userId") long userId) {
        return getDAOFactory().getUserDAO().getUserForManagementById(userId);
    }

    @DELETE
    @Path("/users/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("userId") long userId) {
        getDAOFactory().getUserDAO().deleteUser(userId);
        return Response.ok().build();
    }

    @POST
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserDAOObject addUser(UserDTOObject user) {
        UserDAOObject userDAO = new UserDAOObject();
        userDAO.setRoleId(user.getRole().getId());
        userDAO.setId(user.getId());
        userDAO.setEmail(user.getEmail());
        userDAO.setFirstName(user.getFirstName());
        userDAO.setLastName(user.getLastName());
        userDAO.setPhone(user.getPhone());
        userDAO.setRegistrationDate(user.getRegistrationDate());
        userDAO.setPassword(user.getPassword());
        userDAO.setUserAvatar(user.getUserAvatar());
        return getDAOFactory().getUserDAO().addUser(userDAO);
    }

    @PUT
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserDTOObject updateUserManagement(UserDTOObject user) {
        return getDAOFactory().getUserDAO().updateUserForManagement(user);
    }

    @GET
    @Path("/product/childrenProducts/{categoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductDAOObject> getAllChrildrenProductsByCategoryId(
            @PathParam("categoryId") long categoryId) {
        return getDAOFactory().getProductDAO().getAllChrildrenProductsByCategoryId(categoryId);
    }
}
