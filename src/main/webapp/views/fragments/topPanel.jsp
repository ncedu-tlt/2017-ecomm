<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Used components:
    - categories
    - search
    - profileIcon
    - shoppingCartIcon
--%>

<div class="ui menu main-content">
    <div class="ui container">
        <a class="header item" href="<c:url value="/home"/>">Shop</a>
        <c:import url="../components/categories.jsp"/>
        <div class="ui item right">
            <c:import url="../components/search.jsp"/>
            <c:import url="../components/profileIcon.jsp"/>
            <c:import url="../components/shoppingCartIcon.jsp"/>
        </div>
    </div>
</div>