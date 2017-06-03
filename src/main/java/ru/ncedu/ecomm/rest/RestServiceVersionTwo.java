package ru.ncedu.ecomm.rest;

import ru.ncedu.ecomm.data.models.dao.*;
import ru.ncedu.ecomm.data.models.dto.CharacteristicGroupDTOObject;
import ru.ncedu.ecomm.data.models.dto.OrderDTOObject;
import ru.ncedu.ecomm.data.models.dao.SalesOrder;
import ru.ncedu.ecomm.data.models.dao.UserDAOObject;
import ru.ncedu.ecomm.data.models.dto.ProductDTObject;
import ru.ncedu.ecomm.data.models.dto.ReviewDTOObject;
import ru.ncedu.ecomm.data.models.dto.UserDTOObject;
import ru.ncedu.ecomm.services.RestProductDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
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
    @Path("/users/reviews/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ReviewDTOObject> getReviewsByUserId(@PathParam("userId") long userId) {
        return getDAOFactory().getReviewDAO().getReviewsByUserIdForManagement(userId);
    }

    @GET
    @Path("/product/childrenProducts/{categoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductDTObject> getAllChrildrenProductsByCategoryId(
            @PathParam("categoryId") long categoryId) {
        return RestProductDTO.getAllChrildrenProductsByCategoryIdForRest(categoryId);
    }

    @DELETE
    @Path("/users/reviews/{userId}/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("userId") long userId, @PathParam("productId") long productId) {
        getDAOFactory().getReviewDAO().deleteReviews(userId, productId);
        return Response.ok().build();
    }

    @GET
    @Path("/users/salesOrders/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SalesOrder> getSalesOrderToOrderHistory(@PathParam("userId") long userId) {
        return getDAOFactory().getSalesOrderDAO().getSalesOrderToOrderHistory(userId);
    }


    @GET
    @Path("/characteristics/{categoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CharacteristicGroupDTOObject> getCharacteristicsByCategoryId(@PathParam("categoryId") long categoryId) {
        return getDAOFactory().getChracteristicDAO().getCharacteristicsByCategoryIdForManagement(categoryId);
    }

    @GET
    @Path("/salesOrders")
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderDTOObject> getSalesOrdersForManagement() {
        return getDAOFactory().getSalesOrderDAO().getSalesOrdersForManagement();
    }

    @GET
    @Path("/salesOrders/{salesOrderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public OrderDTOObject getSalesOrderIdForManagement(@PathParam("salesOrderId") long salesOrderId) {
        return getDAOFactory().getSalesOrderDAO().getSalesOrderIdForManagement(salesOrderId);
    }

    @GET
    @Path("/salesOrders/orderItems/{salesOrderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderItem> getOrderItemsToSalesOrder(@PathParam("salesOrderId") long salesOrderId) throws SQLException {
        return getDAOFactory().getSalesOrderDAO().getOrderItemsToSalesOrder(salesOrderId);
    }

    @PUT
    @Path("/salesOrders")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public OrderDTOObject updateSalesOrderForManagement(OrderDTOObject salesOrder) {
        return getDAOFactory().getSalesOrderDAO().updateSalesOrderForManagement(salesOrder);
    }

    @DELETE
    @Path("/salesOrders/{salesOrderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSalesOrderForManagement(@PathParam("salesOrderId") long salesOrderId) {
        getDAOFactory().getSalesOrderDAO().deleteSalesOrderForManagement(salesOrderId);
        return Response.ok().build();
    }

    @GET
    @Path("/statuses")
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderStatusDAOObject> getOrdersStatus() {
        return getDAOFactory().getOrderStatusDAO().getOrdersStatus();
    }
}
