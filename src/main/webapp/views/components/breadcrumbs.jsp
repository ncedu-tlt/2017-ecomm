<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ui container one column grid">
    <div class="ui large breadcrumb column">

        <jsp:include page="/breadcrumbs?category_id=${param.category_id}" flush="true"/>

        <c:if test="${requestScope.categories.size() > 1}">
            <c:forEach var="category" items="${requestScope.categories}" end="${requestScope.categories.size()-2}">
                <a class="section" href="\category?category_id=${category.categoryId}">${category.name}</a>
                <i class="right chevron icon divider"></i>
            </c:forEach>
        </c:if>

        <a class="active section" href="\category?category_id=${requestScope.categories.get(requestScope.categories.size()-1).getCategoryId()}">${requestScope.categories.get(requestScope.categories.size()-1).getName()}</a>

    </div>
</div>