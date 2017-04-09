(function ($, window) {

    var frm = window.frm;

    var ELEMENTS = {
        PRODUCT_RATING: '.rating',
        ADD_TO_CART: '.jsAddToCart',
        ADD_TO_COMPARE: '.jsAddToCompare'
    };
    var EVENTS = {
        CLICK: 'click',
        ADD_TO_CART: 'addToCart',
        ADD_TO_COMPARE: 'addToCompare'
    };

    var ProductListComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */

        init: function () {
            this.content.find(ELEMENTS.PRODUCT_RATING).rating({initialRating: 2, maxRating: 5}).rating('disable');

            this.content.find(ELEMENTS.ADD_TO_CART).on(EVENTS.CLICK, function () {
                var productId = $(this).val();
                frm.events.fire(EVENTS.ADD_TO_CART, productId);
            });

            this.content.find(ELEMENTS.ADD_TO_COMPARE).on(EVENTS.CLICK, function () {
                var productId = $(this).val();
                frm.events.fire(EVENTS.ADD_TO_COMPARE, productId);
            });
        }

    });

    frm.components.register('ProductListComponent', ProductListComponent);

})(jQuery, window);
