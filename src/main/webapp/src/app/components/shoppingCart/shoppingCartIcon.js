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
            $.get("shoppingCartIcon", function(responseText) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                $("#quantityProducts").text(responseText);           // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
            });


        }
    });

    frm.components.register('ShoppingCartIconComponent', ShoppingCartIconComponent);

})(jQuery, window);
