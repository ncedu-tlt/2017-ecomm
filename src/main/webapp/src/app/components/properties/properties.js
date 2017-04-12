

(function ($, window) {

    var frm = window.frm;

    var PropertiesComponent = frm.inheritance.inherits(frm.components.Component, {

        init: function () {
            var tableValue = this.content.find('.jsTableValue');
            var globalUrlTest = this.params.propertiesUrl;
            var panelButton = this.content.find('.jsPanelButton');
            var saveButton = panelButton.find('.jsSaveButton');
            var cancelButton = panelButton.find('.jsCancelButton');
            var removeButton = panelButton.find('.jsRemoveLineButton');
            var addButton = this.content.find('.jsAddButton');
            var saveRow = this.content.find('.jsSaveRow');


            tableValue.click(function () {
                var $this = $(this);
                var text = $this.find('.jsVisible').text();
                var id = $this.find('.jsPropertyId').text();
                $.post(globalUrlTest + '/properties',{propertyId: id, valueText: text, action: 'edit'}, function (data) {
                    $this.html(data);
                });
            });

            saveButton.click(function () {
                var $this = $(this);
                var text = $this.find('.jsText').text();
                var id = $this.find('.jsPropertId').text();
                $.post(globalUrlTest + '/properties',{valueText: text, propertyId: id, action: 'save'}, function (data) {
                    $this.html(data);
                });

            });

            cancelButton.click(function () {
                var $this = $(this);
                var id = $this.find('.jsPropertId').text();
                //var text = $this.find('.jsPropertVal').text();
                $.post(globalUrlTest + '/properties',{propertyId: id/*, valueText: text*/,action: 'cancel'}, function (data) {
                    $this.html(data);
                });
            });

            removeButton.click(function () {
                var $this = $(this);
                var text = $this.closest('.jsTableValue').find('.jsVisible').text();
                var id = $this.closest('.jsTableValue').find('.jsPropertyId').text();
                $.post(globalUrlTest + '/properties',{valueText: text, propertyId: id, action: 'remove'}, function (data) {
                    $this.html(data);
                });
            });


            addButton.click(function () {

                var $this = $(this);
                var text = $this.closest('.jsPropertyValue').text();
                var id = $this.closest('.jsPropertyId').text();
                $.post(globalUrlTest + '/properties',{valueText: text, propertyId: id, action: 'add'}, function (data) {
                    $this.html(data);
                });
            });

            saveRow.click(function () {
                var $this = $(this);
                var text = $this.closest('.jsRowVal').find('.jsInputPropertyValue').text();
                var id = $this.closest('.jsRowVal').find('.jsPropertyId').text();
                $.post(globalUrlTest + '/properties',{valueText: text, propertyId: id, action: 'saveRow'}, function (data) {
                    $this.html(data);
                });

            });

        }
    });

    frm.components.register('PropertiesComponent', PropertiesComponent);

})(jQuery, window);


