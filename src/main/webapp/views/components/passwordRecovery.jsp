<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container jsRecoveryComponent main-content">
    <div class="ui two column centered grid">
        <div class="column">
            <h2 class="ui center aligned icon header">
                <i class="circular unlock icon"></i>
                Password Recovery
            </h2>
            <div class="ui center aligned vertical segment">
                <p>
                    We will send you further instructions to the provided email address
                </p>
            </div>
            <div class="ui three column centered grid">
                <div class="column">
                    <form class="ui form" style="margin: 5em 0" method="post"
                          action="${pageContext.request.contextPath}/recovery">
                        <div class="field">
                            <input type="text" name="email" placeholder="Email">
                        </div>

                        <div class="ui error message"></div>

                        <button class="fluid ui button">Send</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <c:if test="${requestScope.answer == true}">
        <div class="ui success message">
            <p>Message with instructions was sent to you in the email</p>
        </div>
    </c:if>
    <c:if test="${requestScope.answer == false}">
        <div class="ui error message">
            <p>Uncorrect email.</p>
        </div>
    </c:if>
</div>
<%-- JS controller initilization --%>
<script type="text/javascript">
    window.frm.components.init('RecoveryComponent', '.jsRecoveryComponent');
</script>
