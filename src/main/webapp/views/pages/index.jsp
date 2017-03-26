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

    <div class="ui container main-content">
        <div class="ui vertical pointing menu">
            <a class="item" href="<c:url value="/orders"/>">ordersHistory</a>
            <a class="item" href="<c:url value="/properties"/>">properties</a>
            <a class="item" href="<c:url value="/users"/>">users</a>
        </div>
    </div>


    <c:import url="../components/productList.jsp"/>
    <c:import url="../components/companyInfo.jsp"/>

</body>
</html>

