(function ($, window) {

    var frm = window.frm;

    var PropertiesComponent = frm.inheritance.inherits(frm.components.Component, {

        init: function () {
            this.content.find('.jsEdit').on('click', this.edit.bind(this));
            this.content.find('.jsRemoveLineButton').on('click', this.removeButton.bind(this));
            this.content.find('.jsAddButton').on('click', this.addButton.bind(this));
        },
        edit: function () {

            var eventFind = $(event.currentTarget);
            var thisField = eventFind.attr('class');
            var root = eventFind.closest('.jsTableValue');
            var text = root.find('.jsVisible').text();
            var id = root.find('.jsPropertyId').text();
            root.find('.loader').removeClass('disable');

            $.post(this.params.propertiesUrl, {
                propertyId: id,
                valueText: text,
                action: 'edit',
                field: thisField
            }, function (data) {
                eventFind.after(data);
                eventFind.hide();

                this.content.find('.jsCancelButton').on('click', this.cancelEdit.bind(this));
                this.content.find('.jsSaveButton').on('click', this.saveEdit.bind(this));

            }.bind(this));
        },
        cancelEdit: function () {
            var eventFind = $(event.currentTarget);
            var inputText = eventFind.closest('.jsProperty');
            var pol = eventFind.closest('.jsEdit');
            var tableVal = inputText.closest('.jsTableValue');
            var idPr = tableVal.find('.jsPropertyId');
            var valPr = tableVal.find('.jsVisible');
            pol.find('.loader').addClass('disable');

            if (inputText.hasClass('jsPropertId')) {
                inputText.remove();
                idPr.show();
            }
            else {
                inputText.remove();
                valPr.show();
            }

        },

        saveEdit: function () {
            var eventFind = $(event.currentTarget);
            var root = eventFind.closest('.jsProperty');
            var thisField = root.attr('class');
            var id = root.find('.jsPropertId').val();
            var text = root.find('.jsPropertVal').val();

            $.post(this.params.propertiesUrl, {
                propertyId: id,
                valueText: text,
                action: 'save',
                field: thisField
            }, function (data) {
                root.html(data);
                root.find('.jsEdit').on('click', this.edit.bind(this));
                root.find('.jsRemoveLineButton').on('click', this.removeButton.bind(this));

            }.bind(this));

        },

        removeButton: function () {
            var eventFind = $(event.currentTarget);
            var root = eventFind.closest('.jsTableValue');
            var id = root.find('.jsPropertyId').text();
            var text = root.find('.jsVisible').text();

            $.post(this.params.propertiesUrl, {propertyId: id, valueText: text, action: 'remove'}, function () {
                root.html('');
            });
        },

        addButton: function () {
            var eventFind = $(event.currentTarget);
            var root = eventFind.closest('.jsPropertiesComponent');
            var table = root.find('.jsTable');
            $.post(this.params.propertiesUrl, {action: 'add'}, function (data) {
                table.append(data);

                this.content.find('.jsCancelRow').on('click', this.cancelNewRow.bind(this));
                this.content.find('.jsSaveRow').on('click', this.saveNewRow.bind(this));

            }.bind(this));
        },

        cancelNewRow: function () {
            var eventFind = $(event.currentTarget);
            var inputRow = eventFind.closest('.jsTableValue');
            inputRow.remove();
        },

        saveNewRow: function () {
            var eventFind = $(event.currentTarget);
            var root = eventFind.closest('.jsTableValue');
            var text = root.find('.jsInputPropertyValue').val();
            var id = root.find('.jsInputPropertyId').val();

            $.post(this.params.propertiesUrl, {
                valueText: text,
                propertyId: id,
                action: 'saveRow'
            }, function (data) {
                root.html(data);
                root.find('.jsEdit').on('click', this.edit.bind(this));
                root.find('.jsRemoveLineButton').on('click', this.removeButton.bind(this));

            }.bind(this));

        }
    });

    frm.components.register('PropertiesComponent', PropertiesComponent);

})(jQuery, window);




