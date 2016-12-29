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
                    <form class="ui form" style="margin: 5em 0" method="post"
                          action="#">
                        <div class="field">
                            <input type="password" name="password" placeholder="Password">
                        </div>
                        <div class="field">
                            <input type="password" name="passwordRepeat" placeholder="Repeat password">
                        </div>
                        <div class="ui error message"></div>
                        <button class="fluid ui button">Change password</button>
                    </form>
                </div>
            </div>
            <div class="ui message warning">
                <p><c:forEach var="parameter" items="${requestScope.answer}">
                    <c:out value="${parameter}"/>
                </c:forEach></p>
            </div>
        </div>
    </div>
</div>

<%-- JS controller initilization --%>
<script type="text/javascript">
    window.frm.components.init('changePasswordComponent', '.jsChangePasswordComponent');
</script>