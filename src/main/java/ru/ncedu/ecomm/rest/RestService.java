package ru.ncedu.ecomm.rest;

import ru.ncedu.ecomm.data.DAOFactory;
import ru.ncedu.ecomm.data.models.*;

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
    public Category getCategoryById(@PathParam("categoryId") long categoryId) {
        return getDAOFactory().getCategoryDAO().getCategoryById(categoryId);
    }

    @PUT
    @Path("/categories")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Category updateCategory(Category category) {
        return getDAOFactory().getCategoryDAO().updateCategory(category);
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
    @POST
    @Path("/properties")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Property addProperty(Property property) {
        return getDAOFactory().getPropertyDAO().addProperty(property);
    }
    @DELETE
    @Path("/properties/{propertiesId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProperties(@PathParam("propertiesId") String propertiesId) {
        getDAOFactory().getPropertyDAO().deleteProperty(getDAOFactory().getPropertyDAO().getPropertyById(propertiesId));
        return Response.ok().build();
    }
    @PUT
    @Path("/properties")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Property updateProperty(Property property) {
        return getDAOFactory().getPropertyDAO().updateProperty(property);
    }


    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers() {
        return getDAOFactory().getUserDAO().getUsers();
    }

    @GET
    @Path("/users/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserById(@PathParam("userId") long userId) {
        return getDAOFactory().getUserDAO().getUserById(userId);
    }

    @GET
    @Path("/users/role/{roleId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUserByRoleId(@PathParam("roleId") long roleId) {
        return getDAOFactory().getUserDAO().getUserByRoleId(roleId);
    }

    @POST
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User addUser(User user) {
        return getDAOFactory().getUserDAO().addUser(user);
    }

    @PUT
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User updateUser(User user) {
        return getDAOFactory().getUserDAO().updateUser(user);
    }

    @DELETE
    @Path("/users/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("userId") long userId) {
        getDAOFactory().getUserDAO().deleteUser(getDAOFactory().getUserDAO().getUserById(userId));
        return Response.ok().build();
    }

    @GET
    @Path("/salesorder")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SalesOrder> getSalesOrders() {
        return getDAOFactory().getSalesOrderDAO().getSalesOrders();
    }

    @POST
    @Path("/salesorder")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SalesOrder addSalesOrder(SalesOrder salesOrder) {
        return getDAOFactory().getSalesOrderDAO().addSalesOrder(salesOrder);
    }

    @GET
    @Path("/salesorder/{salesOrderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public SalesOrder getSalesOrderById(@PathParam("salesOrderId") long salesOrderId) {
        return getDAOFactory().getSalesOrderDAO().getSalesOrderById(salesOrderId);
    }

    @DELETE
    @Path("/salesorder/{salesOrderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSalesOrder(@PathParam("salesOrderId") long salesOrderId) {
        getDAOFactory().getSalesOrderDAO().deleteSalesOrder(getDAOFactory().getSalesOrderDAO().getSalesOrderById(salesOrderId));
        return Response.ok().build();
    }

    @PUT
    @Path("/salesorder")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SalesOrder updateSalesOrder(SalesOrder salesOrder) {
        return getDAOFactory().getSalesOrderDAO().updateSalesOrder(salesOrder);
    }

    @GET
    @Path("/characteristicvalue")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CharacteristicValue> getCharacteristicValue() {
        return getDAOFactory().getCharacteristicValueDAO().getCharacteristicValue();
    }

    @GET
    @Path("/characteristicvalue/{characteristicvalue}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CharacteristicValue> getCharacteristicValueById(
            @PathParam("characteristicvalue") long characteristicvalue) {
        return getDAOFactory().getCharacteristicValueDAO().getCharacteristicValueById(characteristicvalue);
    }

    @GET
    @Path("/characteristicvalue/byproduct/{productid}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CharacteristicValue> getCharacteristicValueByProductId(@PathParam("productid") long productid) {
        return getDAOFactory().getCharacteristicValueDAO().getCharacteristicValueByProductId(productid);
    }

    @POST
    @Path("/characteristicvalue")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CharacteristicValue addCharacteristicValue(CharacteristicValue characteristicValue) {
        return getDAOFactory().getCharacteristicValueDAO().addCharacteristicValue(characteristicValue);
    }

    @PUT
    @Path("/characteristicvalue")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CharacteristicValue updateCharacteristicValue(CharacteristicValue characteristicValue) {
        return getDAOFactory().getCharacteristicValueDAO().updateCharacteristicValue(characteristicValue);
    }

    @GET
    @Path("/product")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getProducts() {
        return getDAOFactory().getProductDAO().getProducts();
    }

    @GET
    @Path("/product/{productid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getProductById(
            @PathParam("productid") long productid) {
        return getDAOFactory().getProductDAO().getProductById(productid);
    }

    @GET
    @Path("/product/category/{categoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getProductByCategoryId(
            @PathParam("categoryId") long categoryId) {
        return getDAOFactory().getProductDAO().getProductsByCategoryId(categoryId);
    }

    @POST
    @Path("/product")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Product addProduct(Product product) {
        return getDAOFactory().getProductDAO().addProduct(product);
    }

    @PUT
    @Path("/product")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Product updateProduct(Product product) {
        return getDAOFactory().getProductDAO().updateProduct(product);
    }

    @DELETE
    @Path("/product/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("productId") long productId) {
        getDAOFactory().getProductDAO().deleteProduct(getDAOFactory().getProductDAO().getProductById(productId));
        return Response.ok().build();
    }

    @GET
    @Path("/reviews")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Review> getReviews() {
        return getDAOFactory().getReviewDAO().getReviews();
    }

    @GET
    @Path("/reviews/averagerating")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rating> getAverageRating() {
        return getDAOFactory().getReviewDAO().getAverageRatingByProduct();
    }

    @GET
    @Path("/reviews/byuserid/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Review> getReviewsByUserId(@PathParam("userId") long userId) {
        return getDAOFactory().getReviewDAO().getReviewsByUserId(userId);
    }

    @GET
    @Path("/reviews/byproductid/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Review> getReviewsByProductId(@PathParam("productId") long productId) {
        return getDAOFactory().getReviewDAO().getReviewsByProductId(productId);
    }

    @POST
    @Path("/reviews")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Review addReview(Review review) {
        return getDAOFactory().getReviewDAO().addReviews(review);
    }

    @PUT
    @Path("/reviews")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Review updateReview(Review review) {
        return getDAOFactory().getReviewDAO().updateReviews(review);
    }

}
