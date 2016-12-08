<div class="container jsFilteringComponent">
    <div class="ui left filter vertical sidebar labeled icon menu">
        <div class="ui vertical accordion menu ">
            <div class="item">
                <a class="active title">
                    <i class="dropdown icon"></i>
                    Size
                </a>
                <div class="active content">
                    <div class="ui form">
                        <div class="grouped fields ">
                            <div class="field">
                                <div class="ui radio checkbox">
                                    <input type="radio" name="size" value="small">
                                    <label>Small</label>
                                </div>
                            </div>
                            <div class="field">
                                <div class="ui radio checkbox">
                                    <input type="radio" name="size" value="medium">
                                    <label>Medium</label>
                                </div>
                            </div>
                            <div class="field">
                                <div class="ui radio checkbox">
                                    <input type="radio" name="size" value="large">
                                    <label>Large</label>
                                </div>
                            </div>
                            <div class="field">
                                <div class="ui radio checkbox">
                                    <input type="radio" name="size" value="x-large">
                                    <label>X-Large</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="item">
                <a class="title">
                    <i class="dropdown icon"></i>
                    Colors
                </a>
                <div class="content">
                    <div class="ui form">
                        <div class="grouped fields">
                            <div class="field">
                                <div class="ui checkbox">
                                    <input type="checkbox" name="small">
                                    <label>Red</label>
                                </div>
                            </div>
                            <div class="field">
                                <div class="ui checkbox">
                                    <input type="checkbox" name="medium">
                                    <label>Orange</label>
                                </div>
                            </div>
                            <div class="field">
                                <div class="ui checkbox">
                                    <input type="checkbox" name="large">
                                    <label>Green</label>
                                </div>
                            </div>
                            <div class="field">
                                <div class="ui checkbox">
                                    <input type="checkbox" name="x-large">
                                    <label>Blue</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="ui basic black right attached big filtering button" style="position: fixed;">
        <i class="filter icon"></i>
    </div>
</div>
<script type="text/javascript">
    $('.ui.accordion')
            .accordion();
    window.frm.components.init('FilteringComponent', '.jsFilteringComponent');
</script>