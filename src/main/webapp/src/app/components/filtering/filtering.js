(function ($, window) {

    var frm = window.frm;

    var FilteringComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            this.content.find('.filter.sidebar').first()
                .sidebar('attach events', '.filtering.button');
            this.content.find('.ui.accordion')
                .accordion();
        }
    });

    frm.components.register('FilteringComponent', FilteringComponent);

})(jQuery, window);
