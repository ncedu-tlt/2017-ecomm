(function ($, window) {

    var frm = window.frm;

    var ShoppingCartIconComponent = frm.inheritance.inherits(frm.components.Component, {
        init: function () {
            var shoppingCartIcon = this.content.find('.jsShoppingCartIcon');
            this.showQuantityIfHave(shoppingCartIcon);
            frm.events.on('addToCart', function (productIdParam) {
                $.post('/addToShoppingCart',
                    {productId: productIdParam},
                    function (result) {
                        shoppingCartIcon.text(result);
                        shoppingCartIcon.transition('jiggle');
                    });
            });
        },
        showQuantityIfHave: function (shoppingCartIcon) {
            if (shoppingCartIcon.html().trim() === '') {
                shoppingCartIcon.hide();
            }
            else {
                shoppingCartIcon.transition('jiggle');
            }
        }
    });

    frm.components.register('ShoppingCartIconComponent', ShoppingCartIconComponent);

})(jQuery, window);

