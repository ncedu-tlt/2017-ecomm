(function ($, window) {

    var frm = window.frm;

    var ShoppingCartComponent = frm.inheritance.inherits(frm.components.Component, {
        init: function () {
            var
                $this = this.content.find('.jsItemOrder'),
                minus = $this.find('.jsLeft'),
                plus = $this.find('.jsRight'),
                price = $this.find('.jsPrice'),
                amount = $this.find('.jsAmount');

            minus.click(function () {
                var
                    $input = $(this).parent().find('.jsInput'),
                    count = parseInt($input.val()) - 1;
                price.val(parseInt(price.val()) / parseInt($input.val()));
                count = count < 1 ? 1 : count;
                $input.val(count);
                $input.change();
                price.change();
                return false;
            });

            plus.click(function () {
                var $input = $(this).parent().find('.jsInput');
                $input.val(parseInt($input.val()) + 1);
                $input.change();
                price.val(parseInt(price.val()) * parseInt($input.val()));
                price.change();
                return false;
            });
        }
    });

    frm.components.register('ShoppingCartComponent', ShoppingCartComponent);

})(jQuery, window);

