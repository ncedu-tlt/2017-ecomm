<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="property" value="${requestScope.property}"/>
<c:set var="propertyID" value="${property.getId()}"/>
<c:set var="propertyValue" value="${property.getValue()}"/>

<div class="row jsRowVal">

    <div class="three wide column">
        <div class="ui fluid action transparent input">
            <input class="jsInputPropertyId" type="text" value="${propertyID}">
        </div>
    </div>

    <div class="thirteen wide column">
        <div class="ui fluid action transparent input">
            <input class="jsInputPropertyValue" type="text" value="${propertyValue}">
        </div>

        <div class="ui right floated small buttons jsPanelButton">
            <button class="ui button jsCancelButton">Cancel</button>
            <div class="or"></div>
            <button class="ui button jsSaveRow">Save</button>
        </div>

    </div>


</div>



