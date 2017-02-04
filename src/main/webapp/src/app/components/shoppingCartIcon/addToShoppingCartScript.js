(function ($, window) {

    var frm = window.frm;

    var AddToCartComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            console.log('Add to cart');
        }
    });

    frm.components.register('AddToCartComponent', AddToCartComponent);

})(jQuery, window);