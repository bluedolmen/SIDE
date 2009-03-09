<?xml version="1.0" encoding="UTF-8"?>
<workflow:Process xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:workflow="http://www.kerblue.org/workflow/1.0" name="startPotts">
  <swimlane name="User" manage="//@tasknode.2 //@tasknode.3" actorid="initiator"/>
  <swimlane name="EngineeringGroup" manage="//@tasknode.0" pooledactors="GROUP_engineering"/>
  <swimlane name="MarketingGroup" manage="//@tasknode.1" pooledactors="GROUP_marketing"/>
  <startstate name="StartPotts" initiator="//@swimlane.0">
    <transition name="Transition1" to="//@startstate"/>
    <transition name="" to="//@node.1"/>
    <attributes typ="String" name="reviewer_email"/>
  </startstate>
  <endstate name="EndTask"/>
  <node name="Approved">
    <transition name="" to="//@endstate.0">
      <action javaClass="org.alfresco.repo.workflow.jbpm.AlfrescoJavaScript">
        <script expression="logger.log(&quot;Action to realize&quot;);"/>
      </action>
    </transition>
  </node>
  <node name="SubmitPotts">
    <transition name="" to="//@fork.0"/>
    <event type="node-enter">
      <action javaClass="org.alfresco.repo.workflow.jbpm.AlfrescoJavaScript">
        <script expression="approveCount = 0;">
          <variable name="approveCount" access="read,write"/>
        </script>
      </action>
    </event>
  </node>
  <tasknode name="MarketingReview" swimlane="//@swimlane.1">
    <transition name="reject" to="//@join.0"/>
    <transition name="accept" to="//@join.0">
      <action javaClass="org.alfresco.repo.workflow.jbpm.AlfrescoJavaScript">
        <script expression="approveCount = approveCount + 1;">
          <variable name="new" access="read,write"/>
        </script>
      </action>
    </transition>
    <clazz href="My.clazz#//@packageSet.0/@packageSet.0/@classSet.0"/>
  </tasknode>
  <tasknode name="EngineeringReview" swimlane="//@swimlane.2">
    <transition name="reject" to="//@join.0"/>
    <transition name="accept" to="//@join.0">
      <action javaClass="org.alfresco.repo.workflow.jbpm.AlfrescoJavaScript">
        <script expression="approveCount = approveCount + 1;">
          <variable name="new" access="read,write"/>
        </script>
      </action>
    </transition>
  </tasknode>
  <tasknode name="Revise" swimlane="//@swimlane.0">
    <transition name="" to="//@endstate.0">
      <timer duedate="1 minutes"/>
    </transition>
    <transition name="" to="//@node.1"/>
  </tasknode>
  <tasknode name="ThirdPartyReview" swimlane="//@swimlane.0">
    <transition name="" to="//@tasknode.2"/>
    <transition name="" to="//@node.0"/>
    <event type="node-enter">
      <action javaClass="org.alfresco.repo.workflow.jbpm.AlfrescoJavaScript">
        <script expression="notificationRecipient = bxwf_reviewerEmail;">
          <variable name="notificationRecipient" access="read,write"/>
          <variable name="bxwf_reviewerEmail" access="access"/>
        </script>
      </action>
    </event>
  </tasknode>
  <fork>
    <transition name="" to="//@tasknode.0"/>
    <transition name="" to="//@tasknode.1"/>
  </fork>
  <join>
    <transition name="" to="//@decision.0"/>
  </join>
  <decision>
    <transition name="" condition="#{approveCount == 2}" to="//@decision.1" title=""/>
    <transition name="" to="//@tasknode.2"/>
  </decision>
  <decision>
    <transition name="" condition="#{wfbx_reviewerEmail!=&quot;&quot;}" to="//@tasknode.3"/>
    <transition name="" to="//@node.0"/>
  </decision>
</workflow:Process>
