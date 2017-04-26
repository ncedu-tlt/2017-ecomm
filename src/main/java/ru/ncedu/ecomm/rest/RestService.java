package ru.ncedu.ecomm.rest;

import ru.ncedu.ecomm.data.models.dao.*;
import ru.ncedu.ecomm.data.models.dto.UserDTOObject;

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
    @Path("/discounts")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DiscountDAOObject> getDiscount() {
        return getDAOFactory().getDiscountDAO().getDiscount();
    }

    @GET
    @Path("/roles")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RoleDAOObject> getRoles() {
        return getDAOFactory().getRoleDAO().getRoles();
    }

    @GET
    @Path("/roles/{roleId}")
    @Produces(MediaType.APPLICATION_JSON)
    public RoleDAOObject getRole(@PathParam("roleId") long roleId) {
        return getDAOFactory().getRoleDAO().getRoleById(roleId);
    }

    @GET
    @Path("/categories")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CategoryDAOObject> getCategories() {
        return getDAOFactory().getCategoryDAO().getCategories();
    }

    @GET
    @Path("/categories/notempty")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CategoryDAOObject> getNotEmptyCategories() {
        return getDAOFactory().getCategoryDAO().getAllNotEmptyCategories();
    }

    @GET
    @Path("/categories/{categoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public CategoryDAOObject getCategoryById(@PathParam("categoryId") long categoryId) {
        return getDAOFactory().getCategoryDAO().getCategoryById(categoryId);
    }

    @GET
    @Path("/categories/getallchild/{categoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CategoryDAOObject> getAllChildrenCategory(@PathParam("categoryId") long categoryId) {
        return getDAOFactory().getCategoryDAO().getAllNotEmptyChildrenCategoriesById(categoryId);
    }

    @PUT
    @Path("/categories")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CategoryDAOObject updateCategory(CategoryDAOObject category) {
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
    public CategoryDAOObject addCategory(CategoryDAOObject category) {
        return getDAOFactory().getCategoryDAO().addCategory(category);
    }

    @GET
    @Path("/categories/parent/{parentCategoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CategoryDAOObject> getCategoryByParent(@PathParam("parentCategoryId") long parentCategoryId) {
        return getDAOFactory().getCategoryDAO().getCategoriesByParentId(parentCategoryId);
    }

    @GET
    @Path("/properties")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PropertyDAOObject> getProperties() {
        return getDAOFactory().getPropertyDAO().getProperties();
    }

    @GET
    @Path("/properties/{propertyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public PropertyDAOObject getProperties(@PathParam("propertyId") String propertyId) {
        return getDAOFactory().getPropertyDAO().getPropertyById(propertyId);
    }
    @POST
    @Path("/properties")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public PropertyDAOObject addProperty(PropertyDAOObject property) {
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
    public PropertyDAOObject updateProperty(PropertyDAOObject property) {
        return getDAOFactory().getPropertyDAO().updateProperty(property);
    }


    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDAOObject> getUsers() {
        return getDAOFactory().getUserDAO().getUsers();
    }

    @GET
    @Path("/management/users")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDTOObject> getUsersManagement() {return getDAOFactory().getUserDAO().getUsersManagement();
    }

    @GET
    @Path("/management/users/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTOObject getUserManagementById(@PathParam("userId") long userId) {
        return getDAOFactory().getUserDAO().getUserManagementById(userId);
    }

    @DELETE
    @Path("/management/users/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUserManagement(@PathParam("userId") long userId) {
        getDAOFactory().getUserDAO().deleteUserManagement(getDAOFactory().getUserDAO().getUserManagementById(userId));
        return Response.ok().build();
    }

    @GET
    @Path("/users/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDAOObject getUserById(@PathParam("userId") long userId) {
        return getDAOFactory().getUserDAO().getUserById(userId);
    }

    @GET
    @Path("/users/role/{roleId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDAOObject> getUserByRoleId(@PathParam("roleId") long roleId) {
        return getDAOFactory().getUserDAO().getUserByRoleId(roleId);
    }

    @GET
    @Path("/users/password/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDAOObject getUserByPassword(@PathParam("password") String password){
        return getDAOFactory().getUserDAO().getUserByPassword(password);
    }

    @POST
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserDAOObject addUser(UserDAOObject user) {
        return getDAOFactory().getUserDAO().addUser(user);
    }

    @PUT
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserDAOObject updateUser(UserDAOObject user) {
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
    public List<SalesOrderDAOObject> getSalesOrders() {
        return getDAOFactory().getSalesOrderDAO().getSalesOrders();
    }

    @POST
    @Path("/salesorder")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SalesOrderDAOObject addSalesOrder(SalesOrderDAOObject salesOrder) {
        return getDAOFactory().getSalesOrderDAO().addSalesOrder(salesOrder);
    }

    @GET
    @Path("/salesorder/{salesOrderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public SalesOrderDAOObject getSalesOrderById(@PathParam("salesOrderId") long salesOrderId) {
        return getDAOFactory().getSalesOrderDAO().getSalesOrderById(salesOrderId);
    }

    @GET
    @Path("/salesorder/orderstatus/{salesOrderStatusId}/{salesOrderUserId}")
    @Produces(MediaType.APPLICATION_JSON)
    public SalesOrderDAOObject getSalesOrderByOrderStatusId(@PathParam("salesOrderStatusId") long salesOrderStatusId, @PathParam("salesOrderUserId") long salesOrderUserId){
        return getDAOFactory().getSalesOrderDAO().getSalesOrderByOrderStatusId(salesOrderStatusId, salesOrderUserId);
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
    public SalesOrderDAOObject updateSalesOrder(SalesOrderDAOObject salesOrder) {
        return getDAOFactory().getSalesOrderDAO().updateSalesOrder(salesOrder);
    }

    @GET
    @Path("/characteristicvalue")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CharacteristicValueDAOObject> getCharacteristicValue() {
        return getDAOFactory().getCharacteristicValueDAO().getCharacteristicValues();
    }

    @GET
    @Path("/characteristicvalue/{characteristicvalue}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CharacteristicValueDAOObject> getCharacteristicValueById(
            @PathParam("characteristicvalue") long characteristicvalue) {
        return getDAOFactory().getCharacteristicValueDAO().getCharacteristicValuesById(characteristicvalue);
    }

    @GET
    @Path("/characteristicvalue/byproduct/{productid}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CharacteristicValueDAOObject> getCharacteristicValueByProductId(@PathParam("productid") long productid) {
        return getDAOFactory().getCharacteristicValueDAO().getCharacteristicValuesByProductId(productid);
    }

    @POST
    @Path("/characteristicvalue")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CharacteristicValueDAOObject addCharacteristicValue(CharacteristicValueDAOObject characteristicValue) {
        return getDAOFactory().getCharacteristicValueDAO().addCharacteristicValue(characteristicValue);
    }

    @PUT
    @Path("/characteristicvalue")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CharacteristicValueDAOObject updateCharacteristicValue(CharacteristicValueDAOObject characteristicValue) {
        return getDAOFactory().getCharacteristicValueDAO().updateCharacteristicValue(characteristicValue);
    }

    @GET
    @Path("/product")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductDAOObject> getProducts() {
        return getDAOFactory().getProductDAO().getProducts();
    }

    @GET
    @Path("/product/{productid}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductDAOObject getProductById(
            @PathParam("productid") long productid) {
        return getDAOFactory().getProductDAO().getProductById(productid);
    }


    @GET
    @Path("/product/orderId/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductDAOObject> getProductByOrderId(
            @PathParam("orderId") long orderId) {
        return getDAOFactory().getProductDAO().getProductByOrderId(orderId);
    }



    @GET
    @Path("/product/category/{categoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductDAOObject> getProductByCategoryId(
            @PathParam("categoryId") long categoryId) {
        return getDAOFactory().getProductDAO().getProductsByCategoryId(categoryId);
    }

    @POST
    @Path("/product")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ProductDAOObject addProduct(ProductDAOObject product) {
        return getDAOFactory().getProductDAO().addProduct(product);
    }

    @PUT
    @Path("/product")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ProductDAOObject updateProduct(ProductDAOObject product) {
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
    public List<ReviewDAOObject> getReviews() {
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
    public List<ReviewDAOObject> getReviewsByUserId(@PathParam("userId") long userId) {
        return getDAOFactory().getReviewDAO().getReviewsByUserId(userId);
    }

    @GET
    @Path("/reviews/byproductid/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ReviewDAOObject> getReviewsByProductId(@PathParam("productId") long productId) {
        return getDAOFactory().getReviewDAO().getReviewsByProductId(productId);
    }

    @POST
    @Path("/reviews")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ReviewDAOObject addReview(ReviewDAOObject review) {
        return getDAOFactory().getReviewDAO().addReviews(review);
    }

    @PUT
    @Path("/reviews")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ReviewDAOObject updateReview(ReviewDAOObject review) {
        return getDAOFactory().getReviewDAO().updateReviews(review);
    }

    @GET
    @Path("/characteristicgroup")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CharacteristicGroupDAOObject> getCharacteristicGroup() {
        return getDAOFactory().getCharacteristicGroupDAO().getCharacteristicGroup();
    }
    @GET
    @Path("/characteristicgroup/{characteristicgroupbyid}")
    @Produces(MediaType.APPLICATION_JSON)
    public CharacteristicGroupDAOObject getCharacteristicGroupById(
            @PathParam("characteristicgroupbyid") long characteristicgroupbyid) {
        return getDAOFactory().getCharacteristicGroupDAO().getCharacteristicGroupById(characteristicgroupbyid);
    }
    @POST
    @Path("/characteristicgroup")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CharacteristicGroupDAOObject addCharacteristicGroup(CharacteristicGroupDAOObject characteristicGroup) {
        return getDAOFactory().getCharacteristicGroupDAO().addCharacteristicGroup(characteristicGroup);
    }
    @PUT
    @Path("/characteristicgroup")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CharacteristicGroupDAOObject updateCharacteristicGroup(CharacteristicGroupDAOObject characteristicGroup) {
        return getDAOFactory().getCharacteristicGroupDAO().updateCharacteristicGroup(characteristicGroup);
    }
    @DELETE
    @Path("/characteristicgroup/{characteristicgroup}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCharacteristicGroup(@PathParam("characteristicgroup") long characteristicgroup) {
        getDAOFactory().getCharacteristicGroupDAO().deleteCharacteristicGroup(getDAOFactory().getCharacteristicGroupDAO().getCharacteristicGroupById(characteristicgroup));
        return Response.ok().build();
    }
    @GET
    @Path("/characteristic")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CharacteristicDAOObject> getCharacteristic() {
        return getDAOFactory().getChracteristicDAO().getCharacteristic();
    }
    @GET
    @Path("/characteristic/{categoryId}/{groupId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CharacteristicDAOObject> getCharacteristicByCategoryIdAndGroupId(
            @PathParam("categoryId") long categoryId,
            @PathParam("groupId") long groupId
    ) {
        return getDAOFactory()
                .getChracteristicDAO()
                .getCharacteristicByCategoryIdAndGroupId(
                        categoryId,
                        groupId
                );
    }

    @GET
    @Path("/characteristic/groupId/{characteristicGroupId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CharacteristicDAOObject> getCharacteristicByGroupId(
            @PathParam("characteristicGroupId") long characteristicGroupId) {
        return getDAOFactory().getChracteristicDAO().getCharacteristicByGroupId(characteristicGroupId);
    }
    @GET
    @Path("/characteristic/{characteristic}")
    @Produces(MediaType.APPLICATION_JSON)
    public CharacteristicDAOObject getCharacteristicById(
            @PathParam("characteristic") long characteristic) {
        return getDAOFactory().getChracteristicDAO().getCharacteristicById(characteristic);
    }
    @POST
    @Path("/characteristic")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CharacteristicDAOObject addCharacteristic(CharacteristicDAOObject characteristic) {
        return getDAOFactory().getChracteristicDAO().addCharacteristic(characteristic);
    }
    @PUT
    @Path("/characteristic")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CharacteristicDAOObject updateCharacteristic(CharacteristicDAOObject characteristic) {
        return getDAOFactory().getChracteristicDAO().updateCharacteristic(characteristic);
    }
    @DELETE
    @Path("/characteristic/{characteristic}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCharacteristic(@PathParam("characteristic") long characteristic) {
        getDAOFactory().getChracteristicDAO().deleteCharacteristic(getDAOFactory().getChracteristicDAO().getCharacteristicById(characteristic));
        return Response.ok().build();
    }
    @GET
    @Path("/orderitems")
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderItemDAOObject> getOrderItems() {
        return getDAOFactory().getOrderItemsDAO().getOrderItems();
    }

    @GET
    @Path("/orderitems/{salesOrderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderItemDAOObject> getSalesOrderByUserId(@PathParam("salesOrderId") long salesOrderId){
        return getDAOFactory().getOrderItemsDAO().getOrderItemsBySalesOrderId(salesOrderId);
    }

    @POST
    @Path("/orderitems")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public OrderItemDAOObject addOrderItem(OrderItemDAOObject orderItem) {
        return getDAOFactory().getOrderItemsDAO().addOrderItem(orderItem);
    }
    @PUT
    @Path("/orderitems")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public OrderItemDAOObject updateOrderItem(OrderItemDAOObject orderItem) {
        return getDAOFactory().getOrderItemsDAO().updateOrderItem(orderItem);
    }
    @DELETE
    @Path("/orderitems")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOrderItem(OrderItemDAOObject orderItem) {
        getDAOFactory().getOrderItemsDAO().deleteOrderItem(orderItem);
        return Response.ok().build();
    }
    @GET
    @Path("/orderstatus/{oderStatusId}")
    @Produces(MediaType.APPLICATION_JSON)
    public OrderStatusDAOObject getOrderStatusById(
            @PathParam("oderStatusId") long oderStatusId) {
        return getDAOFactory().getOrderStatusDAO().getOrdersStatusById(oderStatusId);
    }
    @GET
    @Path("/recomendedproduct")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RecomendedProductDAOObject> getRecomendedProducts() {
        return getDAOFactory().getRecomendedProductsDAO().getRecomendedProducts();
    }
    @POST
    @Path("/recomendedproduct")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RecomendedProductDAOObject getRecomendedProducts(RecomendedProductDAOObject recomendedProduct) {
        return getDAOFactory().getRecomendedProductsDAO().addRecomendedProduct(recomendedProduct);
    }
    @DELETE
    @Path("/recomendedproduct")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRecomendedProduct(RecomendedProductDAOObject recomendedProduct) {
        getDAOFactory().getRecomendedProductsDAO().deleteRecomendedProduct(recomendedProduct);
        return Response.ok().build();
    }
}
