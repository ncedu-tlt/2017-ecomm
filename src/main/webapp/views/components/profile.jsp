<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/profile"/>
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
        <div class="field">
            <label>Password:</label>
            <input type="password" class="jsPassword" name="password" placeholder="Password">
        </div>
        <div class="field">
            <input type="password" class="jsPasswordConfirm" name="passwordConfirm" placeholder="Current Password">
        </div>
        <button class="ui animated primary button" id="jsSendFormProfileBtn">
            <div class="visible content">Apply Changes</div>
            <div class="hidden content">
                <i class="repeat icon outline"></i>
            </div>
        </button>
        <div class="ui error message"></div>
    </form>
    <c:set var="answer" scope="session" value="${requestScope.answer}"/>
    <div class="ui message warning ">
        <p>${answer}</p>
    </div>
</div>
<script>
    window.frm.components.init('ProfileComponent', '.jsProfileComponent');
</script>