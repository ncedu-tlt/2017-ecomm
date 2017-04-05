<%--
    Used fragments:
    - head
    - topPanel

    Used components:
    - dashboardMenu
    - ordersHistory
    - companyInfo
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Shop - Orders History</title>
    <c:import url="../fragments/head.jsp"/>
</head>
<body>
<div class="jsMainWrapper">
    <c:import url="../fragments/topPanel.jsp"/>

    <c:import url="../components/dashboardMenu.jsp"/>

    <c:import url="../components/ordersHistory.jsp"/>

    <c:import url="../components/companyInfo.jsp"/>
</div>
</body>
</html>