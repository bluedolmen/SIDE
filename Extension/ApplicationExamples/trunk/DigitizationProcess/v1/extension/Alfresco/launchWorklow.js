document.specializeType('DigitizationProcess:com_bluexml_side_models_list_Document');

var wfAction = actions.create("start-workflow");
wfAction.parameters.workflowName = "jbpm$wfbxDigitizationProcess:DigitizationProcess";
wfAction.parameters.endStartTask = true;
wfAction.parameters["bpm:pooledActors"] = new Array(people.getGroup('GROUP_Digitization'));
wfAction.parameters["cm:owner"] = null;
wfAction.parameters["cm:context"] = null;

wfAction.execute(document);