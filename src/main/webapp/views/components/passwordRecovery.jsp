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
                    <form class="ui form jsPasswordRecoveryForm" method="post"
                          action="${pageContext.request.contextPath}/recovery">
                        <div class="field">
                            <input type="text" name="email" placeholder="Email">
                        </div>
                        <div class="ui error message"></div>
                        <button class="ui fluid animated primary button">
                            <div class="visible content">Send</div>
                            <div class="hidden content">
                                <i class="mail outline icon"></i>
                            </div>
                        </button>
                    </form>
                </div>
            </div>
            <c:set var="request" scope="session" value="${requestScope.answer}"/>
            <c:if test="${request != null}">
                <div class="ui message warning">
                    <p>${requestScope.answer}</p>
                </div>
            </c:if>
        </div>
    </div>
</div>
<%-- JS controller initilization --%>
<script type="text/javascript">
    window.frm.components.init('RecoveryComponent', '.jsRecoveryComponent');
</script>
