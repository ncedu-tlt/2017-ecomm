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

    <c:import url="../components/etalon.jsp">
        <c:param name="showCheckbox" value="true"/>
    </c:import>

    <c:import url="../components/companyInfo.jsp"/>

</body>
</html>