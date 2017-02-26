<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ui container jsRegistrationComponent main-content">
    <div class="ui three column centered grid">
        <div class="column">
            <h2 class="ui center aligned icon header">
                <i class="circular registered in icon"></i>
                Registration
            </h2>
            <div>
                ${requestScope.answer}
            </div>
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
                <div class="ui error message">
                    <p>${requestScope.answer}</p>
                </div>
            </form>
            <div class="ui hidden divider"></div>
        </div>
    </div>
</div>
<script type="text/javascript">
   window.frm.components.init('RegistrationComponent', '.jsRegistrationComponent');
</script>