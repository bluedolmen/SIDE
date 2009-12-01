/***
   RWM : Requirements Web Modeler
   Copyright (C) 2008 (BlueXML / IRCCyN)

   This program is free software; you can redistribute it and/or
   modify it under the terms of the GNU General Public License
   as published by the Free Software Foundation; either version 2
   of the License, or (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program; if not, write to the Free Software
   Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
***/

draw2d.XMIImporter=function(/*Modeler:*/ modeler, /*String:*/ nameFile)
{
  this.nameFile = nameFile;
  this.modeler = modeler;
}

/** @private **/
draw2d.XMIImporter.prototype.type="XMIImporter";

draw2d.XMIImporter.prototype.executeImport = function() {
	this.lastGoal = new Array();
	this.loadXML();
	if (this.docXml != null) {
		this.readXML();
	} 
}

draw2d.XMIImporter.prototype.loadXML = function() {
	
	var loader = new XMLHttpRequest();
    loader.open("GET", this.nameFile, false);
    loader.send(null);
	this.docXml = loader.responseXML;
}

draw2d.XMIImporter.prototype.readXML = function() {
	if (this.docXml.childNodes.length == 1) {
		var root = this.docXml.childNodes[0];
		for (var i = 0; i < root.childNodes.length; i++)
	      this.readXMLElement(root.childNodes[i]);
	}
	this.computeAssociations();
}

draw2d.XMIImporter.prototype.readXMLElement = function(/*Node:*/element) {
	//Create the correct figure
	var figure = null;
	if (element.nodeName == "sub")
	  figure = this.createOrganization(element);
	else if (element.nodeName == "goals")
	  figure = this.createGoal(element);
	else if (element.nodeName == "subgoals")
	  figure = this.createGoal(element);
	else if (element.nodeName == "members")
	  figure = this.createAgent(element);
	else if (element.nodeName == "entities")
	  figure = this.createEntity(element);
	else if (element.nodeName == "attributes")
	  figure = this.createAttribute(element);
	else if (element.nodeName == "relations")
	  figure = this.createRelationShip(element);
	else if (element.nodeName == "ends")
	  figure = this.createRelationShipEnd(element);
	else if (element.nodeName == "privilegeGroup")
	  figure = this.createPrivilegeConnection(element);
	else if (element.nodeName == "privileges")
	  figure = this.createPrivilege(element);
	
	if (figure != null) {
  	  // Configure id
	  figure.id = element.getAttribute("xmi:id");
	  figure.html.id = element.getAttribute("xmi:id");
	  
	  //Documentation
	  if (element.hasAttribute("documentation"))
	    figure.setProperty('documentation',element.getAttribute("documentation"));
	  
	  //Save reference
	  if (this.allFigures == null)
        this.allFigures = new Hash();
      this.allFigures.set(figure.id, figure);
	}
	  
	if (element.nodeName != "#text") {
		for (var i = 0; i < element.childNodes.length; i++) {
		  //Read child
		  var child = this.readXMLElement(element.childNodes[i]);
		  
		  //Add child to parent if it is possible
		  if (child != null && figure != null && (figure instanceof draw2d.CompartmentFigure))
		  	figure.addChild(child);
		}
		//Remove last goal
		if ((element.nodeName == "subgoals" || element.nodeName == "goals") && this.lastGoal != null && this.lastGoal.size() > 0)
			this.lastGoal.pop();
	}
	//Return the new figure created
	return figure;
}

draw2d.XMIImporter.prototype.computeAssociations = function() {
  if (this.responsibility)
    this.computeResponsibility();
  if (this.decompositionGoal)
    this.computeDecompositionGoal();
  if (this.allRelationShips)
    this.computeRelationShip();
  if (this.allPrivilegeConnections)
    this.computePrivilegeConnection();
}

draw2d.XMIImporter.prototype.computeResponsibility = function() {
  while (this.responsibility.length > 0) {
  	var newAsso = this.responsibility.pop();
  	var agent = newAsso.agent;
  	while (newAsso.goalsId.length > 0) {
  	  var goal = this.allFigures.get(newAsso.goalsId.pop());
  	  if (agent != null && goal != null) {
  	    var c = new draw2d.ResponsibilityConnection();
  	    c.setSource(agent.getPort("responsibility"));
        c.setTarget(goal.getPort("responsibility"));
        this.modeler.addFigure(c);
  	  }
  	}
  }
}

draw2d.XMIImporter.prototype.computeDecompositionGoal = function() {
  while (this.decompositionGoal.length > 0) {
  	var newAsso = this.decompositionGoal.pop();
  	var parentGoal = newAsso.parentGoal;
  	while (newAsso.childGoals.length > 0) {
  	  var childGoal = this.allFigures.get(newAsso.childGoals.pop());
  	  if (parentGoal != null && childGoal != null) {
  	    var c = new draw2d.GoalConnection();
  		c.setSource(parentGoal.getPort("goalDecompositionPort"));
        c.setTarget(childGoal.getPort("goalCompositionPort"));
        this.modeler.addFigure(c);
  	  }
  	}
  }
}

