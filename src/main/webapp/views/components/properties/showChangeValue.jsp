<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="property" value="${requestScope.property}"/>
<c:set var="propertyID" value="${property.getId()}"/>
<c:set var="propertyValue" value="${property.getValue()}"/>
<c:set var="field" value="${requestScope.field}"/>

<c:if test="${field == 'three wide column jsProperty'}">
    <div class="three wide column jsPropertyId jsEdit">
            ${propertyID}
    </div>
</c:if>

<c:if test="${field == 'thirteen wide column jsProperty'}">
    <div class="thirteen wide column jsVisible jsEdit">

            ${propertyValue}

        <button class="circular right floated ui small  compact icon  button  jsRemoveLineButton">
            <i class="remove icon"></i>
        </button>

    </div>
</c:if>
