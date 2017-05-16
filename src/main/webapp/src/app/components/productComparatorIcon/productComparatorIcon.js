(function ($, window) {

    var frm = window.frm;

    var ELEMENTS = {
        PRODUCT_COMPARATOR_ICON: '.jsContent',
        CLEAR_LIST_BUTTON: '.jsClearList',
        COMPARE_BUTTON: '.jsCompareButton',
        QUANTITY_ICON: '.jsShoppingCartIcon'
    };

    var EVENTS = {
        ADD_TO_COMPARE: 'addToCompare',
        CLEAR_COMPARE_LIST: 'clearList',
        REMOVE_ALL: 'removeAll',
        CLICK: 'click',
        REMOVE: 'remove',
        SEND_ERROR: 'sendError',
        REFRESH_COMPARE_PAGE: 'refreshComparePage',
        UPDATE_COUNT: 'updateCompareInIcon'
    };

    var LINKS = {
        COMPARE_ICON_SERVLET: '/compareIcon'
    };

    var ERRORS = {
        ERROR: 'error',
        MAX_SIZE_ERROR: 'compareListOverflow',
        INCORRECT_CATEGORY_ERROR: 'incorrectCategory',
        PRODUCT_ALREADY_EXISTS: 'productAlreadyExists'
    };

    var CLASS = {
        LOADING: 'loading'
    };

    var ProductComparatorIcon = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            frm.events.on(EVENTS.ADD_TO_COMPARE, this.sendRequest.bind(this, EVENTS.UPDATE_COUNT));
            frm.events.on(EVENTS.REMOVE, this.sendRequest.bind(this, EVENTS.REMOVE));
            frm.events.on(EVENTS.REMOVE_ALL, this.clearList.bind(this));

            this.content.find(ELEMENTS.PRODUCT_COMPARATOR_ICON).on(
                EVENTS.CLICK,
                ELEMENTS.CLEAR_LIST_BUTTON,
                this.clearList.bind(this)
            );

        },

        sendRequest: function (event, productIdParam) {

            $.post(
                this.params.addToCompareUrl + LINKS.COMPARE_ICON_SERVLET,
                {
                    productId: productIdParam,
                    action: event
                },
                function (data) {
                    this.content.find(ELEMENTS.COMPARE_BUTTON).addClass(CLASS.LOADING);
                    var errorArray = data.split(',');
                    var error = errorArray[0];

                    switch (error.trim()) {
                        case ERRORS.MAX_SIZE_ERROR:
                            frm.events.fire(EVENTS.SEND_ERROR, {error: ERRORS.MAX_SIZE_ERROR});
                            break;
                        case ERRORS.PRODUCT_ALREADY_EXISTS:
                            frm.events.fire(EVENTS.SEND_ERROR, {error: ERRORS.PRODUCT_ALREADY_EXISTS});
                            break;
                        case ERRORS.INCORRECT_CATEGORY_ERROR:
                            frm.events.fire(EVENTS.SEND_ERROR, {
                                error: ERRORS.INCORRECT_CATEGORY_ERROR,
                                category: errorArray
                            });
                            break;
                        default:
                            if (event === EVENTS.UPDATE_COUNT) {
                                this.content.find(ELEMENTS.PRODUCT_COMPARATOR_ICON).html(data);
                            }
                    }

                    this.content.find(ELEMENTS.COMPARE_BUTTON).removeClass(CLASS.LOADING);
                    frm.events.fire(EVENTS.REFRESH_COMPARE_PAGE);


                }.bind(this));
        },

        clearList: function () {
            this.content.find(ELEMENTS.COMPARE_BUTTON).addClass(CLASS.LOADING);
            $.post(
                this.params.addToCompareUrl + LINKS.COMPARE_ICON_SERVLET,
                {
                    action: EVENTS.CLEAR_COMPARE_LIST
                },
                function (data) {
                    this.content.find(ELEMENTS.PRODUCT_COMPARATOR_ICON).html(data);

                    frm.events.fire(EVENTS.REFRESH_COMPARE_PAGE, null);

                }.bind(this));
        }
    });

    frm.components.register('productComparatorIcon', ProductComparatorIcon);

})(jQuery, window);
