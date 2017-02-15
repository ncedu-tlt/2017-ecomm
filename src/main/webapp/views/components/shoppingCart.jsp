<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style media='print' type='text/css'>
    .menu {display: none; height: 0px; visibility: hidden;}
    .message {display: none; height: 0px; visibility: hidden;}
    .ui.secondary.basic.right.floated.button.column {display: none; height: 0px; visibility: hidden;}
    .inline.field {display: none; height: 0px; visibility: hidden;}
    .noPrint {display: none; width: auto;}
</style>
<c:set var="salesOrderList" scope="page" value="${requestScope.salesOrderList}"/>
<c:forEach var="salesOrder" items="${salesOrderList}">
    <div class="ui container jsShoppingCardComponent main-content">
        <span class="noPrint">
            <h3 class="ui center aligned header">
                Your Cart
            </h3>
        </span>
        <div class="ui divided items">
            <c:choose>
                <c:when test="${salesOrder.getOrderItems().isEmpty()}">
                    <div class="ui icon message">
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
                    <div class="ui divided items">
                        <c:forEach var="itemOrder" items="${salesOrder.getOrderItems()}">
                            <form method="post" action="cart" class="item">
                                <input name="productId" type="hidden" value="${itemOrder.getProductId()}">
                                <input name="salesOrderId" type="hidden" value="${salesOrder.getSalesOrderId()}">
                                <input name="cartActions" type="hidden" value="delete">
                                <div class="ui small image">
                                    <img src="${itemOrder.getImgUrl()}">
                                </div>
                                <div class="middle aligned content">
                                    <div class="header">
                                            ${itemOrder.getName()}
                                    </div>
                                    <div class="description">
                                        <div class="ui dropdown jsComponentDropdown">
                                            Quantity: ${itemOrder.getQuantity()}
                                            <i class="dropdown icon"></i>
                                            <div class="menu">
                                                <div class="item" data-value="1">Choice 1</div>
                                                <div class="item" data-value="2">Choice 2</div>
                                                <div class="item" data-value="3">Choice 3</div>
                                            </div>
                                        </div>
                                        <button class="circular right floated ui icon button middle aligned"
                                                name="deleteButton">
                                            <i class="icon remove"></i>
                                        </button>
                                        <h2 class="ui header right floated center middle aligned">
                                            $${itemOrder.getPrice()}
                                        </h2>
                                    </div>
                                </div>
                                <div class="ui divider"></div>
                            </form>
                        </c:forEach>
                    </div>
                    <div class="ui section divider"></div>
                        <form method="post" action="cart" class="ui grid seven column row">
                            <input name="emptyActions" type="hidden" value="emptyTrash">
                            <button class="ui secondary basic right floated button column">EMPTY TRASH</button>
                        </form>
                    <div class="ui grid">
                        <div class="eight wide column">
                            <form method="post" action="cart" class="item">
                                <input name="salesOrderId" type="hidden" value="${salesOrder.getSalesOrderId()}">
                                <div class="inline field">
                                    <div class="ui right pointing label big">
                                        Limit:
                                    </div>
                                    <div class="ui left labeled button" tabindex="0">
                                        <input type="text" class="ui basic right pointing label" name="limitInput"
                                               value="${salesOrder.getLimit()}"/>
                                        <button class="ui button" name="limitButton">APPLY</button>
                                    </div>
                                </div>
                            </form>
                            <c:if test="${salesOrder.getLimit() < salesOrder.getTotalAmount()}">
                                    <div class="ui message warning">
                                        <i class="close icon"></i>
                                        <div class="header">
                                            <p>Total price, exceeds limit value!</p>
                                        </div>
                                    </div>
                            </c:if>
                        </div>
                        <div class="eight wide column">
                            <h3 class="ui grey header right floated bottom">$${salesOrder.getTotalAmount()}</h3>
                            <h3 class="ui header right floated bottom">
                                Total:
                            </h3>
                        </div>
                    </div>
                    <span class="noPrint">
                        <div class="ui grid">
                            <div class="eight wide column">
                                <a href='javascript:window.print(); void 0;'>
                                    <button class="ui secondary basic button">PRINT</button>
                                </a>
                            </div>
                            <div class="eight wide column">
                                <button class="ui secondary basic right floated button">CHECKOUT</button>
                            </div>
                        </div>
                    </span>
                </c:when>
            </c:choose>
        </div>
    </div>
</c:forEach>
<script>
    window.frm.components.init('ShoppingCardComponent', '.jsShoppingCardComponent');
</script>