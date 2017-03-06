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

            this.content.find('.jsThisUserReview .jsEdit').on('click', this.getData.bind(this));

            this.content.find('.jsNegative').on('click', this.hidePopup.bind(this));

        },
        getData: function () {
            var reviewBody = this.content.find('.jsThisUserReview');
            var thisUserRating = reviewBody.find('.jsUsersRating').data('rating');
            var reviewText = reviewBody.find('.jsReview p').text();
            var productId = this.content.find('.jsThisUserReview').data('value');

            $.post('/review',
                {
                    reviewActions: 'edit',
                    productId: productId,
                    reviewText: reviewText,
                    thisUserRating: thisUserRating
                },
                this.writeFormForUpdateComment.bind(this));
        },

        writeFormForUpdateComment: function (data) {
            var reviewParent = this.content.find('.jsThisUserReview .jsReviewParent')
            var reviewData = reviewParent.find('.jsReviewData');

            reviewParent.append(data);

            var editData = reviewParent.find('.jsReviewEditData')
            reviewData.fadeOut('fast', function () {
                editData.fadeIn();
            });

            var ratingField = editData.find('.jsEditRating');
            var cancelButton = editData.find('.jsCancel');


            ratingField.rating('setting', 'onRate', function (value) {
                ratingField.val(value);
            });

            cancelButton.on('click', this.cancelEdit.bind(this));
        },
        cancelEdit: function () {
            var reviewParent = this.content.find('.jsReviewParent');
            var reviewData = reviewParent.find('.jsReviewData');
            var editData = reviewParent.find('.jsReviewEditData');

            reviewData.fadeIn();
            editData.remove();

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
