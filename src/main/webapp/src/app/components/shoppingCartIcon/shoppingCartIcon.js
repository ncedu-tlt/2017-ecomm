(function ($, window) {

    var frm = window.frm;

    var ShoppingCartIconComponent = frm.inheritance.inherits(frm.components.Component, {
        init: function () {
            this.showQuantityIfHave();
            frm.events.on('addToCart', function (productIdParam) {
                var shoppingCartIcon = this.content.find('.jsShoppingCartIcon');
                $.post('/addToShoppingCart',
                    {productId: productIdParam},
                    function (result) {
                        shoppingCartIcon.text(result);
                        $(this).showQuantityIfHave();
                    });
            });
        },
        showQuantityIfHave: function () {
            var shoppingCartIcon = this.content.find('.jsShoppingCartIcon');
            if (shoppingCartIcon.html().trim() === '') {
                shoppingCartIcon.hide();
            }
            else {
                shoppingCartIcon.show();
            }
        }
    });

    frm.components.register('ShoppingCartIconComponent', ShoppingCartIconComponent);

})(jQuery, window);

