<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/shoppingCartIcon"/>
<div class="ui item jsShoppingCartIconComponent jsShoppingCartComponent" id="shoppingCartTopPanel">
    <button onclick="window.location.href = '${pageContext.request.contextPath}/cart'" class="circular ui icon button">
        <i class="icon shop"></i>
            <div class="floating ui circular teal label jsShoppingCartIcon" id="shoppingCartIcon">
                <c:if test="${requestScope.quantityProducts > 0}">
                    ${requestScope.quantityProducts}
                </c:if>
            </div>
    </button>
    <div class="ui page dimmer jsDimmerAdd">
        <div class="content">
            <div class="ui text loader">Adding goods</div>
        </div>
    </div>
</div>
<script>
    window.frm.components.init('ShoppingCartIconComponent', '.jsShoppingCartIconComponent', {
        baseIconUrl: '${pageContext.request.contextPath}'
    });
</script>



