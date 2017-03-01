(function ($, window) {

    var frm = window.frm;

    var ShoppingCartComponent = frm.inheritance.inherits(frm.components.Component, {
            init: function () {
                var
                    $this = this.content.find('.jsItemOrder'),
                    minus = $this.find('.jsLeft'),
                    plus = $this.find('.jsRight'),
                    amount = this.content.find('.jsAmount'),
                    clear;

                minus.click(function () {
                    var
                        input = $(this).parent().find('.jsInput'),
                        price = $(this).parent().parent().find('.jsPrice'),
                        standardPrice = parseInt(price.text()) / parseInt(input.val()),
                        count = parseInt(input.val()),
                        product = $(this).parent().find(".jsProductId"),
                        salesOrder = $(this).parent().find(".jsSalesOrderId"),
                        productId = parseInt(product.val()),
                        salesOrderId = parseInt(salesOrder.val());
                    if (count > 1) {
                        amount.text(parseInt(amount.text()) - standardPrice);
                        count = count <= 1 ? 1 : count - 1;
                        price.text(standardPrice * count);
                        input.val(count);
                        clearTimeout(clear);
                        clear = setTimeout(function () {
                            $.post('/cart', {input: input.val(), product: productId, salesOrder: salesOrderId});
                        }, 2000);
                    }
                    input.change();
                    price.change();
                    amount.change();
                    return false;
                });

                plus.click(function () {
                    var input = $(this).parent().find('.jsInput'),
                        price = $(this).parent().parent().find('.jsPrice'),
                        standardPrice = parseInt(price.text()) / parseInt(input.val()),
                        product = $(this).parent().parent().parent().parent().find(".jsProductId"),
                        salesOrder = $(this).parent().parent().parent().parent().find(".jsSalesOrderId"),
                        productId = parseInt(product.val()),
                        salesOrderId = parseInt(salesOrder.val()),
                        count = parseInt(input.val());
                    if (count >= 1) {
                        input.val(parseInt(input.val()) + 1);
                        price.text(standardPrice * parseInt(input.val()));
                        amount.text(parseInt(amount.text()) + standardPrice);
                    }
                    clearTimeout(clear);
                    clear = setTimeout(function () {
                        $.post('/cart', {input: input.val(), product: productId, salesOrder: salesOrderId});
                    }, 2000);
                    input.change();
                    price.change();
                    amount.change();
                    return false;
                });
            }
        })
        ;

    frm.components.register('ShoppingCartComponent', ShoppingCartComponent);

})
(jQuery, window);

