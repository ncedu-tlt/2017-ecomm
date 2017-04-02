(function ($, window) {

    var frm = window.frm;

    var ELEMENTS = {
        SHOPPING_CART_ICON: '.jsShoppingCartIcon',
        DIMMER: '.jsDimmerAdd'
    };
    var ShoppingCartIconComponent = frm.inheritance.inherits(frm.components.Component, {
        init: function () {
            var jsDimmer = this.content.find(ELEMENTS.DIMMER);
            this.dimmerConfig(jsDimmer);
            this.showIconIfNeed();
            frm.events.on('addToCart', this.sendRequest.bind(this, jsDimmer));
        },
        showIconIfNeed: function () {
            var cartIcon = this.content.find(ELEMENTS.SHOPPING_CART_ICON);
            if (cartIcon.text().trim() != '0') {
                cartIcon.removeClass('hidden');
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
            if (!jsDimmer.dimmer('is active'))
                jsDimmer.dimmer('show');
            else
                jsDimmer.dimmer('hide');
        },
        displayQuantity: function (result) {
            var shoppingCartIcon = this.content.find(ELEMENTS.SHOPPING_CART_ICON);
            shoppingCartIcon.text(result);
            shoppingCartIcon.transition('pulse');
        },
        redirectToLogin: function () {
            window.location.href = this.params.loginUrl;
        }
    });

    frm.components.register('ShoppingCartIconComponent', ShoppingCartIconComponent);

})(jQuery, window);

