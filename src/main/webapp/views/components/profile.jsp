<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
${requestScope.username}
<div class="ui aligned center text container segment jsProfileComponent" data-tab="profile">
    <form class="ui form jsProfileForm" method="post" action="${pageContext.request.contextPath}/profile">
        <div class="field">
            <label>First Name:</label>
            <input type="text" name="firstName" placeholder="First Name" class="jsProfileTextParam"
                   value="${requestScope.firstName}">
        </div>
        <div class="field">
            <label>Last Name:</label>
            <input type="text" name="lastName" placeholder="Last Name" class="jsProfileTextParam"
                   value="${requestScope.lastName}">
        </div>
        <div class="field">
            <label>Email:</label>
            <input type="text" name="email" placeholder="Email" class="jsProfileTextParam"
                   value="${requestScope.email}">
        </div>
        <div class="field jsPasswordField">
            <label>Password:</label>
            <input type="password" class="jsPassword jsProfileTextParam" name="password" placeholder="Password">
        </div>
        <div class="field jsPasswordField">
            <input type="password" class="jsPasswordConfirm jsProfileTextParam" name="passwordConfirm"
                   placeholder="Current Password">
        </div>
        <button class="ui animated primary button" id="jsSendFormProfileBtn">
            <div class="visible content">Save</div>
            <div class="hidden content">
                <i class="repeat icon outline"></i>
            </div>
        </button>
        <button type="button" class="positive ui button jsPasswordCall">Change Password</button>
        <div class="ui error message"></div>
    </form>
    <c:set var="request" scope="session" value="${requestScope.answer}"/>
    <c:if test="${request != null}">
        <c:choose>
            <c:when test='${requestScope.answer == "success"}'>
                <div class="ui positive message">
                    <i class="close icon"></i>
                    <div class="header">
                        Success!
                    </div>
                    <p>Your profile was change successful!</p>
                </div>
            </c:when>
            <c:when test='${requestScope.answer == "error"}'>
                <div class="ui positive message">
                    <i class="close icon"></i>
                    <div class="header">
                        Error!
                    </div>
                    <p>Your profile could not be edited. Please try again!!</p>
                </div>
            </c:when>
        </c:choose>
    </c:if>
</div>
<script>
    window.frm.components.init('ProfileComponent', '.jsProfileComponent');
</script>