draw2d.XMIImporter.prototype.computeRelationShip = function() {
  while (this.allRelationShips.length > 0) {
  	var relation = this.allRelationShips.pop();
  	
  	if (relation.ends) {
      var source;
      if (relation.ends[0].targetId)
      	relation.ends[0].target = this.allFigures.get(relation.ends[0].targetId)
      source = relation.ends[0].target;
      var target;
      if (relation.ends[1].targetId)
      	relation.ends[1].target = this.allFigures.get(relation.ends[1].targetId)
      target = relation.ends[1].target;
       
  	  relation.setSource(source.getPort("relationship"));
  	  relation.setTarget(target.getPort("relationship"));
  	  this.modeler.addFigure(relation);
  	}
  }
}

draw2d.XMIImporter.prototype.computePrivilegeConnection = function() {
  while (this.allPrivilegeConnections.length > 0) {
  	var relation = this.allPrivilegeConnections.pop();
  	
  	/*Compute target object of each privilege*/
  	var pset = relation.privileges;
  	if (pset)
  	  for (var i=0; i<pset.length; ++i) {
  	  	var p = pset[i];
  	  	if (p.objectId)
  	  	  p.object = this.allFigures.get(p.objectId);
  	  }
  	
  	if (relation.entryPointId && relation.goalId) {
      var source;
      if (relation.entryPointId)
      	source = this.allFigures.get(relation.entryPointId);
      var target;
      if (relation.goalId)
      	target = this.allFigures.get(relation.goalId)
       
  	  relation.setSource(source.getPort("privilege"));
  	  relation.setTarget(target.getPort("privilege"));
  	  this.modeler.addFigure(relation);
  	}
  }
}

draw2d.XMIImporter.prototype.createOrganization = function(/*Node:*/element) {
  var org = new draw2d.OrganizationFigure();
  if (element.hasAttribute("name"))
    org.setName(element.getAttribute("name"));
  
  var w=0, h=0;
  if (element.hasAttribute("width"))
    w = parseInt(element.getAttribute("width"));
  if (element.hasAttribute("height"))
    h = parseInt(element.getAttribute("height"));
  if (w != 0 && h != 0)
  	org.setDimension(w,h);
  
  var x = 0,y = 0;
  if (element.hasAttribute("x"))
    x = parseInt(element.getAttribute("x"));
  if (element.hasAttribute("y"))
    y = parseInt(element.getAttribute("y"));
  
  this.modeler.addFigure(org,x,y);
  
  return org;
}

draw2d.XMIImporter.prototype.createGoal = function(/*Node:*/element) {
  var goal = new draw2d.GoalFigure();
  if (element.hasAttribute("name"))
    goal.setGoalValue(element.getAttribute("name"));
  
  var w=0, h=0;
  if (element.hasAttribute("width"))
    w = parseInt(element.getAttribute("width"));
  if (element.hasAttribute("height"))
    h = parseInt(element.getAttribute("height"));
  if (w != 0 && h != 0)
  	goal.setDimension(w,h);
  	
  if (element.hasAttribute("priority")) {
  	goal.setPriority(element.getAttribute("priority"));
  }
  
  var x = 0,y = 0;
  if (element.hasAttribute("x"))
    x = parseInt(element.getAttribute("x"));
  if (element.hasAttribute("y"))
    y = parseInt(element.getAttribute("y"));
  
  this.modeler.addFigure(goal,x,y);
  
  if (element.tagName == "subgoals") {
  	var last = this.lastGoal.pop();
  	var newAsso = new Object();
  	newAsso.parentGoal = last;
  	newAsso.childGoals = new Array();
    newAsso.childGoals.push(element.getAttribute("xmi:id"));
    if (this.decompositionGoal == null)
      this.decompositionGoal = new Array();
    this.decompositionGoal.push(newAsso);
    this.lastGoal.push(last);
  }
  this.lastGoal.push(goal);
  return goal;
}

draw2d.XMIImporter.prototype.createAgent = function(/*Node:*/element) {
  var agent = new draw2d.AgentFigure();
  agent.id = element.getAttribute("xmi:id");
  if (element.hasAttribute("name"))
    agent.setRole(element.getAttribute("name"));
  
  var w=0, h=0;
  if (element.hasAttribute("width"))
    w = parseInt(element.getAttribute("width"));
  if (element.hasAttribute("height"))
    h = parseInt(element.getAttribute("height"));
  if (w != 0 && h != 0)
  	agent.setDimension(w,h);
  
  var x = 0,y = 0;
  if (element.hasAttribute("x"))
    x = parseInt(element.getAttribute("x"));
  if (element.hasAttribute("y"))
    y = parseInt(element.getAttribute("y"));
  
  this.modeler.addFigure(agent,x,y);
  
  if (element.hasAttribute("isResponsible")) {
  	var newAsso = new Object();
  	newAsso.agent = agent;
  	newAsso.goalsId = new Array();
    var responsible = element.getAttribute("isResponsible").split(" ");
    for (var i = 0; i < responsible.length ; ++i)
      newAsso.goalsId.push(responsible[i].substring(1,responsible[i].length));

    if (this.responsibility == null)
      this.responsibility = new Array();
    this.responsibility.push(newAsso);
  }
  
  return agent;
}

