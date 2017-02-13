<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container jsFilteringComponent">
    <div class="ui left filter vertical sidebar labeled menu">
        <br>
        <h2 class="ui header center aligned">Filter</h2>
        <form class="ui equal dividing width form" action="${pageContext.request.contextPath}/filtering" method="get">
            <input type="hidden" name="category_id" value="${param.category_id}"/>
            <div class="ui item">
                <h2 class="ui header small center aligned">Price</h2>
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
                <button class="ui primary fluid button" type="submit">Filter</button>
            </div>
            <div class="ui divider hidden"></div>
            <div class="ui vertical fluid accordion menu">
                <c:set var="activeFirst" value="active"/>
                <c:forEach var="filter" items="${requestScope.filters}">
                    <c:if test="${!filter.getValues().isEmpty()}">
                    <div class="item">
                        <c:if test="${filter.isValuesHaveChecked()}">
                            <c:set var="active" value="active"/>
                        </c:if>
                        <c:if test="${!filter.isValuesHaveChecked()}">
                            <c:set var="active" value=""/>
                        </c:if>
                        <a class="ui ${active} ${activeFirst} title medium header">
                            <i class="dropdown icon"></i>
                                ${filter.getName()}
                        </a>
                        <div class="${active} ${activeFirst} content">
                            <c:set var="activeFirst" value=""/>
                            <div class="ui form">
                                <div class="grouped fields">
                                    <c:forEach var="value" items="${filter.getValues()}">
                                        <div class="field">
                                            <div class="ui checkbox">
                                                <input type="checkbox" ${value.isChecked()?"checked":""}
                                                       name="${filter.getName()}"
                                                       value="${value.getName()}"/>
                                                <label>${value.getName()}</label>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                    </c:if>
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
