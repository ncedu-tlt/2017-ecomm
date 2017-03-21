(function ($, window) {

    var frm = window.frm;

    var ShoppingCartComponent = frm.inheritance.inherits(frm.components.Component, {
        init: function () {
            var $this = this.content.find('.jsItemOrder');
            var minus = $this.find('.jsLeft');
            var plus = $this.find('.jsRight');
            var amount = this.content.find('.jsAmount');
            var clear;
            var globalInput = $this.find('.jsInput');
            var globalPrice = $this.find('.jsPrice');
            var globalUrl = this.params.shoppingCartUrl;

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
                        $.post(globalUrl + '/cart', {input: input.val(), product: productId, salesOrder: salesOrderId});
                    }, 5000);
                }
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
                    $.post(globalUrl + '/cart', {input: input.val(), product: productId, salesOrder: salesOrderId});
                }, 5000);
            });

            globalInput.keyup('inputChange', function () {
                var $input = $(this).val();
                var input = parseInt($input);
                var $price = $(this).parent().parent().find('.jsPrice');
                var $standardPrice = $(this).parent().parent().find('.jsStandardPrice');
                var standardPrice = parseInt($standardPrice.val());
                var amountSum = 0;
                var priceMassive = [];
                if (input > 0) {
                    $price.text(standardPrice * input);
                    $price.change();
                    globalPrice.each(function () {
                        var price = $(this).text();
                        priceMassive.push(parseInt(price));
                    });
                    for (var i=0;i<priceMassive.length;i++) {
                        amountSum = amountSum + priceMassive[i];
                    }
                    priceMassive.length = 0;
                    amount.text(amountSum);
                    amount.change();
                }
                return false;
            });
        }
    });
    frm.components.register('ShoppingCartComponent', ShoppingCartComponent);

})(jQuery, window);

