<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="ui container one column grid">
    <div class="ui large breadcrumb column">
        <jsp:include page="/breadcrumbs" flush="true">
            <jsp:param name="category_id" value="${param.category_id}"/>
            <jsp:param name="product_id" value="${param.product_id}"/>
        </jsp:include>
        <c:if test="${param.category_id != null}">
            <c:if test="${requestScope.categories.size() > 1 && !requestScope.categories.isEmpty()}">
                <c:forEach var="category" items="${requestScope.categories}"
                           end="${requestScope.categories.size()-2}">
                    <a class="section" href="${pageContext.request.contextPath}\category?category_id=${category.categoryId}">${category.name}</a>
                    <i class="right chevron icon divider"></i>
                </c:forEach>
                <c:set var="category" scope="page"
                       value="${requestScope.categories.get(requestScope.categories.size()-1)}"/>
                <a class="active section"
                   href="${pageContext.request.contextPath}\category?category_id=${category.getCategoryId()}">${category.getName()}</a>
            </c:if>
        </c:if>
        <c:if test="${param.product_id != null && !requestScope.categories.isEmpty()}">
            <c:forEach var="category" items="${requestScope.categories}">
                <a class="section" href="${pageContext.request.contextPath}\category?category_id=${category.categoryId}">${category.name}</a>
                <i class="right chevron icon divider"></i>
            </c:forEach>
            <a class="active section"
               href="${pageContext.request.contextPath}\product?product_id=${requestScope.product.getProductId()}">${requestScope.product.getName()}</a>
        </c:if>
    </div>
</div>