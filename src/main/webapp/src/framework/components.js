(function ($, window) {

    window.frm = window.frm || {};

    var frm = window.frm;
    frm.components = frm.components || {};



    frm.components.componentsMap = frm.components.componentsMap || {};

    frm.components.registerComponent = function (name, controller) {
        frm.components.componentsMap[name] = controller;
    };

    frm.components.initComponent = function (name, selector, params) {
        var component = new frm.components.componentsMap[name]();
        component._init(selector, params);
        $(document).ready(component.init.bind(component));
    };



    frm.components.Component = function () {};

    frm.components.Component.prototype = {

        _init: function (selector, params) {
            this.content = $(selector);
            this.params = params;
        },

        init: function () {

        }

    };

})(jQuery, window);
