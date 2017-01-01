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
            <form class="ui equal dividing width form">
                <div class="field">
                    <label> Your E-mail: </label>
                    <input type="text" name="first-name" placeholder="E-mail">
                </div>
                <div class="field">
                    <label> Your Password: </label>
                    <input type="password" name="last-name" placeholder="Password">
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
                            <p><a class="item" href="<c:url value="/recovery"/>">Forgot your password?</a></p>
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
                            <p>Not a member yet?<a class="item" href="<c:url value="/registration"/>">Register</a></p>
                    </div>
                </div>
            </form>
            <div class="ui hidden divider"></div>
        </div>
    </div>
</div>
<script>
    window.frm.components.init('LoginComponent', '.jsLoginComponent');
</script>