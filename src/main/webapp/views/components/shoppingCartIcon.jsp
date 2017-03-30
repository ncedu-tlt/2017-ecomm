<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/shoppingCartIcon"/>
<div class="ui item jsShoppingCartIconComponent jsShoppingCartComponent" id="shoppingCartTopPanel">
    <a href="${pageContext.request.contextPath}/cart" class="circular ui icon button">
        <i class="icon shop"></i>
        <div class="floating ui circular teal label jsShoppingCartIcon hidden">
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



