<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="property"  value="${requestScope.property}"/>
<!--<div class="row"> -->
    <div class="three wide column jsPropertyId">
        ${property.getId()}
    </div>
    <div class="thirteen wide column jsPropertyValue">
        <!--<div class="jsVisible"> -->
            ${property.getValue()}

            <button class="circular right floated mini ui icon button content jsRemoveLineButton">
                <i class="remove icon"></i>
            </button>
        <!--</div> ->>
    </div>
<!--</div> -->


