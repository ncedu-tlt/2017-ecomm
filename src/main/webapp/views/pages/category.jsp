<%--
    Used fragments:
    - head
    - topPanel

    Used components:
    - breadcrumbs
    - filtering
    - productList
    - pagination
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
    <c:import url="../components/breadcrumbs.jsp"/>

    <c:import url="../components/productList.jsp"/>

    <c:import url="../components/companyInfo.jsp"/>

</body>
</html>