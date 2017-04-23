(function ($, window) {

    var frm = window.frm;

    var ELEMENTS = {
        PRODUCT_COMPARATOR_ICON: '.jsContent',
        CLEAR_LIST_BUTTON: '.jsClearList'
    };

    var EVENTS = {
        ADD_TO_COMPARE: 'addToCompare',
        CLEAR_COMPARE_LIST: 'clearList',
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
            frm.events.on(EVENTS.ADD_TO_COMPARE, this.sendRequest.bind(this));

            this.content.find(ELEMENTS.PRODUCT_COMPARATOR_ICON).on(
                'click',
                ELEMENTS.CLEAR_LIST_BUTTON,
                this.clearList.bind(this)
            );

        },

        sendRequest: function (productIdParam) {
            $.post(
                this.params.addToCompareUrl + LINKS.COMPARE_ICON_SERVLET,
                {
                    productId: productIdParam,
                    action: EVENTS.UPDATE_COUNT
                },
                function (data) {
                    this.content.find(ELEMENTS.PRODUCT_COMPARATOR_ICON).html(data);

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

                }.bind(this));
        }
    });

    frm.components.register('productComparatorIcon', ProductComparatorIcon);

})(jQuery, window);
