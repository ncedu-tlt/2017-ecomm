<div class="ui text container jsEtalonComponent">
    <form class="ui form">
        <div class="field">
            <label>User Input</label>
            <input type="text" class="jsInput" value="${param.initialValue}">
        </div>
        <div class="field">
            <div class="ui checkbox">
                <input type="checkbox" tabindex="0" class="hidden">
                <label>Checkbox</label>
            </div>
        </div>
        <div class="field">
            <div class="ui slider checkbox">
                <input type="checkbox" tabindex="0" class="hidden">
                <label>Slider</label>
            </div>
            <label></label>
        </div>
        <div class="field">
            <div class="ui toggle checkbox">
                <input type="checkbox" tabindex="0" class="hidden">
                <label>Toggle</label>
            </div>
        </div>
        <div class="field">
            <button type="button" class="ui primary button jsPrint">
                Print
            </button>
            <button type="reset" class="ui button">
                Discard
            </button>
        </div>
    </form>
</div>

<script type="text/javascript">
    window.frm.components.initComponent('EtalonComponent', '.jsEtalonComponent', {
        message: '${param.message}'
    });
</script>