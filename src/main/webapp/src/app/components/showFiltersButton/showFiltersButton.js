(function ($, window) {

    var frm = window.frm;

    var ShowButtonComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            this.content.find('.jsShowFilters').click(function () {

                frm.events.fire('showFilters');
            });
        }

    });
    frm.components.register('ShowButtonComponent', ShowButtonComponent);

})(jQuery, window);
