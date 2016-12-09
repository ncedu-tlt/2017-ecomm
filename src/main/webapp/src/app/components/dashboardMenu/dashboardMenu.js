(function ($, window) {

    var frm = window.frm;

    var DashboardComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            this.content.find('.menu.dashboardMenu .item').tab();
        }
    });

    frm.components.register('DashboardComponent', DashboardComponent);

})(jQuery, window);
