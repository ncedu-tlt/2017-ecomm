(function ($, window) {

    var frm = window.frm;

    var ProductListComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            this.content.find('.rating').rating({initialRating: 2, maxRating: 5}).rating('disable');
            this.content.find('.jsAddToCart').on('click', function () {
                var productId = $(this).val();
                frm.events.fire('addToCart', productId);
            });
        }
    });

    frm.components.register('ProductListComponent', ProductListComponent);

})(jQuery, window);
