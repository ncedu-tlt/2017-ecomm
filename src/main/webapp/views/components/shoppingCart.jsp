<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="showSalesOrderList" scope="page" value="${requestScope.showSalesOrderList}"/>
<c:forEach var="salesOrder" items="${showSalesOrderList}">
<div class="ui container jsShoppingCardComponent main-content">
    <h3 class="ui center aligned header">
        Your Cart
    </h3>
    <div class="ui divided items">
            <div class="ui divided items">
                <c:forEach var="itemOrder" items="${salesOrder.getOrderItems()}">
                    <div class="item">
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
                                <button class="circular right floated ui icon button middle aligned ">
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
    </div>
    <div class="ui section divider"></div>
        <div class="ui grid">
            <div class="eight wide column">
                <div class="inline field">
                    <div class="ui right pointing label big">
                        Limit:
                    </div>
                    <div class="ui left labeled button" tabindex="0">
                        <input type="text" class="ui basic right pointing label" name="limitInput" value=""/>
                        <button class="ui  button" name="limitButton">APPLY</button>
                    </div>
                </div>
            </div>
            <div class="eight wide column">
                <h3 class="ui grey header right floated bottom">$${salesOrder.getTotalAmount()}</h3>
                <h3 class="ui header right floated bottom">
                    Total:
                </h3>
            </div>
        </div>
    <div class="ui grid">
        <div class="eight wide column">
            <button class="ui secondary basic button">PRINT</button>
        </div>
        <div class="eight wide column">
            <button class="ui secondary basic right floated button">CHECKOUT</button>
        </div>
    </div>
</div>
</c:forEach>
<script>
    window.frm.components.init('ShoppingCardComponent', '.jsShoppingCardComponent');
</script>