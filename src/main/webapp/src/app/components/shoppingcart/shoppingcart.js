(function ($, window) {

    var frm = window.frm;

    var ShoppingCardComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            this.content.find('.jsComponentDropdown').dropdown();
        }

    });

    frm.components.register('ShoppingCardComponent', ShoppingCardComponent);

})(jQuery, window);
