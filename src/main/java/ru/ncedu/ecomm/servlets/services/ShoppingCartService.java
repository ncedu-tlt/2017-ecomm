package ru.ncedu.ecomm.servlets.services;

import ru.ncedu.ecomm.data.DAOFactory;
import ru.ncedu.ecomm.data.models.OrderItem;
import ru.ncedu.ecomm.data.models.OrderStatus;
import ru.ncedu.ecomm.data.models.Product;
import ru.ncedu.ecomm.data.models.SalesOrder;
import ru.ncedu.ecomm.servlets.models.EnumOrderStatus;
import ru.ncedu.ecomm.servlets.models.OrderItemViewModel;
import ru.ncedu.ecomm.servlets.models.ProductViewModel;
import ru.ncedu.ecomm.servlets.models.SalesOrderViewModel;
import ru.ncedu.ecomm.servlets.models.builders.OrderItemViewBuilder;
import ru.ncedu.ecomm.servlets.models.builders.SalesOrderViewBuilder;

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

    private static ShoppingCartService instance;

    public static synchronized ShoppingCartService getInstance() {
        if (instance == null) {
            instance = new ShoppingCartService();
        }
        return instance;
    }

    public void addToShoppingCart(long userId, long productId) throws SQLException {
        Long salesOrderId = getSalesOrderId(userId);
            if (Objects.isNull(salesOrderId)) {
                addNewSalesOrder(userId);
            } else {
                addProductToOrderItem(productId, salesOrderId);
            }
    }

    public Long getSalesOrderId(long userId) throws SQLException {
        SalesOrder salesOrderByUserId = salesOrderByUserId(userId);
        if (Objects.isNull(salesOrderByUserId)) {
            return null;
        } else {
            return salesOrderByUserId.getSalesOrderId();
        }
    }

    private SalesOrder salesOrderByUserId(long userId) {
        List<SalesOrder> salesOrderList =
                getSalesOrder(EnumOrderStatus.ENTERING.getStatus(), userId);
        if (Objects.isNull(salesOrderList)) {
            return null;
        } else {
            final int FIRST_INDEX = 0;
            if (salesOrderList.size() == 0) {
                return null;
            } else {
                return salesOrderList.get(FIRST_INDEX);
            }
        }
    }

    /**
     * Универсальный метод для обновления количества товара в корзине.
     * Используется при любом изменении количества товара в корзине.
     *
     * @param inputQuantity нужен для задания требуемого количества товара
     *                      для изменения. В случае изменении количества товара
     *                      из корзины, может принимать значения больше 1.
     */
    public void updateQuantity(long salesOrderId, int inputQuantity, long productId) throws SQLException {
        List<OrderItemViewModel> orderItems = getOrderItemModelList(salesOrderId);
        OrderItemViewModel orderItemBySalesOrderId = getOrderItemBySalesOrderId(productId, salesOrderId, orderItems);
        if (orderItemBySalesOrderId != null) {
            orderItemBySalesOrderId.setQuantity(inputQuantity);
            changeQuantityOrderItem(orderItemBySalesOrderId, inputQuantity, false);
        } else {
            System.out.println("Error of update quantity");
        }
    }

    private void addProductToOrderItem(long productId, long salesOrderId) throws SQLException {
        List<OrderItemViewModel> orderItems = getOrderItemModelList(salesOrderId);
        OrderItemViewModel orderItemBySalesOrderId = getOrderItemBySalesOrderId(productId, salesOrderId, orderItems);
        if (orderItemBySalesOrderId == null) {
            addNewOrderItem(productId, salesOrderId);
        } else {
            try {
                final int INPUT_QUANTITY_CHANGE = 1;
                changeQuantityOrderItem(orderItemBySalesOrderId, INPUT_QUANTITY_CHANGE, true);
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
    private OrderItemViewModel getOrderItemBySalesOrderId(long productId, long salesOrderId, List<OrderItemViewModel> orderItems) {
        for (OrderItemViewModel orderItem : orderItems) {
            if (orderItem.getProductId() == productId
                    && orderItem.getSalesOrderId() == salesOrderId) {
                return orderItem;
            }
        }
        return null;
    }

    private void addNewOrderItem(long productId, long salesOrderId) throws SQLException {
        OrderItem orderItem = addToOrderItem(productId, salesOrderId);
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
    private void changeQuantityOrderItem(OrderItemViewModel orderItemBySalesOrderId, int inputQuantity, boolean isSingleAdd) throws SQLException {
        if (isSingleAdd) {
            singleUpdatingOrderItem(orderItemBySalesOrderId, inputQuantity);
        } else {
            multiplyUpdatingOrderItem(orderItemBySalesOrderId, inputQuantity);
        }
    }

    private void singleUpdatingOrderItem(OrderItemViewModel orderItemBySalesOrderId, int inputQuantity) {
        OrderItem orderItemWithChangeQuantity = getSingleAddToCart(orderItemBySalesOrderId, inputQuantity);
        getDAOFactory().getOrderItemsDAO().updateOrderItem(orderItemWithChangeQuantity);
    }

    private void multiplyUpdatingOrderItem(OrderItemViewModel orderItemBySalesOrderId, int inputQuantity) {
        OrderItem orderItemWithChangeQuantity = getOrderItemByViewModel(orderItemBySalesOrderId);
        orderItemWithChangeQuantity.setQuantity(inputQuantity);
        getDAOFactory().getOrderItemsDAO().updateOrderItem(orderItemWithChangeQuantity);
    }

    private OrderItem getSingleAddToCart(OrderItemViewModel orderItemBySalesOrderId, int inputQuantity) {
        OrderItem orderItemWithChangeQuantity = getOrderItemByViewModel(orderItemBySalesOrderId);
        int oldQuantity = orderItemWithChangeQuantity.getQuantity();
        int newQuantity = oldQuantity + inputQuantity;
        orderItemWithChangeQuantity.setQuantity(newQuantity);
        return orderItemWithChangeQuantity;
    }


    private OrderItem getOrderItemByViewModel(OrderItemViewModel orderItemBySalesOrderId) {
        OrderItem orderItem = new OrderItem();
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

    private OrderItem addToOrderItem(long productId, long salesOrderId) throws SQLException {
        final int minQuantity = 1;

        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(productId);
        orderItem.setSalesOrderId(salesOrderId);
        orderItem.setQuantity(minQuantity);
        orderItem.setStandardPrice(getPrice(productId));

        return orderItem;
    }

    private void addNewSalesOrder(long userId) {
        SalesOrder salesOrder = addToSalesOrder(userId);
        getDAOFactory().getSalesOrderDAO().addSalesOrder(salesOrder);
    }

    private SalesOrder addToSalesOrder(long userId) {
        SalesOrder saleOrder = new SalesOrder();
        Date creationDate = new Date(System.currentTimeMillis());

        saleOrder.setUserId(userId);
        saleOrder.setCreationDate(creationDate);
        saleOrder.setOrderStatusId(EnumOrderStatus.ENTERING.getStatus());

        return saleOrder;
    }

    public List<SalesOrderViewModel> getSalesOrderModelList(long orderStatusId, long userId) throws SQLException {
        List<SalesOrderViewModel> salesOrderViewModels = new ArrayList<>();
        List<SalesOrder> salesOrders = getSalesOrder(orderStatusId, userId);
        for (SalesOrder salesOrder : salesOrders) {
            SalesOrderViewModel salesOrderViewModel = new SalesOrderViewBuilder()
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

    public List<SalesOrderViewModel> getSalesOrderModelListByStatusId(long orderStatusId) throws SQLException {
        List<SalesOrderViewModel> salesOrderViewModels = new ArrayList<>();
        List<SalesOrder> salesOrders = getSalesOrder(orderStatusId);
        for (SalesOrder salesOrder : salesOrders) {
            SalesOrderViewModel salesOrderViewModel = new SalesOrderViewBuilder()
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

    public List<SalesOrderViewModel> getSalesOrderModelListByUserId(long userId) throws SQLException {
        List<SalesOrderViewModel> salesOrderViewModels = new ArrayList<>();
        List<SalesOrder> salesOrders = getSalesOrderByUserId(userId);
        for (SalesOrder salesOrder : salesOrders) {
            SalesOrderViewModel salesOrderViewModel = new SalesOrderViewBuilder()
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

    public List<SalesOrderViewModel> getSalesOrderModelList() throws SQLException {
        List<SalesOrderViewModel> salesOrderViewModels = new ArrayList<>();
        List<SalesOrder> salesOrders = DAOFactory.getDAOFactory().getSalesOrderDAO().getSalesOrders();
        for (SalesOrder salesOrder : salesOrders) {
            SalesOrderViewModel salesOrderViewModel = new SalesOrderViewBuilder()
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

    private List<OrderItemViewModel> getOrderItemModelList(long salesOrderId) throws SQLException {
        List<OrderItemViewModel> orderItemsView = new ArrayList<>();
        List<ProductViewModel> products = ProductViewService.getInstance().getProductModelByOrderId(salesOrderId);
        for (ProductViewModel product : products) {
            OrderItemViewModel orderItemViewModel = new OrderItemViewBuilder()
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
        List<OrderItemViewModel> orderItemViewModels = relationPriceAndQuantity(salesOrderId);
        priceList.addAll(orderItemViewModels.stream().map(OrderItemViewModel::getPrice).collect(Collectors.toList()));
        for (Long sum : priceList) {
            sumAllPrice += sum;
        }
        return sumAllPrice;
    }

    private List<OrderItemViewModel> relationPriceAndQuantity(long salesOrderId) throws SQLException {
        List<OrderItemViewModel> orderItemViewModels = getOrderItemModelList(salesOrderId);
        for (OrderItemViewModel model : orderItemViewModels) {
            long amount = model.getStandardPrice();
            amount *= model.getQuantity();
            model.setPrice(amount);
        }
        return orderItemViewModels;
    }

    public void deletedProductInOrderItemDataBase(long productId, long salesOrderId) throws SQLException {
        OrderItem orderItem = getOrderItem(productId, salesOrderId);
        orderItem.setProductId(productId);
        orderItem.setSalesOrderId(salesOrderId);
        getDAOFactory().getOrderItemsDAO().deleteOrderItem(orderItem);
    }

    public void deletedAllProductsInOrderItemDataBase(long userId) throws SQLException {
        List<ProductViewModel> products = ProductViewService.getInstance().getProductModelByOrderId(getSalesOrderId(userId));
        for (ProductViewModel product : products) {
            OrderItem orderItem = getOrderItem(product.getId(), getSalesOrderId(userId));
            orderItem.setProductId(product.getId());
            orderItem.setSalesOrderId(getSalesOrderId(userId));
            getDAOFactory().getOrderItemsDAO().deleteOrderItem(orderItem);
        }
    }

    private long getPrice(long productId) throws SQLException {
        Product product = DAOFactory.getDAOFactory().getProductDAO().getProductById(productId);
        long discountPrice = DiscountService.getInstance().getDiscountPrice(product.getDiscountId(), product.getPrice());
        if (product.getDiscountId() > 1) {
            return discountPrice;
        }
        return product.getPrice();
    }

    private int getQuantity(long productId, long salesOrderId) throws SQLException {
        OrderItem orderItem = getOrderItem(productId, salesOrderId);
        return orderItem.getQuantity();
    }

    private long getStandardPrice(long productId, long salesOrderId) throws SQLException {
        OrderItem orderItem = getOrderItem(productId, salesOrderId);
        return orderItem.getStandardPrice();
    }

    private OrderItem getOrderItem(long productId, long salesOrderId) throws SQLException {
        return DAOFactory.getDAOFactory().getOrderItemsDAO().getOrderItem(productId, salesOrderId);
    }

    private List<SalesOrder> getSalesOrder(long orderStatusId, long userId) {
        List<SalesOrder> salesOrders = DAOFactory.getDAOFactory().getSalesOrderDAO().getSalesOrderByOrderStatusId(orderStatusId, userId);
        if (Objects.isNull(salesOrders)) {
            return null;
        } else {
            return salesOrders;
        }
    }

    private List<SalesOrder> getSalesOrderByUserId(long userId) {
        return DAOFactory.getDAOFactory().getSalesOrderDAO().getSalesOrderByOrderUserId(userId);
    }

    private List<SalesOrder> getSalesOrder(long orderStatusId) {
        return DAOFactory.getDAOFactory().getSalesOrderDAO().getSalesOrderByOrderStatusId(orderStatusId);
    }

    private String getStatusName(long orderStatusId) {
        OrderStatus orderStatus = DAOFactory.getDAOFactory().getOrderStatusDAO().getOrdersStatusById(orderStatusId);
        return orderStatus.getName();
    }
}
