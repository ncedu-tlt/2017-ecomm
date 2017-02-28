<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ui container jsRegistrationComponent main-content">
    <div class="ui three column centered grid">
        <div class="column">
            <h2 class="ui center aligned icon header">
                <i class="circular registered in icon"></i>
                Registration
            </h2>
            <form class="ui equal dividing width form jsRegistrationForm" method="post">
                <div class="field">
                    <label for="email">E-mail: </label>
                    <input type="text" id="email" name="email" placeholder="E-mail">
                </div>
                <div class="field">
                    <label for="password">Password: </label>
                    <input type="password" id="password" name="password" placeholder="Password">
                </div>
                <div class="field">
                    <label for="confirmPassword">Confirm password: </label>
                    <input type="password" id="confirmPassword" name="checkPassword" placeholder="Confirm password">
                </div>
                <div class="column">
                    <button class="ui fluid animated primary button" type="submit">
                        <div class="visible content">Register</div>
                        <div class="hidden content">
                            <i class="right arrow icon"></i>
                        </div>
                    </button>
                </div>
                <p>Already a member? <a href="${pageContext.request.contextPath}/login"> Login </a></p>

                <div class="ui error message"></div>
                <c:set var="request" scope="session" value="${requestScope.answer}"/>
                    <c:if test="${request != null}">
                        <div class="ui error message" style="display: block">

                        <c:choose>
                            <c:when test='${requestScope.answer == "Fields must not be empty"}'>
                                Fields must not be empty
                            </c:when>
                            <c:when test='${requestScope.answer == "Email is incorrect"}'>
                                Email is incorrect
                            </c:when>
                            <c:when test='${requestScope.answer == "Passwords dont match"}'>
                                Passwords dont match
                            </c:when>
                            <c:when test='${requestScope.answer == "Email is already in use"}'>
                                Email is already in use
                            </c:when>
                        </c:choose>
                    </c:if>
                </div>

            </form>
            <div class="ui hidden divider"></div>
        </div>
    </div>
</div>
<script type="text/javascript">
   window.frm.components.init('RegistrationComponent', '.jsRegistrationComponent');
</script>