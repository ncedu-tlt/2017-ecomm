<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container jsChangePasswordComponent main-content">
    <div class="ui three column centered grid">
        <div class="column">
            <h2 class="ui center aligned icon header">
                <i class="circular unlock icon"></i>
                Password Change
            </h2>
            <div class="ui center aligned vertical segment">
                <p>
                    Please, enter your new password.
                </p>
            </div>
            <div class="ui two column center aligned grid">
                <div class="column">
                    <form class="ui equal dividing width form jsPasswordChangeForm" method="post"
                          action="${pageContext.request.contextPath}/passwordChange">
                        <input name="hiddenEmail" type="hidden"
                               value="${requestScope.email}">
                        <input name="hiddenHash" type="hidden"
                               value="${requestScope.recoveryHash}">
                        <div class="field">
                            <input type="password" name="password" placeholder="Password">
                        </div>
                        <div class="field">
                            <input type="password" name="passwordConfirm" placeholder="Password Confirm">
                        </div>
                        <div class="ui error message"></div>
                        <button class="ui primary button jsPasswordChangeBtn">
                            <div class="content">Change Password</div>
                        </button>
                    </form>
                    <c:set var="request" scope="session" value="${requestScope.answer}"/>
                    <c:choose>
                        <c:when test='${requestScope.answer == "ChangeSuccess"}'>
                            <div class="ui positive message jsMessageFromServlet">
                                <i class="close icon jsCloseMessageFromServlet"></i>
                                <div class="header">
                                    Success!
                                </div>
                                <p>
                                <p>
                                    Your password was change.
                                    <a class="item" href="${pageContext.request.contextPath}/login">Login.</a>
                                </p>
                            </div>
                        </c:when>
                        <c:when test='${requestScope.answer == "ChangeError"}'>
                            <div class="ui negative message jsMessageFromServlet">
                                <i class="close icon jsCloseMessageFromServlet"></i>
                                <div class="header">
                                    Error!
                                </div>
                                <p>
                                    Error. Try again to recovery your password.
                                    <a class="item" href="${pageContext.request.contextPath}/recovery">Password
                                        recovery?</a>
                                </p>
                            </div>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
    <div class="ui page dimmer jsDimmerPasswordChange">
        <div class="content">
            <div class="ui text loader">Change password</div>
        </div>
    </div>
</div>

<%-- JS controller initilization --%>
<script type="text/javascript">
    window.frm.components.init('changePasswordComponent', '.jsChangePasswordComponent');
</script>