<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row jsRowVal">

    <div class="three wide column jsPropertyId">
        <input class="jsInputPropertyId" type="text" value="${property.getID()}">
    </div>

    <div class="thirteen wide column jsPropertyValue">

        <input class="jsInputPropertyValue" type="text" value="${property.getValue()}">

        <div class="ui right floated small buttons jsPanelButton">
            <button class="ui button jsCancelButton">Cancel</button>
            <div class="or"></div>
            <button class="ui button jsSaveRow">Save</button>
        </div>

    </div>



</div>



