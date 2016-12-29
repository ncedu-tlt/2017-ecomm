<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ui container jsProductListComponent main-content" style="margin: 1.5em 0;">
    <c:forEach var="category" items="${requestScope.categoriesForView}">
        <c:if test="${category.getParentId() != 0}">
            <h2 class="ui center aligned header horizontal divider">
                <a href="\category?category_id=${category.categoryId}">${category.getName()}</a>
            </h2>
            <div class="ui grid centered container">
                <c:forEach var="product" items="${requestScope.productToView}">
                    <c:if test="${product.getCategoryId() == category.getCategoryId()}">
                        <form action="shoppingCart.jsp" method="post" class="five wide column">
                            <img class="ui fluid image" src="${product.getImageUrl()}">
                            <h3 class="ui center aligned header horizontal divider">
                                <a href="\product?product_id=${product.getProductId()}">
                                        ${product.getName()}
                                </a>
                            </h3>
                            <div class="ui grid centered container">
                                <div class="ui eleven wide column large rating disabled"
                                     data-rating="${product.getRating()}">
                                </div>
                            </div>
                            <h3 class="ui center aligned grey header">
                                $${product.getPrice()}
                            </h3>
                            <button class="ui labeled icon  fluid blue button" type="submit"
                                    value="${product.getProductId()}">
                                <i class="add to car icon"></i>
                                Add to card
                            </button>
                        </form>
                    </c:if>
                </c:forEach>
            </div>
        </c:if>
    </c:forEach>
</div>
<script>
    window.frm.components.init('ProductListComponent', '.jsProductListComponent');
</script>