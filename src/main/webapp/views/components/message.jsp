<div class="ui basic modal jsErrorMessageContainer">
    <div class="ui icon header jsMessageHeader">
    </div>
    <div class="content jsErrorMessage">
        <p class="center aligned"></p>
    </div>
    <div class="ui three top attached actions buttons">
        <div class="ui red basic cancel inverted button jsClose">
            <i class="remove icon"></i>
            Close
        </div>
        <div class="ui green ok inverted button jsClearList">
            <i class="recycle icon"></i>
            Clear List
        </div>
        <div class="ui green ok inverted button jsGoToCompare">
            <i class="law icon"></i>
            Go to Compare
        </div>
    </div>
</div>
<script type="text/javascript">
    window.frm.components.init('ErrorMessage', '.jsErrorMessageContainer', {
        errorMessageContext: '${pageContext.request.contextPath}'
    });
</script>