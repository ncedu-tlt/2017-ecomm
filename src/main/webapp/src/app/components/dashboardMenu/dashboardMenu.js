(function ($, window) {

    var frm = window.frm;

    var DashboardComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
        }
    });

    frm.components.register('DashboardComponent', DashboardComponent);

})(jQuery, window);
