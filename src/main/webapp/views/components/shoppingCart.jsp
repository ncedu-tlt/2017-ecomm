<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="salesOrder" scope="page" value="${requestScope.salesOrder}"/>
<div class="ui container jsShoppingCartComponent main-content">
    <h3 class="ui center aligned header">
        Your Cart
    </h3>
    <div class="ui divided items">
        <c:choose>
            <c:when test="${salesOrder == null || salesOrder.getOrderItems().isEmpty() ||
            salesOrder.getOrderItems() == null}">
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
                                    <form class="ui input jsInputClass">
                                        <input class="jsProductId" name="productId" type="hidden"
                                               value="${itemOrder.getProductId()}">
                                        <input class="jsSalesOrderId" name="salesOrderId" type="hidden"
                                               value="${itemOrder.getSalesOrderId()}">
                                        <input class="jsStandardPrice" name="standardPrice" type="hidden"
                                               value="${itemOrder.getStandardPrice()}">
                                        <button class="ui left attached button jsLeft" type="button">-</button>
                                        <input type="number" min="1" name="quantityValue" class="jsInput"
                                               value="${itemOrder.getQuantity()}">
                                        <button class="ui right attached button jsRight" type="button">+</button>
                                    </form>
                                    <form method="post" action="cart"
                                          class="ui right floated center middle aligned">
                                        <input name="productId" type="hidden" value="${itemOrder.getProductId()}">
                                        <input name="salesOrderId" type="hidden"
                                               value="${itemOrder.getSalesOrderId()}">
                                        <button class="circular right floated ui icon button middle aligned"
                                                name="action" type="submit" value="delete">
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
                    <button class="ui secondary basic right floated button column hide" name="action"
                            type="submit" value="emptyCart">EMPTY CART
                    </button>
                </form>
                <div class="ui grid">
                    <form class="eight wide column jsLimitInputClass">
                        <div class="item">
                            <div class="inline field hide">
                                <div class="ui right pointing label big">
                                    Limit:
                                </div>
                                <div class="ui left labeled button" tabindex="0">
                                    <input type="number" min="0" class="ui basic right pointing label jsLimitInput"
                                           name="limitInput"
                                           value="${salesOrder.getLimit()}">
                                </div>
                            </div>
                        </div>
                        <div class="ui message warning jsExceedingTheLimit"
                            ${salesOrder.getLimit() > salesOrder.getTotalAmount() ? 'hidden': '' ||
                                    salesOrder.getLimit() == null ? 'hidden': ''}>
                            <i class="close icon"></i>
                            <div class="header">
                                <p>Total price, exceeds limit value!</p>
                            </div>
                        </div>
                    </form>
                    <div class="eight wide column">
                        <h3 class="ui grey header right floated bottom jsAmount">
                                ${salesOrder.getTotalAmount()}</h3>
                        <h3 class="ui header right floated bottom">Total: $</h3>
                    </div>
                </div>
                <div class="ui grid hide">
                    <div class="eight wide column jsPrint">
                        <button class="ui secondary basic button" type="button">PRINT</button>
                    </div>
                    <form method="post" action="cart" class="eight wide column">
                        <button class="ui secondary basic right floated button" name="action" value="checkout">
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
        shoppingCartUrl: '${requestScope.shoppingCartUrl}'
    });
</script>