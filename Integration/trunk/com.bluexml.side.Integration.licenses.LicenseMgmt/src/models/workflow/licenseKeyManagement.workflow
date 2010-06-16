<?xml version="1.0" encoding="UTF-8"?>
<workflow:Process xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:workflow="http://www.kerblue.org/workflow/1.0" xmi:id="_SL3msFLZEd-i6vpttZrofQ" description="licenseQuery" name="licenseQuery" title="licenseQuery">
  <swimlane xmi:id="_g1FToFeLEd-bCtLOdKek7g" description="manager" name="manager" manage="_TTI0YFm2Ed-jOfBZTg8wXw _OgJxEFm3Ed-jOfBZTg8wXw" actorid="manager" pooledactors=""/>
  <swimlane xmi:id="_xAg1kFjtEd-xdN4UvW9xAQ" description="user" name="user" actorid="user" pooledactors=""/>
  <startstate xmi:id="_1imWcFeJEd-bCtLOdKek7g" description="start" name="start" title="start" initiator="_xAg1kFjtEd-xdN4UvW9xAQ">
    <transition xmi:id="_MHIgsFm2Ed-jOfBZTg8wXw" description="goAskEvaluationKey" name="goAskEvaluationKey" to="_IpAM8Fm2Ed-jOfBZTg8wXw" title="goAskEvaluationKey"/>
    <attributes xmi:id="_nCR4gFm2Ed-jOfBZTg8wXw" description="typeEvaluationKey" title="typeEvaluationKey" name="typeEvaluationKey">
      <allowedValues>Prototype</allowedValues>
      <allowedValues>SIDE</allowedValues>
    </attributes>
    <attributes xmi:id="_9DpOYW10Ed-vEaearzKnog" description="nbPreviousQueries" title="nbPreviousQueries" name="nbPreviousQueries">
      <allowedValues>Less or equal to two</allowedValues>
      <allowedValues>More than two</allowedValues>
    </attributes>
  </startstate>
  <endstate xmi:id="_QgWRwFm4Ed-jOfBZTg8wXw" description="keyQueryPrototypeFinishedValidated" name="keyQueryPrototypeFinishedValidated" title="keyQueryPrototypeFinishedValidated"/>
  <endstate xmi:id="_l9SfAFm5Ed-jOfBZTg8wXw" description="keyQueryPrototypeFinishedUnvalidated" name="keyQueryPrototypeFinishedUnvalidated" title="keyQueryPrototypeFinishedUnvalidated"/>
  <endstate xmi:id="_SALa4Fm6Ed-jOfBZTg8wXw" description="keyQuerySIDEFinishedUnvalidated" name="keyQuerySIDEFinishedUnvalidated" title="keyQuerySIDEFinishedUnvalidated"/>
  <endstate xmi:id="_5QAlgFm8Ed-jOfBZTg8wXw" description="keyQuerySIDEFinishedValidatedShort" name="keyQuerySIDEFinishedValidatedShort" title="keyQuerySIDEFinishedValidatedShort"/>
  <endstate xmi:id="_8Q8m8Fm8Ed-jOfBZTg8wXw" description="keyQuerySIDEFinishedValidatedLong" name="keyQuerySIDEFinishedValidatedLong" title="keyQuerySIDEFinishedValidatedLong"/>
  <tasknode xmi:id="_TTI0YFm2Ed-jOfBZTg8wXw" description="validateKeyQueryPrototype" name="validateKeyQueryPrototype" title="validateKeyQueryPrototype" swimlane="_g1FToFeLEd-bCtLOdKek7g">
    <transition xmi:id="_gShxEFm4Ed-jOfBZTg8wXw" description="goDecideGenerateKeyPrototype" name="goDecideGenerateKeyPrototype" parentTaskNode="_TTI0YFm2Ed-jOfBZTg8wXw" to="_c7QTQFm4Ed-jOfBZTg8wXw" title="goDecideGenerateKeyPrototype"/>
    <attributes xmi:id="_tIo9UFm3Ed-jOfBZTg8wXw" description="validatedKeyQueryPrototype" title="validatedKeyQueryPrototype" name="validatedKeyQueryPrototype">
      <allowedValues>Yes</allowedValues>
      <allowedValues>No</allowedValues>
    </attributes>
  </tasknode>
  <tasknode xmi:id="_OgJxEFm3Ed-jOfBZTg8wXw" description="validateKeyQuerySIDE" name="validateKeyQuerySIDE" title="validateKeyQuerySIDE" swimlane="_g1FToFeLEd-bCtLOdKek7g">
    <transition xmi:id="_Ge364Fm6Ed-jOfBZTg8wXw" description="goDecideGenerateKeySIDE" name="goDecideGenerateKeySIDE" condition="" parentTaskNode="_OgJxEFm3Ed-jOfBZTg8wXw" to="_EPFCgFm6Ed-jOfBZTg8wXw" title="goDecideGenerateKeySIDE"/>
    <attributes xmi:id="_vViSsFm3Ed-jOfBZTg8wXw" description="validatedKeyQuerySIDE" title="validatedKeyQuerySIDE" name="validatedKeyQuerySIDE">
      <allowedValues>Yes</allowedValues>
      <allowedValues>No</allowedValues>
    </attributes>
  </tasknode>
  <decision xmi:id="_IpAM8Fm2Ed-jOfBZTg8wXw" description="decisionTypeEvaluationKey" name="decisionTypeEvaluationKey" title="decisionTypeEvaluationKey">
    <transition xmi:id="_ckHQgFm2Ed-jOfBZTg8wXw" description="goValidationKeyQueryPrototype" name="goValidationKeyQueryPrototype" condition="" to="_TTI0YFm2Ed-jOfBZTg8wXw" title="goValidationKeyQueryPrototype"/>
    <transition xmi:id="_QWGmEFm3Ed-jOfBZTg8wXw" description="goValidationKeyQuerySIDE" name="goValidationKeyQuerySIDE" condition="#{wfbxlicenseQuery_typeEvaluationKey=='SIDE'}" to="_OgJxEFm3Ed-jOfBZTg8wXw" title="goValidationKeyQuerySIDE"/>
  </decision>
  <decision xmi:id="_c7QTQFm4Ed-jOfBZTg8wXw" description="decisionGenerateKeyPrototype" name="decisionGenerateKeyPrototype" title="decisionGenerateKeyPrototype">
    <transition xmi:id="_9wXIEFm4Ed-jOfBZTg8wXw" description="generateKeyPrototype" name="generateKeyPrototype" condition="#{wfbxlicenseQuery_validatedKeyQueryPrototype=='Yes'}" to="_QgWRwFm4Ed-jOfBZTg8wXw" title="generateKeyPrototype">
      <action xmi:id="_HGZqYFm5Ed-jOfBZTg8wXw" javaClass="com.bluexml.side.Integration.licenses.LicenseMgmt.workflow.GenerateKeyPrototype">
        <script xmi:id="_XGKSsFm5Ed-jOfBZTg8wXw" expression="//"/>
      </action>
    </transition>
    <transition xmi:id="_urVNMFm5Ed-jOfBZTg8wXw" description="noGenerateKeyPrototype" name="noGenerateKeyPrototype" to="_l9SfAFm5Ed-jOfBZTg8wXw" title="noGenerateKeyPrototype">
      <action xmi:id="_0il7gFm5Ed-jOfBZTg8wXw" javaClass="com.bluexml.side.Integration.licenses.LicenseMgmt.workflow.NoGenerateKeyPrototype">
        <script xmi:id="_9qD2UFm5Ed-jOfBZTg8wXw" expression="//"/>
      </action>
    </transition>
  </decision>
  <decision xmi:id="_EPFCgFm6Ed-jOfBZTg8wXw" description="decisionGenerateKeySIDE" name="decisionGenerateKeySIDE" title="decisionGenerateKeySIDE">
    <transition xmi:id="_maDeEFm6Ed-jOfBZTg8wXw" description="noGenerateKeySIDE" name="noGenerateKeySIDE" to="_SALa4Fm6Ed-jOfBZTg8wXw" title="noGenerateKeySIDE">
      <action xmi:id="_2WGEAFm6Ed-jOfBZTg8wXw" javaClass="com.bluexml.side.Integration.licenses.LicenseMgmt.workflow.NoGenerateKeySide">
        <script xmi:id="_-Dt3AFm6Ed-jOfBZTg8wXw" expression="//"/>
      </action>
    </transition>
    <transition xmi:id="_nCBAgFm7Ed-jOfBZTg8wXw" description="goDependsNumberQuery" name="goDependsNumberQuery" condition="#{wfbxlicenseQuery_validatedKeyQuerySIDE=='Yes'}" to="_l04fAFm7Ed-jOfBZTg8wXw" title="goDependsNumberQuery"/>
  </decision>
  <decision xmi:id="_l04fAFm7Ed-jOfBZTg8wXw" description="decisionDependsNumberQuery" name="decisionDependsNumberQuery" title="decisionDependsNumberQuery">
    <transition xmi:id="__KBF0Fm8Ed-jOfBZTg8wXw" description="generateKeySIDEShort" name="generateKeySIDEShort" condition="#{wfbxlicenseQuery_nbPreviousQueries == 'Less or equal to two'}" to="_5QAlgFm8Ed-jOfBZTg8wXw" title="generateKeySIDEShort">
      <action xmi:id="_NmuxkFm-Ed-jOfBZTg8wXw" javaClass="com.bluexml.side.Integration.licenses.LicenseMgmt.workflow.GenerateKeySideShort">
        <script xmi:id="_ZnkGMFm-Ed-jOfBZTg8wXw" expression="//"/>
      </action>
    </transition>
    <transition xmi:id="_eXiRwFm9Ed-jOfBZTg8wXw" description="generateKeySIDELong" name="generateKeySIDELong" to="_8Q8m8Fm8Ed-jOfBZTg8wXw" title="generateKeySIDELong">
      <action xmi:id="_uY-foFm-Ed-jOfBZTg8wXw" javaClass="com.bluexml.side.Integration.licenses.LicenseMgmt.workflow.GenerateKeySideLong">
        <script xmi:id="_88elUFm-Ed-jOfBZTg8wXw" expression="//"/>
      </action>
    </transition>
  </decision>
</workflow:Process>
