(function ($, window) {

    var frm = window.frm;

    var ShoppingCartComponent = frm.inheritance.inherits(frm.components.Component, {

        TIMEOUT: 5000,

        init: function () {

            this.content.find('.jsItemOrder .jsLeft').on('click', this.onMinusClick.bind(this));

            this.content.find('.jsItemOrder .jsRight').on('click', this.onPlusClick.bind(this));

            this.content.find('.jsItemOrder .jsInput').on('keyup', this.onInputWrite.bind(this));

            this.content.find('.jsLimitInputClass .jsLimitInput').on('keyup', this.onInputLimit.bind(this));
            
            this.content.find('.jsPrint').on('click', this.onPrint);

            this.content.find('.jsInputClass').form(
                {
                    fields: {
                        quantityValue: {
                            rules: [{type: 'integer[1..999999999]', prompt: 'Please enter a number'},
                                {type: 'empty', prompt: 'Please enter a number'}]
                        }
                    }

                })
            ;
            this.content.find('.jsLimitInputClass').form(
                {
                    fields: {
                        limitInput: {
                            rules: [{type: 'integer[0..999999999]', prompt: 'Please enter a number'},
                                {type: 'empty', prompt: 'Please enter a number'}]
                        }
                    }
                }
            );
        },

        onInputWrite: function (operation) {
            var eventFind = $(event.currentTarget);
            var input = eventFind.closest('.jsInputClass').find('.jsInput');
            var inputValue = +input.val();
            var price = eventFind.closest('.description').find('.jsPrice');
            var standardPrice = eventFind.closest('.description').find('.jsStandardPrice');
            var amount = this.content.find('.jsAmount');
            var globalPrice = this.content.find('.jsItemOrder .jsPrice');
            var product = eventFind.closest('.description').find('.jsProductId');
            var salesOrder = this.content.find('.jsSalesOrderId');
            var warningMassage = this.content.find('.jsExceedingTheLimit');
            var limitInput = this.content.find('.jsLimitInput');
            var limitInputValue = +limitInput.val() || 0;
            switch(operation){
                case 'plus':
                    ++inputValue;
                    break;
                case 'minus':
                    inputValue = (inputValue <= 1 ? 1 : inputValue - 1);
                    break;
                case 'limit':
                    this.limitTimeOut(this.params.shoppingCartUrl, limitInputValue, parseInt(salesOrder.val()));
                    break;
            }
            if (inputValue > 0) {
                input.val(inputValue);
                price.text(parseInt(standardPrice.val()) * inputValue);
                amount.text(this.sumAllPrice(globalPrice));
                this.timeOut(this.params.shoppingCartUrl, inputValue, parseInt(salesOrder.val()), parseInt(product.val()));
            }
            this.warningMassage(amount, limitInputValue, warningMassage);
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
        },

        onPlusClick: function () {
            var mathOperation = 'plus';
            this.onInputWrite(mathOperation);
        },

        onMinusClick: function () {
            var mathOperation = 'minus';
            this.onInputWrite(mathOperation);
        },

        onInputLimit: function () {
            var operation = 'limit';
            this.onInputWrite(operation);
        },

        timeOut: function (globalUrl, input, salesOrderId, productId) {
            clearTimeout(this.timer);
            this.timer = setTimeout(function () {
                $.post(globalUrl, {input: input, salesOrderId: salesOrderId, productId: productId});
            }, this.TIMEOUT);
        },

        limitTimeOut: function (globalUrl, inputLimit, salesOrderId) {
            clearTimeout(this.timer);
            this.timer = setTimeout(function () {
                $.post(globalUrl, {inputLimit: inputLimit, salesOrderId: salesOrderId});
            }, this.TIMEOUT);
        },

        warningMassage: function (amount, limitInput, warningMassage) {
            if (parseInt(amount.text()) > limitInput && limitInput !== 0) {
                warningMassage.show();
            } else {
                warningMassage.hide();
            }
        },

        onPrint: function () {
            window.print();
            void 0;
        },
    });
    frm.components.register('ShoppingCartComponent', ShoppingCartComponent);

})(jQuery, window);

