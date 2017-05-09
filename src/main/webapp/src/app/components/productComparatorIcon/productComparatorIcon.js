(function ($, window) {

    var frm = window.frm;

    var ELEMENTS = {
        PRODUCT_COMPARATOR_ICON: '.jsContent',
        CLEAR_LIST_BUTTON: '.jsClearList'
    };

    var EVENTS = {
        ADD_TO_COMPARE: 'addToCompare',
        CLEAR_COMPARE_LIST: 'clearList',
        REMOVE_ALL: 'removeAll',
        REMOVE: 'remove',
        REFRESH_COMPARE_PAGE: 'refreshComparePage',
        UPDATE_COUNT: 'updateCompareInIcon'
    };
    var LINKS = {
        COMPARE_ICON_SERVLET: '/compareIcon'
    };

    var ProductComparatorIcon = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            frm.events.on(EVENTS.ADD_TO_COMPARE, this.sendRequest.bind(this, EVENTS.UPDATE_COUNT));
            frm.events.on(EVENTS.REMOVE, this.sendRequest.bind(this, EVENTS.REMOVE));

            this.content.find(ELEMENTS.PRODUCT_COMPARATOR_ICON).on(
                'click',
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
                    this.content.find(ELEMENTS.PRODUCT_COMPARATOR_ICON).html(data);
                    frm.events.fire(EVENTS.REFRESH_COMPARE_PAGE, null);

                }.bind(this));
        },

        clearList: function () {
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
