(function ($, window) {

    var frm = window.frm;

    var ReviewData = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {

            var
                userRating = this.content.find('.jsEditUserReviewRating'),
                oldReviewText = this.content.find('.editTextArea'),
                ratingField = this.content.find('.jsEditRating'),
                productId = this.content.find('.productId');

            frm.events.on('addEditDataToReview', function (param) {
                oldReviewText.html(param.reviewText);
                productId.attr('value', param.productId);
                userRating.attr('data-rating', param.userRating);
                userRating.rating('setting', 'onRate', function (value) {
                    ratingField.val(value);
                });
            });
        }
    });

    frm.components.register('ReviewData', ReviewData);

})(jQuery, window);