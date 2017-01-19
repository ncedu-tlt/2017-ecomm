<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ui item">
    <form action="/profileIcon">
        <button class="circular ui icon button" type="submit">
            <i class="icon user"></i>
        </button>
    </form>
    <form action="/logoutIcon">
    <c:if test="${sessionScope.userId != null}">
        <button class="circular ui icon button" type="submit">
            <i class="icon sign out"></i>
        </button>
    </c:if>
    </form>
</div>
