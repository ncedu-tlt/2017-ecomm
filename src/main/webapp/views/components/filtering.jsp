<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/filter?category_id=${param.category_id}"/>

<div class="container jsFilteringComponent">
    <div class="ui left filter vertical sidebar labeled icon menu">
        <h2 class="ui header">Filter</h2>
        <div class="ui vertical accordion menu" style="width:200px;">
            <c:forEach var="filter" items="${requestScope.filters}">
                <div class="item">
                    <a class="ui active title medium header">
                        <i class="dropdown icon"></i>
                            ${filter.getName()}
                    </a>
                    <div class="active">
                        <div class="ui form">
                            <div class="grouped fields">
                                <c:forEach var="value" items="${filter.getValues()}">
                                    <div class="field">
                                        <div class="ui checkbox">
                                            <input type="checkbox" name="small">
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
    </div>
    <div class="ui icon blue right attached big jsShowFiltering button" style="position: fixed;">
        <i class="filter icon"></i>
    </div>
</div>
<script type="text/javascript">
    window.frm.components.init('FilteringComponent', '.jsFilteringComponent');
</script>