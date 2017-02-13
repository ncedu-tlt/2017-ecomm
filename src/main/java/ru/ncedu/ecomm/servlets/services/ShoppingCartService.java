package ru.ncedu.ecomm.servlets.services;

import ru.ncedu.ecomm.data.DAOFactory;
import ru.ncedu.ecomm.data.models.OrderItem;
import ru.ncedu.ecomm.data.models.SalesOrder;
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

//TODO: слишком много запросов к БД, можно оптимизировать
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
            salesOrderId = getSalesOrderId(userId);
        }
        addProductToOrderItem(productId, salesOrderId);
    }

    public Long getSalesOrderId(long userId) throws SQLException {
        List<SalesOrderViewModel> salesOrders = getSalesOrderModelList(EnumOrderStatus.ENTERING.getStatus(), userId);
        for (SalesOrderViewModel salesOrder : salesOrders) {
            if (salesOrder.getUserId() == userId) {
                return salesOrder.getSalesOrderId();
            }
        }
        return null;
    }

    private void addProductToOrderItem(long productId, Long salesOrderId) throws SQLException {
        List<OrderItemViewModel> orderItems = getOrderItemModelList(salesOrderId);
        OrderItemViewModel orderItemBySalesOrderId = getOrderItemBySalesOrderId(productId, salesOrderId, orderItems);
        if (orderItemBySalesOrderId == null) {
            addNewOrderItem(productId, salesOrderId);
        } else {
            try {
                incrementQuantityOrderItem(orderItemBySalesOrderId);
            } catch (NullPointerException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private OrderItemViewModel getOrderItemBySalesOrderId(long productId, long salesOrderId, List<OrderItemViewModel> orderItems) {
        for (OrderItemViewModel orderItem : orderItems) {
            if (orderItem.getProductId() == productId
                    && orderItem.getSalesOrderId() == salesOrderId) {
                return orderItem;
            }
        }
        return null;
    }

    private void addNewOrderItem(long productId, long salesOrderId) {
        OrderItem orderItem = addToOrderItem(productId, salesOrderId);
        getDAOFactory().getOrderItemsDAO().addOrderItem(orderItem);
    }

    private OrderItemViewModel incrementQuantityOrderItem(OrderItemViewModel orderItemBySalesOrderId) throws SQLException {
        int quantity = orderItemBySalesOrderId.getQuantity() + 1;
        OrderItem orderItem = getOrderItemByViewModel(orderItemBySalesOrderId);
        orderItem.setQuantity(quantity);
        getDAOFactory().getOrderItemsDAO().updateOrderItem(orderItem);
        return orderItemBySalesOrderId;
    }

    private OrderItem getOrderItemByViewModel(OrderItemViewModel orderItemBySalesOrderId) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(orderItemBySalesOrderId.getProductId());
        orderItem.setSalesOrderId(orderItemBySalesOrderId.getSalesOrderId());

        return orderItem;
    }

    private OrderItem addToOrderItem(long productId, long salesOrderId) {
        final int minQuantity = 1;

        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(productId);
        orderItem.setSalesOrderId(salesOrderId);
        orderItem.setQuantity(minQuantity);

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
                    .setTotalAmount(totalAmountSumAllPriceInOrderItemViewModelList(salesOrder.getSalesOrderId()))
                    .setOrderItems(relationPriceAndQuantityInOrderItemViewModelList(salesOrder.getSalesOrderId()))
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
                    .setName(product.getName())
                    .setPrice(product.getPrice())
                    .setImgUrl(product.getImageUrl())
                    .setDiscount(product.getDiscount())
                    .build();
            orderItemsView.add(orderItemViewModel);
        }

        return orderItemsView;
    }

    private List<OrderItemViewModel> relationPriceAndQuantityInOrderItemViewModelList(long salesOrderId) throws SQLException {
        List<OrderItemViewModel> orderItemViewModels = getOrderItemModelList(salesOrderId);
        for (OrderItemViewModel model : orderItemViewModels) {
            long amount = model.getPrice();
            if (model.getDiscount() != 0) {
                amount = model.getDiscount() * model.getQuantity();
            } else {
                amount *= model.getQuantity();
            }
            model.setPrice(amount);
        }
        return orderItemViewModels;
    }

    private long totalAmountSumAllPriceInOrderItemViewModelList(long salesOrderId) throws SQLException {
        long sumAllPrice = 0;
        List<Long> priceList = new ArrayList<>();
        List<OrderItemViewModel> orderItemViewModels = relationPriceAndQuantityInOrderItemViewModelList(salesOrderId);
        priceList.addAll(orderItemViewModels.stream().map(OrderItemViewModel::getPrice).collect(Collectors.toList()));
        for (Long sum : priceList) {
            sumAllPrice += sum;
        }
        setTotalPriceInDatabase(salesOrderId, sumAllPrice);
        return sumAllPrice;
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

    private void setTotalPriceInDatabase(long salesOrderId, long totalPrice) throws SQLException {
        SalesOrder salesOrder = getDAOFactory().getSalesOrderDAO().getSalesOrderById(salesOrderId);
        salesOrder.setTotalPrice(totalPrice);
        getDAOFactory().getSalesOrderDAO().updateSalesOrder(salesOrder);
    }

    private int getQuantity(long productId, long salesOrderId) throws SQLException {
        OrderItem orderItem = getOrderItem(productId, salesOrderId);
        return orderItem.getQuantity();

    }

    private OrderItem getOrderItem(long productId, long salesOrderId) throws SQLException {
        return DAOFactory.getDAOFactory().getOrderItemsDAO().getOrderItem(productId, salesOrderId);
    }

    private List<SalesOrder> getSalesOrder(long orderStatusId, long userId) {
        return DAOFactory.getDAOFactory().getSalesOrderDAO().getSalesOrderByOrderStatusId(orderStatusId, userId);
    }


}