draw2d.XMIImporter.prototype.createEntity = function(/*Node:*/element) {
  var entity = new draw2d.EntityFigure();
  entity.id = element.getAttribute("xmi:id");
  if (element.hasAttribute("name"))
    entity.setEntityName(element.getAttribute("name"));
  
  var w=0, h=0;
  if (element.hasAttribute("width"))
    w = parseInt(element.getAttribute("width"));
  if (element.hasAttribute("height"))
    h = parseInt(element.getAttribute("height"));
  if (w != 0 && h != 0)
  	entity.setDimension(w,h);
  
  var x = 0,y = 0;
  if (element.hasAttribute("x"))
    x = parseInt(element.getAttribute("x"));
  if (element.hasAttribute("y"))
    y = parseInt(element.getAttribute("y"));
  
  this.modeler.addFigure(entity,x,y);
  this.lastEntity = entity;
  
  return entity;
}

draw2d.XMIImporter.prototype.createAttribute = function(/*Node:*/element) {
  var attribute = new draw2d.AttributeFigure();

  if (element.hasAttribute("name"))
    attribute.setName(element.getAttribute("name"));
  if (element.hasAttribute("type"))
    if (element.getAttribute("type") == 'TextualValue')
      attribute.setAttributeType('text');
    else if (element.getAttribute("type") == 'NumericalValue')
      attribute.setAttributeType('number');
    else if (element.getAttribute("type") == 'TemporalValue')
      attribute.setAttributeType('DateTime');
    else
      attribute.setAttributeType('other');

  if (this.lastEntity)
    this.lastEntity.addAttribute(attribute);  

  return attribute;
}

draw2d.XMIImporter.prototype.createRelationShip = function(/*Node:*/element) {
  var relation = new draw2d.RelationShipConnection();
  if (element.hasAttribute("name"))
    relation.setAssociationName(element.getAttribute("name"));
  this.lastRelationShip = relation;
  
  if (!this.allRelationShips)
    this.allRelationShips = new Array();
  this.allRelationShips.push(relation);
  
  return null;
}

draw2d.XMIImporter.prototype.createRelationShipEnd = function(/*Node:*/element) {
  var end = new draw2d.EndFigure();
  if (element.hasAttribute("minCardinality"))
    end.setMin(element.getAttribute("minCardinality"));
  if (element.hasAttribute("maxCardinality"))
    end.setMax(element.getAttribute("maxCardinality"));
  if (element.hasAttribute("object")) {
  	var id = element.getAttribute("object");
  	if (id.substring(0,1) == '#')
  	  id = id.substring(1,id.length);
    if (id) {
      end.targetId = id;
    }
  }
  
  if (this.lastRelationShip)
    if (!this.lastRelationShip.ends[0].targetId)
      this.lastRelationShip.ends[0] = end;
    else if (!this.lastRelationShip.ends[1].targetId)
      this.lastRelationShip.ends[1] = end;
  
  return null;
}

draw2d.XMIImporter.prototype.createPrivilegeConnection = function(/*Node:*/element) {
  var c = new draw2d.PrivilegeConnection();
  if (element.hasAttribute("entryPoint")) {
  	var id = element.getAttribute("entryPoint");
  	if (id.substring(0,1) == '#')
  	  id = id.substring(1,id.length);
    if (id) {
      c.entryPointId = id;
    }
  }
  
  if (this.lastGoal && this.lastGoal.size() > 0) {
  	var goal = this.lastGoal.pop();
    c.goalId = goal.id;
    this.lastGoal.push(goal);
  }
  
  this.lastPrivilegeConnection = c;
  
  if (!this.allPrivilegeConnections)
    this.allPrivilegeConnections = new Array();
  this.allPrivilegeConnections.push(c);
  
  return null;
}

draw2d.XMIImporter.prototype.createPrivilege = function(/*Node:*/element) {
  if (element.hasAttribute("element")) {
  	var id = element.getAttribute("element");
  	if (id.substring(0,1) == '#')
  	  id = id.substring(1,id.length);
  }
  
  if (this.lastPrivilegeConnection) {
  	var p = this.lastPrivilegeConnection.getPrivilege(id);
  	if (!p) {
  	  p = new draw2d.Privilege(null, false, false, false, false);
      p.objectId = id;
      this.lastPrivilegeConnection.addPrivilege(p);
  	}
  	
  	if (element.hasAttribute("category")) {
  	  var cat = element.getAttribute("category");
  	  if (cat == 'create')
  	    p.create = true;
  	  else if (cat == 'read')
  	    p.read = true;
  	  else if (cat == 'update')
  	    p.update = true;
  	  else if (cat == 'delete')
  	    p.del = true;				
  	} else
  	  p.create = true;
  }
  
  return null;
}
