<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ui center aligned text container segment jsProfileComponent" id="profileBlock" data-tab="profile">
    <form class="ui form jsProfileForm" method="post"
          action="#">
        <div class="field">
            <input type="text" name="firstName" placeholder="First Name" value="${requestScope.firstName}">
        </div>
        <div class="field">
            <input type="text" name="lastName" placeholder="Last Name" value="${requestScope.lastName}">
        </div>
        <div class="field">
            <input type="text" name="email" placeholder="Email" value="${requestScope.email}">
        </div>
        <div class="field">
            <input type="password" name="password" placeholder="Password" value="${requestScope.password}">
        </div>
        <div class="field jsPasswordConfirm">
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
    <c:set var="request" scope="session" value="${requestScope.answer}"/>
    <c:if test="${request != null}">
        <div class="ui message warning ">
            <p>${requestScope.answer}</p>
        </div>
    </c:if>
</div>

<%-- JS controller initilization --%>
<script type="text/javascript">
    window.frm.components.init('DashboardComponent', '.jsDashboardComponent');
    window.frm.components.init('profileComponent', '.jsProfileComponent');
</script>