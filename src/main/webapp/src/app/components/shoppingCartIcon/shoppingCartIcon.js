(function ($, window) {

    var frm = window.frm;

    var ShoppingCartComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            $('#addToCart').click(function(){
                console.log('click');
            });
            console.log('function was called');
        }
    });

    frm.components.register('ShoppingCartComponent', ShoppingCartComponent);

})(jQuery, window);
