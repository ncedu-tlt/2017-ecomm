<div class="ui container jsShoppingCardComponent">
    <h3 class="ui center aligned header">
        Your Cart
    </h3>
    <div class="ui items">
        <div class="item">
            <div class="ui small image">
                <img src="http://semantic-ui.com/images/wireframe/image.png">
            </div>
            <div class="middle aligned content">
                <div class="header">
                    Dell XPS 13
                </div>
                <div class="description">
                    <div class="ui dropdown">
                        Quantity:
                        <i class="dropdown icon"></i>
                        <div class="menu">
                            <div class="item">Choice 1</div>
                            <div class="item">Choice 2</div>
                            <div class="item">Choice 3</div>
                        </div>
                    </div>
                </div>
                <div class="extra">
                    <h2 class="ui header right floated center">
                        $720.00
                        <i class="remove icon"></i>
                    </h2>
                </div>
            </div>
        </div>
        <div class="item">
            <div class="ui small image">
                <img src="http://semantic-ui.com/images/wireframe/image.png">
            </div>
            <div class="middle aligned content">
                <div class="header">
                    iPad Mini 2
                </div>
                <div class="description">
                    <div class="ui dropdown">
                        Quantity:
                        <i class="dropdown icon"></i>
                        <div class="menu">
                            <div class="item">Choice 1</div>
                            <div class="item">Choice 2</div>
                            <div class="item">Choice 3</div>
                        </div>
                    </div>
                </div>
                <div class="extra">
                    <h2 class="ui header right floated bottom">
                        $438.00
                        <i class="remove icon"></i>
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
                <div class="ui input">
                    <input type="text">
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
    /* TODO: remove later and init component from source js */
    $('.dropdown').dropdown()
        window.frm.components.init('ShoppingCardComponent', '.jsShoppingCardComponent');
</script>