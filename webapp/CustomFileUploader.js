sap.ui.define(
	["sap/ui/unified/FileUploader"],
	function (FileUploader) {
        return FileUploader.extend("Elearning.CustomFileUploader", {
            metadata: {
                aggregations : {
                    parameters : {type : "sap.ui.unified.FileUploaderParameter", multiple : true, singularName : "parameter"},
                    headerParameters : {type : "sap.ui.unified.FileUploaderParameter", multiple : true, singularName : "headerParameter"},
                    xhrSettings : {type : "sap.ui.unified.FileUploaderXHRSettings", multiple : false}
                },

            },
            
            upload: function() {
                  var oFile = jQuery.sap.domById(this.getId() + "-fu").files[0];
                  var oScheduleID = this.getParameters()[0].getValue();
                  try {
                     if (oFile) {
                        this._bUploading = true;
                        var that = this;
                        var _handleSuccess = function(data, textStatus, jqXHR){
                              that.fireUploadComplete({"response": data});
                              that._bUploading = false;
                        }
                     };
                     var _handleError = function(data) {
                        var errorMsg = "";
                        if (data.responseText[1]){
                              errorMsg = /<message>(.*?)<\/message>/.exec(data.responseText)[1];
                        } else {
                              errorMsg = "Something bad happened";
                        }
                        that.fireUploadComplete({"response": "Error: " + errorMsg});
                        that._bUploading = false;
                     };
                    var fd = new FormData();
                    var files = oFile;
                    fd.append('file',files);
                    fd.append('scheduleId',oScheduleID);
                     var oData = {"file":oFile, "scheduleId": oScheduleID};
                     jQuery.ajax({
                        type: "POST",
                        url: this.getUploadUrl(),
                        cache: false,
                        contentType: false,
                        data: fd,
                        processData : false,
                        success: _handleSuccess,
                        error: _handleError

                     });
                     jQuery.sap.log.info("File uploading to " + this.getUploadUrl());
                  } catch (oException) {
                     jQuery.sap.log.error("File upload failed:\n" + oException.message);
                  }
            },
            renderer : {}
         });
    });