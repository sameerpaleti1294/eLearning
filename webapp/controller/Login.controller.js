sap.ui.define([
    "sap/ui/core/mvc/Controller",
    "sap/ui/core/UIComponent",
	"sap/m/MessageToast",
    "sap/ui/model/json/JSONModel",
    "Elearning/constants/constants"
], function (Controller, UIComponent, MessageToast, JSONModel, constants) {
	"use strict";

	return Controller.extend("Elearning.controller.Login", {
        onInit: function () {
            var oLoginForm = new JSONModel({
                "userName": "",
                "password": "",
                "role":"STUDENT"
            })
            this.getView().setModel(oLoginForm, 'loginDetails')
        },
        onLoginPress: function() {
            this.authenticateCredentials();
        }, 
        authenticateCredentials: function() {
            this.getView().setBusy(true);
            var oData = this.getView().getModel('loginDetails').getData();
            var role = oData.role;
            if(role === 'PARENT'){
                this.getView().setBusy(false);
                this.getView().getModel('appConstants').setData(oData);
                var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
                oRouter.navTo("parentdashboard");
            } else {
                if(oData && oData.userName && oData.role) {
                    jQuery.ajax({
                        contentType: "application/json",
                        dataType: "json",
                        url: constants.authentication_url,
                        type: 'POST',
                        data: JSON.stringify(oData)
                    }).done(function (oResponse) {
                        this.getView().setBusy(false);
                        this.getView().getModel('appConstants').setData(oResponse);
                        var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
                        oRouter.navTo("homepage");
                    }.bind(this)).fail(function (oResponse) {
                        this.getView().setBusy(false);
                        MessageToast.show("Invalid User");
                    }.bind(this));
                } else {
                    this.getView().setBusy(false);
                    MessageToast.show("Please Fill all the details")
                }
            }


        },
        handleSelect: function(oEvent) {
            var oSelected = oEvent.getSource().getText();
            if(this.getView().getModel('loginDetails')){
                this.getView().getModel('loginDetails').setProperty('/role', oSelected.toUpperCase());
            }
        }
	});

});