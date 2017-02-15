<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/shoppingCartIcon"/>
<div class="ui item jsShoppingCartIcon jsShoppingCartComponent" id="shoppingCartTopPanel">
    <button onclick="window.location.href = '${pageContext.request.contextPath}/cart'" class="circular ui icon button"
            id="shoppingCartIcon">
        <i class="icon shop"></i>
        <c:if test="${requestScope.quantityProducts > 0}">
            <div class="floating ui circular teal label" id="quantityProducts">
                    ${requestScope.quantityProducts}
            </div>
        </c:if>
    </button>
</div>



