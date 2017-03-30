<%--
    Used fragments:
    - head
    - topPanel

    Used components:
    - shoppingCart
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
<div class="jsIndexMainWrapper">
    <c:import url="../fragments/topPanel.jsp"/>

    <c:import url="../components/shoppingCart.jsp"/>

    <c:import url="../components/companyInfo.jsp"/>
</div>
</body>
</html>