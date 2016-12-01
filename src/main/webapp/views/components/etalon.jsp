<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.ncedu.ecomm.utils.JSONUtils" %>

<div class="ui text container jsEtalonComponent">
    <form class="ui form">

        <div class="field">
            <label>User Input</label>
            <input type="text" class="jsInput" value="${requestScope.initialValue}">
        </div>

        <div class="field">
            <label>Role</label>
            <select class="ui dropdown">
                <c:forEach items="${requestScope.roles}" var="role">
                    <option value="${role.id}">${role.name}</option>
                </c:forEach>
            </select>
        </div>

        <%-- showCheckbox param usage. Params are received from the parent JSP. --%>
        <c:if test="${param.showCheckbox.equals('true')}">
            <div class="field">
                <div class="ui checkbox">
                    <input type="checkbox" tabindex="0" class="hidden">
                    <label>Checkbox</label>
                </div>
            </div>
        </c:if>

        <div class="field">
            <button type="button" class="ui primary button jsPrint">
                Print
            </button>
            <button type="reset" class="ui button">
                Discard
            </button>
        </div>

    </form>
</div>

<%-- JS controller initilization --%>
<script type="text/javascript">
    window.frm.components.init('EtalonComponent', '.jsEtalonComponent', {
        // Roles should be converted to JSON, in order to be handled as a JS object
        roles: <%= JSONUtils.toJSON(request.getAttribute("roles")) %>
    });
</script>