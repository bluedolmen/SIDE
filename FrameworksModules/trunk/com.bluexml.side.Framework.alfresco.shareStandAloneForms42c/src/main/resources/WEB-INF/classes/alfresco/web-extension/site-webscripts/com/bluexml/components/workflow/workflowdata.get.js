function main()
{
   // Widget instantiation metadata...
   var startWorkflow = {
      id : "StandAloneStartWorkflow", 
      name : "SIDE.StandAloneStartWorkflow",
      options : {
         failureMessage : "message.failure",
         submitButtonMessageKey : "button.startWorkflow",
         selectedItems : (page.url.args.selectedItems != null) ? page.url.args.selectedItems: "",
         destination : (page.url.args.destination != null) ? page.url.args.destination : ""
      }
   };
   model.widgets = [startWorkflow];
}

main();
