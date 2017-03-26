<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ui center aligned container jsPropertiesComponent">
    <h3 class="ui center aligned header">
        Properties
    </h3>
    <table class="ui celled structured table jsProperties">
        <thead>
        <tr>
            <th>Properties</th>
            <th>Comment</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach var="property" items="${requestScope.properties}">
                <tr>
                    <td>${property.getId()}</td>

                            <div class="jsVisible">
                               <td>
                                    ${property.getValue()}
                               </td>
                            </div>


                            <div class="jsHide" style="display: none;" contenteditable="true">
                                <td>
                                    ${property.getValue()}
                                </td>
                                <button class="ui button jsSaveButton"  style="display: none" type="submit" value="save">SAVE
                                </button>
                                <button class="ui button jsCancelButton" style="display: none" type="submit" value="cancel">CANCEL
                                </button>
                                <button class="ui button jsRemoveLineButton" style="display: none" type="submit" value="cancel">REMOVE LINE
                                </button>
                            </div>



                </tr>
            </c:forEach>
        </tbody>
    </table>


    <button class="ui button jsAddButton" type="submit" value="cancel">ADD PROPERTY
    </button>


    <div  class="ui right floated center middle aligned"></div>

</div>
<script>
    window.frm.components.init('PropertiesComponent', '.jsPropertiesComponent', {
        propertiesUrl: '${pageContext.request.contextPath}' + '/properties'
    });
</script>
