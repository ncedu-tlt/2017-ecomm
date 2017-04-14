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
            this.jsDimmer = this.content.find(ELEMENTS.DIMMER).dimmer({
                closable: false
            });
            frm.events.on(EVENTS.ADD_TO_CART, this.sendRequest.bind(this));
        },
        sendRequest: function (productIdParam) {
            $.ajax({
                url: this.params.addToCartUrl,
                beforeSend: this.showDimmer.bind(this),
                type: 'POST',
                data: {productId: productIdParam},
                success: this.displayQuantity.bind(this),
                complete: this.hideDimmer.bind(this),
                error: this.redirectToLogin.bind(this)
            });
        },
        showDimmer: function() {
            this.jsDimmer.dimmer(DISPLAY.SHOW);
        },
        hideDimmer: function() {
            this.jsDimmer.dimmer(DISPLAY.HIDE);
        },
        displayQuantity: function (result) {
            var shoppingCartIcon = this.content.find(ELEMENTS.SHOPPING_CART_ICON);
            shoppingCartIcon.text(result);
            this.showIconIfNeed();
            shoppingCartIcon.transition(DISPLAY.TRANSITION_PULSE);
        },
        showIconIfNeed: function () {
            var cartIcon = this.content.find(ELEMENTS.SHOPPING_CART_ICON);
            if (cartIcon.text().trim() != CONDITIONS.EMPTY_QUANTITY) {
                cartIcon.removeClass(DISPLAY.HIDDEN_CLASS);
            }
        },
        redirectToLogin: function () {
            window.location.href = this.params.loginUrl;
        }
    });

    frm.components.register('ShoppingCartIconComponent', ShoppingCartIconComponent);

})(jQuery, window);

