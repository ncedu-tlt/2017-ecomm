(function ($, window) {

    var frm = window.frm;

    var ShoppingCartIconComponent = frm.inheritance.inherits(frm.components.Component, {
        init: function () {
            var shoppingCartIcon = this.content.find('.jsShoppingCartIcon');
            this.showQuantityIfHave(shoppingCartIcon);
            frm.events.on('addToCart', function (productIdParam) {
                var jsDimmer = this.content.find('.jsDimmerAdd');
                this.dimmerConfig(jsDimmer);
                jsDimmer.dimmer('show');
                this.ajaxRequest(productIdParam, shoppingCartIcon, jsDimmer);
            }.bind(this));
        },
        showQuantityIfHave: function (shoppingCartIcon) {
            if (!shoppingCartIcon.html().trim()) {
                shoppingCartIcon.hide();
            }
            else {
                shoppingCartIcon.transition('jiggle');
            }
        },
        dimmerConfig: function(jsDimmer) {
            jsDimmer.dimmer({
                closable: false
            });
        },
        ajaxRequest: function(productIdParam, shoppingCartIcon, jsDimmer) {
            $.ajax({
                url: this.params.baseIconUrl + '/addToShoppingCart',
                type: 'POST',
                data: {productId: productIdParam},
                success: function (result) {
                    shoppingCartIcon.text(result);
                    shoppingCartIcon.transition('jiggle');
                },
                error: function () {
                    window.location.href = this.params.baseIconUrl + '/login';
                }.bind(this)
            }).done(function () {
                jsDimmer.dimmer('hide');
            });
        }
    });

    frm.components.register('ShoppingCartIconComponent', ShoppingCartIconComponent);

})(jQuery, window);

