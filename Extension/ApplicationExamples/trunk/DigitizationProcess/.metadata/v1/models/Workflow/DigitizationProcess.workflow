<?xml version="1.0" encoding="ASCII"?>
<workflow:Process xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:workflow="http://www.kerblue.org/workflow/1.0" description=" " name="DigitizationProcess" title="Digitization Process" processDescription="The Digitization Process allows to integrate digitized document in an ECM repository through meta-data editing and approval steps.">
  <swimlane documentation="This agent group is secialized in document digitization process.&#xD;&#xA;They do not have business knowledge about the document they handled." description="Agent performing the integration of the digitized document in alfresco" name="Digitization" manage="//@tasknode.0" actorid="" pooledactors="Digitization"/>
  <swimlane documentation="This agent group has a business knowledge of what is related to vehicles." description="agents updating the meta-data of vehicle related documents" name="Vehicle" manage="//@tasknode.2" pooledactors="Vehicle"/>
  <swimlane documentation="This agent group has a business knowledge of what is related to mail." description="agents updating the meta-data of mail related documents" name="Mail" manage="//@tasknode.1" pooledactors="Mail"/>
  <swimlane name="Manager" manage="//@tasknode.3" pooledactors="Manager"/>
  <swimlane documentation="This agent group has a business knowledge of what is related to quotation." description="agents updating the meta-data of quotation related documents" name="Quotation" manage="//@tasknode.4" pooledactors="Quotation"/>
  <startstate name="Start" title="Start of the e-procedure" stateDescription="Start by integration of the digitized document in alfresco repository" initiator="//@swimlane.0">
    <transition description="Assign the document type choosing between mail, vehicle, quotation or invoice" name="DocumentType" to="//@tasknode.0" title="Assign the document type">
      <action javaClass="org.alfresco.repo.workflow.jbpm.AlfrescoJavaScript">
        <script expression="var node =bpm_package.children[0];&#xD;&#xA;node.properties['DigitizationProcess:com_bluexml_side_models_list_Document_Author'] = node.properties['cm:creator'];&#xD;&#xA;node.properties['DigitizationProcess:com_bluexml_side_models_list_Document_DigitizationDate'] = node.properties['cm:created'];&#xD;&#xA;node.save();"/>
      </action>
    </transition>
  </startstate>
  <endstate name="End"/>
  <node name="MoveDocument">
    <event type="node-enter">
      <action javaClass="org.alfresco.repo.workflow.jbpm.AlfrescoJavaScript">
        <script expression="var node =bpm_package.children[0];&#xD;&#xA;&#xD;&#xA;var destination = node.parent.parent.childByNamePath(&quot;achieved&quot;);&#xD;&#xA;&#xD;&#xA;if (node.typeShort == 'DigitizationProcess:com_bluexml_side_models_list_OutgoingMail') {&#xD;&#xA;&#x9;destination = destination.childByNamePath(&quot;mail&quot;);&#xD;&#xA;} else if (node.typeShort == 'DigitizationProcess:com_bluexml_side_models_list_Vehicle') {&#xD;&#xA;&#x9;destination = destination.childByNamePath(&quot;vehicle&quot;);&#xD;&#xA;} else if (node.typeShort == 'DigitizationProcess:com_bluexml_side_models_list_Quotation') {&#xD;&#xA;&#x9;destination = destination.childByNamePath(&quot;quotation&quot;);&#xD;&#xA;}&#xD;&#xA;&#xD;&#xA;node.move(destination);"/>
      </action>
    </event>
    <transition name="toEnd" to="//@endstate.0" title="Fin"/>
  </node>
  <tasknode description="" name="AssignDocumentType" title="Assign the type of the document" stateDescription="Assignment of the document type" swimlane="//@swimlane.0">
    <transition name="testDocumentType" parentTaskNode="//@tasknode.0" to="//@decision.0" title="Check document type">
      <action javaClass="org.alfresco.repo.workflow.jbpm.AlfrescoJavaScript">
        <script expression="var node =bpm_package.children[0];&#xD;&#xA;var typeDoc = wfbxDigitizationProcess_DocumentType;&#xD;&#xA;if (typeDoc == 'mail') {&#xD;&#xA;&#x9;node.specializeType('DigitizationProcess:com_bluexml_side_models_list_OutgoingMail');&#xD;&#xA;} else if (typeDoc == 'vehicle') {&#xD;&#xA;&#x9;node.specializeType('DigitizationProcess:com_bluexml_side_models_list_Vehicle');&#xD;&#xA;} else if (typeDoc == 'quotation') {&#xD;&#xA;&#x9;node.specializeType('DigitizationProcess:com_bluexml_side_models_list_Quotation');&#xD;&#xA;};"/>
      </action>
    </transition>
    <attributes description="" title="Document Content type" name="DocumentType">
      <allowedValues>vehicle</allowedValues>
      <allowedValues>mail</allowedValues>
      <allowedValues>quotation</allowedValues>
    </attributes>
  </tasknode>
  <tasknode description="" name="ProcessMail" title="Process a mail related document" stateDescription="Manage the integration of a outgoing mail related document in ECM" swimlane="//@swimlane.2">
    <transition description="Request the approval before integrating the document in the ECM" name="ApprovalRequest" parentTaskNode="//@tasknode.1" to="//@tasknode.3" title="Request the approval"/>
  </tasknode>
  <tasknode description="Manage the integration of a vehicle related document" name="ProcessVehicleDocument" title="Process a vehicle related document" stateDescription="Manage the integration of a vehicle related document in ECM" swimlane="//@swimlane.1">
    <transition description="The document is integrated in the targted space in the ECM" name="integrateVehicleDocument" parentTaskNode="//@tasknode.2" to="//@node.0" title="Integrate the document in the ECM"/>
  </tasknode>
  <tasknode description="" name="Approve" title="Approve a mail related document integration" stateDescription="Approve the integration in the ECM repository" swimlane="//@swimlane.3">
    <transition description="The document is integrated in the targeted space of the ECM" name="integrateMailDocument" parentTaskNode="//@tasknode.3" to="//@node.0" title="Integrate the document in the ECM repository"/>
    <transition name="RequestUpdate" parentTaskNode="//@tasknode.3" to="//@tasknode.1" title="Complete the document meta-data"/>
    <attributes name="Comment"/>
  </tasknode>
  <tasknode name="ProcessQuotation" title="Process a quotation related document" stateDescription="Manage the integration of a quotation related document in ECM" swimlane="//@swimlane.4">
    <transition description="The document is integrated in the targeted space of the ECM" name="integrateQuotationDocument" parentTaskNode="//@tasknode.4" to="//@node.0" title="Integrate the document in the ECM repository"/>
  </tasknode>
  <decision description="" name="TestDocumentType" title="Test the selected document Type">
    <transition name="IfMail" condition="#{wfbxDigitizationProcess_DocumentType==&quot;mail&quot;}" to="//@tasknode.1" title="If document related to a mail"/>
    <transition name="IfVehicle" condition="#{wfbxDigitizationProcess_DocumentType=='vehicle'}" to="//@tasknode.2" title="If document related to vehicle"/>
    <transition name="typeUndefined" condition="" to="//@tasknode.0" title="type not defined"/>
    <transition description="" name="IfQuotation" condition="#{wfbxDigitizationProcess_DocumentType==&quot;quotation&quot;}" to="//@tasknode.4" title="If document related to a quotation document"/>
  </decision>
  <elements xsi:type="workflow:Action" javaClass="Action1"/>
</workflow:Process>
