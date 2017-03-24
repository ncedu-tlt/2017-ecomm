<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container jsChangePasswordComponent main-content">
    <div class="ui two column centered grid">
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
            <div class="ui three column centered grid">
                <div class="column">
                    <form class="ui form jsPasswordRecoveryForm" method="post"
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
                        <button class="ui fluid animated primary button jsPasswordChangeBtn">
                            <div class="visible content">Change Password</div>
                            <div class="hidden content">
                                <i class="repeat icon outline"></i>
                            </div>
                        </button>
                    </form>
                </div>
            </div>
            <c:set var="request" scope="session" value="${requestScope.answer}"/>
            <c:if test="${request != null}">
                <div class="ui message warning">
                    <c:choose>
                        <c:when test='${requestScope.answer == "ChangeSuccess"}'>
                            <p>Your password was change.</p>
                        </c:when>
                        <c:when test='${requestScope.answer == "ChangeError"}'>
                            <p>Error. Try again to recovery your password</p>
                        </c:when>
                    </c:choose>
                </div>
            </c:if>
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