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
            <c:forEach var="categories" items="${requestScope.categories}">
                <div class="ui dropdown center aligned blue item">
                    <b>${categories.getHead().getName()}</b>
                    <i class="dropdown icon"></i>
                    <div class="menu">
                        <c:forEach var="middle" items="${categories.getMiddles()}">
                            <div class="header"><a href="#">${middle.getHead().getName()}</a></div>
                            <c:if test="${!middle.getCategories().isEmpty()}">
                                <c:forEach var="category" items="${middle.getCategories()}">
                                    <a class="item">${category.getName()}</a>
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
</script>