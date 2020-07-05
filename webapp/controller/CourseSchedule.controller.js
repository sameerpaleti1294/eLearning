sap.ui.define([
    "sap/ui/core/mvc/Controller",
    "sap/ui/model/json/JSONModel",
    "sap/ui/core/UIComponent",
	"sap/m/MessageToast",
    "sap/ui/core/routing/History",
    "Elearning/constants/constants",
    "sap/ui/unified/FileUploader"
], function (Controller, JSONModel, UIComponent, MessageToast, History, constants) {
	"use strict";

	return Controller.extend("Elearning.controller.CourseSchedule", {
        onInit : function () {
            var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
			oRouter.getRoute("courseSchedule").attachPatternMatched(this._onObjectMatched, this);
	

        },
        _onObjectMatched: function (oEvent) {
            this.courseId = oEvent.getParameter("arguments").courseId;
			this.getView().bindElement({
				path: "/Course/" + window.decodeURIComponent(this.courseId),
				model: "courses"
			});
			var oCourseId= this.getView().getBindingContext('courses').getObject().id;
			this.loadData(oCourseId);

		},
        onNavBack : function () {   
            var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
            oRouter.navTo("mycourses");
            
        },
        loadData: function (oCourseId) {
            this.getView().setBusy(true);
            var oUrl = constants.coursesSchedule_url.replace("{courseId}", oCourseId);
            jQuery.ajax({
                contentType: "application/json",
                dataType: "json",
                url: oUrl,
                type: 'GET'
            }).done(function (oResponse) {
                this.getView().setBusy(false);
                var oData = new JSONModel({
                    "CourseSchedule": oResponse
                });
                this.getView().setModel(oData, "courseSchedule");
            }.bind(this)).fail(function (oResponse) {
               
               MessageToast.show("Failed to load data")
            }.bind(this));
        },
        handleUploadComplete: function(oEvent) {
			var sResponse = oEvent.getParameter("response");
			if (sResponse) {
                oEvent.getSource().getParent().getItems()[2].setVisible(true);
				MessageToast.show('File Uploaded Successfully');
			}
		},
		handleTypeMissmatch: function(oEvent) {
			var aFileTypes = oEvent.getSource().getFileType();
			jQuery.each(aFileTypes, function(key, value) {aFileTypes[key] = "*." +  value;});
			var sSupportedFileTypes = aFileTypes.join(", ");
			MessageToast.show("The file type *." + oEvent.getParameter("fileType") +
									" is not supported. Choose one of the following types: " +
									sSupportedFileTypes);
		},
		handleValueChange: function(oEvent) {
            sap.m.MessageToast.show("Uploading " + oEvent.getParameter("newValue"));
		},
		downloadFile: function(oEvent) {
		    this.getView().setBusy(true);
		    var oScheduleId = oEvent.getSource().getParent().getParent().getBindingContext('courseSchedule').getObject().scheduleId;
            var oUrl = constants.downloadFile_url.replace("{scheduleId}", oScheduleId);
		    jQuery.ajax({
                contentType: "application/json",
                url: oUrl,
                type: 'GET'
            }).done(function (oResponse) {
                this.getView().setBusy(false);
                var binaryData = [];
                binaryData.push(oResponse);
                var url = window.URL.createObjectURL(new Blob(binaryData, {type:"application/pdf"}))
                var $a = $('<a />', {
                  'href': url,
                  'download': 'download.pdf',
                  'text': "click"
                }).hide().appendTo("body")[0].click();
//                var oVideo= $('#video-player');
//                                $('#video-player')[0].src = url;
            }.bind(this)).fail(function (oResponse) {
                this.getView().setBusy(false);
                MessageToast.show("File download Failed")
            }.bind(this));
		}

	});

});