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
        <button class="ui animated primary button jsSendFormProfileBtn">
            <div class="visible content">Save</div>
            <div class="hidden content">
                <i class="repeat icon outline"></i>
            </div>
        </button>
        <button type="button" class="positive ui button jsPasswordCall">Change Password</button>
    </form>
    <c:set var="request" scope="session" value="${requestScope.answer}"/>
    <c:if test="${request != null}">
        <c:choose>
            <c:when test='${requestScope.answer == "Success"}'>
                <div class="ui positive message jsMessageFromServlet">
                    <i class="close icon jsCloseMessageFromServlet"></i>
                    <div class="header">
                        Success!
                    </div>
                    <p>Your profile was change successful!</p>
                </div>
            </c:when>
            <c:when test='${requestScope.answer == "ErrorInputEmail"}'>
                <div class="ui negative  message jsMessageFromServlet">
                    <i class="close icon jsCloseMessageFromServlet"></i>
                    <div class="header">
                        Error typing email!
                    </div>
                    <p>Your email has been entered incorrectly or differs from the old.</p>
                </div>
            </c:when>
            <c:when test='${requestScope.answer == "ErrorInputPassword"}'>
                <div class="ui negative  message jsMessageFromServlet">
                    <i class="close icon jsCloseMessageFromServlet"></i>
                    <div class="header">
                        Password failed!
                    </div>
                    <p>
                        Your password must contain uppercase, or
                        lowercase Latin characters or Cyrillic
                        (special characters: @ $ _) and be a length of 4 to 20 characters.
                    </p>
                </div>
            </c:when>
            <c:when test='${requestScope.answer == "ErrorInputFirstName"}'>
                <div class="ui negative  message jsMessageFromServlet">
                    <i class="close icon jsCloseMessageFromServlet"></i>
                    <div class="header">
                        Error entering first name!
                    </div>
                    <p>
                        Your first name must contain Latin characters or
                        Cyrillic characters and be from 2 to 20 characters in length.
                    </p>
                </div>
            </c:when>
            <c:when test='${requestScope.answer == "ErrorInputLastName"}'>
                <div class="ui negative message jsMessageFromServlet">
                    <i class="close icon jsCloseMessageFromServlet"></i>
                    <div class="header">
                        Error entering last name!
                    </div>
                    <p>
                        Your last name must contain Latin characters or
                        Cyrillic characters and be from 2 to 20 characters in length.
                    </p>
                </div>
            </c:when>
        </c:choose>
    </c:if>
    <div class="ui page dimmer jsDimmerProfile">
        <div class="content">
            <div class="ui text loader">Loading</div>
        </div>
    </div>
</div>
<script>
    window.frm.components.init('ProfileComponent', '.jsProfileComponent');
</script>