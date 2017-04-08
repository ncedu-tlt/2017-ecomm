<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/shoppingCartIcon"/>
<div class="ui item jsShoppingCartIconComponent" id="shoppingCartTopPanel">
    <a href="${pageContext.request.contextPath}/cart" class="circular ui icon button">
        <i class="icon shop"></i>
        <c:set var="quantity" scope="session" value="${requestScope.quantityProducts}"/>
        <div class="floating ui circular teal label jsShoppingCartIcon ${quantity == 0 ? 'hidden': ''}">
            ${requestScope.quantityProducts}
        </div>
    </a>
    <div class="ui page dimmer jsDimmerAdd">
        <div class="content">
            <div class="ui text loader">Adding goods</div>
        </div>
    </div>
</div>
<script>
    window.frm.components.init('ShoppingCartIconComponent', '.jsShoppingCartIconComponent', {
        addToCartUrl: '${requestScope.addToCartURL}',
        loginUrl: '${requestScope.loginURL}'
    });
</script>



