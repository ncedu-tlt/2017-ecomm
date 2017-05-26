(function ($, window) {

    var frm = window.frm;
    var ELEMENTS = {
        ADD_TO_CART: '.jsAddToCart',
        REMOVE_PRODUCT: '.jsRemoveProduct',
        LOADER: '.jsLoader',
        PARENT_ITEM: '.jsProductItem',
        TABLE_ITEM: '.jsTableItem',
        PRODUCT_RATING: '.jsCompareProductRating',
        GO_TO_HOME_PAGE_BUTTON: '.jsGoToHomePage',
        REMOVE_ALL_PRODUCTS_BUTTON: '.jsRemoveAllProducts'
    };

    var EVENTS = {
        CLICK: 'click',
        REMOVE: 'remove',
        REMOVE_ALL: 'removeAll',
        ADD_TO_CART: 'addToCart',
        REFRESH_PAGE: 'refreshPage'
    };

    var LINKS = {
        COMPARE_SERVLET: '/compare',
        HOME_SERVLET: '/home'

    };

    var CLASS = {
        LOADING: 'loading'
    };

    var ProductComparator = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            frm.events.on(EVENTS.REFRESH_PAGE, this.refreshPage.bind(this));

            this.content.find(ELEMENTS.PRODUCT_RATING).rating({initialRating: 2, maxRating: 5}).rating('disable');

            this.content.find(ELEMENTS.REMOVE_ALL_PRODUCTS_BUTTON).on(EVENTS.CLICK, function () {
                var $this = $(this);
                $this.addClass(CLASS.LOADING);
                frm.events.fire(EVENTS.REMOVE_ALL);

            });

            this.content.find(ELEMENTS.LOADER).hide();

            this.content.find(ELEMENTS.ADD_TO_CART).on(EVENTS.CLICK, function () {
                var productId = $(this).val();
                frm.events.fire(EVENTS.ADD_TO_CART, productId);
            });

            this.content.find(ELEMENTS.GO_TO_HOME_PAGE_BUTTON).on(EVENTS.CLICK, function (event) {
                $(event.target).addClass(CLASS.LOADING);
                window.location.href = this.params.comparePageUrl + LINKS.HOME_SERVLET;
            }.bind(this));

            this.content.find(ELEMENTS.REMOVE_PRODUCT).on(EVENTS.CLICK, function (event) {
                var $this = $(event.currentTarget);
                var parent = $this.closest(ELEMENTS.PARENT_ITEM);
                var loader = parent.find(ELEMENTS.LOADER);
                var productId = $this.val();
                loader.show();
                $this.addClass(CLASS.LOADING);

                frm.events.fire(EVENTS.REMOVE, productId);

                this.refreshPage.bind(this);
            }.bind(this))

        },
        refreshPage: function () {
            $.post(
                this.params.comparePageUrl + LINKS.COMPARE_SERVLET,
                {
                    action: EVENTS.REFRESH_PAGE
                },
                function (data) {
                    this.content.parent().html(data);

                }.bind(this));
        }
    });

    frm.components.register('productComparator', ProductComparator);

})(jQuery, window);
