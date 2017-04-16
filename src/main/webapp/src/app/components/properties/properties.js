

(function ($, window) {

    var frm = window.frm;

    var PropertiesComponent = frm.inheritance.inherits(frm.components.Component, {

        init: function () {
            var tableValue = this.content.find('.jsTableValue');
            var edit = this.content.find('.jsEdit');
            var globalUrlTest = this.params.propertiesUrl;
            var panelButton = this.content.find('.jsPanelButton');
            var saveButton = panelButton.find('.jsSaveButton');
            var cancelButton = panelButton.find('.jsCancelButton');
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


                var cancelBut =  this.content.find('.jsCancelButton');


                cancelBut.click(function () {
                    var inputText = this.closest('.jsProperty');
                    edit.show();
                    inputText.remove();


                });

                });
            });


            saveButton.click(function () {
                var $this = $(this);
                var root = $this.closest('.jsProperty');
                var text = root.find('.jsPropertVal').text();
                var id = root.find('.jsPropertId').text();

                $.post(globalUrlTest + '/properties',{valueText: text, propertyId: id, action: 'save'}, function (data) {
                    root.append(data);


                });

            });
            
            

            removeButton.click(function () {
                var $this = $(this);
                var root = $this.closest('.jsTableValue');
                var text = root.find('.jsVisible').text();
                var id = root.find('.jsPropertyId').text();

                $.post(globalUrlTest + '/properties',{valueText: text, propertyId: id, action: 'remove'}, function () {
                    root.html("")
                });
            });


            addButton.click(function () {
                var root = $this.closest('.jsTable');
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


