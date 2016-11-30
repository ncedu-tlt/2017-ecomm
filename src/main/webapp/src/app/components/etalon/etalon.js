(function ($, window) {

    var frm = window.frm;

    var EtalonComponent = frm.inheritance.inherits(frm.components.Component, {

        init: function () {
            this.content.find('.ui.checkbox').checkbox();
            this.content.find('.jsPrint').on('click', this.onButtonClick.bind(this));
        },

        onButtonClick: function () {
            var input = this.content.find('.jsInput').val();
            alert(this.params.message + '\n' + input);
        }

    });

    frm.components.registerComponent('EtalonComponent', EtalonComponent);

})(jQuery, window);
