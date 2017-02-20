(function ($, window) {

    var frm = window.frm;

    var ShoppingCartIconComponent = frm.inheritance.inherits(frm.components.Component, {
        init: function () {
            var shoppingCartIcon = $('.jsShoppingCartIcon');
            this.showQuantityIfHave();
            frm.events.on('addToCart', function (productIdParam) {
                $.post('/addToShoppingCart',
                    {productId: productIdParam},
                    function (result) {
                        if (shoppingCartIcon.html().trim() === '') {
                            shoppingCartIcon.show();
                        }
                        shoppingCartIcon.text(result);
                    });
            });
        },
        showQuantityIfHave: function () {
            if (this.content.find('.jsShoppingCartIcon').html().trim() === '') {
                $('.jsShoppingCartIcon').hide();
            }
            else {
                $('.jsShoppingCartIcon').show();
            }
        }
    });

    frm.components.register('ShoppingCartIconComponent', ShoppingCartIconComponent);

})(jQuery, window);

