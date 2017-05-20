(function ($, window) {

    var frm = window.frm;

    var ELEMENTS = {
        PRODUCT_RATING: '.rating',
        ADD_TO_CART: '.jsAddToCart',
        PRODUCT_ITEM: '.jsProductItem',
        REMOVE_FROM_COMPARE: '.jsRemoveFromCompareList',
        ADD_TO_COMPARE: '.jsAddToCompare'
    };

    var CLASS = {
        LOADING: 'loading'
    };

    var EVENTS = {
        REMOVE: 'remove',
        CLICK: 'click',
        ADD_TO_CART: 'addToCart',
        DISABLE: 'disable',
        ADD_AND_REFRESH_ITEM: 'addRefreshItem',
        REFRESH_PAGE: 'refreshPage',
        ADD_TO_COMPARE: 'addToCompare'
    };

    var ProductListComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */

        init: function () {
            frm.events.on(EVENTS.REFRESH_PAGE, this.refreshPage.bind(this));


            this.content.find(ELEMENTS.PRODUCT_RATING).rating({initialRating: 2, maxRating: 5}).rating(EVENTS.DISABLE);

            this.content.find(ELEMENTS.ADD_TO_CART).on(EVENTS.CLICK, function () {
                var productId = $(this).val();
                frm.events.fire(EVENTS.ADD_TO_CART, productId);
            });

            this.content.find(ELEMENTS.REMOVE_FROM_COMPARE).on(EVENTS.CLICK, function (event) {
                var $this = $(event.currentTarget);
                var productId = $this.val();
                $this.removeClass(ELEMENTS.REMOVE_FROM_COMPARE).addClass(CLASS.LOADING);

                frm.events.fire(EVENTS.REMOVE, productId);
            });

            this.content.find(ELEMENTS.ADD_TO_COMPARE).on(EVENTS.CLICK, function (event) {
                var $this = $(event.currentTarget);
                var productId = $this.val();
                $this.removeClass(ELEMENTS.ADD_TO_COMPARE).addClass(CLASS.LOADING);

                frm.events.fire(EVENTS.ADD_TO_COMPARE, productId);

            }.bind(this));
        },

        refreshPage: function () {
            $.post(
                window.location.pathname + window.location.search,
                {
                    action: EVENTS.REFRESH_PAGE
                },
                function (data) {
                    this.content.parent().html(data);

                }.bind(this));
        }
    });

    frm.components.register('ProductListComponent', ProductListComponent);

})(jQuery, window);
