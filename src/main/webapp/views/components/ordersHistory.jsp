<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="orderHistory" scope="page" value="${requestScope.orderHistory}"/>
<div class="ui center aligned text container segment active main-content jsOrdersHistoryComponent"
     id="orderHistoryBlock" data-tab="orderHistory">
    <div class="ui items orders">
        <c:forEach var="orders" items="${orderHistory}">
            <div class="item order">
                <div class="content">
                    <div class="ui horizontal divider">
                        Order #${orders.getSalesOrderId()}
                    </div>
                    <h3 class="ui teal tag label right floated header">
                            ${orders.getCreationDate()}
                    </h3>
                    <div class="ui divided items">
                        <c:forEach var="orderItems" items="${orders.getOrderItems()}">
                            <div class="item">
                                <div class="ui small image">
                                    <img src="${orderItems.getImgUrl()}">
                                </div>
                                <div class="middle aligned content">
                                    <div class="left floated header">
                                            ${orderItems.getName()}
                                    </div>
                                    <div class="extra">
                                        <div class="ui right floated header">
                                                ${orderItems.getPrice()}
                                        </div>
                                    </div>
                                    <div class="left floated description">

                                        <p>Quantity: ${orderItems.getQuantity()}</p>
                                    </div>
                                </div>
                                <div class="ui section divider"></div>
                            </div>
                        </c:forEach>

                    </div>
                    <h2 class="ui horizontal divider blue header">
                        <i class="bar chart icon"></i>

                        Total: ${orders.getTotalAmount()}
                    </h2>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<script type="text/javascript">
    window.frm.components.init('DashboardComponent', '.jsDashboardComponent');
</script>