(function ($, window) {

    var frm = window.frm;

    var PropertiesComponent = frm.inheritance.inherits(frm.components.Component, {

        init: function () {
            var tableValue = this.content.find('.jsTableValue');
            var edit = this.content.find('.jsEdit');
            var panelButton = this.content.find('.jsPanelButton');
            var removeButton = this.content.find('.jsRemoveLineButton');
            var addButton = this.content.find('.jsAddButton');
            var globalUrlTest = this.params.propertiesUrl;

            edit.click(function () {
                var $this = $(this);
                var thisField = this.className;
                var root = $this.closest('.jsTableValue');
                var text = root.find('.jsVisible').text();
                var id = root.find('.jsPropertyId').text();

                $.post(globalUrlTest + '/properties', {
                    propertyId: id,
                    valueText: text,
                    action: 'edit',
                    field: thisField
                }, function (data) {
                    $this.after(data);
                    $this.hide();

                    var cancelButton = root.find('.jsCancelButton');
                    cancelButton.click(function () {
                        var inputText = this.closest('.jsProperty');
                        inputText.remove();
                        $this.show();
                    });

                    var saveButton = root.find('.jsSaveButton');
                    saveButton.click(function () {
                        var $this = $(this);
                        var root = $this.closest('.jsProperty');
                        var thisField = root.attr('class');
                        var id = root.find('.jsPropertId').val();
                        var text = root.find('.jsPropertVal').val();


                        $.post(globalUrlTest + '/properties', {
                            propertyId: id,
                            valueText: text,
                            action: 'save',
                            field: thisField
                        }, function (data) {
                            root.html(data);
                        });
                    });

                });
            });

            removeButton.click(function () {
                var $this = $(this);
                var root = $this.closest('.jsTableValue');
                var id = root.find('.jsPropertyId').text();
                var text = root.find('.jsVisible').text();

                $.post(globalUrlTest + '/properties', {propertyId: id, valueText: text, action: 'remove'}, function () {
                    root.html("")
                });
            });

            addButton.click(function () {
                var $this = $(this);
                var root = $this.closest('.jsPropertiesComponent');
                var table = root.find('.jsTable');
                $.post(globalUrlTest + '/properties', {action: 'add'}, function (data) {
                    table.append(data);

                    var cancelRow = table.find('.jsCancelRow');
                    cancelRow.click(function () {
                        var inputRow = this.closest('.jsTableValue');
                        inputRow.remove();
                    });


                    var saveRow = table.find('.jsSaveRow');
                    saveRow.click(function () {
                        var $this = $(this);
                        var root = $this.closest('.jsTableValue');
                        var text = root.find('.jsInputPropertyValue').val();
                        var id = root.find('.jsInputPropertyId').val();

                        $.post(globalUrlTest + '/properties', {
                            valueText: text,
                            propertyId: id,
                            action: 'saveRow'
                        }, function (data) {
                            root.html(data);
                        });

                    });

                });
            });


        }
    });

    frm.components.register('PropertiesComponent', PropertiesComponent);

})(jQuery, window);