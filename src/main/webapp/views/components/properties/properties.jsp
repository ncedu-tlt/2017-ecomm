<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="ui center aligned container jsPropertiesComponent">

    <div class="ui hidden divider"></div>


    <div class="ui celled grid">

        <div class="row">
            <div class="three wide column"><h3>Properties</h3></div>
            <div class="thirteen wide column"><h3>Description</h3></div>
        </div>

        <c:forEach var="property" items="${requestScope.properties}">
            <div class="row jsTableValue">
                <div class="three wide column jsPropertyId">
                        ${property.getId()}
                </div>
                <div class="thirteen wide column jsVisible">

                        ${property.getValue()}

                    <button class="circular right floated ui small  compact icon  button  jsRemoveLineButton">
                        <i class="remove icon"></i>
                    </button>
                </div>

            </div>


        </c:forEach>
    </div>


    <div class="ui hidden divider"></div>
    <div class="ui hidden divider"></div>

    <button class="ui button jsAddButton" type="submit" value="cancel">
        ADD PROPERTY
    </button>


    <div class="ui right floated center middle aligned"></div>

</div>
<script>
    window.frm.components.init('PropertiesComponent', '.jsPropertiesComponent', {
        propertiesUrl: '${pageContext.request.contextPath}'
    });
</script>


<!--<button class="right floated small ui button jsRemoveLineButton">
Delete property
</button>
-->
<!--<button class="circular right floated ui icon button middle aligned">-->

<!--<div class="ui mini basic icon buttons">
<button class="mini ui button jsRemoveLineButton">
<i class="remove icon"></i>
</button>
</div>-->