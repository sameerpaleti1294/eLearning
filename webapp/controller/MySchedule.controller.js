sap.ui.define([
    "sap/ui/core/mvc/Controller",
    "sap/ui/model/json/JSONModel",
    "sap/ui/core/UIComponent",
	"sap/m/MessageToast",
    "sap/ui/core/routing/History",
    "Elearning/constants/constants",
    "sap/ui/core/format/DateFormat",
    "sap/ui/core/library"
], function (Controller, JSONModel, UIComponent, MessageToast, History, constants, DateFormat, coreLibrary) {
	"use strict";
	var CalendarType = coreLibrary.CalendarType;

	return Controller.extend("Elearning.controller.MySchedule", {

        onInit : function () {
            this.oFormatYyyymmdd = DateFormat.getInstance({pattern: "yyyy-MM-dd", calendarType: CalendarType.Gregorian});
            var oCalendar = this.byId('calendar');
            oCalendar.addSelectedDate(new sap.ui.unified.DateRange({startDate: new Date()}));
            var aSelectedDates = oCalendar.getSelectedDates(), oDate = this.oFormatYyyymmdd.format(aSelectedDates[0].getStartDate());
            this.loadData(oDate);
        },
        onNavBack : function () {   
            var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
            oRouter.navTo("homepage");
            
        },
        loadData: function (oDate) {
            var oUserID = this.getOwnerComponent().getModel('appConstants').getData().userId;
            var oUrl = constants.mySchedule_url.replace('{userId}', oUserID).replace('{date}', oDate); 
            jQuery.ajax({
                contentType: "application/json",
                dataType: "json",
                url: oUrl,
                type: 'GET'
            }).done(function (oResponse) {
                this.getView().setBusy(false);
                var oData = new JSONModel({
                    "MySchedule": oResponse
                });
                this.getView().setModel(oData, "mySchedule");
            }.bind(this)).fail(function (oResponse) {
               MessageToast.show("Failed to load Schedule data");
                
            }.bind(this));
        },
        handleCalendarSelect : function(oEvent) {
            this.getView().setBusy(true);
            var oCalendar = oEvent.getSource();
            var aSelectedDates = oCalendar.getSelectedDates(), oDate = this.oFormatYyyymmdd.format(aSelectedDates[0].getStartDate());
			this.loadData(oDate);
        }
	});

});