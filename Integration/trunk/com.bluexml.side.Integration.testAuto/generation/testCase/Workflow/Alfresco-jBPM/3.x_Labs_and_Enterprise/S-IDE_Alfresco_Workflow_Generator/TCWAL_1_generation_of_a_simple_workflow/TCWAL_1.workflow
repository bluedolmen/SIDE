<?xml version="1.0" encoding="UTF-8"?>
<workflow:Process xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:workflow="http://www.kerblue.org/workflow/1.0" name="TCWAL_1">
  <packageSet xsi:type="workflow:Process" name="bluexml">
    <swimlane name="admin" manage="//@packageSet.0/@tasknode.0" actorid="admin"/>
    <startstate name="Statrt1" initiator="//@packageSet.0/@swimlane.0">
      <transition name="Transition1" to="//@packageSet.0/@tasknode.0"/>
    </startstate>
    <endstate name="EndState1"/>
    <tasknode name="TaskNode1" swimlane="//@packageSet.0/@swimlane.0">
      <transition name="Transition2" parentTaskNode="//@packageSet.0/@tasknode.0" to="//@packageSet.0/@endstate.0"/>
    </tasknode>
  </packageSet>
</workflow:Process>
