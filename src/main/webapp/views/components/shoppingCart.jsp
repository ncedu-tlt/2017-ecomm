<div class="ui container jsShoppingCardComponent main-content">
    <h3 class="ui center aligned header">
        Your Cart
    </h3>
    <div class="ui divided items">
        <div class="item">
            <div class="ui small image">
                <img src="http://semantic-ui.com/images/wireframe/image.png">
            </div>
            <div class="middle aligned content">
                <div class="header">
                    Dell XPS 13
                </div>
                <div class="description">
                    <div class="ui dropdown jsComponentDropdown">
                        Quantity:
                        <i class="dropdown icon"></i>
                        <div class="menu">
                            <div class="item" data-value="1">Choice 1</div>
                            <div class="item" data-value="2">Choice 2</div>
                            <div class="item" data-value="3">Choice 3</div>
                        </div>
                    </div>
                    <button class="circular right floated ui icon button middle aligned ">
                        <i class="icon remove"></i>
                    </button>
                    <h2 class="ui header right floated center middle aligned">
                        $720.00
                    </h2>
                </div>
            </div>
            <div class="ui divider"></div>
        </div>
        <div class="item">
            <div class="ui divider"></div>
            <div class="ui small image">
                <img src="http://semantic-ui.com/images/wireframe/image.png">
            </div>
            <div class="middle aligned content">
                <div class="header">
                    iPad Mini 2
                </div>
                <div class="description">
                    <div class="ui dropdown jsComponentDropdown">
                        Quantity:
                        <i class="dropdown icon"></i>
                        <div class="menu">
                            <div class="item" data-value="1">Choice 1</div>
                            <div class="item"  data-value="2">Choice 2</div>
                            <div class="item" data-value="3">Choice 3</div>
                        </div>
                    </div>
                    <button class="circular right floated ui icon button middle aligned">
                        <i class="icon remove"></i>
                    </button>
                    <h2 class="ui header right floated bottom middle aligned">
                        $438.00
                    </h2>
                </div>
            </div>
        </div>
    </div>
    <div class="ui section divider"></div>
    <div class="ui grid">
        <div class="eight wide column">
            <div class="inline field">
                <div class="ui right pointing label big">
                    Limit:
                </div>
                <div class="ui left labeled button" tabindex="0">
                    <input type="text" class="ui basic right pointing label">
                    <button class="ui  button">APPLY</button>
                </div>
            </div>
        </div>
        <div class="eight wide column">
            <h3 class="ui grey header right floated bottom">$1188.00</h3>
            <h3 class="ui header right floated bottom">
                Total:
            </h3>
        </div>
    </div>
    <div class="ui grid">
        <div class="eight wide column">
            <button class="ui secondary basic button">PRINT</button>
        </div>
        <div class="eight wide column">
            <button class="ui secondary basic right floated button">CHECKOUT</button>
        </div>
    </div>
</div>
<script>
    window.frm.components.init('ShoppingCardComponent', '.jsShoppingCardComponent');
</script>