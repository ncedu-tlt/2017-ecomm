<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/profileIcon"/>
<div class="ui item">
    <c:choose>
        <c:when test="${userId != null}">
            <div class="ui labeled">
                <a href="${pageContext.request.contextPath}/profile" class="ui basic image label">
                    <img src="${pageContext.request.contextPath}${requestScope.avatarPath}">
                        ${requestScope.email}
                </a>
            </div>
        </c:when>
        <c:when test="${userId == null}">
            <a href="${pageContext.request.contextPath}/profile" class="circular ui icon button">
                <i class="sign in icon"></i>
            </a>
        </c:when>
    </c:choose>
</div>
