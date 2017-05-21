(function ($, window) {

    var frm = window.frm;

    var ELEMENTS = {
        PRODUCT_RATING: '.rating',
        ADD_TO_CART: '.jsAddToCart',
        PRODUCT_ITEM: '.jsProductItem',
        PRODUCT_COMPARE_BUTTON: '.jsProductCompareButton',
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
        CLEAR_COMPARE_LIST: 'clearList',
        SEND_ERROR: 'sendError',
        ADD_TO_CART: 'addToCart',
        REFRESH_PAGE: 'refreshPage',
        ADD_TO_COMPARE: 'addToCompare'
    };
    var loadingProducts = [];
    var loadedProducts = [];
    var ProductListComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */

        init: function () {
            frm.events.on(EVENTS.SEND_ERROR, this.removeProductIdFromList.bind(this));
            frm.events.on(EVENTS.REFRESH_PAGE, this.refreshPage.bind(this));
            frm.events.on(EVENTS.CLEAR_COMPARE_LIST, this.addLoadingLabelOnCompareButton.bind(this));

            this.content.find(ELEMENTS.PRODUCT_RATING).rating({initialRating: 2, maxRating: 5}).rating(EVENTS.DISABLE);

            this.content.find(ELEMENTS.ADD_TO_CART).on(EVENTS.CLICK, this.addToShoppingCard.bind(this));

            this.content.find(ELEMENTS.REMOVE_FROM_COMPARE).on(EVENTS.CLICK, function (event) {
                this.doActionWithCompareButton(EVENTS.REMOVE, event);
            }.bind(this));

            this.content.find(ELEMENTS.ADD_TO_COMPARE).on(EVENTS.CLICK, function (event) {
                this.doActionWithCompareButton(EVENTS.ADD_TO_COMPARE, event);

            }.bind(this));
        },

        addLoadingLabelOnCompareButton: function () {
            this.content.find(ELEMENTS.REMOVE_FROM_COMPARE).addClass(CLASS.LOADING).unbind(EVENTS.CLICK);

        },

        removeProductIdFromList: function (productId) {
            var elementIndex = loadingProducts.indexOf(productId);
            loadingProducts.splice(elementIndex, 1);
            loadedProducts.push(productId);
        },

        checkProductsOnLoading: function (productId) {
            var buttons = this.content.find(ELEMENTS.PRODUCT_COMPARE_BUTTON);

            buttons.each(function () {
                var element = $(this);
                var elementValue = element.val();
                if (elementValue === productId) {
                    element.removeClass(CLASS.LOADING);
                    loadingProducts.splice(loadingProducts.indexOf(productId), 1);

                } else if (loadingProducts.includes(elementValue)) {
                    element.addClass(CLASS.LOADING);

                } else if (loadedProducts.includes(elementValue)) {
                    element.removeClass(CLASS.LOADING);
                    loadedProducts.splice(loadedProducts.indexOf(elementValue), 1);
                }

            });
        },

        addToShoppingCard: function (event) {
            var $this = $(event.currentTarget);
            var productId = $this.val();

            frm.events.fire(EVENTS.ADD_TO_CART, productId);
        },

        doActionWithCompareButton: function (action, event) {

            var $this = $(event.currentTarget);
            var productId = $this.val();
            loadingProducts.push(productId);
            this.checkProductsOnLoading();

            $this.unbind(EVENTS.CLICK);
            frm.events.fire(action, productId);

        },

        refreshPage: function (productId) {
            $.post(
                window.location.pathname + window.location.search,
                {
                    action: EVENTS.REFRESH_PAGE
                },
                function (data) {

                    var dataContent = $(data);
                    var dataMainContent = dataContent.find(ELEMENTS.MAIN_CONTENT).children();
                    this.content.html(dataMainContent);
                    this.checkProductsOnLoading(productId);

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
