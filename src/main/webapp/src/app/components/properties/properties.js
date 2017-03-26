

(function ($, window) {

    var frm = window.frm;

    var PropertiesComponent = frm.inheritance.inherits(frm.components.Component, {

        init: function () {
            var $this = this.content.find('.jsProperties');
            var tableValue = $this.find('.jsVisible');
            var edit = $this.find('.jsHide');
            var addButton = this.content.find('.jsAddButton');


            tableValue.click(function () {
                var saveButton = $(this).find('.jsSaveButton');
                var cancelButton = $(this).find('.jsCancelButton');
                var removeButton = $(this).find('.jsRemoveLineButton');
                tableValue.hide();
                edit.show();
                addButton.hide();

            });

            saveButton.click(function () {
                var saveButton = $(this).find('.jsSaveButton');
                var cancelButton = $(this).find('.jsCancelButton');
                var removeButton = $(this).find('.jsRemoveLineButton');
                var globalUrl = this.params.propertiesUrl;
                tableValue.show();
                edit.hide();
                saveButton.hide();
                cancelButton.hide();
                removeButton.hide();
                addButton.show();


                setTimeout(function () {
                    $.post(globalUrl, {input: input.val(), product: productId, salesOrder: salesOrderId});
                }, 5000);

            });

            cancelButton.click(function () {
                var saveButton = $(this).find('.jsSaveButton');
                var cancelButton = $(this).find('.jsCancelButton');
                var removeButton = $(this).find('.jsRemoveLineButton');
                tableValue.show();
                edit.hide();
                saveButton.hide();
                cancelButton.hide();
                removeButton.hide();
                addButton.show();
            });

            removeButton.click(function () {
                tableValue.show();
                edit.hide();
                saveButton.hide();
                cancelButton.hide();
                removeButton.hide();
                addButton.show();


                var line = $(this).parent().find('.jsProperties');
                line.remove();
            });


            addButton.click(function () {
                tableValue.show();
                edit.hide();
                saveButton.hide();
                cancelButton.hide();
                removeButton.hide();
                addButton.show();

                var propertyId =$('#ProductName').val(); // ???
                var value =$('.jsVisible').val();

                $('.jsProperties').append('<tr><td>'+propertyId+'</td><td>'+value+'</td>');

            });






        }
    });

    frm.components.register('PropertiesComponent', PropertiesComponent);

})(jQuery, window);



