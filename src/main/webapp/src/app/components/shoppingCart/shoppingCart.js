(function ($, window) {

    var frm = window.frm;

    var ShoppingCartComponent = frm.inheritance.inherits(frm.components.Component, {
        init: function () {
            var $this = this.content.find('.jsItemOrder');
            var minus = $this.find('.jsLeft');
            var plus = $this.find('.jsRight');
            var amount = this.content.find('.jsAmount');
            var clear;

            minus.click(function () {
                var input = $(this).parent().find('.jsInput');
                var price = $(this).parent().parent().find('.jsPrice');
                var standardPrice = parseInt(price.text()) / parseInt(input.val());
                var count = parseInt(input.val());
                var product = $(this).parent().find('.jsProductId');
                var salesOrder = $(this).parent().find('.jsSalesOrderId');
                var productId = parseInt(product.val());
                var salesOrderId = parseInt(salesOrder.val());
                if (count > 1) {
                    amount.text(parseInt(amount.text()) - standardPrice);
                    count = count <= 1 ? 1 : count - 1;
                    price.text(standardPrice * count);
                    input.val(count);
                    clearTimeout(clear);
                    clear = setTimeout(function () {
                        $.post('/cart', {input: input.val(), product: productId, salesOrder: salesOrderId});
                    }, 5000);
                }
                input.change();
                price.change();
                amount.change();
                return false;
            });

            plus.click(function () {
                var input = $(this).parent().find('.jsInput');
                var price = $(this).parent().parent().find('.jsPrice');
                var standardPrice = parseInt(price.text()) / parseInt(input.val());
                var product = $(this).parent().find('.jsProductId');
                var salesOrder = $(this).parent().find('.jsSalesOrderId');
                var productId = parseInt(product.val());
                var salesOrderId = parseInt(salesOrder.val());
                var count = parseInt(input.val());
                if (count >= 1) {
                    input.val(parseInt(input.val()) + 1);
                    price.text(standardPrice * parseInt(input.val()));
                    amount.text(parseInt(amount.text()) + standardPrice);
                }
                clearTimeout(clear);
                clear = setTimeout(function () {
                    $.post('/cart', {input: input.val(), product: productId, salesOrder: salesOrderId});
                }, 5000);
                input.change();
                price.change();
                amount.change();
                return false;
            });
        }
    });
    frm.components.register('ShoppingCartComponent', ShoppingCartComponent);

})(jQuery, window);

