(function ($, window) {

    var frm = window.frm;

    var ShoppingCartIconComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            var counter = new Number($(".counter").text());
            $(".shoppingCartIcon").click(function(){
                window.location.replace("/views/pages/cart.jsp");
            });
        }
    });

    frm.components.register('ShoppingCartIconComponent', ShoppingCartIconComponent);

})(jQuery, window);
