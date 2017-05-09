<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="ui container main-content jsProductComparator">
    <div class="ui segments">
        <div class="ui grid centered container gap">
            <h2 class="ui center aligned header horizontal divider">
                Compare
            </h2>

            <c:if test='${sessionScope == null || sessionScope.compareList == null || sessionScope.compareList.size() == 0}'>
                <h2 class="ui center aligned icon header">
                    <i class="circular ban icon"></i>
                    No more products to compare...
                </h2>

            </c:if>
            <c:set var="compareList" value="${sessionScope.compareList}"/>
            <c:set var="charForProduct" value="${sessionScope.charForProduct}"/>


            <c:if test='${compareList.size() == 3}'>
                <div class="four wide column">
                </div>
            </c:if>
            <c:if test='${sessionScope.compareList.size() != 0 || compareList != null}'>
            <c:forEach var="product" items="${compareList}">
                <c:set var="image" value="${product.imageUrl}"/>
                <div class="four wide column">
                    <img class="ui fluid image"
                         src="${pageContext.request.contextPath}${image != null ? image : "/images/defaultimage/image.png"}">
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
                            <button class="ui icon red button jsRemoveProduct" value="${product.id}"><i
                                    class="remove icon"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    </c:if>
    <c:if test='${sessionScope.compareList.size() != 0 || sessionScope.compareList == null}'>
        <div class="html ui top attached segment">
            <table class="ui selectable celled fixed table jsCompareTable">
                <c:forEach var="charGroup" items="${charForProduct}">
                    <thead>
                    <tr>
                        <th colspan="4">
                            <p>${charGroup.name}</p>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <c:forEach var="chars" items="${charGroup.getProductChars()}">
                    <tr>
                        <td class="collapsing">
                            <p>${chars.name}</p>
                        </td>
                        <c:forEach var="charValue" items="${chars.getCharLines()}">
                            <td class="collapsing  <c:if test='${charValue.length() > 40}'> jsTableItem </c:if>"
                                data-content="${charValue}">
                                <p>${charValue}</p>
                            </td>
                        </c:forEach>
                    </tr>
                    </c:forEach>
                    </tbody>
                </c:forEach>
            </table>
        </div>
    </c:if>
</div>

<script>
    window.frm.components.init('productComparator', '.jsProductComparator', {
        comparePageUrl: '${pageContext.request.contextPath}'
    });
</script>
