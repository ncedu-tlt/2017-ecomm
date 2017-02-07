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

            this.removePopup = this.content.find('.removeButton')
                .popup({
                    popup: this.content.find('.removeForm'),
                    on: 'click'
                });

            this.content.find('.negative').on('click', this.hidePopup.bind(this));
        },

        onRatingChange: function (value) {
            this.content.find('.jsRating').val(value);

        },
        hidePopup: function () {
            this.removePopup.popup('hide');
        }


    });

    frm.components.register('productReviews', ProductComponent);

})(jQuery, window);
