(function ($, window) {

    var frm = window.frm;

    var ELEMENTS = {
        RATING: '.jsUsersRating',
        REVIEW_RATING: '.jsUserReviewRating',
        REMOVE_BUTTON: '.jsRemoveButton',
        REMOVE_FORM: '.jsRemoveForm',
        EDIT_BUTTON: '.jsEdit',
        NEGATIVE_BUTTON: '.jsNegative',
        PARENT_REVIEW: '.jsThisUserReview .jsReviewParent',
        CHANGE_RATING: '.jsEditRating',
        REVIEW_TEXT: '.jsReview',
        REVIEW_DATA: '.jsReviewData',
        EDIT_FIELD: '.jsReviewEditData',
        REVIEW_BODY: '.jsThisUserReview',
        CANCEL_BUTTON: '.jsCancel',
        EDIT_RATING: '.jsRating'

    };

    var EVENTS = {
        DISABLE: 'disable',
        ON_RATE: 'onRate',
        LOADING: 'loading',
        CLICK: 'click'
    };

    var ACTIONS = {
        EDIT: 'edit',
        HIDE: 'hide'
    };

    var PROPERTY = {
        SETTINGS: 'setting',
        RATING: 'rating',
        VALUE: 'value',
        FAST: 'fast'
    };

    var LINKS = {
        REVIEW: '/review'
    };

    var ProductComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            this.content.find(ELEMENTS.RATING)
                .rating({initialRating: 2, maxRating: 5})
                .rating(EVENTS.DISABLE);

            this.content.find(ELEMENTS.REVIEW_RATING)
                .rating({initialRating: 2, maxRating: 5})
                .rating(PROPERTY.SETTINGS, EVENTS.ON_RATE, this.onRatingChange.bind(this));

            this.removePopup = this.content.find(ELEMENTS.REMOVE_BUTTON)
                .popup({
                    popup: this.content.find(ELEMENTS.REMOVE_FORM),
                    on: EVENTS.CLICK
                });

            this.content.find(ELEMENTS.EDIT_BUTTON).on(EVENTS.CLICK, function (event) {
                var $this = $(event.currentTarget);
                $this.addClass(EVENTS.LOADING).unbind(EVENTS.CLICK);
                this.getData(event).bind(this)

            }.bind(this));

            this.content.find(ELEMENTS.NEGATIVE_BUTTON).on(EVENTS.CLICK, this.hidePopup.bind(this));

        },
        getData: function (event) {
            var $this = $(event.currentTarget);
            var reviewBody = $this.closest(ELEMENTS.REVIEW_BODY);
            var thisUserRating = reviewBody.find(ELEMENTS.RATING).data(PROPERTY.RATING);
            var reviewText = reviewBody.find(ELEMENTS.REVIEW_TEXT).text();
            var productId = reviewBody.data(PROPERTY.VALUE);


            $.post(this.params.baseUrl + LINKS.REVIEW,
                {
                    reviewActions: ACTIONS.EDIT,
                    productId: productId,
                    review: reviewText,
                    rating: thisUserRating
                },
                this.writeFormForUpdateComment.bind(this));
        },

        writeFormForUpdateComment: function (data) {
            var reviewParent = this.content.find(ELEMENTS.PARENT_REVIEW);
            var reviewData = reviewParent.find(ELEMENTS.REVIEW_DATA);


            reviewData.fadeOut(PROPERTY.FAST, function () {
                reviewParent.append(data);


                var editData = reviewParent.find(ELEMENTS.EDIT_FIELD);
                var ratingField = editData.find(ELEMENTS.CHANGE_RATING);
                var cancelButton = editData.find(ELEMENTS.CANCEL_BUTTON);

                ratingField.rating(PROPERTY.SETTINGS, EVENTS.ON_RATE, function (value) {
                    ratingField.val(value);
                });

                cancelButton.on(EVENTS.CLICK, this.cancelEdit.bind(this));

            }.bind(this));
        },
        cancelEdit: function () {
            var reviewParent = this.content.find(ELEMENTS.PARENT_REVIEW);
            var reviewData = reviewParent.find(ELEMENTS.REVIEW_DATA);
            var editData = reviewParent.find(ELEMENTS.EDIT_FIELD);
            var editButton = reviewData.find(ELEMENTS.EDIT_BUTTON);

            editButton.removeClass(EVENTS.LOADING).on(EVENTS.CLICK, function (event) {

                var $this = $(event.currentTarget);
                $this.addClass(EVENTS.LOADING).unbind(EVENTS.CLICK);
                this.getData(event).bind(this)

            }.bind(this));

            reviewData.fadeIn();
            editData.remove();

        },
        onRatingChange: function (value) {
            this.content.find(ELEMENTS.EDIT_RATING).val(value);

        },
        hidePopup: function () {
            this.removePopup.popup(ACTIONS.HIDE);
        }
    });

    frm.components.register('productReviews', ProductComponent);

})(jQuery, window);
