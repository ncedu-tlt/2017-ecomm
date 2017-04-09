<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/compareIcon"/>
<div class="ui item jsProductComparatorIcon">
    <a href="${pageContext.request.contextPath}/compare" class="circular ui icon button">
        <i class="icon bar chart"></i>
    </a>
</div>
<script>
    window.frm.components.init('productComparatorIcon', '.jsProductComparatorIcon', {
        addToCompareUrl: '${requestScope.addToCartURL}'
    });
</script>
