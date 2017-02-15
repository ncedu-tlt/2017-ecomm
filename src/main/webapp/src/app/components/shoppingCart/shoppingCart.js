(function ($, window) {

    var frm = window.frm;

    var ShoppingCartComponent = frm.inheritance.inherits(frm.components.Component, {
        init: function () {
            var
                $this = this.content.find('.jsInputButtons'),
                minus = $this.find('.jsLeft'),
                plus = $this.find('.jsRight');

            minus.click(function () {
                var
                    $input = $(this).parent().find('.jsInput'),
                    count = parseInt($input.val()) - 1;
                count = count < 1 ? 1 : count;
                $input.val(count);
                $input.change();
                return false;
            });

            plus.click(function () {
                var $input = $(this).parent().find('.jsInput');
                $input.val(parseInt($input.val()) + 1);
                $input.change();
                return false;
            });
        }
    });

    frm.components.register('ShoppingCartComponent', ShoppingCartComponent);

})(jQuery, window);

