<%--
    Used fragments:
    - head
    - topPanel

    Used components:
    - productList
    - companyInfo
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Shop</title>
    <c:import url="../fragments/head.jsp"/>
</head>
<body>

    <c:import url="../fragments/topPanel.jsp"/>

    <c:import url="../components/productList.jsp"/>


    <div class="ui container main-content">
        <h1 class="ui header">Pages:</h1>
        <div class="ui vertical pointing menu">
            <a class="item" href="<c:url value="/cart"/>">cart</a>
            <a class="item" href="<c:url value="/category?category_id=4"/>">category</a>
            <a class="item" href="<c:url value="/login"/>">login</a>
            <a class="item" href="<c:url value="/orders"/>">ordersHistory</a>
            <a class="item" href="<c:url value="/recovery"/>">passwordRecovery</a>
            <a class="item" href="<c:url value="/product?product_id=5"/>">product</a>
            <a class="item" href="<c:url value="/profile?user_id=6"/>">profile</a>
            <a class="item" href="<c:url value="/registration"/>">registration</a>
            <a class="item" href="<c:url value="/users"/>">users</a>
        </div>
    </div>

    <c:import url="../components/companyInfo.jsp"/>
</body>
</html>