<div class="container jsFilteringComponent">
    <div class="ui left filter vertical sidebar labeled icon menu">
        <h2 class="ui header">Filter</h2>
        <div class="ui vertical accordion menu ">
            <div class="item">
                <a class="active title">
                    <i class="dropdown icon"></i>
                    Brands
                </a>
                <div class="active">
                    <div class="ui form">
                        <div class="grouped fields">
                            <div class="field">
                                <div class="ui checkbox">
                                    <input type="checkbox" name="small">
                                    <label>Asus</label>
                                </div>
                            </div>
                            <div class="field">
                                <div class="ui checkbox">
                                    <input type="checkbox" name="medium">
                                    <label>Apple</label>
                                </div>
                            </div>
                            <div class="field">
                                <div class="ui checkbox">
                                    <input type="checkbox" name="large">
                                    <label>HP</label>
                                </div>
                            </div>
                            <div class="field">
                                <div class="ui checkbox">
                                    <input type="checkbox" name="x-large">
                                    <label>Lenovo</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="ui basic black right attached big jsShowFiltering button" style="position: fixed;">
        <i class="filter icon"></i>
    </div>
</div>
<script type="text/javascript">
    window.frm.components.init('FilteringComponent', '.jsFilteringComponent');
</script>