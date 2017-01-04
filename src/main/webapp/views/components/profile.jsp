<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ui center aligned text container segment jsProfileComponent" data-tab="profile">
    <form class="ui form" method="post"
          action="#">
        <div class="field">
            <input type="text" name="firstName" placeholder="First Name">
        </div>
        <div class="field">
            <input type="text" name="lastName" placeholder="Last Name">
        </div>
        <div class="field">
            <input type="text" name="email" placeholder="Email">
        </div>
        <div class="field">
            <input type="password" name="password" placeholder="Password">
        </div>
        <div class="field">
            <input type="password" name="passwordConfirm" placeholder="Current Password">
        </div>
        <button class="ui animated primary button">
            <div class="visible content">Apply Changes</div>
            <div class="hidden content">
                <i class="repeat icon outline"></i>
            </div>
        </button>
        <div class="ui error message"></div>
    </form>
</div>
<c:set var="request" scope="session" value="${requestScope.answer}"/>
<c:if test="${request != null}">
    <div class="ui message warning ">
        <p>${requestScope.answer}</p>
    </div>
</c:if>
<%-- JS controller initilization --%>
<script type="text/javascript">
    window.frm.components.init('profileComponent', '.jsProfileComponent');
</script>