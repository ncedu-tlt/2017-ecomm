(function ($, window) {

    var frm = window.frm;

    var SearchComponent = frm.inheritance.inherits(frm.components.Component, {

        init: function () {
            this.content.find('.jsSearchForm').submit(this.onSubmit.bind(this));
        },
        onSubmit: function () {

            if (!this.content.find('.jsSearchText').val().trim()) {
                return false;
            }

        }


    });

    frm.components.register('SearchComponent', SearchComponent);

})(jQuery, window);
