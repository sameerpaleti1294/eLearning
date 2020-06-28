sap.ui.define([
    "sap/ui/core/mvc/Controller",
    "sap/ui/core/UIComponent",
	"sap/m/MessageToast",
], function (Controller,UIComponent, MessageToast) {
	"use strict";

	return Controller.extend("Elearning.controller.Dashboard", {
        onInit : function () {
           

        },
        navToMyCourses: function () {
            var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
			oRouter.navTo("mycourses");
        },
        onLogOut: function () {
            var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
			oRouter.navTo("loginpage");
        }
	});

});