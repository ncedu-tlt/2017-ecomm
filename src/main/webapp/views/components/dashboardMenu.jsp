<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ui center aligned text container jsDashboardComponent">
    <h3>Dashboard</h3>
    <div class="ui two item menu secondary pointing">
        <a href="<c:url value="/profile"/>" class="item menuElement jsDashboardElement jsProfile">
            Profile
        </a>
        <a href="<c:url value="/orders"/>" class="item menuElement jsDashboardElement jsOrderHistory">
            Order History
        </a>
    </div>
    <div class="ui page dimmer jsDimmerDashboard">
        <div class="content">
            <div class="ui text loader">Loading</div>
        </div>
    </div>
</div>
<script type="text/javascript">
    window.frm.components.init('DashboardComponent', '.jsDashboardComponent');
</script>