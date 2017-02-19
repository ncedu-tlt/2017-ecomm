(function ($, window) {

    var frm = window.frm;
    var ProductComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            this.content.find('.jsUsersRating')
                .rating({initialRating: 2, maxRating: 5})
                .rating('disable');

            this.content.find('.jsUserReviewRating')
                .rating({initialRating: 2, maxRating: 5})
                .rating('setting', 'onRate', this.onRatingChange.bind(this));

            this.removePopup = this.content.find('.jsRemoveButton')
                .popup({
                    popup: this.content.find('.jsRemoveForm'),
                    on: 'click'
                });

            var
                reviewBody = this.content.find('.jsThisUserReview'),
                reviewData = reviewBody.find('.jsReviewData'),
                thisUserRating = reviewBody.find('.jsUsersRating').attr('data-rating'),
                reviewText = reviewBody.find('.jsReview p').text(),
                productId = reviewBody.attr('data-value');

            reviewBody.find('.jsEdit').on('click', function () {
                $.post( '/review', { reviewActions: 'edit' }, function( data ) {
                    reviewData.html(data);
                    frm.events.fire('addEditDataToReview', {
                        userRating : thisUserRating,
                        reviewText : reviewText,
                        productId : productId});
                });
            });

            this.content.find('.jsNegative').on('click', this.hidePopup.bind(this));

            this.content.find('.jsEdit').on('click', this.doRequestToServer);
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
