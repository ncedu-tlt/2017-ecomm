<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="category" items="${requestScope.categoriesForView}">
    <div class="ui container jsProductListComponent main-content" style="margin: 1.5em 0;">
    <h2 class="ui center aligned header horizontal divider">
        <c:if test="${category.getId() == null}">
            <c:choose>
                <c:when test="${category.getName() == 'Search'}">
            <span class="withoutUrl">${category.getProducts().isEmpty() ?
                    'Sorry, no products matched "'.concat(param.search).concat('"') :
                    "Search results"}
            </span>
                </c:when>
                <c:when test="${category.getName() == 'Filter'}">
            <span class="withoutUrl">${category.getProducts().isEmpty() ?
                    "No products that meet the selected criteria" :
                    "Filtered"}
            </span>
                </c:when>
                <c:otherwise> ${category.getName()}</c:otherwise>
            </c:choose>
        </c:if>
    <c:if test="${not empty category.getProducts()}">
        <c:if test="${category.getId() > 0}">
            <a href="${pageContext.request.contextPath}/category?category_id=${category.getId()}">
                    ${category.getName()}
            </a>
        </c:if>
        <c:if test="${category.getId() == 0}">
            Best Offers
        </c:if>
    </c:if>
    </h2>
    <div class="ui grid centered container">
        <c:forEach var="product" items="${category.getProducts()}">
            <div class="five wide column">
                <img class="ui fluid image" src="${pageContext.request.contextPath}${product.getImageUrl()}">
                <h3 class="ui center aligned header horizontal divider">
                    <a href="${pageContext.request.contextPath}/product?product_id=${product.getId()}">
                            ${product.getName()}
                    </a>
                </h3>
                <div class="ui grid centered container">
                    <div class="ui ten wide column centered grid massive rating disabled"
                         data-rating="${product.getRating()}">
                    </div>
                </div>

                <h3 class="ui center aligned grey header">
                    <c:if test="${product.getDiscount() != 0}">
                        <b style="text-decoration: line-through;">$${product.getPrice()}</b>
                        <a style="margin-left: .2em"
                           href="${pageContext.request.contextPath}/product?product_id=${product.getId()}"
                           class="ui red large label">
                            $${product.getDiscount()}
                        </a>
                    </c:if>
                    <c:if test="${product.getDiscount() == 0}">
                        $${product.getPrice()}
                    </c:if>
                </h3>
                <button class="ui labeled icon fluid blue button jsAddToCart"
                        name="productId" value="${product.getId()}">
                    <i class="add
                    to car icon"></i>
                    Add to cart
                </button>
            </div>
        </c:forEach>
    </div>
</c:forEach>
<p>${requestScope.max}</p>
<div class="ui page dimmer" id="jsDimmerAdd">
    <div class="content">
        <div class="ui text loader">Loading</div>
    </div>
</div>
</div>

<script>
    window.frm.components.init('ProductListComponent', '.jsProductListComponent');
</script>