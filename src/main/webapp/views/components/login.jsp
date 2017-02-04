<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ui container jsLoginComponent main-content">
    <div class="ui three column centered grid">
        <div class="column">
            <h2 class="ui center aligned icon header">
                <i class="circular sign in icon"></i>
                Login
            </h2>
            <div>
                ${requestScope.registration}
            </div>
            <form class="ui equal dividing width form" action="${pageContext.request.contextPath}/login" method="post">
                <div class="field">
                    <label> Your E-mail: </label>
                    <input type="text" name="email" placeholder="E-mail">
                </div>
                <div class="field">
                    <label> Your Password: </label>
                    <input type="password" name="password" placeholder="Password">
                </div>
                <div class="two fields">
                    <div class="field">
                        <div class="ui checkbox">
                            <input type="checkbox" class="large">
                            <label>Remember Me</label>
                        </div>
                    </div>
                    <div class="field">
                        <div class="ui item right">
                            <p><a class="item" href="${pageContext.request.contextPath}/recovery">Forgot your password?</a></p>
                        </div>
                    </div>
                </div>
                <div class="ui two column centered grid">
                    <div class="column">
                        <button class="ui fluid animated primary button" type="submit">
                            <div class="visible content">Login</div>
                            <div class="hidden content">
                                <i class="right arrow icon"></i>
                            </div>
                        </button>
                    </div>
                    <div class="ui two column centered row grid">
                            <p>Not a member yet?<a class="item" href="${pageContext.request.contextPath}/registration">Register</a></p>
                    </div>
                </div>
            </form>
            <div class="ui hidden divider"></div>
            <c:set var="request" scope="session" value="${requestScope.answer}"/>
            <c:if test="${request != null}">
                <div class="ui message warning">
                    <p>${requestScope.answer}</p>
                </div>
            </c:if>
        </div>
    </div>
</div>
<script>
    window.frm.components.init('LoginComponent', '.jsLoginComponent');
</script>