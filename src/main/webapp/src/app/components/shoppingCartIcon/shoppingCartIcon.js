(function ($, window) {

    var frm = window.frm;

    var ELEMENTS = {
        SHOPPING_CART_ICON: '.jsShoppingCartIcon',
        DIMMER: '.jsDimmerAdd'
    };

    var ShoppingCartIconComponent = frm.inheritance.inherits(frm.components.Component, {
        init: function () {
            this.showIconIfNeed();
            frm.events.on('addToCart', this.ajaxRequest.bind(this));
        },
        showIconIfNeed: function() {
            var cartIcon = this.content.find(ELEMENTS.SHOPPING_CART_ICON);
            if (cartIcon.text().trim() != '0') {
                cartIcon.removeClass('hidden');
            }
        },
        ajaxRequest: function (productIdParam) {
            var jsDimmer = this.dimmerInit();
            $.ajax({
                url: this.params.addToCartUrl,
                beforeSend: this.dimmerToggle(jsDimmer),
                type: 'POST',
                data: {productId: productIdParam},
                success: function (result) {
                    this.displayQuantity(result);
                    setTimeout(this.dimmerToggle(jsDimmer), 1000);
                }.bind(this),
                error: this.errorAction.bind(this)
            });
        },
        dimmerInit: function () {
            var jsDimmer = this.content.find(ELEMENTS.DIMMER);
            jsDimmer.dimmer({
                closable: false
            });
            return jsDimmer;
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
            shoppingCartIcon.transition('jiggle');
        },
        errorAction: function () {
            window.location.href = this.params.loginUrl;
        }
    });

    frm.components.register('ShoppingCartIconComponent', ShoppingCartIconComponent);

})(jQuery, window);

