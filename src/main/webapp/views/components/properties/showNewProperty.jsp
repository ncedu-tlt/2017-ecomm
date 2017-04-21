<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="property" value="${requestScope.property}"/>
<div class="row jsNewTableValue">
    <div class="three wide column jsNewPropertyId">
        ${property.getId()}
    </div>
    <div class="thirteen wide column jsNewPropertyValue">

        ${property.getValue()}

        <button class="circular right floated ui small  compact icon  button  jsRemoveLineButton">
            <i class="remove icon"></i>
        </button>

    </div>
</div>



