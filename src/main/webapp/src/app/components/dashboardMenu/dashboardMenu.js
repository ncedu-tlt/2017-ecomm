(function ($, window) {

    var frm = window.frm;

    var dashboardComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            var locationPage = window.location.pathname;
            this.addActive(locationPage);
            this.content.find('.jsDashboardElement')
                .on('click', this.showDimmer.bind(this));
        },
        addActive: function (locationPage) {
            if (locationPage === '/profile') {
                this.content.find('.jsProfile').addClass('active');
            }
            if (locationPage === '/orders') {
                this.content.find('.jsOrderHistory').addClass('active');
            }
        },
        showDimmer: function () {
            var jsDimmer = this.content.find('.jsDimmerDashboard');
            this.dimmerConfig(jsDimmer);
            jsDimmer.dimmer('show');
        },
        dimmerConfig: function(jsDimmer) {
            jsDimmer.dimmer({
                closable: false
            });
        }
    });
    frm.components.register('DashboardComponent', dashboardComponent);

})(jQuery, window);