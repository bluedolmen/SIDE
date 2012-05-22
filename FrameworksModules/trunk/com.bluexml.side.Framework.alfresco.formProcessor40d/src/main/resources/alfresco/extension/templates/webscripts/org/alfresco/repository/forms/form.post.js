function main() {
   // Extract template args
	var itemKind = decodeURIComponent(url.templateArgs["item_kind"]);
	var itemId = decodeURIComponent(url.templateArgs["item_id"]);

   if (logger.isLoggingEnabled()) {
      logger.log("multipart form submission for item:");
      logger.log("\tkind = " + itemKind);
      logger.log("\tid = " + itemId);
   }

   // potential redirect URL
   var redirect = null;
   var persistedObject = null;

   try {
      // persist the submitted data using the most appropriate data set
      if (typeof formdata !== "undefined") {
         logger.log("webscript formdata :" + formdata);
         // N.B. This repoFormData is a different FormData class to that used
         // above.
         var repoFormData = new Packages.com.bluexml.side.framework.alfresco.formProcessor.CustomFormData();
         logger.log("repoFormData :" + repoFormData);
         for ( var i = 0; i < formdata.fields.length; i++) {
            if (formdata.fields[i].name == "alf_redirect") {
               // store redirect url
               redirect = formdata.fields[i].value;
            } else {
               if (formdata.fields[i].isFile) {
                  repoFormData.addFieldData(formdata.fields[i]);
               } else {
                  // add field to form data
                  repoFormData.addFieldData(formdata.fields[i].name, formdata.fields[i].value);
               }
            }
         }

         persistedObject = formService.saveForm(itemKind, itemId, repoFormData);
      } else {
         if (logger.isLoggingEnabled()) {
            logger.log("Saving form with args = " + args);
         }

         persistedObject = formService.saveForm(itemKind, itemId, args);
      }
   } catch (error) {
      var msg = error.message;

      if (logger.isLoggingEnabled())
         logger.log(msg);

      // if a redirect URL was provided send a redirect response
      if (redirect !== null) {

         status.redirect = true;
         status.code = 301;
         if (redirect.indexOf("?") != -1) {
            redirect += "&";
         } else {
            redirect += "?";
         }
         
         redirect += "error=" + msg;
         status.location = redirect;
         if (logger.isLoggingEnabled())
            logger.log("Returning 301 status code to redirect to: " + status.location);

      } else {
         // determine if the exception was a FormNotFoundException, if so return
         // 404 status code otherwise return 500
         if (msg.indexOf("FormNotFoundException") != -1) {
            status.setCode(404, msg);

            if (logger.isLoggingEnabled())
               logger.log("Returning 404 status code");
         } else {
            status.setCode(500, msg);

            if (logger.isLoggingEnabled())
               logger.log("Returning 500 status code");
         }
      }

      return;

   }

   // if a redirect URL was provided send a redirect response
   if (redirect !== null) {

      status.redirect = true;
      status.code = 301;
      if (redirect.indexOf("?") != -1) {
         redirect += "&";
      } else {
         redirect += "?";
      }
      redirect += "nodeRef=" + persistedObject.toString();
      status.location = redirect;
      if (logger.isLoggingEnabled())
         logger.log("Returning 301 status code to redirect to: " + status.location);

   }

   model.persistedObject = persistedObject.toString();
   model.message = "Successfully persisted form for item [" + itemKind + "]" + itemId;

}

main();