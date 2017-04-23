(function ($, window) {

    var frm = window.frm;
    var ELEMENTS = {
        PRODUCT_RATING: '.jsCompareProductRating'
    };

    var ProductComparator = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {

            this.content.find(ELEMENTS.PRODUCT_RATING).rating({initialRating: 2, maxRating: 5}).rating('disable');
        }


    });

    frm.components.register('productComparator', ProductComparator);

})(jQuery, window);
