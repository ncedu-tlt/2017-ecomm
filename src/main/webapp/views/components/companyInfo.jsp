<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/companyInfo">
    <jsp:param name="socialsUrlId" value="facebookUrl"/>
    <jsp:param name="socialsUrlId" value="twitterUrl"/>
    <jsp:param name="socialsUrlId" value="google+Url"/>
    <jsp:param name="socialsUrlId" value="vkUrl"/>
</jsp:include>
<div class="ui grid one column center aligned container footerCompanyInfo">
    <div class="column">
        <p>
            <c:set var="socialsUrl" value="${requestScope.companyInfo.getSocials()}"/>
            <a href="${socialsUrl.get("facebookUrl")}" class="text-border-right">Facebook</a>
            <span> | </span>
            <a href="${socialsUrl.get("twitterUrl")}" class="text-border-right">Twitter</a>
            <span> | </span>
            <a href="${socialsUrl.get("google+Url")}" class="text-border-right">Google+</a>
            <span> | </span>
            <a href="${socialsUrl.get("vkUrl")}" class="text-border-right">VK</a>
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