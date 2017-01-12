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
            if($(".jsProfileComponent").length) {
                $("#profile").addClass("active");
            }

            if($(".jsOrdersHistoryComponent").length) {
                $("#orderHistory").addClass("active");
            }

            var href=$(".menuElement");
            href.click(function () {
                changeActive($(this));
                return false;
            });

            function changeActive(current) {
                console.log(href);
                href.removeClass("active");
                current.addClass("active");
            }

            $(".item").click(function(){
                changeStateDisplay($(this).attr('id'));
            });

            function changeStateDisplay(block){
                if(block === 'profile'){
                    window.location.replace("/views/pages/profile.jsp");
                }
                if(block === 'orderHistory'){
                    window.location.replace("/views/pages/ordersHistory.jsp");
                }
            }
        }
    });
    frm.components.register('DashboardComponent', dashboardComponent);

})(jQuery, window);