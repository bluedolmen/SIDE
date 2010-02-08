<?xml version="1.0" encoding="UTF-8"?>
<workflow:Process xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:workflow="http://www.kerblue.org/workflow/1.0" xmi:id="_reS3oBAGEd-QPZneBoCQXA" name="demande" title="Demande de publication">
  <swimlane xmi:id="_2FdDkBAGEd-QPZneBoCQXA" name="ServiceAuteurs" manage="_ixM5wBAIEd-QPZneBoCQXA" pooledactors="auteur" clazz=""/>
  <swimlane xmi:id="_ABTBoBAHEd-QPZneBoCQXA" name="ServiceCommunication" manage="_GeAmYBAHEd-QPZneBoCQXA _1BhPcBAIEd-QPZneBoCQXA _18888BAIEd-QPZneBoCQXA" pooledactors="communication"/>
  <startstate xmi:id="_5g8TABAGEd-QPZneBoCQXA" name="DemandePublication" title="Demande de publication" initiator="_2FdDkBAGEd-QPZneBoCQXA">
    <transition xmi:id="_MNuiQBAHEd-QPZneBoCQXA" name="demande" to="_GeAmYBAHEd-QPZneBoCQXA" title="Demande de la publication">
      <action xmi:id="_jRklYBDYEd-2T7u5qLlNWA" javaClass="org.alfresco.repo.workflow.jbpm.AlfrescoJavaScript">
        <script xmi:id="_jRklYRDYEd-2T7u5qLlNWA" expression="var dmd = bpm_package.children[0].assocs[&quot;COEPrePRESS:Document_demande_Demande&quot;][0];&#xD;dmd.properties['COEPrePRESS:Demande_DateDemande'] = dmd.properties['cm:created'];&#xD;dmd.save();"/>
      </action>
    </transition>
  </startstate>
  <endstate xmi:id="_FvBHgBAJEd-QPZneBoCQXA" name="Fin"/>
  <node xmi:id="_W06HABDxEd-O_agBGzZuOQ" name="Traitement">
    <transition xmi:id="_i4VcoBDxEd-O_agBGzZuOQ" name="goToEnd" to="_FvBHgBAJEd-QPZneBoCQXA"/>
  </node>
  <tasknode xmi:id="_GeAmYBAHEd-QPZneBoCQXA" name="Reception" title="Reception de la demande" swimlane="_ABTBoBAHEd-QPZneBoCQXA">
    <transition xmi:id="_qIDLgBAIEd-QPZneBoCQXA" name="complement" parentTaskNode="_GeAmYBAHEd-QPZneBoCQXA" to="_ixM5wBAIEd-QPZneBoCQXA" title="Demande de complément d'information"/>
    <transition xmi:id="_3D1FcBAIEd-QPZneBoCQXA" name="refuser" parentTaskNode="_GeAmYBAHEd-QPZneBoCQXA" to="_1BhPcBAIEd-QPZneBoCQXA" title="Refuser la demande"/>
    <transition xmi:id="_5CHXEBAIEd-QPZneBoCQXA" name="accepter" parentTaskNode="_GeAmYBAHEd-QPZneBoCQXA" to="_18888BAIEd-QPZneBoCQXA" title="Accepter la demande"/>
    <attributes xmi:id="_cqNGUBAIEd-QPZneBoCQXA" name="commentaire"/>
  </tasknode>
  <tasknode xmi:id="_ixM5wBAIEd-QPZneBoCQXA" name="Complement" swimlane="_2FdDkBAGEd-QPZneBoCQXA">
    <transition xmi:id="_unWBUBAIEd-QPZneBoCQXA" name="repondre" parentTaskNode="_ixM5wBAIEd-QPZneBoCQXA" to="_GeAmYBAHEd-QPZneBoCQXA" title="Soumettre ma réponse"/>
    <attributes xmi:id="_o8ohQBAIEd-QPZneBoCQXA" name="reponse"/>
  </tasknode>
  <tasknode xmi:id="_1BhPcBAIEd-QPZneBoCQXA" name="Refuser" swimlane="_ABTBoBAHEd-QPZneBoCQXA">
    <transition xmi:id="_DDwbIBAJEd-QPZneBoCQXA" name="archiver" parentTaskNode="_1BhPcBAIEd-QPZneBoCQXA" to="_W06HABDxEd-O_agBGzZuOQ" title="Archiver la demande">
      <action xmi:id="_MXMZsBDrEd-O_agBGzZuOQ" javaClass="org.alfresco.repo.workflow.jbpm.AlfrescoJavaScript">
        <script xmi:id="_NsexoBDrEd-O_agBGzZuOQ" expression="var doc =bpm_package.children[0];&#xD;var folder = doc.parent;&#xD;folder.move(companyhome.childByNamePath(&quot;/Sites/coe/documentLibrary/Demande de publication/Statuee&quot;));"/>
      </action>
    </transition>
    <attributes xmi:id="__jup8BAIEd-QPZneBoCQXA" name="motif"/>
  </tasknode>
  <tasknode xmi:id="_18888BAIEd-QPZneBoCQXA" name="Accepter" swimlane="_ABTBoBAHEd-QPZneBoCQXA">
    <transition xmi:id="_7sAVABAJEd---eo9saYntw" name="creationProjet" parentTaskNode="_18888BAIEd-QPZneBoCQXA" to="_FvBHgBAJEd-QPZneBoCQXA" title="Création du projet de publication">
      <action xmi:id="_6rZykBDYEd-2T7u5qLlNWA" javaClass="org.alfresco.repo.workflow.jbpm.AlfrescoJavaScript">
        <script xmi:id="_7YlosBDYEd-2T7u5qLlNWA" expression="var doc = bpm_package.children[0];&#xA;var isbn = wfbxdemande_ISBN;&#xA;var prix = wfbxdemande_Prix;&#xA;&#xA;var mail = actions.create(&quot;mail&quot;);&#xA;mail.parameters.to = &quot;bchevallereau@bluexml.com&quot;;&#xA;mail.parameters.subject = &quot;Demande acceptée&quot;;&#xA;mail.parameters.from = &quot;bchevallereau@bluexml.com&quot;;&#xA;mail.parameters.text = &quot;Votre demande est acceptée.\n Prix : &quot; + prix + &quot;\n ISBN : &quot; + isbn;&#xA;mail.execute(doc);&#xA;"/>
      </action>
      <action xmi:id="_JC3iMBDrEd-O_agBGzZuOQ" javaClass="org.alfresco.repo.workflow.jbpm.AlfrescoJavaScript">
        <script xmi:id="_LDpuMBDrEd-O_agBGzZuOQ" expression="var projects = companyhome.childByNamePath(&quot;/Sites/coe/documentLibrary/Projet de publication&quot;);&#xD;var doc =bpm_package.children[0];&#xD;var demande = doc.assocs[&quot;COEPrePRESS:Document_demande_Demande&quot;][0];&#xD;var demandeF = doc.parent;&#xD;&#xD;var name = demandeF.properties.name;&#xD;var projetF = projects.createFolder(name);&#xD;&#xD;var newDoc = projetF.createNode(name+&quot;-project&quot;,&quot;COEPrePRESS:Projet&quot;);&#xD;newDoc.createAssociation(demande,&quot;COEPrePRESS:Projet_demande_Demande&quot;);&#xD;&#xD;var wfAction = actions.create(&quot;start-workflow&quot;);&#xD;wfAction.parameters.workflowName = &quot;jbpm$wfbxpreprod:preprod&quot;;&#xD;wfAction.parameters.endStartTask = false;&#xD;wfAction.parameters[&quot;bpm:pooledActors&quot;] = new Array(people.getGroup('GROUP_preprod'));&#xD;wfAction.parameters[&quot;cm:owner&quot;] = null;&#xD;wfAction.parameters[&quot;cm:context&quot;] = null;&#xD;wfAction.execute(newDoc);&#xD;&#xD;var preprodF = projetF.createFolder(&quot;Pré-production&quot;);&#xD;preprodF.createFolder(&quot;Devis&quot;);&#xD;var dF = preprodF.createFolder(&quot;Demande&quot;);&#xD;doc.move(dF);&#xD;demande.move(dF);&#xD;&#xD;var prodF = projetF.createFolder(&quot;Production&quot;);&#xD;prodF.createFolder(&quot;Planning&quot;);&#xD;prodF.createFolder(&quot;Intervenant&quot;);&#xD;prodF.createFolder(&quot;Epreuve&quot;);&#xD;prodF.createFolder(&quot;BAT&quot;);&#xD;prodF.createFolder(&quot;Bon de commande&quot;);&#xD;prodF.createFolder(&quot;Contrôle Qualités&quot;);&#xD;prodF.createFolder(&quot;Commande&quot;);&#xD;&#xD;var impF = projetF.createFolder(&quot;Impression&quot;);&#xD;impF.createFolder(&quot;Bon de commande&quot;);&#xD;impF.createFolder(&quot;Commande&quot;);&#xD;impF.createFolder(&quot;Contrôle Qualités&quot;);&#xD;&#xD;var statutF = projetF.createFolder(&quot;Statut&quot;);&#xD;statutF.createFolder(&quot;En cours&quot;);&#xD;statutF.createFolder(&quot;Archivé&quot;);"/>
      </action>
    </transition>
    <attributes xmi:id="_Nda9oBAJEd-QPZneBoCQXA" name="ISBN"/>
    <attributes xmi:id="_RUrjEBAJEd-QPZneBoCQXA" typ="double" name="Prix"/>
  </tasknode>
</workflow:Process>
