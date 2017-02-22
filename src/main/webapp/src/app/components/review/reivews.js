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

            this.content.find('.jsThisUserReview').find('.jsEdit').on('click', this.getData.bind(this));

            this.content.find('.jsNegative').on('click', this.hidePopup.bind(this));

        },
        getData: function () {
            $.post('/review',
                {reviewActions: 'edit'},
                this.writeFormForUpdateComment.bind(this));
        },

        writeFormForUpdateComment: function (data) {

            var reviewBody = this.content.find('.jsThisUserReview');
            var thisUserRating = reviewBody.find('.jsUsersRating').attr('data-rating');
            var reviewText = reviewBody.find('.jsReview p').text();
            var productId = reviewBody.attr('data-value');
            var reviewData = reviewBody.find('.jsReviewData');


            reviewData.html(data);

            var userRatingReload = this.content.find('.jsEditUserReviewRating');
            var oldReviewText = this.content.find('.editTextArea');
            var ratingField = this.content.find('.jsEditRating');
            var cancelButton = this.content.find('.jsCancel');
            var productIdReload = this.content.find('.productId');

            oldReviewText.html(reviewText);
            productIdReload.attr('value', productId);
            ratingField.attr('value', thisUserRating);
            userRatingReload.attr('data-rating', thisUserRating);

            userRatingReload.rating('setting', 'onRate', function (value) {
                ratingField.val(value);
            });

            cancelButton.on('click', this.reloadPage);
        },
        reloadPage: function () {
            window.location.reload();
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
