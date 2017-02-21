<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <style media='print' type='text/css'>
        .menu {
            display: none;
            height: 0px;
            visibility: hidden;
        }

        .hide {
            display: none;
            height: 0px;
            visibility: hidden;
        }
    </style>
</head>
<c:set var="salesOrderList" scope="page" value="${requestScope.salesOrderList}"/>
<c:set var="request" scope="session" value="${requestScope.exception}"/>
<c:forEach var="salesOrder" items="${salesOrderList}">
    <div class="ui container jsShoppingCartComponent main-content">
        <h3 class="ui center aligned header">
            Your Cart
        </h3>
        <form method="post" action="cart" class="ui divided items">
            <input name="salesOrderId" type="hidden" value="${salesOrder.getSalesOrderId()}">
            <c:choose>
                <c:when test="${salesOrder.getOrderItems().isEmpty()}">
                    <div class="ui icon message hide">
                        <i class="trash icon"></i>
                        <div class="content">
                            <div class="header">
                                Trash is Empty
                            </div>
                            <p>You needs, add to cart any product</p>
                        </div>
                    </div>
                </c:when>
                <c:when test="${salesOrder.getOrderItems() != null}">
                    <div class="ui divided items jsItemOrder">
                        <c:forEach var="itemOrder" items="${salesOrder.getOrderItems()}">
                            <div class="item">
                                <input name="productId" type="hidden" value="${itemOrder.getProductId()}">
                                <div class="ui small image">
                                    <img src="${itemOrder.getImgUrl()}">
                                </div>
                                <div class="middle aligned content">
                                    <div class="header">
                                            ${itemOrder.getName()}
                                    </div>
                                    <div class="ui container">
                                        <p>Quantity:</p>
                                    </div>
                                    <div class="description">
                                        <div class="ui input">
                                            <button class="ui left attached button jsLeft" type="button">-</button>
                                            <input type="text" name="quantityValue" class="jsInput"
                                                   value="${itemOrder.getQuantity()}">
                                            <button class="ui right attached button jsRight" type="button">+</button>
                                            <button class="ui secondary button" name="quantityButton" value="quantity">
                                                Save
                                            </button>
                                        </div>
                                        <button class="circular right floated ui icon button middle aligned"
                                                name="deleteButton" value="delete">
                                            <i class="icon remove"></i>
                                        </button>
                                        <h2 class="ui header right floated center middle aligned">
                                            $${itemOrder.getPrice()}
                                        </h2>
                                    </div>
                                </div>
                                <div class="ui divider"></div>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="ui section divider"></div>
                    <div class="ui grid seven column row">
                        <button class="ui secondary basic right floated button column hide" name="emptyActions"
                                value="emptyTrash">EMPTY TRASH
                        </button>
                    </div>
                    <div class="ui grid">
                        <div class="eight wide column">
                            <div class="item">
                                <div class="inline field hide">
                                    <div class="ui right pointing label big">
                                        Limit:
                                    </div>
                                    <div class="ui left labeled button" tabindex="0">
                                        <input type="text" class="ui basic right pointing label" name="limitInput"
                                               value="${salesOrder.getLimit()}"/>
                                        <button class="ui button" name="limitButton" value="apply">APPLY</button>
                                    </div>
                                </div>
                            </div>
                            <c:if test="${salesOrder.getLimit() < salesOrder.getTotalAmount() && salesOrder.getLimit() > 0}">
                                <div class="ui message warning hide">
                                    <i class="close icon"></i>
                                    <div class="header">
                                        <p>Total price, exceeds limit value!</p>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${request != null}">
                                <div class="ui message warning hide">
                                    <p>${requestScope.exception}</p>
                                </div>
                            </c:if>
                        </div>
                        <div class="eight wide column">
                            <h3 class="ui grey header right floated bottom jsAmount">
                                $${salesOrder.getTotalAmount()}</h3>
                            <h3 class="ui header right floated bottom">
                                Total:
                            </h3>
                        </div>
                    </div>
                        <div class="ui grid hide">
                            <div class="eight wide column">
                                <a href='javascript:window.print(); void 0;'>
                                    <button class="ui secondary basic button" type="button">PRINT</button>
                                </a>
                            </div>
                            <div class="eight wide column">
                                <button class="ui secondary basic right floated button">CHECKOUT</button>
                            </div>
                        </div>
                </c:when>
            </c:choose>
        </form>
    </div>
</c:forEach>
<script>
    window.frm.components.init('ShoppingCartComponent', '.jsShoppingCartComponent');
</script>