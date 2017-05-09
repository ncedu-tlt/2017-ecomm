(function ($, window) {

    var frm = window.frm;
    var ELEMENTS = {
        ADD_TO_CART: '.jsAddToCart',
        REMOVE_PRODUCT: '.jsRemoveProduct',
        TABLE_ITEM: '.jsCompareTable .jsTableItem',
        PRODUCT_RATING: '.jsCompareProductRating'
    };

    var EVENTS = {
        CLICK: 'click',
        REMOVE: 'remove',
        REMOVE_ALL: 'removeAll',
        ADD_TO_CART: 'addToCart',
        REFRESH_COMPARE_PAGE: 'refreshComparePage'
    };

    var LINKS = {
        COMPARE_SERVLET: '/compare'

    };

    var ProductComparator = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            frm.events.on(EVENTS.REFRESH_COMPARE_PAGE, this.refreshPage.bind(this));

            this.content.find(ELEMENTS.PRODUCT_RATING).rating({initialRating: 2, maxRating: 5}).rating('disable');

            this.content.find(ELEMENTS.TABLE_ITEM)
                .popup({
                    hoverable: true
                });

            this.content.find(ELEMENTS.ADD_TO_CART).on(EVENTS.CLICK, function () {
                var productId = $(this).val();
                frm.events.fire(EVENTS.ADD_TO_CART, productId);
            });

            this.content.find(ELEMENTS.REMOVE_PRODUCT).on(EVENTS.CLICK, function (event) {
                var productId = event.currentTarget.value;
                frm.events.fire(EVENTS.REMOVE, productId);

                this.refreshPage.bind(this);
            }.bind(this))

        },
        refreshPage: function () {
            $.post(
                this.params.comparePageUrl + LINKS.COMPARE_SERVLET,
                {
                    action: EVENTS.REFRESH_COMPARE_PAGE
                },
                function (data) {
                    this.content.parent().html(data);

                }.bind(this));
        }
    });

    frm.components.register('productComparator', ProductComparator);

})(jQuery, window);
