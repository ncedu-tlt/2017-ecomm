<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="property" value="${requestScope.property}"/>
<c:set var="propertyID" value="${property.getId()}"/>
<c:set var="propertyValue" value="${property.getValue()}"/>

<div class="${propertyValue == null ? "three" : "thirteen"} wide column jsProperty">

    <div class="ui fluid action transparent input jsInput">
        <c:if test="${propertyValue != null}" >
            <input class="jsPropertId" type="hidden" value="${propertyID}">
            <input class="jsPropertVal" type="text" value="${propertyValue}">
        </c:if>

        <c:if test="${propertyValue == null}" >
            <input class="jsPropertId" type="text" value="${propertyID}">
            <input class="jsPropertVal" type="hidden" value="${propertyValue}">
        </c:if>
    </div>


    <div class="ui right floated small buttons jsPanelButton">
        <button class="ui button jsCancelButton">Cancel</button>
        <div class="or"></div>
        <button class="ui button jsSaveButton">Save</button>
    </div>

</div>

