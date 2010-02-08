var folder = companyhome.childByNamePath("/Sites/coe/documentLibrary/Demande de publication/En cours");
var name =  (new Date()).getTime();
var sfolder = folder.createFolder(name);
document.move(sfolder);
document.specializeType("COEPrePRESS:Document");

var demande = sfolder.createNode(name+"demande","COEPrePRESS:Demande");
document.createAssociation(demande,"COEPrePRESS:Document_demande_Demande");

var wfAction = actions.create("start-workflow");
wfAction.parameters.workflowName = "jbpm$wfbxdemande:demande";
wfAction.parameters.endStartTask = false;
wfAction.parameters["bpm:pooledActors"] = new Array(people.getGroup('GROUP_auteur'));
wfAction.parameters["cm:owner"] = null;
wfAction.parameters["cm:context"] = null;

wfAction.execute(document);