sap.ui.define([
    "sap/ui/core/mvc/Controller",
    "sap/ui/model/json/JSONModel",
    "sap/ui/core/UIComponent",
	"sap/m/MessageToast",
    "sap/ui/core/routing/History",
    "Elearning/constants/constants"
], function (Controller, JSONModel, UIComponent, MessageToast, History, constants) {
	"use strict";

	return Controller.extend("Elearning.controller.MyCourses", {
        onInit : function () {
            this.loadData();
        },
        onNavBack : function () {   
            var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
            oRouter.navTo("homepage");
            
        },
        navToCourseSchedulePage: function (oEvent) {
            var oItem = oEvent.getSource();
            var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
            oRouter.navTo("courseSchedule", {
				courseId: window.encodeURIComponent(oItem.getBindingContext("courses").getPath().split('/')[2])
			});
        },
        loadData: function () {
            this.getView().setBusy(true);
            var oUserId = this.getOwnerComponent().getModel('appConstants').getData().userId;
            var oUrl = constants.myCourses_url.replace("{userId}", oUserId);
            jQuery.ajax({
                contentType: "application/json",
                dataType: "json",
                url: oUrl,
                type: 'GET'
            }).done(function (oResponse) {
                this.getView().setBusy(false);
                var oData = {
                    "Course": oResponse
                };
                this.getView().getModel("courses").setData(oData);
            }.bind(this)).fail(function (oResponse) {
                MessageToast.show("Failed to load Data");
            }.bind(this));
        }
	});

});