<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ui center aligned container">
    <table class="ui celled structured table">
        <thead>
        <tr>
            <th>Name</th>
            <th>Role</th>
            <th>Email</th>
            <th>Registration Date</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${requestScope.users}">
            <tr>
                <td>
                   <a href="${pageContext.request.contextPath}/profile?user_id=${user.getId()}">
                      ${user.getFio()}
                   </a>
                </td>
                <td>${user.getRole()}</td>
                <td>${user.getEmail()}</td>
                <td>${user.getRegistrationDate()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
