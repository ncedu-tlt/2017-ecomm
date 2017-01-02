/**
 * Created by Alexander on 03.01.2017.
 */
(function ($, window) {

    var frm = window.frm;

    var dashboardComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            var href=$(".item");
            href.click(function () {
                changeActive($(this));
                return false;
            });

            function changeActive(current) {
                console.log(href);
                href.removeClass("active");
                current.addClass("active");
            }
        }
    });
    frm.components.register('DashboardComponent', dashboardComponent);

})(jQuery, window);