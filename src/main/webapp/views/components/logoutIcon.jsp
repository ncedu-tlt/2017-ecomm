<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.userId != null}">
    <div class="ui item">
        <a href="/logout" class="circular ui icon button">
            <i class="icon sign out"></i>
        </a>
    </div>
</c:if>

