(function ($, window) {

    var frm = window.frm;

    var ELEMENTS = {
        DIMMER: '.jsDimmerAdd'
    };

    var EVENTS = {
        ADD_TO_COMPARE: 'addToCompare'
    };

    var jsDimmer;

    var ProductComparatorIcon = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            this.initDimer();
            frm.events.on(EVENTS.ADD_TO_COMPARE, this.sendRequest.bind(this));
        },

        sendRequest: function (productIdParam) {
            console.log(productIdParam);
        },

        initDimer: function () {
            var dimmer = this.content.find(ELEMENTS.DIMMER);
            dimmer.dimmer({
                closable: false
            });
            jsDimmer = dimmer;
        }

    });

    frm.components.register('productComparatorIcon', ProductComparatorIcon);

})(jQuery, window);
