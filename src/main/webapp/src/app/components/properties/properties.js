

(function ($, window) {

    var frm = window.frm;

    var PropertiesComponent = frm.inheritance.inherits(frm.components.Component, {

        init: function () {
            var tableValue = this.content.find('.jsTableValue');
            var edit = this.content.find('.jsEdit');
            var globalUrlTest = this.params.propertiesUrl;
            var panelButton = this.content.find('.jsPanelButton');
            var removeButton = this.content.find('.jsRemoveLineButton');
            var addButton = this.content.find('.jsAddButton');
            var saveRow = this.content.find('.jsSaveRow');


            edit.click(function () {
                var $this = $(this);
                var root = $this.closest('.jsTableValue');
                var text = root.find('.jsVisible').text();
                var id = root.find('.jsPropertyId').text();

                $.post(globalUrlTest + '/properties',{propertyId: id, valueText: text, action: 'edit'}, function (data) {
                    root.append(data);
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
                        var id = root.find('.jsPropertId').val();
                        var text = root.find('.jsPropertVal').val();

                        $.post(globalUrlTest + '/properties',{propertyId: id, valueText: text, action: 'save'}, function (data) {
                            //root.remove();
                            root.append(data);

                        });
                    });

                });
            });

            removeButton.click(function () {
                var $this = $(this);
                var root = $this.closest('.jsTableValue');
                var id = root.find('.jsPropertyId').text();
                var text = root.find('.jsVisible').text();

                $.post(globalUrlTest + '/properties',{propertyId: id, valueText: text, action: 'remove'}, function () {
                    root.html("")
                });
            });

            addButton.click(function () {
                var $this = $(this);
                var root = $this.closest('.jsTable');
                //var root = this.content.find('.jsTable');
                $.post(globalUrlTest + '/properties',{ action: 'add'}, function (data) {
                    root.append(data);
                });
            });

            saveRow.click(function () {
                var $this = $(this);
                var root = $this.closest('.jsRowVal');
                var text = root.find('.jsInputPropertyValue').text();
                var id = root.find('.jsInputPropertyId').text();

                $.post(globalUrlTest + '/properties',{valueText: text, propertyId: id, action: 'saveRow'}, function (data) {
                    $this.html(data);
                });

            });

        }
    });

    frm.components.register('PropertiesComponent', PropertiesComponent);

})(jQuery, window);




