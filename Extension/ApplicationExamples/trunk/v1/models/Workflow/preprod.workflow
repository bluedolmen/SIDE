<?xml version="1.0" encoding="UTF-8"?>
<workflow:Process xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:workflow="http://www.kerblue.org/workflow/1.0" xmi:id="_16bV4BDjEd-eFJrsZcrkww" name="preprod" title="Pré-production">
  <swimlane xmi:id="_S_x7cBDkEd-eFJrsZcrkww" name="ServicePreProd" manage="_n6xusBDkEd-eFJrsZcrkww _ykopUBDkEd-eFJrsZcrkww" pooledactors="preprod"/>
  <swimlane xmi:id="_1Q2_ABDkEd-eFJrsZcrkww" name="DAF" manage="_rVErUBDkEd-eFJrsZcrkww" pooledactors="daf"/>
  <startstate xmi:id="_VOsqEBDkEd-eFJrsZcrkww" name="Reception" initiator="_S_x7cBDkEd-eFJrsZcrkww">
    <transition xmi:id="_8qe38BDkEd-eFJrsZcrkww" name="etabDevis" to="_n6xusBDkEd-eFJrsZcrkww" title="Etablissement du devis"/>
  </startstate>
  <endstate xmi:id="_vB4UQBDkEd-eFJrsZcrkww" name="DevisRefuse"/>
  <endstate xmi:id="_-LD54BDkEd-eFJrsZcrkww" name="Production"/>
  <tasknode xmi:id="_n6xusBDkEd-eFJrsZcrkww" name="EtablissementDevis" swimlane="_S_x7cBDkEd-eFJrsZcrkww">
    <transition xmi:id="_8-q_QBDkEd-eFJrsZcrkww" name="analyse" parentTaskNode="_n6xusBDkEd-eFJrsZcrkww" to="_rVErUBDkEd-eFJrsZcrkww" title="Analyse"/>
  </tasknode>
  <tasknode xmi:id="_rVErUBDkEd-eFJrsZcrkww" name="AnalyseDevis" swimlane="_1Q2_ABDkEd-eFJrsZcrkww">
    <transition xmi:id="_9ShIUBDkEd-eFJrsZcrkww" name="ok" parentTaskNode="_rVErUBDkEd-eFJrsZcrkww" to="_ykopUBDkEd-eFJrsZcrkww" title="Accepter le devis"/>
    <transition xmi:id="_9pNxEBDkEd-eFJrsZcrkww" name="ko" parentTaskNode="_rVErUBDkEd-eFJrsZcrkww" to="_vB4UQBDkEd-eFJrsZcrkww" title="Refuser"/>
  </tasknode>
  <tasknode xmi:id="_ykopUBDkEd-eFJrsZcrkww" name="DemarrageProduction" swimlane="_S_x7cBDkEd-eFJrsZcrkww">
    <transition xmi:id="__2HGcBDkEd-eFJrsZcrkww" name="prod" parentTaskNode="_ykopUBDkEd-eFJrsZcrkww" to="_-LD54BDkEd-eFJrsZcrkww" title="Mise en production"/>
  </tasknode>
</workflow:Process>
