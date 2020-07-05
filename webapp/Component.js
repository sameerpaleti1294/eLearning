sap.ui.define([
    "sap/ui/core/UIComponent",
    "sap/ui/model/json/JSONModel",
    "sap/ui/unified/FileUploader"
], function (UIComponent, JSONModel, FileUploader){
    "use strict";
    return UIComponent.extend("Elearning.Component", {
       metadata : {
          manifest:"json"
       },
       init : function () {
        // call the init function of the parent
        
        UIComponent.prototype.init.apply(this, arguments);
        //set application constants Model
        var oAppContantsModel = new JSONModel();
        this.setModel(oAppContantsModel, "appConstants");

        //set course Model
        var oCoursesModel = new JSONModel();
        this.setModel(oCoursesModel, "courses");
        this.getRouter().initialize();
       }
       
    });
 });