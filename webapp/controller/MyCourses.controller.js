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
            console.log("test");
        },
        onNavBack : function () {
            var oHistory= History.getInstance();
            var sPreviousHash = oHistory.getPreviousHash();
            if(!sPreviousHash){
                var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
                oRouter.navTo("loginpage",{}, true);
            } else {
                window.history.go(-1);
            }
        },
        loadData: function () {
            this.getView().setBusy(true);
            jQuery.ajax({
                contentType: "application/json",
                dataType: "json",
                url: constants.courses_url,
            }).done(function (oResponse) {
                this.getView().setBusy(false);
                var oData = new JSONModel({
                    "Course": oResponse
                });
                this.getView().setModel(oData, "courses");
            }.bind(this)).fail(function (oResponse) {
                this.getView().setBusy(false);
                var oData = new JSONModel({
                    "Course":[
                        {
                            "id": 1,
                            "name": "course 1"
                        },
                        {
                            "id": 2,
                            "name": "course 2"
                        },
                        {
                            "id": 3,
                            "name": "course 22"
                        }
                ]});
                this.getView().setModel(oData, "courses");
            }.bind(this));
        }
	});

});