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
                .rating({initialRating: 2, maxRating: 5})
                .rating('setting', 'onRate', this.onRatingChange.bind(this));

        },
        onRatingChange: function (value) {
            this.content.find('.jsRating').val(value);

        }


    });

    frm.components.register('productReviews', ProductComponent);

})(jQuery, window);
