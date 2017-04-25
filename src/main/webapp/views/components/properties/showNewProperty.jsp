<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="property" value="${requestScope.property}"/>

    <div class="three wide column jsPropertyId jsEdit">
        ${property.getId()}
    </div>
    <div class="thirteen wide column jsVisible jsEdit">

        ${property.getValue()}

        <button class="circular right floated ui small  compact icon  button  jsRemoveLineButton">
            <i class="remove icon"></i>
        </button>

    </div>
