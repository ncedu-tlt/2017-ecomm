<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="property" value="${requestScope.property}"/>

    <div class="three wide column jsPropertId">
        ${property.getId()}
    </div>

    <div class="thirteen wide column jsPropertVal">

        <input class="jsText" type="text" value="${property.getValue()}">

        <div class="ui right floated small buttons jsPanelButton">
            <button class="ui button jsCancelButton">Cancel</button>
            <div class="or"></div>
            <button class="ui button jsSaveButton">Save</button>
        </div>

    </div>


