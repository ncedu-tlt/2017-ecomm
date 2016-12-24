<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ui four column grid centered main-content">
    <div class="row">
        <h2 class="ui center aligned icon header">
            <i class="circular registered in icon"></i>
            Registration
        </h2>
    </div>
    <form action= "/registration" method="post" class="ui equal dividing width form">
        <div class="field">
            <label for="email">Your email:</label>
            <input type="text" name="email" id="email" placeholder="E-mail">
        </div>
        <div class="field">
            <label for="password">Your password</label>
            <input type="password" name="password" id="password" placeholder="Password">
        </div>
        <div class="field">
            <label for="ConfirmPassword">Confirm password</label>
            <input type="password" name="ConfirmPassword" id="ConfirmPassword" placeholder="Confirm password">
        </div>

        <input class="ui blue animated fade button" type="submit" value="Registration" />
        <%--<div class="ui blue animated fade button " tabindex="0">--%>
            <%--<div class="visible content">Registration</div>--%>
            <%--<div class="hidden content">Send data?</div>--%>
        <%--</div>--%>

        <p>Already a member? <a href='<c:url value="/login"/>'> Login </a></p>
    </form>
</div>