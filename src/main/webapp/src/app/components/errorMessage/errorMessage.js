(function ($, window) {

    var EVENTS = {
        SEND_ERROR: 'sendError',
        CLICK: 'click',
        REMOVE_ALL: 'removeAll',
        SHOW: 'show',
        HIDE: 'hide'
    };

    var ELEMENTS = {
        TO_COMPARE_LINK: 'compare',
        MESSAGE_TITLE: '.jsMessageHeader',
        CLEAR_LIST_BUTTON: '.jsClearList',
        GO_TO_COMPARE: '.jsGoToCompare',
        MESSAGE_TEXT: '.jsErrorMessage p',
        POPUP: '.jsErrorMessageContainer'
    };

    var LINKS = {
        COMPARE_LINK: '/compare'
    };

    var ERRORS = {
        ERROR: 'error',
        MAX_SIZE_ERROR: 'compareListOverflow',
        INCORRECT_CATEGORY_ERROR: 'incorrectCategory',
        PRODUCT_ALREADY_EXISTS: 'productAlreadyExists',
        COMPARE_LIST_OVERFLOW_TITLE: 'Compare List Overflow!',
        COMPARE_LIST_OVERFLOW_MESSAGE: 'Please, go to compare or clear list for remaining',
        PRODUCT_ALREADY_EXISTS_TITLE: 'This product already added to compare!',
        PRODUCT_ALREADY_EXISTS_MESSAGE: 'Please add another product for compare',
        INCORRECT_CATEGORY_TITLE: 'Incorrect Category!'
    };

    var frm = window.frm;

    var ErrorMessage = frm.inheritance.inherits(frm.components.Component, {

        init: function () {
            frm.events.on(EVENTS.SEND_ERROR, this.showErrorMessage.bind(this));

            this.content.find(ELEMENTS.CLEAR_LIST_BUTTON).on(EVENTS.CLICK, function () {
                frm.events.fire(EVENTS.REMOVE_ALL);
            });

            this.content.find(ELEMENTS.GO_TO_COMPARE).on(EVENTS.CLICK, function () {
               window.location.href =  this.params.errorMessageContext + LINKS.COMPARE_LINK;
            }.bind(this));
        },

        showErrorMessage: function (error) {
            var errorBody = {};

            switch (error.error) {
                case ERRORS.MAX_SIZE_ERROR:
                    errorBody.title = ERRORS.COMPARE_LIST_OVERFLOW_TITLE;
                    errorBody.message = ERRORS.COMPARE_LIST_OVERFLOW_MESSAGE;
                    break;
                case  ERRORS.PRODUCT_ALREADY_EXISTS:
                    errorBody.title = ERRORS.PRODUCT_ALREADY_EXISTS_TITLE;
                    errorBody.message = ERRORS.PRODUCT_ALREADY_EXISTS_MESSAGE;
                    break;
                case ERRORS.INCORRECT_CATEGORY_ERROR:
                    errorBody.title = ERRORS.INCORRECT_CATEGORY_TITLE;
                    errorBody.message = 'Please add product from category  ' +
                        '<a href=' + this.params.errorMessageContext + '/category?category_id='
                        + error.category[2] + '\>'
                        + error.category[1].toUpperCase().bold() + '</a>'
                        + ' or clear compare list!';
                    break;

            }
            this.content.find(ELEMENTS.MESSAGE_TITLE).text('' + errorBody.title);
            this.content.find(ELEMENTS.MESSAGE_TEXT).html('' + errorBody.message);
            this.content.modal(EVENTS.SHOW);
        }
    });

    frm.components.register('ErrorMessage', ErrorMessage);

})(jQuery, window);
