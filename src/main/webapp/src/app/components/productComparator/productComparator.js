(function ($, window) {

    var frm = window.frm;
    var ProductComparator = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {

        }


    });

    frm.components.register('productComparator', ProductComparator);

})(jQuery, window);
