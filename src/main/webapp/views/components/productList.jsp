<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="category" items="${requestScope.categoriesForView}">
    <div class="ui container jsProductListComponent main-content" style="margin: 1.5em 0;">
    <h2 class="ui center aligned header horizontal divider">
        <a href="\category?category_id=${category.getId()}">
                ${category.getName()}
        </a>
    </h2>
    <div class="ui grid centered container">
        <c:forEach var="product" items="${category.getProducts()}">
            <form action="${pageContext.request.contextPath}/addToShoppingCart" method="post" class="five wide column">
                <img class="ui fluid image" src="${product.getImageUrl()}">
                <h3 class="ui center aligned header horizontal divider">
                    <a href="\product?product_id=${product.getId()}">
                            ${product.getName()}
                    </a>
                </h3>
                <div class="ui grid centered container">
                    <div class="ui eleven wide column large rating disabled"
                         data-rating="${product.getRating()}">
                    </div>
                </div>

                <h3 class="ui center aligned grey header">
                    <c:if test="${product.getDiscount() != 0}">
                        <b style="text-decoration: line-through;">$${product.getPrice()}</b>
                        <a style="margin-left: .2em" href="\product?product_id=${product.getId()}"
                           class="ui red large label">
                            $${product.getDiscount()}
                        </a>
                    </c:if>
                    <c:if test="${product.getDiscount() == 0}">
                        $${product.getPrice()}
                    </c:if>
                </h3>
                <%--<input type="hidden" name="productId" value="${product.getId()}" />--%>
                <button class="ui labeled icon  fluid blue button" type="submit"
                        name="productId" value="${product.getId()}">
                    <i class="add
                    to car icon"></i>
                    Add to card
                </button>
            </form>
        </c:forEach>
    </div>
</c:forEach>
<p>${requestScope.max}</p>
</div>
<script>
    window.frm.components.init('ProductListComponent', '.jsProductListComponent');
</script>