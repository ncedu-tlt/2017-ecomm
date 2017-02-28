
(function ($, window) {

    var frm = window.frm;

    var dashboardComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            var locationPage = window.location.pathname;
            this.addActive(locationPage);
        },
        addActive: function(locationPage) {
            if (locationPage === '/profile') {
                this.content.find('.jsProfile').addClass('active');
            }
            if (locationPage === '/orders') {
                this.content.find('.jsOrderHistory').addClass('active');
            }
        }
    });
    frm.components.register('DashboardComponent', dashboardComponent);

})(jQuery, window);