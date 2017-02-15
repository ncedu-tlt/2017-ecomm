<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ui item">
    <a href="${pageContext.request.contextPath}/profile" class="circular ui icon button">
        <c:choose>
            <c:when test="${userId != null}">
                <i class="icon user"></i>
            </c:when>
            <c:when test="${userId == null}">
                <i class="sign in icon"></i>
            </c:when>
        </c:choose>
    </a>
</div>
