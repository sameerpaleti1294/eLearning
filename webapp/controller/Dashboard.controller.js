sap.ui.define([
    "sap/ui/core/mvc/Controller",
    "sap/ui/core/UIComponent",
	"sap/m/MessageToast",
], function (Controller,UIComponent, MessageToast) {
	"use strict";

	return Controller.extend("Elearning.controller.Dashboard", {
        onInit : function () {
           

        },
        navToLinks: function (oEvent) {
            var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
            if(oEvent.getSource().getTitle() === 'My Courses'){
                oRouter.navTo("mycourses");
            } else {
                oRouter.navTo("myschedule");
            }
			
        },
        onLogOut: function () {
            var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
			oRouter.navTo("loginpage");
        }
	});

});