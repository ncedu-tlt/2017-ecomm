(function ($, window) {

    var frm = window.frm;

    var ShoppingCartComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            this.content.find("#addToCart").click(function(){

            });
        }
    });

    frm.components.register('ShoppingCartComponent', ShoppingCartComponent);

})(jQuery, window);
