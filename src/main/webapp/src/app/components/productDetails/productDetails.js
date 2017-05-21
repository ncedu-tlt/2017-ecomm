(function ($, window) {

    var frm = window.frm;

    var ELEMENTS = {
        SLIDE_SHOW_PICTURE: '.slideShowPic',
        PRODUCT_RATING: '.rating',
        SLIDE: '.slideShowItem',
        SLIDE_SHOW: '.slideShow',
        DISPLAY: '.showDisplay',
        COMPARE_BUTTON: '.jsProductCompareButton',
        IMAGE: 'img',
        COMPARE_BUTTON_CONTAINER: '.jsCompareIconContainer',
        REMOVE_FROM_COMPARE: '.jsRemoveFromCompareList',
        ATTRIBUTE_SRC: 'src',
        ADD_TO_CART: '.jsAddToCart',
        ADD_TO_COMPARE: '.jsAddToCompare'
    };


    var CLASSES = {
        LOADING: 'loading'
    };

    var EVENTS = {
        CLICK: 'click',
        REFRESH_PAGE: 'refreshPage',
        ADD_TO_CART: 'addToCart',
        CLEAR_COMPARE_LIST: 'clearList',
        REMOVE: 'remove',
        ADD_TO_COMPARE: 'addToCompare'
    };

    var STATES = {
        DISABLE: 'disable',
        ACTIVE: 'active'
    };

    var PARAMETERS = {
        DURATION: 300,
        INIT_RATING: 2,
        MAX_RATING: 5
    };


    var ProductComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            frm.events.on(EVENTS.CLEAR_COMPARE_LIST, this.addLoadingLabel.bind(this));
            frm.events.on(EVENTS.REFRESH_PAGE, this.refreshPage.bind(this));

            this.content.find(ELEMENTS.PRODUCT_RATING)
                .rating({initialRating: PARAMETERS.INIT_RATING, maxRating: PARAMETERS.MAX_RATING})
                .rating(STATES.DISABLE);

            this.content.find(ELEMENTS.SLIDE_SHOW_PICTURE).on(EVENTS.CLICK, function (e) {
                e.preventDefault();

                var
                    $this = $(this),
                    item = $this.closest(ELEMENTS.SLIDE),
                    container = $this.closest(ELEMENTS.SLIDE_SHOW),
                    display = container.find(ELEMENTS.DISPLAY),
                    path = item.find(ELEMENTS.IMAGE).attr(ELEMENTS.ATTRIBUTE_SRC);

                if (!item.hasClass(STATES.ACTIVE)) {
                    item.addClass(STATES.ACTIVE).siblings().removeClass(STATES.ACTIVE);

                    display.find(ELEMENTS.IMAGE).fadeOut(PARAMETERS.DURATION, function () {
                        $(this).attr(ELEMENTS.ATTRIBUTE_SRC, path).fadeIn(PARAMETERS.DURATION);
                    });
                }
            });

            this.content.find(ELEMENTS.ADD_TO_CART).on(EVENTS.CLICK, function () {
                var productId = $(this).val();
                frm.events.fire(EVENTS.ADD_TO_CART, productId);
            });

            this.content.find(ELEMENTS.REMOVE_FROM_COMPARE).on(EVENTS.CLICK, function (event) {
                this.doActionWithCompareButton(EVENTS.REMOVE, event);
            }.bind(this));
            this.content.find(ELEMENTS.ADD_TO_COMPARE).on(EVENTS.CLICK, function (event) {
                this.doActionWithCompareButton(EVENTS.ADD_TO_COMPARE, event);
            }.bind(this));
        },

        addLoadingLabel: function () {
            this.content.find(ELEMENTS.COMPARE_BUTTON).addClass(CLASSES.LOADING);
        },

        doActionWithCompareButton: function (action, event) {

            var $this = $(event.currentTarget);
            var productId = $this.val();
            $this.addClass(CLASSES.LOADING);

            frm.events.fire(action, productId);
        },
        refreshPage: function () {
            $.post(
                window.location.pathname + window.location.search,
                {
                    action: EVENTS.REFRESH_PAGE
                },
                function (data) {

                    var dataContent = $(data);
                    var compareButton = dataContent.find(ELEMENTS.COMPARE_BUTTON);
                    this.content.find(ELEMENTS.COMPARE_BUTTON_CONTAINER).html(compareButton);

                    this.content.find(ELEMENTS.REMOVE_FROM_COMPARE).on(EVENTS.CLICK, function (event) {
                        this.doActionWithCompareButton(EVENTS.REMOVE, event);
                    }.bind(this));

                    this.content.find(ELEMENTS.ADD_TO_COMPARE).on(EVENTS.CLICK, function (event) {
                        this.doActionWithCompareButton(EVENTS.ADD_TO_COMPARE, event);

                    }.bind(this));

                }.bind(this));
        }

    });

    frm.components.register('productDetails', ProductComponent);

})
(jQuery, window);
