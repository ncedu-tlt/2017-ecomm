(function ($, window) {

    var frm = window.frm;

    var CategoryComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            this.content.find('.dropdown').dropdown();
        }

    });

    frm.components.register('ShoppingCard', CategoryComponent);

})(jQuery, window);
