<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="jsCategoryComponent item">
    <jsp:include page="/CategoriesServlet"/>
    <a class="ui floating labeled icon dropdown button categories">
        Categories
        <i class="dropdown icon"></i>
    </a>
    <div class="ui flowing basic admission popup">
        <div class="ui link vertical menu">
            <c:forEach var="head" items="${requestScope.heads}">
                <div class="ui dropdown center aligned blue item">
                    <b>${head.getName()}</b>
                    <i class="dropdown icon"></i>
                    <div class="menu">
                        <c:forEach var="subcategory" items="${head.getSubcategories()}">
                            <div class="header">
                                <a href="\category?category_id=${subcategory.getCategoryId()}">${subcategory.getName()}</a>
                            </div>
                            <c:forEach var="child" items="${requestScope.child}">
                                <c:if test="${subcategory.getCategoryId() == child.getId()}">
                                    <c:forEach var="category" items="${child.getSubcategories()}">
                                        <a class="item"
                                           href="\category?category_id=${category.getCategoryId()}">${category.getName()}</a>
                                    </c:forEach>
                                </c:if>
                            </c:forEach>
                        </c:forEach>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<script type="text/javascript">
    window.frm.components.init('CategoryComponent', '.jsCategoryComponent');
</script>