(function ($, window) {

    var frm = window.frm;

    var ShoppingCartIconComponent = frm.inheritance.inherits(frm.components.Component, {
        init: function () {
            frm.events.on('addToCart', function(param){
                $('#quantityProducts').html(param);
            });

        }
    });

    frm.components.register('ShoppingCartIconComponent', ShoppingCartIconComponent);

})(jQuery, window);

