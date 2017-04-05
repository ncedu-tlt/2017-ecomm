package ru.ncedu.ecomm.servlets.services;

import ru.ncedu.ecomm.data.DAOFactory;
import ru.ncedu.ecomm.data.models.OrderItemDAOObject;
import ru.ncedu.ecomm.data.models.OrderStatusDAOObject;
import ru.ncedu.ecomm.data.models.ProductDAOObject;
import ru.ncedu.ecomm.data.models.SalesOrderDAOObject;
import ru.ncedu.ecomm.servlets.models.EnumOrderStatus;
import ru.ncedu.ecomm.data.models.OrderItem;
import ru.ncedu.ecomm.servlets.models.ProductViewModel;
import ru.ncedu.ecomm.data.models.SalesOrder;
import ru.ncedu.ecomm.data.models.builders.OrderItemBuilder;
import ru.ncedu.ecomm.data.models.builders.SalesOrderBuilder;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

public class ShoppingCartService {

    private ShoppingCartService() {
    }

    private final static int MIN_QUANTITY = 1;

    private static ShoppingCartService instance;

    public static synchronized ShoppingCartService getInstance() {
        if (instance == null) {
            instance = new ShoppingCartService();
        }
        return instance;
    }

    /**
     * @param userId    - из этого параметра мы получим salesOrderId или создадим новый salesOrder
     * @param productId - продукт, который добавится в базу
     */
    public void addToShoppingCart(long userId, long productId) throws SQLException {
        Long salesOrderId = getSalesOrderId(userId);
        if (Objects.isNull(salesOrderId)) {
            SalesOrderDAOObject newSalesOrder = createAndGetNewSalesOrder(userId);
            salesOrderId = newSalesOrder.getSalesOrderId();
            addProductToOrderItem(productId, salesOrderId);
        } else {
            addProductToOrderItem(productId, salesOrderId);
        }
    }

    public Long getSalesOrderId(long userId) throws SQLException {
        SalesOrderDAOObject salesOrderByUserId = getSalesOrderFromCollection(userId);
        if (Objects.isNull(salesOrderByUserId)) {
            return null;
        } else {
            return salesOrderByUserId.getSalesOrderId();
        }
    }

    private SalesOrderDAOObject getSalesOrderFromCollection(long userId) {
        SalesOrderDAOObject salesOrder =
                getSalesOrder(EnumOrderStatus.ENTERING.getStatus(), userId);
        if (Objects.isNull(salesOrder)) {
            return null;
        } else {
            return salesOrder;
        }
    }


    /**
     * Универсальный метод для обновления количества товара в корзине.
     * Используется при любом изменении количества товара в корзине.
     *
     * @param inputQuantity нужен для задания требуемого количества товара
     *                      для изменения.
     */
    public void updateQuantity(long salesOrderId, int inputQuantity, long productId) throws SQLException {
        List<OrderItem> orderItems = getOrderItemModelList(salesOrderId);
        OrderItem orderItemBySalesOrderId = getOrderItemBySalesOrderId(productId, salesOrderId, orderItems);
        if (Objects.isNull(orderItemBySalesOrderId)) {
            System.out.println("Error of update quantity");
        } else {
            orderItemBySalesOrderId.setQuantity(inputQuantity);
            changeQuantityOrderItem(orderItemBySalesOrderId, inputQuantity, false);
        }
    }

