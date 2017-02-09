<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/filter?category_id=${param.category_id}"/>

<div class="container jsFilteringComponent">
    <div class="ui left filter vertical sidebar labeled menu">
        <br>
        <h2 class="ui header">Filter</h2>
        <form class="ui equal dividing width form" action="${pageContext.request.contextPath}/filtering" method="get">
            <input type="hidden" name="category_id" value="${param.category_id}"/>
            <div class="ui item">
                <h2 class="ui header small">Price</h2>
                <div class="tow fields ">
                    <div class="field">
                        <input class="jsOnlyNumber" type="text" name="min" placeholder="min"
                               value="${requestScope.priceRange.getMin()}"/>
                    </div>
                    <div class="field">
                        <input class="jsOnlyNumber" type="text" name="max" placeholder="max"
                               value="${requestScope.priceRange.getMax()}"/>
                    </div>
                </div>
                <button class="ui primary button" type="submit">Filter</button>
            </div>
            <div class="ui vertical accordion menu">
                <c:set var="active" value="active"/>
                <c:forEach var="filter" items="${requestScope.filters}">
                    <div class="item">
                        <a class="ui ${active} title medium header">
                            <i class="dropdown icon"></i>
                                ${filter.getName()}
                        </a>
                        <div class="${active} content">
                            <c:set var="active" value=""/>
                            <div class="ui form">
                                <div class="grouped fields">
                                    <c:forEach var="value" items="${filter.getValues()}">
                                        <div class="field">
                                            <div class="ui checkbox">
                                                <input type="checkbox" tabindex="0" class="hidden"
                                                       name="${filter.getName()}"
                                                       value="${value}">
                                                <label>${value}</label>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </form>
    </div>
    <div class="ui icon blue right attached big jsShowFiltering button" style="position: fixed;">
        <i class="filter icon"></i>
    </div>
</div>
<script type="text/javascript">
    window.frm.components.init('FilteringComponent', '.jsFilteringComponent');
</script>
