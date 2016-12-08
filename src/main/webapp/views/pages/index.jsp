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

    <h1>Pages:</h1>
    <a href="<c:url value="/cart"/>">cart</a><br>
    <a href="<c:url value="/category"/>">category</a><br>
    <a href="<c:url value="/login"/>">login</a><br>
    <a href="<c:url value="/orders"/>">ordersHistory</a><br>
    <a href="<c:url value="/recovery"/>">passwordRecovery</a><br>
    <a href="<c:url value="/product"/>">product</a><br>
    <a href="<c:url value="/profile"/>">profile</a><br>
    <a href="<c:url value="/registration"/>">registration</a><br>
    <a href="<c:url value="/users"/>">users</a><br>

    <c:import url="../components/etalon.jsp">
        <c:param name="showCheckbox" value="true"/>
    </c:import>

    <c:import url="../components/companyInfo.jsp"/>

</body>
</html>