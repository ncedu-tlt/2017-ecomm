<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="property" value="${requestScope.property}"/>
<c:set var="propertyID" value="${property.getId()}"/>
<c:set var="propertyValue" value="${property.getValue()}"/>
<c:set var="field" value="${requestScope.field}"/>
<c:set var="setTextArea" value="${requestScope.setTextArea}"/>

<c:if test="${field == 'three wide column jsPropertyId jsEdit'}">
    <div class="three wide column jsProperty">

        <div class="ui fluid action transparent input jsInput">
            <input class="jsPropertId" type="text" value="${propertyID}">
            <input class="jsPropertVal" type="hidden" value="${propertyValue}">
        </div>

        <div class="ui right floated small buttons jsPanelButton">
            <button class="ui button jsCancelButton">Cancel</button>
            <div class="or"></div>
            <button class="ui button jsSaveButton">Save</button>
        </div>
    </div>
</c:if>

<c:if test="${field == 'thirteen wide column jsVisible jsEdit'}">
    <div class="thirteen wide column jsProperty">

        <c:if test="${setTextArea == false}">
            <div class="ui fluid action transparent input jsInput">
                <input class="jsPropertId" type="hidden" value="${propertyID}">
                <input class="jsPropertVal" type="text" value="${propertyValue}">
            </div>
        </c:if>

        <c:if test="${setTextArea == true}">
            <input class="jsPropertId" type="hidden" value="${propertyID}">
            <div class="ui form">
                <div class="field">
                    <textarea class="jsPropertVal">${propertyValue}</textarea>
                </div>
            </div>
        </c:if>

        <div class="ui right floated small buttons jsPanelButton">
            <button class="ui button jsCancelButton">Cancel</button>
            <div class="or"></div>
            <button class="ui button jsSaveButton">Save</button>
        </div>
    </div>
</c:if>
