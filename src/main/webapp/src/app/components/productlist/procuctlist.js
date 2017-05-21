(function ($, window) {

    var frm = window.frm;

    var ELEMENTS = {
        PRODUCT_RATING: '.rating',
        ADD_TO_CART: '.jsAddToCart',
        PRODUCT_ITEM: '.jsProductItem',
        REMOVE_FROM_COMPARE: '.jsRemoveFromCompareList',
        MAIN_CONTENT: '.main-content',
        ADD_TO_COMPARE: '.jsAddToCompare'
    };

    var CLASS = {
        LOADING: 'loading',
        REMOVE_FROM_COMPARE: 'jsRemoveFromCompareList',
        ADD_TO_COMPARE: 'jsAddToCompare'
    };

    var EVENTS = {
        REMOVE: 'remove',
        CLICK: 'click',
        DISABLE: 'disable',
        ADD_TO_CART: 'addToCart',
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

            this.content.find(ELEMENTS.ADD_TO_CART).on(EVENTS.CLICK, this.addToShoppingCard.bind(this));

            this.content.find(ELEMENTS.REMOVE_FROM_COMPARE).on(EVENTS.CLICK, function (event) {
                this.doActionWithCompareButton(EVENTS.REMOVE, event);
            }.bind(this));

            this.content.find(ELEMENTS.ADD_TO_COMPARE).on(EVENTS.CLICK, function (event) {
                this.doActionWithCompareButton(EVENTS.ADD_TO_COMPARE, event);

            }.bind(this));
        },
        addToShoppingCard: function (event) {
            var $this = $(event.currentTarget);
            var productId = $this.val();

            frm.events.fire(EVENTS.ADD_TO_CART, productId);
        },
        doActionWithCompareButton: function (action, event) {

            var $this = $(event.currentTarget);
            var productId = $this.val();

            $this.unbind(EVENTS.CLICK);
            $this.addClass(CLASS.LOADING);

            frm.events.fire(action, productId);
        },

        refreshPage: function () {
            $.post(
                window.location.pathname + window.location.search,
                {
                    action: EVENTS.REFRESH_PAGE
                },
                function (data) {
                    clearTimeout(this.timer);

                    var dataContent = $(data);
                    var dataMainContent = dataContent.find(ELEMENTS.MAIN_CONTENT).children();
                    this.timer = setTimeout(this.content.html(dataMainContent), 1200);

                    this.content.find(ELEMENTS.PRODUCT_RATING).rating({
                        initialRating: 2,
                        maxRating: 5
                    }).rating(EVENTS.DISABLE);

                    this.content.find(ELEMENTS.ADD_TO_CART).on(EVENTS.CLICK, this.addToShoppingCard.bind(this));

                    this.content.find(ELEMENTS.REMOVE_FROM_COMPARE).on(EVENTS.CLICK, function (event) {
                        this.doActionWithCompareButton(EVENTS.REMOVE, event);
                    }.bind(this));

                    this.content.find(ELEMENTS.ADD_TO_COMPARE).on(EVENTS.CLICK, function (event) {
                        this.doActionWithCompareButton(EVENTS.ADD_TO_COMPARE, event);

                    }.bind(this));

                }.bind(this));
        }
    });

    frm.components.register('ProductListComponent', ProductListComponent);

})(jQuery, window);
