<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/companyInfo"/>
<div class="ui grid one column center aligned container footerCompanyInfo">
    <div class="column">
        <p>
            <c:forEach var="item" items="${requestScope.companyInfo.getSocials()}" varStatus="loop">
            <a href="${item.getValue()}" class="text-border-right">${item.getId()}</a>
                <c:if test="${!loop.last}"><span> | </span></c:if>
            </c:forEach>
        </p>
        <p>
            ${requestScope.companyInfo.getEmail()}
            ${requestScope.companyInfo.getPhone()}
        </p>
        <p>
            ${requestScope.companyInfo.getRights()}
        </p>
    </div>
</div>