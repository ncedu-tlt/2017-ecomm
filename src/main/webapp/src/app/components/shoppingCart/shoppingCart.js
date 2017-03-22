(function ($, window) {

    var frm = window.frm;

    var ShoppingCartComponent = frm.inheritance.inherits(frm.components.Component, {

        TIMEOUT: 5000,

        init: function () {
            this.content.find('.jsItemOrder .jsLeft').on('click', this.onMinusClick.bind(this));

            this.content.find('.jsItemOrder .jsRight').on('click', this.onPlusClick.bind(this));

            this.content.find('.jsItemOrder .jsInput').on('keyup', this.onInputWrite.bind(this));
        },

        onMinusClick: function (event) {
            var eventFind = $(event.currentTarget);
            var amount = this.content.find('.jsAmount');
            var input = eventFind.closest('.description').find('.jsInput');
            var price = eventFind.closest('.description').find('.jsPrice');
            var standardPrice = eventFind.closest('.description').find('.jsStandardPrice');
            var product = eventFind.closest('.description').find('.jsProductId');
            var salesOrder = eventFind.closest('.description').find('.jsSalesOrderId');
            var globalUrl = this.params.shoppingCartUrl;
            var inputCount = parseInt(input.val());
            if (inputCount > 1) {
                amount.text(parseInt(amount.text()) - parseInt(standardPrice.val()));
                inputCount = inputCount <= 1 ? 1 : inputCount - 1;
                price.text(parseInt(standardPrice.val()) * inputCount);
                input.val(inputCount);
                clearTimeout(this.clear);
                this.clear = this.timeOut(globalUrl, input, parseInt(product.val()), parseInt(salesOrder.val()));
            }
        },

        onPlusClick: function (event) {
            var eventFind = $(event.currentTarget);
            var input = eventFind.closest('.description').find('.jsInput');
            var price = eventFind.closest('.description').find('.jsPrice');
            var standardPrice = eventFind.closest('.description').find('.jsStandardPrice');
            var amount = this.content.find('.jsAmount');
            var product = eventFind.closest('.description').find('.jsProductId');
            var salesOrder = eventFind.closest('.description').find('.jsSalesOrderId');
            var globalUrl = this.params.shoppingCartUrl;
            if (parseInt(input.val()) >= 1) {
                input.val(parseInt(input.val()) + 1);
                price.text(parseInt(standardPrice.val()) * parseInt(input.val()));
                amount.text(parseInt(amount.text()) + parseInt(standardPrice.val()));
            }
            clearTimeout(this.clear);
            this.clear = this.timeOut(globalUrl, input, parseInt(product.val()), parseInt(salesOrder.val()));
        },

        onInputWrite: function (event) {
            var eventFind = $(event.currentTarget);
            var input = eventFind.val();
            var price = eventFind.parent().parent().find('.jsPrice');
            var standardPrice = eventFind.closest('.description').find('.jsStandardPrice');
            var amount = this.content.find('.jsAmount');
            var globalPrice = this.content.find('.jsItemOrder .jsPrice');
            var product = eventFind.closest('.description').find('.jsProductId');
            var salesOrder = eventFind.closest('.description').find('.jsSalesOrderId');
            var globalUrl = this.params.shoppingCartUrl;
            if (parseInt(input) > 0) {
                price.text(parseInt(standardPrice.val()) * parseInt(input));
                amount.text(this.sumAllPrice(globalPrice));
                input = eventFind;
            }
            clearTimeout(this.clear);
            this.clear = this.timeOut(globalUrl, input, parseInt(product.val()), parseInt(salesOrder.val()));
        },

        timeOut: function (globalUrl, input, productId, salesOrderId) {
            setTimeout(function () {
                $.post(globalUrl, {input: input.val(), product: productId, salesOrder: salesOrderId});
            }, this.TIMEOUT);
        },

        sumAllPrice: function (globalPrice) {
            var amountSum = 0;
            var priceArray = [];
            globalPrice.each(function () {
                var price = $(this).text();
                priceArray.push(parseInt(price));
            });
            for (var i = 0; i < priceArray.length; i++) {
                amountSum = amountSum + priceArray[i];
            }
            priceArray.length = 0;
            return amountSum;
        }
    });
    frm.components.register('ShoppingCartComponent', ShoppingCartComponent);

})(jQuery, window);

