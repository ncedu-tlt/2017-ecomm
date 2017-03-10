<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="browseProduct" scope="page"
       value="${requestScope.browseProduct}"/>
<div class="ui text two column grid container jsProductDetailsComponent">
    <div class="column slideShow">
        <div class="ui one column grid ">
            <div class="column showDisplay">
                <a href="#">
                    <img class="ui large image" src="${pageContext.request.contextPath}${browseProduct.getImagesList().get(0)}" alt="">
                </a>
            </div>
        </div>
        <div class="ui three column grid">
            <c:forEach var="image" items="${browseProduct.getImagesList()}">
                <div class="column slideShowItem">
                    <a class="slideShowPic" href="#">
                        <img class="ui large image" src="${pageContext.request.contextPath}${image}" alt="">
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>
    <div class="column">
        <h2 class="ui header">${browseProduct.getName()}</h2>
        <div class="ui two column grid">
            <div class="column">
                <div class="ui eleven wide column large rating disabled" data-rating="${browseProduct.getRating()}" data-max-rating="5"></div>
            </div>
            <div class="column right aligned container">
                <c:if test="${browseProduct.getDiscount() != 0}">
                    <span class="productPrice" style="text-decoration: line-through;">$${browseProduct.getPrice()}</span>
                    <span style="   margin-left: .2em"  class="ui red large label">
                        $${browseProduct.getDiscount()}
                    </span>
                </c:if>
                <c:if test="${browseProduct.getDiscount() == 0}">
                    <span class="productPrice">$${browseProduct.getPrice()}</span>
                </c:if>
            </div>
        </div>
        <div class="ui two column grid">
            <div class="column aligned left">
                <button name="productId" value="${browseProduct.getId()}" class="ui button jsAddToCart">ADD TO CART</button>
            </div>
            <div class="column right aligned container">
                <div class="ui button" tabindex="0">COMPARE</div>
            </div>
        </div>
        <div class="ui one column grid">
            <p>${browseProduct.getDescription()}</p>
        </div>
    </div>
<c:forEach var="characteristicGroup" items="${browseProduct.getCharacteristicGroupModels()}">
    <div class="ui top attached menu">
        <a class="item">${characteristicGroup.getName()}</a>
    </div>
    <div class="ui attached segment">
    <c:forEach var="characteristics" items="${characteristicGroup.getCharacteristics()}">
        <div class="ui computer reversed equal width grid">
            <div class="row">
                <div class="column" id="brandValue">
                    <p>${characteristics.getValue()}</p>
                </div>
                <div class="column" id="brand">
                    <p>${characteristics.getName()}</p>
                </div>
            </div>
        </div>
    </c:forEach>
    </div>
</c:forEach>
</div>

<%-- JS controller initilization --%>
<script type="text/javascript">
    window.frm.components.init('productDetails', '.jsProductDetailsComponent');
</script>