var mail = actions.create("mail");
mail.parameters.to = initiator.properties["cm:email"];
mail.parameters.subject = "Task " + bpm_workflowDescription;
//mail.parameters.from = bpm_assignee.properties["cm:email"];
mail.parameters.from = "system@alfresco.com"
mail.parameters.text = "It's done";
mail.execute(bpm_package);