<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ui container jsShoppingCartComponent main-content">
    <h3 class="ui center aligned header">
        Payment
    </h3>
    <div class="ui form">
        <div class="ui divided items">
            <div class="grouped fields">
                <label>Select a Payment Method</label>
                <div class="field">
                    <div class="ui radio checkbox">
                        <input type="radio" name="example2" checked="checked">
                        <label>Cash</label>
                    </div>
                </div>
                <div class="field">
                    <div class="ui radio checkbox">
                        <input type="radio" name="example2">
                        <label>Credit card (online)</label>
                    </div>
                </div>
                <div class="field">
                    <div class="ui radio checkbox">
                        <input type="radio" name="example2">
                        <label>Payment of the legal entity</label>
                    </div>
                </div>
                <div class="field">
                    <div class="ui radio checkbox">
                        <input type="radio" name="example2">
                        <label>Purchase on credit</label>
                    </div>
                </div>
            </div>
            <button class="ui secondary button">
                Okay
            </button>
            <button class="ui button">
                Cancel
            </button>
        </div>
    </div>
</div>
