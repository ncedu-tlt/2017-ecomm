<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/profileIcon"/>
<c:if test="${userRoleId == 1}">
    <div class="ui item">

        <a href="${pageContext.request.contextPath}/management" class="circular ui icon button">
            <i class="settings icon"></i>
        </a>
    </div>
</c:if>