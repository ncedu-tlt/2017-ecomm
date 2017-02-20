(function ($, window) {

    var frm = window.frm;

    var ShoppingCartIconComponent = frm.inheritance.inherits(frm.components.Component, {
        init: function () {

            frm.events.on('addToCart', function (productId) {
                $.post('/shoppingCartIcon',
                    {productId: productId},
                    function(result) {
                        $('.jsShoppingCartIcon').text(result);
                    });
            });

        }
    });

    frm.components.register('ShoppingCartIconComponent', ShoppingCartIconComponent);

})(jQuery, window);

