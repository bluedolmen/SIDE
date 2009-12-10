<?xml version="1.0" encoding="UTF-8"?>
<workflow:Process xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:workflow="http://www.kerblue.org/workflow/1.0" xmi:id="_30L-UNRSEd6mvv4ISlOk-Q" description=" " name="DigitizationProcess" title="Digitization Process" processDescription="The Digitization Process allows to integrate digitized document in an ECM repository through meta-data editing and approval steps.">
  <swimlane xmi:id="_30MlYNRSEd6mvv4ISlOk-Q" documentation="This agent group is secialized in document digitization process.&#xD;&#xA;They do not have business knowledge about the document they handled." description="Agent performing the integration of the digitized document in alfresco" name="Digitization" manage="_30SsANRSEd6mvv4ISlOk-Q" actorid="num" pooledactors="Digitization"/>
  <swimlane xmi:id="_30MlYdRSEd6mvv4ISlOk-Q" documentation="This agent group has a business knowledge of what is related to vehicles." description="agents updating the meta-data of vehicle related documents" name="Vehicle" manage="_30TTE9RSEd6mvv4ISlOk-Q" pooledactors="Vehicle"/>
  <swimlane xmi:id="_30MlYtRSEd6mvv4ISlOk-Q" documentation="This agent group has a business knowledge of what is related to mail." description="agents updating the meta-data of mail related documents" name="Mail" manage="_30TTEdRSEd6mvv4ISlOk-Q" pooledactors="Mail"/>
  <swimlane xmi:id="_30MlY9RSEd6mvv4ISlOk-Q" name="Manager" manage="_30TTHdRSEd6mvv4ISlOk-Q" pooledactors="Manager"/>
  <swimlane xmi:id="_TqytcOWZEd6to8edPQE7Gw" documentation="This agent group has a business knowledge of what is related to quotation." description="agents updating the meta-data of quotation related documents" name="Quotation" manage="_W2J2MOWZEd6to8edPQE7Gw" pooledactors="Quotation"/>
  <startstate xmi:id="_30OakNRSEd6mvv4ISlOk-Q" name="Start" title="Start of the e-procedure" stateDescription="Start by integration of the digitized document in alfresco repository" initiator="_30MlYNRSEd6mvv4ISlOk-Q">
    <transition xmi:id="_30PBoNRSEd6mvv4ISlOk-Q" description="Assign the document type choosing between mail, vehicle, quotation or invoice" name="DocumentType" to="_30SsANRSEd6mvv4ISlOk-Q" title="Assign the document type">
      <action xmi:id="_9QcKUNXAEd6FW7J925m8sQ" javaClass="org.alfresco.repo.workflow.jbpm.AlfrescoJavaScript">
        <script xmi:id="_-RZ4ENXAEd6FW7J925m8sQ" expression="var node =bpm_package.children[0];&#xD;&#xA;node.properties['DigitizationProcess:com_bluexml_side_models_liste_Document_Auteur'] = node.properties['cm:creator'];&#xD;&#xA;node.properties['DigitizationProcess:com_bluexml_side_models_liste_Document_DateNumerisation'] = node.properties['cm:created'];&#xD;&#xA;node.save();"/>
      </action>
    </transition>
  </startstate>
  <endstate xmi:id="_30PosNRSEd6mvv4ISlOk-Q" name="End"/>
  <node xmi:id="_gwoYQNW0Ed6FW7J925m8sQ" name="MoveDocument">
    <event xmi:id="_wBbBwNW0Ed6FW7J925m8sQ" type="node-enter">
      <action xmi:id="_w9oLUNW0Ed6FW7J925m8sQ" javaClass="org.alfresco.repo.workflow.jbpm.AlfrescoJavaScript">
        <script xmi:id="_SoKzUNW1Ed6FW7J925m8sQ" expression="var node =bpm_package.children[0];&#xD;&#xA;&#xD;&#xA;var destination = node.parent.parent.childByNamePath(&quot;achieved&quot;);&#xD;&#xA;&#xD;&#xA;if (node.typeShort == 'DigitizationProcess:com_bluexml_side_models_liste_OutgoingMail') {&#xD;&#xA;&#x9;destination = destination.childByNamePath(&quot;mail&quot;);&#xD;&#xA;} else if (node.typeShort == 'DigitizationProcess:com_bluexml_side_models_liste_Vehicle') {&#xD;&#xA;&#x9;destination = destination.childByNamePath(&quot;vehicle&quot;);&#xD;&#xA;} else if (node.typeShort == 'DigitizationProcess:com_bluexml_side_models_liste_Quotation') {&#xD;&#xA;&#x9;destination = destination.childByNamePath(&quot;quotation&quot;);&#xD;&#xA;}&#xD;&#xA;&#xD;&#xA;node.move(destination);"/>
      </action>
    </event>
    <transition xmi:id="_tzGJANW0Ed6FW7J925m8sQ" name="toEnd" to="_30PosNRSEd6mvv4ISlOk-Q" title="Fin"/>
  </node>
  <tasknode xmi:id="_30SsANRSEd6mvv4ISlOk-Q" description="" name="AssignDocumentType" title="Assign the type of the document" stateDescription="Assignment of the document type" swimlane="_30MlYNRSEd6mvv4ISlOk-Q">
    <transition xmi:id="_30SsCNRSEd6mvv4ISlOk-Q" name="testDocumentType" parentTaskNode="_30SsANRSEd6mvv4ISlOk-Q" to="_30T6INRSEd6mvv4ISlOk-Q" title="Check document type">
      <action xmi:id="_O_3CkNU2Ed6Ay6xwhgeFBA" javaClass="org.alfresco.repo.workflow.jbpm.AlfrescoJavaScript">
        <script xmi:id="_O_3CkdU2Ed6Ay6xwhgeFBA" expression="var node =bpm_package.children[0];&#xD;&#xA;var typeDoc = wfbxDigitizationProcess_CategorieDocument;&#xD;&#xA;if (typeDoc == 'mail') {&#xD;&#xA;&#x9;node.specializeType('DigitizationProcess:com_bluexml_side_models_liste_OutgoingMail');&#xD;&#xA;} else if (typeDoc == 'vehicle') {&#xD;&#xA;&#x9;node.specializeType('DigitizationProcess:com_bluexml_side_models_liste_Vehicle');&#xD;&#xA;} else if (typeDoc == 'quotation') {&#xD;&#xA;&#x9;node.specializeType('DigitizationProcess:com_bluexml_side_models_liste_Quotation');&#xD;&#xA;};"/>
      </action>
    </transition>
    <attributes xmi:id="_30TTENRSEd6mvv4ISlOk-Q" description="" title="Document Content type" name="DocumentType">
      <allowedValues>vehicle</allowedValues>
      <allowedValues>mail</allowedValues>
      <allowedValues>quotation</allowedValues>
    </attributes>
  </tasknode>
  <tasknode xmi:id="_30TTEdRSEd6mvv4ISlOk-Q" description="" name="ProcessMail" title="Process a mail related document" stateDescription="Manage the integration of a outgoing mail related document in ECM" swimlane="_30MlYtRSEd6mvv4ISlOk-Q">
    <transition xmi:id="_30TTEtRSEd6mvv4ISlOk-Q" description="Request the approval before integrating the document in the ECM" name="ApprovalRequest" parentTaskNode="_30TTEdRSEd6mvv4ISlOk-Q" to="_30TTHdRSEd6mvv4ISlOk-Q" title="Request the approval"/>
  </tasknode>
  <tasknode xmi:id="_30TTE9RSEd6mvv4ISlOk-Q" description="Manage the integration of a vehicle related document" name="ProcessVehicleDocument" title="Process a vehicle related document" stateDescription="Manage the integration of a vehicle related document in ECM" swimlane="_30MlYdRSEd6mvv4ISlOk-Q">
    <transition xmi:id="_30TTFNRSEd6mvv4ISlOk-Q" description="The document is integrated in the targted space in the ECM" name="integrateVehicleDocument" parentTaskNode="_30TTE9RSEd6mvv4ISlOk-Q" to="_gwoYQNW0Ed6FW7J925m8sQ" title="Integrate the document in the ECM"/>
  </tasknode>
  <tasknode xmi:id="_30TTHdRSEd6mvv4ISlOk-Q" description="" name="Approve" title="Approve a mail related document integration" stateDescription="Approve the integration in the ECM repository" swimlane="_30MlY9RSEd6mvv4ISlOk-Q">
    <transition xmi:id="_30TTHtRSEd6mvv4ISlOk-Q" description="The document is integrated in the targeted space of the ECM" name="integrateMailDocument" parentTaskNode="_30TTHdRSEd6mvv4ISlOk-Q" to="_gwoYQNW0Ed6FW7J925m8sQ" title="Integrate the document in the ECM repository"/>
    <transition xmi:id="_wXK6MNWyEd6Ay6xwhgeFBA" name="RequestUpdate" parentTaskNode="_30TTHdRSEd6mvv4ISlOk-Q" to="_30TTEdRSEd6mvv4ISlOk-Q" title="Complete the document meta-data"/>
    <attributes xmi:id="_30TTH9RSEd6mvv4ISlOk-Q" name="Comment"/>
  </tasknode>
  <tasknode xmi:id="_W2J2MOWZEd6to8edPQE7Gw" name="ProcessQuotation" title="Process a quotation related document" stateDescription="Manage the integration of a quotation related document in ECM" swimlane="_TqytcOWZEd6to8edPQE7Gw">
    <transition xmi:id="_cGcrIOWZEd6to8edPQE7Gw" description="The document is integrated in the targeted space of the ECM" name="integrateQuotationDocument" parentTaskNode="_W2J2MOWZEd6to8edPQE7Gw" to="_gwoYQNW0Ed6FW7J925m8sQ" title="Integrate the document in the ECM repository"/>
  </tasknode>
  <decision xmi:id="_30T6INRSEd6mvv4ISlOk-Q" description="" name="TestDocumentType" title="Test the selected document Type">
    <transition xmi:id="_30T6IdRSEd6mvv4ISlOk-Q" name="IfMail" condition="#{wfbxDigitizationProcess_CategorieDocument==&quot;mail&quot;}" to="_30TTEdRSEd6mvv4ISlOk-Q" title="If document related to a mail"/>
    <transition xmi:id="_30T6ItRSEd6mvv4ISlOk-Q" name="IfVehicle" condition="#{wfbxDigitizationProcess_CategorieDocument=='vehicle'}" to="_30TTE9RSEd6mvv4ISlOk-Q" title="If document related to vehicle"/>
    <transition xmi:id="_30T6I9RSEd6mvv4ISlOk-Q" name="SiAutreType" condition="" to="_30SsANRSEd6mvv4ISlOk-Q" title="Si autre type"/>
    <transition xmi:id="_mH0CYOWZEd6to8edPQE7Gw" name="IfQuotation" condition="#{wfbxDigitizationProcess_CategorieDocument==&quot;quotation&quot;}" to="_W2J2MOWZEd6to8edPQE7Gw" title="If document related to a quotation document"/>
  </decision>
  <elements xsi:type="workflow:Action" xmi:id="_UjKYgOWcEd6to8edPQE7Gw" javaClass="Action1"/>
</workflow:Process>