    private void addProductToOrderItem(long productId, long salesOrderId) throws SQLException {
        List<OrderItem> orderItems = getOrderItemModelList(salesOrderId);
        OrderItem orderItemBySalesOrderId = getOrderItemBySalesOrderId(productId, salesOrderId, orderItems);
        if (orderItemBySalesOrderId == null) {
            addNewOrderItem(productId, salesOrderId);
        } else {
            try {
                changeQuantityOrderItem(orderItemBySalesOrderId, MIN_QUANTITY, true);
            } catch (NullPointerException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Метод возвращает orderItem в случае наличия товара в корзине.
     *
     * @param productId    - параметр для проверки
     * @param salesOrderId - параметр для проверки
     * @param orderItems   - нужен для проверки наличия товара в корзине
     * @return orderItem или null в зависимости от того, есть товар в корзине
     * или нет
     */
    private OrderItem getOrderItemBySalesOrderId(long productId, long salesOrderId, List<OrderItem> orderItems) {
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getProductId() == productId
                    && orderItem.getSalesOrderId() == salesOrderId) {
                return orderItem;
            }
        }
        return null;
    }

    private void addNewOrderItem(long productId, long salesOrderId) throws SQLException {
        OrderItemDAOObject orderItem = addToOrderItem(productId, salesOrderId);
        getDAOFactory().getOrderItemsDAO().addOrderItem(orderItem);
    }

    /**
     * Метод в зависимости от характера изменения количества товара,
     * по разному обновляет параметр quantity. Если товар изменяется из корзины,
     * то есть возможность обновить счётчик как от меньшего к большему,
     * так и от большего к меньшему - поэтому необходимы разные методы для обновления.
     *
     * @param isSingleAdd - проверка на добавление товара из корзины(false)
     *                    и из других страниц(true)
     */
    private void changeQuantityOrderItem(OrderItem orderItemBySalesOrderId, int inputQuantity, boolean isSingleAdd) throws SQLException {
        if (isSingleAdd) {
            singleUpdatingOrderItem(orderItemBySalesOrderId, inputQuantity);
        } else {
            multiplyUpdatingOrderItem(orderItemBySalesOrderId, inputQuantity);
        }
    }

    private void singleUpdatingOrderItem(OrderItem orderItemBySalesOrderId, int inputQuantity) {
        OrderItemDAOObject orderItemWithChangeQuantity = getSingleAddToCart(orderItemBySalesOrderId, inputQuantity);
        getDAOFactory().getOrderItemsDAO().updateOrderItem(orderItemWithChangeQuantity);
    }

    private void multiplyUpdatingOrderItem(OrderItem orderItemBySalesOrderId, int inputQuantity) {
        OrderItemDAOObject orderItemWithChangeQuantity = getOrderItemByViewModel(orderItemBySalesOrderId);
        orderItemWithChangeQuantity.setQuantity(inputQuantity);
        getDAOFactory().getOrderItemsDAO().updateOrderItem(orderItemWithChangeQuantity);
    }

    private OrderItemDAOObject getSingleAddToCart(OrderItem orderItemBySalesOrderId, int inputQuantity) {
        OrderItemDAOObject orderItemWithChangeQuantity = getOrderItemByViewModel(orderItemBySalesOrderId);
        int oldQuantity = orderItemWithChangeQuantity.getQuantity();
        int newQuantity = oldQuantity + inputQuantity;
        orderItemWithChangeQuantity.setQuantity(newQuantity);
        return orderItemWithChangeQuantity;
    }


    private OrderItemDAOObject getOrderItemByViewModel(OrderItem orderItemBySalesOrderId) {
        OrderItemDAOObject orderItem = new OrderItemDAOObject();
        orderItem.setProductId(orderItemBySalesOrderId.getProductId());
        orderItem.setSalesOrderId(orderItemBySalesOrderId.getSalesOrderId());
        orderItem.setQuantity(orderItemBySalesOrderId.getQuantity());
        if (orderItemBySalesOrderId.getDiscount() != 0) {
            orderItem.setStandardPrice(orderItemBySalesOrderId.getDiscount());
        } else {
            orderItem.setStandardPrice(orderItemBySalesOrderId.getPrice());
        }
        return orderItem;
    }

    private OrderItemDAOObject addToOrderItem(long productId, long salesOrderId) throws SQLException {
        OrderItemDAOObject orderItem = new OrderItemDAOObject();
        orderItem.setProductId(productId);
        orderItem.setSalesOrderId(salesOrderId);
        orderItem.setQuantity(MIN_QUANTITY);
        orderItem.setStandardPrice(getPrice(productId));

        return orderItem;
    }

    private SalesOrderDAOObject createAndGetNewSalesOrder(long userId) {
        SalesOrderDAOObject salesOrder = getSalesOrderByEnteringStatus(userId);
        return getDAOFactory().getSalesOrderDAO().addSalesOrder(salesOrder);
    }

    private SalesOrderDAOObject getSalesOrderByEnteringStatus(long userId) {
        SalesOrderDAOObject saleOrder = new SalesOrderDAOObject();
        Date creationDate = new Date(System.currentTimeMillis());

        saleOrder.setUserId(userId);
        saleOrder.setCreationDate(creationDate);
        saleOrder.setOrderStatusId(EnumOrderStatus.ENTERING.getStatus());

        return saleOrder;
    }

    public SalesOrder getSalesOrderModel(long orderStatusId, long userId) throws SQLException {
        SalesOrderDAOObject salesOrder = getSalesOrder(orderStatusId, userId);
        if (Objects.isNull(salesOrder)) {
            return null;
        } else {
            return new SalesOrderBuilder()
                    .setUserId(salesOrder.getUserId())
                    .setSalesOrderId(salesOrder.getSalesOrderId())
                    .setCreationDate(salesOrder.getCreationDate())
                    .setLimit(salesOrder.getLimit())
                    .setTotalAmount(totalAmount(salesOrder.getSalesOrderId()))
                    .setOrderItems(relationPriceAndQuantity(salesOrder.getSalesOrderId()))
                    .setStatusName(getStatusName(salesOrder.getOrderStatusId()))
                    .build();
        }
    }

    public List<SalesOrder> getSalesOrderModelListByStatusId(long orderStatusId) throws SQLException {
        List<SalesOrder> salesOrderViewModels = new ArrayList<>();
        List<SalesOrderDAOObject> salesOrders = getSalesOrder(orderStatusId);
        for (SalesOrderDAOObject salesOrder : salesOrders) {
            SalesOrder salesOrderViewModel = new SalesOrderBuilder()
                    .setUserId(salesOrder.getUserId())
                    .setSalesOrderId(salesOrder.getSalesOrderId())
                    .setCreationDate(salesOrder.getCreationDate())
                    .setLimit(salesOrder.getLimit())
                    .setTotalAmount(totalAmount(salesOrder.getSalesOrderId()))
                    .setOrderItems(relationPriceAndQuantity(salesOrder.getSalesOrderId()))
                    .build();
            salesOrderViewModels.add(salesOrderViewModel);
        }
        return salesOrderViewModels;
    }

    public List<SalesOrder> getSalesOrderModelListByUserId(long userId) throws SQLException {
        List<SalesOrder> salesOrderViewModels = new ArrayList<>();
        List<SalesOrderDAOObject> salesOrders = getSalesOrderByUserId(userId);
        for (SalesOrderDAOObject salesOrder : salesOrders) {
            SalesOrder salesOrderViewModel = new SalesOrderBuilder()
                    .setUserId(salesOrder.getUserId())
                    .setSalesOrderId(salesOrder.getSalesOrderId())
                    .setCreationDate(salesOrder.getCreationDate())
                    .setLimit(salesOrder.getLimit())
                    .setTotalAmount(totalAmount(salesOrder.getSalesOrderId()))
                    .setOrderItems(relationPriceAndQuantity(salesOrder.getSalesOrderId()))
                    .setStatusName(getStatusName(salesOrder.getOrderStatusId()))
                    .build();
            salesOrderViewModels.add(salesOrderViewModel);
        }
        return salesOrderViewModels;
    }

    public List<SalesOrder> getSalesOrderModel() throws SQLException {
        List<SalesOrder> salesOrderViewModels = new ArrayList<>();
        List<SalesOrderDAOObject> salesOrders = DAOFactory.getDAOFactory().getSalesOrderDAO().getSalesOrders();
        for (SalesOrderDAOObject salesOrder : salesOrders) {
            SalesOrder salesOrderViewModel = new SalesOrderBuilder()
                    .setUserId(salesOrder.getUserId())
                    .setSalesOrderId(salesOrder.getSalesOrderId())
                    .setCreationDate(salesOrder.getCreationDate())
                    .setLimit(salesOrder.getLimit())
                    .setTotalAmount(totalAmount(salesOrder.getSalesOrderId()))
                    .setOrderItems(relationPriceAndQuantity(salesOrder.getSalesOrderId()))
                    .setStatusName(getStatusName(salesOrder.getOrderStatusId()))
                    .build();
            salesOrderViewModels.add(salesOrderViewModel);
        }
        return salesOrderViewModels;
    }

    private List<OrderItem> getOrderItemModelList(long salesOrderId) throws SQLException {
        List<OrderItem> orderItemsView = new ArrayList<>();
        List<ProductViewModel> products = ProductViewService.getInstance().getProductModelByOrderId(salesOrderId);
        for (ProductViewModel product : products) {
            OrderItem orderItemViewModel = new OrderItemBuilder()
                    .setSalesOrderId(salesOrderId)
                    .setProductId(product.getId())
                    .setQuantity(getQuantity(product.getId(), salesOrderId))
                    .setStandardPrice(getStandardPrice(product.getId(), salesOrderId))
                    .setName(product.getName())
                    .setPrice(product.getPrice())
                    .setImgUrl(product.getImageUrl())
                    .setDiscount(product.getDiscount())
                    .build();
            orderItemsView.add(orderItemViewModel);
        }
        return orderItemsView;
    }

    private long totalAmount(long salesOrderId) throws SQLException {
        long sumAllPrice = 0;
        List<Long> priceList = new ArrayList<>();
        List<OrderItem> orderItemViewModels = relationPriceAndQuantity(salesOrderId);
        priceList.addAll(orderItemViewModels.stream().map(OrderItem::getPrice).collect(Collectors.toList()));
        for (Long sum : priceList) {
            sumAllPrice += sum;
        }
        return sumAllPrice;
    }

    private List<OrderItem> relationPriceAndQuantity(long salesOrderId) throws SQLException {
        List<OrderItem> orderItemViewModels = getOrderItemModelList(salesOrderId);
        for (OrderItem model : orderItemViewModels) {
            long amount = model.getStandardPrice();
            amount *= model.getQuantity();
            model.setPrice(amount);
        }
        return orderItemViewModels;
    }

    public void deletedProductsInOrderItem(long productId, long salesOrderId) throws SQLException {
        OrderItemDAOObject orderItem = getOrderItem(productId, salesOrderId);
        orderItem.setProductId(productId);
        orderItem.setSalesOrderId(salesOrderId);
        getDAOFactory().getOrderItemsDAO().deleteOrderItem(orderItem);
    }

    public void deletedAllProductsInOrderItemDataBase(long userId) throws SQLException {
        List<ProductViewModel> products = ProductViewService.getInstance().getProductModelByOrderId(getSalesOrderId(userId));
        for (ProductViewModel product : products) {
            OrderItemDAOObject orderItem = getOrderItem(product.getId(), getSalesOrderId(userId));
            orderItem.setProductId(product.getId());
            orderItem.setSalesOrderId(getSalesOrderId(userId));
            getDAOFactory().getOrderItemsDAO().deleteOrderItem(orderItem);
        }
    }

    private long getPrice(long productId) throws SQLException {
        ProductDAOObject product = DAOFactory.getDAOFactory().getProductDAO().getProductById(productId);
        long discountPrice = DiscountService.getInstance().getDiscountPrice(product.getDiscountId(), product.getPrice());
        if (product.getDiscountId() > 1) {
            return discountPrice;
        }
        return product.getPrice();
    }

    private int getQuantity(long productId, long salesOrderId) throws SQLException {
        OrderItemDAOObject orderItem = getOrderItem(productId, salesOrderId);
        return orderItem.getQuantity();
    }

    private long getStandardPrice(long productId, long salesOrderId) throws SQLException {
        OrderItemDAOObject orderItem = getOrderItem(productId, salesOrderId);
        return orderItem.getStandardPrice();
    }

    private OrderItemDAOObject getOrderItem(long productId, long salesOrderId) throws SQLException {
        return DAOFactory.getDAOFactory().getOrderItemsDAO().getOrderItem(productId, salesOrderId);
    }

    private SalesOrderDAOObject getSalesOrder(long orderStatusId, long userId) {
        SalesOrderDAOObject salesOrder = DAOFactory.getDAOFactory().getSalesOrderDAO().getSalesOrderByOrderStatusId(orderStatusId, userId);
        if (Objects.isNull(salesOrder)) {
            return null;
        } else {
            return salesOrder;
        }
    }

    private List<SalesOrderDAOObject> getSalesOrderByUserId(long userId) {
        return DAOFactory.getDAOFactory().getSalesOrderDAO().getSalesOrderByOrderUserId(userId);
    }

    private List<SalesOrderDAOObject> getSalesOrder(long orderStatusId) {
        return DAOFactory.getDAOFactory().getSalesOrderDAO().getSalesOrderByOrderStatusId(orderStatusId);
    }

    private String getStatusName(long orderStatusId) {
        OrderStatusDAOObject orderStatus = DAOFactory.getDAOFactory().getOrderStatusDAO().getOrdersStatusById(orderStatusId);
        return orderStatus.getName();
    }
}
