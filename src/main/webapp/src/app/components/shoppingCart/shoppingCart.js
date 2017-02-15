(function ($, window) {

    var frm = window.frm;

    var ShoppingCartComponent = frm.inheritance.inherits(frm.components.Component, {
        init: function () {
            var count =  this.content.find(".jsInput").attr("value");
            plus = this.content.find(".jsLeft");
            minus = this.content.find(".jsRight");
            plus.on("click", function () {
                count++;
            })
        }
    });

    frm.components.register('ShoppingCartComponent', ShoppingCartComponent);

})(jQuery, window);

