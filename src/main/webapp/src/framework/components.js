(function ($, window) {

    window.frm = window.frm || {};

    var frm = window.frm;
    frm.components = frm.components || {};



    var registry = {};

    frm.components.register = function (name, controller) {
        registry[name] = controller;
    };

    frm.components.init = function (name, selector, params) {
        var component = new registry[name]();
        component._init(selector, params);
        component.init();
        $(document).ready(component.onDocumentReady.bind(component));
    };



    frm.components.Component = function () {};

    frm.components.Component.prototype = {

        _init: function (selector, params) {
            this.content = $(selector);
            this.params = params;
        },

        init: function () {

        },

        onDocumentReady: function () {

        }

    };

})(jQuery, window);
