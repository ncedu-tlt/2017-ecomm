package ru.ncedu.ecomm.servlets.services;

import ru.ncedu.ecomm.data.DAOFactory;
import ru.ncedu.ecomm.data.models.OrderItem;
import ru.ncedu.ecomm.data.models.SalesOrder;
import ru.ncedu.ecomm.servlets.models.OrderItemViewModel;
import ru.ncedu.ecomm.servlets.models.ProductViewModel;
import ru.ncedu.ecomm.servlets.models.SalesOrderViewModel;
import ru.ncedu.ecomm.servlets.models.builders.OrderItemViewBuilder;
import ru.ncedu.ecomm.servlets.models.builders.SalesOrderViewBuilder;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
        if (searchSalesOrderByUserId(userId)) {
            addProductToOrderItem(productId, userId);
        } else {
            createSalesOrder(userId);
            addProductToOrderItem(productId, userId);
        }
    }

    public long getSalesOrderId(long userId) {
        SalesOrder salesOrder = getDAOFactory().getSalesOrderDAO().getSalesOrderByUserId(userId);
        return salesOrder.getSalesOrderId();
    }

    private boolean searchSalesOrderByUserId(long userId) {
        SalesOrder salesOrder = getDAOFactory().getSalesOrderDAO().getSalesOrderByUserId(userId);
        return salesOrder != null;
    }

    private void addProductToOrderItem(long productId, long userId) throws SQLException {
        long salesOrderId = getSalesOrderId(userId);
        if (isProductAtOrderItem(productId)) {
            incrementQuantityOrderItem(productId, salesOrderId);
        } else {
            addNewOrderItem(productId, salesOrderId);
        }
    }

    private boolean isProductAtOrderItem(long productId) {
        return getDAOFactory().getOrderItemsDAO().isHaveProductId(productId);
    }


    private void incrementQuantityOrderItem(long productId, long salesOrderId) throws SQLException {
        OrderItem orderItem = getDAOFactory().getOrderItemsDAO()
                .getOrderItemByUserConfig(productId, salesOrderId);
        int quantity = orderItem.getQuantity() + 1;
        orderItem.setQuantity(quantity);
        getDAOFactory().getOrderItemsDAO().updateOrderItem(orderItem);
    }

    private void addNewOrderItem(long productId, long salesOrderId) {
        OrderItem orderItem = addToOrderItem(productId, salesOrderId);
        getDAOFactory().getOrderItemsDAO().addOrderItem(orderItem);
    }

    private OrderItem addToOrderItem(long productId, long salesOrderId) {
        int minQuantity = 1;

        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(productId);
        orderItem.setSalesOrderId(salesOrderId);
        orderItem.setQuantity(minQuantity);

        return orderItem;
    }

    private void createSalesOrder(long userId) {
        SalesOrder salesOrder = addToSalesOrder(userId);
        getDAOFactory().getSalesOrderDAO().addSalesOrder(salesOrder);
    }

    private SalesOrder addToSalesOrder(long userId) {
        SalesOrder saleOrder = new SalesOrder();
        Date creationDate = new Date(System.currentTimeMillis());
        BigDecimal limit = new BigDecimal("50000.00");
        int EnteringOrderStatus = 1;

        saleOrder.setUserId(userId);
        saleOrder.setCreationDate(creationDate);
        saleOrder.setLimit(limit);
        saleOrder.setOrderStatusId(EnteringOrderStatus);

        return saleOrder;
    }

    public List<SalesOrderViewModel> getSalesOrderModel(long orderStatusId, long userId) throws SQLException {
        List<SalesOrderViewModel> salesOrderViewModels = new ArrayList<>();
        List<SalesOrder> salesOrders = getSalesOrder(orderStatusId, userId);
        for (SalesOrder salesOrder : salesOrders) {
            SalesOrderViewModel salesOrderViewModel = new SalesOrderViewBuilder()
                    .setSalesOrderId(salesOrder.getSalesOrderId())
                    .setCreationDate(salesOrder.getCreationDate())
                    .setLimit(salesOrder.getLimit())
                    .setTotalAmount(totalAmount(salesOrder.getSalesOrderId()))
                    .setOrderItems(incrementAmountInOrderItem(salesOrder.getSalesOrderId()))
                    .build();
            salesOrderViewModels.add(salesOrderViewModel);
        }
        return salesOrderViewModels;
    }

    private List<OrderItemViewModel> getOrderItemModel(long salesOrderId) throws SQLException {
        List<OrderItemViewModel> orderItemsView = new ArrayList<>();
        List<ProductViewModel> products = ProductViewService.getInstance().getProductModelByOrderId(salesOrderId);
        for (ProductViewModel product : products) {
            OrderItemViewModel orderItemViewModel = new OrderItemViewBuilder()
                    .setProductId(product.getId())
                    .setQuantity(getQuantity(product.getId(), salesOrderId))
                    .setName(product.getName())
                    .setPrice(product.getPrice())
                    .setImgUrl(product.getImageUrl())
                    .build();
            orderItemsView.add(orderItemViewModel);
        }

        return orderItemsView;
    }

    private List<OrderItemViewModel> incrementAmountInOrderItem(long salesOrderId) throws SQLException {
        List<OrderItemViewModel> orderItemViewModels = getOrderItemModel(salesOrderId);
        for (OrderItemViewModel model : orderItemViewModels) {
            long amount = model.getPrice();
            long quantity = model.getQuantity();
            amount *= quantity;
            model.setPrice(amount);
        }
        return orderItemViewModels;
    }

    private long totalAmount(long salesOrderId) throws SQLException {
        long sumAllPrice = 0;
        List<Long> priceList = new ArrayList<>();
        List<OrderItemViewModel> orderItemViewModels = incrementAmountInOrderItem(salesOrderId);
        priceList.addAll(orderItemViewModels.stream().map(OrderItemViewModel::getPrice).collect(Collectors.toList()));
        for (Long sum : priceList) {
            sumAllPrice += sum;
        }
        return sumAllPrice;
    }

    private int getQuantity(long productId, long salesOrderId) throws SQLException {
        int quantity;
        OrderItem orderItem = getOrderItemByUserConfig(productId, salesOrderId);
        quantity = orderItem.getQuantity();
        return quantity;
    }

    private OrderItem getOrderItemByUserConfig(long productId, long salesOrderId) throws SQLException {
        return DAOFactory.getDAOFactory().getOrderItemsDAO().getOrderItemByUserConfig(productId, salesOrderId);
    }

    private List<SalesOrder> getSalesOrder(long orderStatusId, long userId) {
        return DAOFactory.getDAOFactory().getSalesOrderDAO().getSalesOrderByOrderStatusId(orderStatusId, userId);
    }


}
