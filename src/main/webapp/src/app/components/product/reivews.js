(function ($, window) {

    var frm = window.frm;
    var ProductComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            this.content.find('.column .rating')
                .rating({initialRating: 2, maxRating: 5})
                .rating('disable');

            this.content.find('.field .rating')
                .rating({initialRating: 2, maxRating: 5});

            var
                rating = this.content.find('.field .rating').val();
            if (rating < 0) {
                this.content.find('.field .productRating').attr('value', rating);
            }
        }

    });

    frm.components.register('productReviews', ProductComponent);

})(jQuery, window);
