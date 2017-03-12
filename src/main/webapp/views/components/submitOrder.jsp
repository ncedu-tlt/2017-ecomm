<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ui container jsShoppingCartComponent main-content">
    <h3 class="ui center aligned header">
        Payment
    </h3>
    <div class="ui form">
        <div class="ui divided items">
            <div class="ui message">
                <div class="header">
                    Congratulations
                </div>
                <p>Your Order was submitted</p>
            </div>
            <button class="ui secondary button" onclick="window.location.href = '${pageContext.request.contextPath}' + '/home'">
                Okay
            </button>
        </div>
    </div>
</div>
