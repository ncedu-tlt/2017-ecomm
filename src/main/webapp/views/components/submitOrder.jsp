<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<jsp:include page="/payment"/>--%>
<div class="ui container jsShoppingCartComponent main-content">
    <h3 class="ui center aligned header">
        Payment
    </h3>
    <div class="ui form">
        <form method="post" action="submitOrder" class="ui divided items">
            <div class="ui message">
                <div class="header">
                    Confirm your order?
                </div>
                <p>Push Okay if you agree, or Cancel if you not</p>
            </div>
            <button class="ui secondary button" name="submitButton" value="okay">
                Okay
            </button>
            <button class="ui button" name="submitButton" value="cancel">
                Cancel
            </button>
        </form>
    </div>
</div>
