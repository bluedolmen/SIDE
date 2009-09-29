<?xml version="1.0" encoding="UTF-8"?>
<workflow:Process xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:workflow="http://www.kerblue.org/workflow/1.0" name="TCWAL_1">
  <swimlane name="admin" manage="//@tasknode.0" actorid="admin"/>
  <startstate name="StartSate" initiator="//@swimlane.0">
    <transition name="Transition1" to="//@tasknode.0" title="Transition 1"/>
  </startstate>
  <endstate name="EndState1"/>
  <tasknode name="TaskNode1" swimlane="//@swimlane.0">
    <transition name="Transition2" parentTaskNode="//@tasknode.0" to="//@endstate.0" title="Transition 2"/>
  </tasknode>
</workflow:Process>
