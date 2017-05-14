(function ($, window) {

    var frm = window.frm;

    var ELEMENTS = {
        PRODUCT_COMPARATOR_ICON: '.jsContent',
        CLEAR_LIST_BUTTON: '.jsClearList',
        COMPARE_BUTTON: '.jsCompareButton'
    };

    var EVENTS = {
        ADD_TO_COMPARE: 'addToCompare',
        CLEAR_COMPARE_LIST: 'clearList',
        REMOVE_ALL: 'removeAll',
        CLICK: 'click',
        REMOVE: 'remove',
        REFRESH_COMPARE_PAGE: 'refreshComparePage',
        UPDATE_COUNT: 'updateCompareInIcon'
    };

    var LINKS = {
        COMPARE_ICON_SERVLET: '/compareIcon'
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
            this.content.find(ELEMENTS.COMPARE_BUTTON).addClass(CLASS.LOADING);
            $.post(
                this.params.addToCompareUrl + LINKS.COMPARE_ICON_SERVLET,
                {
                    productId: productIdParam,
                    action: event
                },
                function (data) {
                    if (event === EVENTS.UPDATE_COUNT) {
                        this.content.find(ELEMENTS.PRODUCT_COMPARATOR_ICON).html(data);
                    }
                    frm.events.fire(EVENTS.REFRESH_COMPARE_PAGE, null);

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
