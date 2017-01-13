
(function ($, window) {

    var frm = window.frm;
    var ProductComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            this.content.find('.rating').rating({initialRating: 2, maxRating: 10}).rating('disable');
        }

    });

    frm.components.register('productDetails', ProductComponent);

})(jQuery, window);
