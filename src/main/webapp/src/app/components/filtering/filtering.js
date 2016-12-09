(function ($, window) {

    var frm = window.frm;

    var FilteringComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            this.content.find('.accordion')
                .accordion();
            this.content.find('.filter.sidebar').first()
                .sidebar('setting', 'dimPage', false)
                .sidebar('attach events', '.jsShowFiltering');
        }
    });

    frm.components.register('FilteringComponent', FilteringComponent);

})(jQuery, window);
