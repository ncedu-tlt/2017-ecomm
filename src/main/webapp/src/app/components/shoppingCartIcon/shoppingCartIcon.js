(function ($, window) {

    var frm = window.frm;

    var ELEMENTS = {
        SHOPPING_CART_ICON: '.jsShoppingCartIcon',
        DIMMER: '.jsDimmerAdd'
    };

    var EVENTS = {
        ADD_TO_CART: 'addToCart'
    };

    var CONDITIONS = {
        IS_ACTIVE: 'is active',
        EMPTY_QUANTITY: '0'
    };

    var DISPLAY = {
        SHOW: 'show',
        HIDE: 'hide',
        TRANSITION_PULSE: 'pulse',
        HIDDEN_CLASS: 'hidden'
    };

    var ShoppingCartIconComponent = frm.inheritance.inherits(frm.components.Component, {
        init: function () {
            var jsDimmer = this.content.find(ELEMENTS.DIMMER);
            this.dimmerConfig(jsDimmer);
            frm.events.on(EVENTS.ADD_TO_CART, this.sendRequest.bind(this, jsDimmer));
        },
        showIconIfNeed: function () {
            var cartIcon = this.content.find(ELEMENTS.SHOPPING_CART_ICON);
            if (cartIcon.text().trim() != CONDITIONS.EMPTY_QUANTITY) {
                cartIcon.removeClass(DISPLAY.HIDDEN_CLASS);
            }
        },
        sendRequest: function (jsDimmer, productIdParam) {
            $.ajax({
                url: this.params.addToCartUrl,
                type: 'POST',
                beforeSend: this.dimmerToggle(jsDimmer),
                data: {productId: productIdParam},
                success: this.displayQuantity.bind(this),
                error: this.redirectToLogin.bind(this),
                complete: setTimeout(this.dimmerToggle, 1000, jsDimmer)
            });
        },
        dimmerConfig: function (jsDimmer) {
            jsDimmer.dimmer({
                closable: false
            });
        },
        dimmerToggle: function (jsDimmer) {
            if (!jsDimmer.dimmer(CONDITIONS.IS_ACTIVE))
                jsDimmer.dimmer(DISPLAY.SHOW);
            else
                jsDimmer.dimmer(DISPLAY.HIDE);
        },
        displayQuantity: function (result) {
            var shoppingCartIcon = this.content.find(ELEMENTS.SHOPPING_CART_ICON);
            shoppingCartIcon.text(result);
            this.showIconIfNeed();
            shoppingCartIcon.transition(DISPLAY.TRANSITION_PULSE);
        },
        redirectToLogin: function () {
            window.location.href = this.params.loginUrl;
        }
    });

    frm.components.register('ShoppingCartIconComponent', ShoppingCartIconComponent);

})(jQuery, window);

