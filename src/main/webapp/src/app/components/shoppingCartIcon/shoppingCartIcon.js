(function ($, window) {

    var frm = window.frm;

    var ShoppingCartIconComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            $.get('shoppingCartIcon', function(responseText){
                console.log(responseText);
            });
        }
    });


    frm.components.register('ShoppingCartIconComponent', ShoppingCartIconComponent);

})(jQuery, window);
