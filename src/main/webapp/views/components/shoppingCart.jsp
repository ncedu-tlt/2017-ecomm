<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="salesOrder" scope="page" value="${requestScope.salesOrder}"/>
<div class="ui container jsShoppingCartComponent main-content">
    <h3 class="ui center aligned header">
        Your Cart
    </h3>
    <div class="ui divided items">
        <c:choose>
            <c:when test="${salesOrder == null || salesOrder.getOrderItems().isEmpty()}">
                <div class="ui icon message hide">
                    <i class="in cart icon"></i>
                    <div class="content">
                        <div class="header">
                            Cart is Empty
                        </div>
                        <p>You needs, add to cart any product</p>
                    </div>
                </div>
            </c:when>
            <c:when test="${salesOrder.getOrderItems() != null}">
                <div class="ui divided items jsItemOrder">
                    <c:forEach var="itemOrder" items="${salesOrder.getOrderItems()}">
                        <div class="item">
                            <div class="ui small image">
                                <img src="${pageContext.request.contextPath}${itemOrder.getImgUrl()}">
                            </div>
                            <div class="middle aligned content">
                                <div class="header">
                                    <a href="${pageContext.request.contextPath}/product?product_id=${itemOrder.getProductId()}">
                                            ${itemOrder.getName()}
                                    </a>
                                </div>
                                <div class="ui container">
                                    <p>Quantity:</p>
                                </div>
                                <div class="description">
                                    <form method="post" action="cart" class="ui input">
                                        <input class="jsProductId" name="productId" type="hidden"
                                               value="${itemOrder.getProductId()}">
                                        <input class="jsSalesOrderId" name="salesOrderId" type="hidden"
                                               value="${itemOrder.getSalesOrderId()}">
                                        <input class="jsStandardPrice" name="standardPrice" type="hidden"
                                               value="${itemOrder.getStandardPrice()}">
                                        <input name="submitButton" type="hidden" value="quantity">
                                        <button class="ui left attached button jsLeft" type="button">-</button>
                                        <input type="number" name="quantityValue" class="jsInput"
                                               value="${itemOrder.getQuantity()}">
                                        <button class="ui right attached button jsRight" type="button">+</button>
                                    </form>
                                    <form method="post" action="cart"
                                          class="ui right floated center middle aligned">
                                        <input name="productId" type="hidden" value="${itemOrder.getProductId()}">
                                        <input name="salesOrderId" type="hidden"
                                               value="${itemOrder.getSalesOrderId()}">
                                        <button class="circular right floated ui icon button middle aligned"
                                                name="submitButton" type="submit" value="delete">
                                            <i class="icon remove"></i>
                                        </button>
                                        <h2 class="ui header right floated center middle aligned jsPrice">
                                                ${itemOrder.getPrice()}
                                        </h2>
                                        <h2 class="ui header right floated center middle aligned">$</h2>
                                    </form>
                                </div>
                            </div>
                            <div class="ui divider"></div>
                        </div>
                    </c:forEach>
                </div>
                <div class="ui section divider"></div>
                <form method="post" action="cart" class="ui grid seven column row">
                    <button class="ui secondary basic right floated button column hide" name="submitButton"
                            type="submit" value="emptyCart">EMPTY CART
                    </button>
                </form>
                <div class="ui grid">
                    <form method="post" action="cart" class="eight wide column">
                        <input name="salesOrderId" type="hidden" value="${salesOrder.getSalesOrderId()}">
                        <div class="item">
                            <div class="inline field hide">
                                <div class="ui right pointing label big">
                                    Limit:
                                </div>
                                <div class="ui left labeled button" tabindex="0">
                                    <input type="number" class="ui basic right pointing label" name="limitInput"
                                           value="${salesOrder.getLimit()}">
                                    <button class="ui button" name="submitButton" type="submit" value="apply">APPLY
                                    </button>
                                </div>
                            </div>
                        </div>
                        <c:if test="${salesOrder.getLimit() < salesOrder.getTotalAmount() && salesOrder.getLimit() > 0
                            && salesOrder.getLimit() != 0.00}">
                            <div class="ui message warning hide">
                                <i class="close icon"></i>
                                <div class="header">
                                    <p>Total price, exceeds limit value!</p>
                                </div>
                            </div>
                        </c:if>
                    </form>
                    <div class="eight wide column">
                        <h3 class="ui grey header right floated bottom jsAmount">
                                ${salesOrder.getTotalAmount()}</h3>
                        <h3 class="ui header right floated bottom">Total: $</h3>
                    </div>
                </div>
                <div class="ui grid hide">
                    <div class="eight wide column">
                        <a href='javascript:window.print(); void 0;'>
                            <button class="ui secondary basic button" type="button">PRINT</button>
                        </a>
                    </div>
                    <form method="post" action="cart" class="eight wide column">
                        <button class="ui secondary basic right floated button" name="submitButton" value="checkout">
                            CHECKOUT
                        </button>
                    </form>
                </div>
            </c:when>
        </c:choose>
    </div>
</div>

<script>
    window.frm.components.init('ShoppingCartComponent', '.jsShoppingCartComponent', {
        shoppingCartUrl: '${pageContext.request.contextPath}' + '/cart'
    });
</script>