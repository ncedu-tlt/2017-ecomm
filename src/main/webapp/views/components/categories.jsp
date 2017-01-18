<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="jsCategoryComponent item">
    <jsp:include page="/categories"/>
    <a class="ui floating labeled icon dropdown button categories">
        Categories
        <i class="dropdown icon"></i>
    </a>
    <div class="ui flowing basic admission popup">
        <div class="ui vertical menu">
            <c:forEach var="head" items="${requestScope.heads}">
                <div class="ui left pointing dropdown link item">
                    <a href="/category?category_id=${head.getId()}" style="color: #000;">
                        <b>${head.getName()}</b>
                    </a>
                    <i class="dropdown icon"></i>
                    <div class="menu">
                        <c:forEach var="subcategory" items="${head.getSubcategories()}">
                            <c:if test="${subcategory.getSubcategories().isEmpty()}">
                                <a class="item"
                                   href="/category?category_id=${subcategory.getId()}">${subcategory.getName()}</a>
                            </c:if>
                            <c:if test="${!subcategory.getSubcategories().isEmpty()}">
                                <div class="header">
                                    <a href="/category?category_id=${subcategory.getId()}">${subcategory.getName()}</a>
                                </div>
                                <c:forEach var="category" items="${subcategory.getSubcategories()}">
                                    <a class="item"
                                       href="/category?category_id=${category.getId()}">${category.getName()}</a>
                                </c:forEach>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<script type="text/javascript">
    window.frm.components.init('CategoryComponent', '.jsCategoryComponent');
    $('.dropdown').dropdown({on: 'hover'});
</script>