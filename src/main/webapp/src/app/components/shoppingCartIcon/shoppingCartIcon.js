(function ($, window) {

    var frm = window.frm;

    var ShoppingCartComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            
        }
    });

    frm.components.register('ShoppingCartComponent', ShoppingCartComponent);

})(jQuery, window);
