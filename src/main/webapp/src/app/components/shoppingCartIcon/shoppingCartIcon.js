(function ($, window) {

    var frm = window.frm;

    var ShoppingCartIconComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
                // if (responseText == '0') {
                //     $('#shoppingCartTopPanel').css({'display': 'block'});
                //     $('#quantityProducts').css({'display': 'none'});
                // }
                // else {
                //     $('#shoppingCartTopPanel').css({'display': 'block'});
                //     $('#quantityProducts').css({'display': 'block'});
                // }
                // $('#quantityProducts').text(responseText);
        }
    });

    frm.components.register('ShoppingCartIconComponent', ShoppingCartIconComponent);

})(jQuery, window);
