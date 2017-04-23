(function ($, window) {

    var frm = window.frm;

    var TopPanelComponent = frm.inheritance.inherits(frm.components.Component, {


        init: function () {
            var $this = $(this.content);
            var body = $this.closest('body');

            $this.sticky({
                context: body
            });
        }

    });

    frm.components.register('topPanelComponent', TopPanelComponent);

})(jQuery, window);
