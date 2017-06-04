<div class="ui item jsShowButtonComponent">
    <div class="ui icon blue right attached big button jsShowFilters" style="position: fixed; top: 200px;">
        <i class="filter icon"></i>
    </div>
</div>
<script>
    window.frm.components.init('ShowButtonComponent', '.jsShowButtonComponent', {
        baseUrl: '${pageContext.request.contextPath}'
    });
</script>