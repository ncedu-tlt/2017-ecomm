(function ($, window) {

    var frm = window.frm;

    var FilteringComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */


        init: function () {
            this.content.find('.jsOnlyNumber').on('keypress', this.keypress.bind(this));
            this.content.find('.ui.accordion').accordion({exclusive: false});
            this.content.find('.ui.checkbox').checkbox({indeterminate: true});
            this.content.find('.filter.sidebar').first()
                .sidebar('setting', 'dimPage', false)
                .sidebar('attach events', '.jsShowFiltering');

            frm.events.on('showFilters', function () {
                $('.filter.sidebar').sidebar('toggle');
            }.bind(this));
        },

        keypress: function (event) {
            if (event.keyCode < 48 || event.keyCode > 57) {
                return false;
            }
        }

    });
    frm.components.register('FilteringComponent', FilteringComponent);

})(jQuery, window);
