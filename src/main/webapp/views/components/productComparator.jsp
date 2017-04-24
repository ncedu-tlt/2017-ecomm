<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="ui container main-content jsProductComparator">
    <div class="ui segments">
        <div class="ui grid centered container gap">
            <h2 class="ui center aligned header horizontal divider">
                Compare
            </h2>

            <c:if test='${sessionScope == null || sessionScope.compareList == null}'>
                <h2 class="ui center aligned icon header">
                    <i class="circular ban icon"></i>
                    No more products to compare...
                </h2>

            </c:if>
            <c:if test='${sessionScope.compareList != null}'>

                <c:set var="compareList" value="${sessionScope.compareList}"/>

                <c:forEach var="product" items="${compareList}">
                    <c:set var="image" value="${product.imageUrl}"/>
                    <div class="four wide column">
                        <img class="ui fluid image"
                             src="${pageContext.request.contextPath}/${image != null ? image : "/images/defaultimage/image.png"}">
                        <h3 class="ui center aligned header">
                            <a>
                                    ${product.name}
                            </a>
                        </h3>
                        <div class="ui grid centered container">
                            <div class="ui column centered grid massive rating disabled centered-rating jsCompareProductRating"
                                 data-rating="${product.rating}">
                            </div>
                        </div>
                        <h3 class="ui center aligned header">
                            <c:if test='${product.discount != product.price}'>
                                <b style="text-decoration: line-through;">$${product.price}</b>
                                <a style="margin-left: .2em"
                                   href="${pageContext.request.contextPath}/product?product_id=${product.id}"
                                   class="ui red large label">
                                    $${product.discount}
                                </a>
                            </c:if>
                            <c:if test='${product.discount == product.price}'>
                                <b>$${product.price}</b>
                            </c:if>
                        </h3>
                        <div class="centered-button">
                            <div class="ui buttons">
                                <button class="ui labeled icon blue button" value="${product.id}">Add to cart</button>
                                <button class="ui icon red button" value="${product.id}"><i class="remove icon"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </div>
</div>
<script>
    window.frm.components.init('productComparator', '.jsProductComparator', {
        addToCompareUrl: '${requestScope.addToCartURL}'
    });
</script>
