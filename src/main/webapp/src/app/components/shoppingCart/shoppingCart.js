(function ($, window) {

    var frm = window.frm;

    var ShoppingCartComponent = frm.inheritance.inherits(frm.components.Component, {
        init: function () {
            var
                $this = this.content.find('.jsItemOrder'),
                minus = $this.find('.jsLeft'),
                plus = $this.find('.jsRight'),
                amount = this.content.find('.jsAmount');

            minus.click(function () {
                var
                    $input = $(this).parent().find('.jsInput'),
                    price = $(this).parent().parent().find('.jsPrice'),
                    standardPrice = parseInt(price.text()) / parseInt($input.val()),
                    count = parseInt($input.val());
                if (count > 1) {
                    amount.text(parseInt(amount.text()) - standardPrice);
                    count = count <= 1 ? 1 : count - 1;
                    price.text(standardPrice * count);
                    $input.val(count);
                    $input.change();
                    price.change();
                    amount.change();
                    return false;
                }
            });

            plus.click(function () {
                var $input = $(this).parent().find('.jsInput'),
                    price = $(this).parent().parent().find('.jsPrice'),
                    standardPrice = parseInt(price.text()) / parseInt($input.val()),
                    count = parseInt($input.val());
                if(count >= 1){
                    $input.val(parseInt($input.val()) + 1);
                    $input.change();
                    price.text(standardPrice * parseInt($input.val()));
                    price.change();
                    amount.text(parseInt(amount.text()) + standardPrice);
                    amount.change();
                    return false;
                }
            });
        }
    });

    frm.components.register('ShoppingCartComponent', ShoppingCartComponent);

})(jQuery, window);

