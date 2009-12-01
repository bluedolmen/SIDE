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

draw2d.GoalFigure=function()
{
  draw2d.Node.call(this);
  this.setColor(null);
  this.setResizeable(false);
  this.parentGoals = new Array();
  this.childGoals = new Array();
  this.elementList = new Array();
}

draw2d.GoalFigure.prototype = new draw2d.Node;
draw2d.GoalFigure.prototype.type = "GoalFigure";

draw2d.GoalFigure.prototype.createHTMLElement=function() {
	var item = draw2d.Node.prototype.createHTMLElement.call(this);
	
	this.globalDiv = document.createElement("div");
	this.globalDiv.className = "box";
	this.globalDiv.style.position = "absolute";
		
	var topB = document.createElement("div");
	topB.className = "boxtop";
	var boxtopleft = document.createElement("div");
	boxtopleft.className = "boxtopleft";
	var middle = document.createElement("div");
	middle.className = "boxmiddle";
	boxtopleft.appendChild(middle);
	topB.appendChild(boxtopleft);
	
	var div = document.createElement("div");
	div.className = "boxcontent";
	div.style.fontSize = "11px";
	
	var table = document.createElement("table");
	table.style.textAlign = "center";
	table.style.verticalAlign = "middle";
	table.style.fontSize = "11px";
	table.style.fontFamily = "sans-serif";
	table.style.width = "100%";
	table.style.height = "100%";
	div.appendChild(table);
	
	var tr=document.createElement("tr");
	table.appendChild(tr);
	
	var td = document.createElement("td");
	this.contentDiv = document.createElement("span");
	td.appendChild(this.contentDiv);
	tr.appendChild(td);
	
	var bottom = document.createElement("div");
	bottom.className = "boxbottom";
	var boxbottomleft = document.createElement("div");
	boxbottomleft.className = "boxbottomleft";
	middle = document.createElement("div");
	middle.className = "boxmiddle";
	boxbottomleft.appendChild(middle);
	bottom.appendChild(boxbottomleft);
	
	this.globalDiv.appendChild(topB);
	this.globalDiv.appendChild(div);
	this.globalDiv.appendChild(bottom);
	item.appendChild(this.globalDiv);
	
	disableSelection(item);
	return item;
}

draw2d.GoalFigure.prototype.setDimension=function(/*:int*/ w, /*:int*/ h )
{
  draw2d.Node.prototype.setDimension.call(this,w, h);
  if (this.globalDiv != null)
  	this.globalDiv.style.width = w +"px";
  //if (this.contentDiv != null)
  	//this.contentDiv.style.height = h-30;
  if(this.responsibilityPort!=null)
    this.responsibilityPort.setPosition(0, this.height/2);
  if(this.goalCompositionPort!=null)
    this.goalCompositionPort.setPosition(this.width/2, 0);
  if(this.goalDecompositionPort!=null)
    this.goalDecompositionPort.setPosition(this.width/2, this.height+5);
  if(this.privilegePort!=null)  
    this.privilegePort.setPosition(this.width-5,this.height/2-5);
  if (this.SVGElement) {
    this.SVGElement.setAttribute("width",w);
    this.SVGElement.setAttribute("height",h);
  }
}

draw2d.GoalFigure.prototype.setWorkflow=function(/*:draw2d.Workflow*/ workflow)
{
  draw2d.Node.prototype.setWorkflow.call(this,workflow);

  if(workflow!=null && this.outputPort==null)
  {
  	var imgFigure = new draw2d.ImageFigure("./figures/connection/ResponsibilityPort.png");
  	imgFigure.setBackgroundColor(null);
  	
    this.responsibilityPort = new draw2d.ResponsibilityPort(imgFigure);
    this.responsibilityPort.setWorkflow(workflow);
    this.responsibilityPort.setName("responsibility");
    this.addPort(this.responsibilityPort,0,this.height/2);
    
    imgFigure = new draw2d.ImageFigure("./figures/connection/GoalPort.png");
  	imgFigure.setBackgroundColor(null);
  	
  	this.goalCompositionPort = new draw2d.GoalDecompositionPort(imgFigure);
    this.goalCompositionPort.setWorkflow(workflow);
    this.goalCompositionPort.setName("goalCompositionPort");
    this.addPort(this.goalCompositionPort,this.width/2,0);
    
    imgFigure = new draw2d.ImageFigure("./figures/connection/GoalPort.png");
  	imgFigure.setBackgroundColor(null);
    
    this.goalDecompositionPort = new draw2d.GoalDecompositionPort(imgFigure);
    this.goalDecompositionPort.setWorkflow(workflow);
    this.goalDecompositionPort.setName("goalDecompositionPort");
    this.addPort(this.goalDecompositionPort,this.width/2,this.height+5);
    
    this.privilegePort = new draw2d.PrivilegePort();
    this.privilegePort.setWorkflow(workflow);
    this.privilegePort.setName("privilege");
    this.addPort(this.privilegePort,this.width-5,this.height/2-5);
  }
}

draw2d.GoalFigure.prototype.setGoalValue=function(/*:String*/ value) {
	this.headerValue = value;
	var array = value.split("\n");
	
	while (this.contentDiv.childNodes[0])
      this.contentDiv.removeChild(this.contentDiv.childNodes[0]);
  
	for (var i = 0; i < array.length; ++i) {
	  if (i > 0)
	    this.contentDiv.appendChild(document.createElement("br"));
	  this.contentDiv.appendChild(document.createTextNode(array[i]))
	}
	var w = this.getWidth();
	this.globalDiv.style.width="5000px";
	this.setDimension(this.contentDiv.offsetWidth + 40, 30 + 12*array.length)
	
	for (var i = 0; i < this.elementList.length; ++i) {
		this.elementList[i].innerHTML = value;
	}
}

draw2d.GoalFigure.prototype.setPriority=function(/*:String*/ value) {
	this.priority = value;
}

draw2d.GoalFigure.prototype.getMinWidth=function() {
	if (this.headerValue != null) {
	  var array = this.headerValue.split("\n");
	  return 30 + 12*array.length;
	} else
	  return draw2d.Node.prototype.getMinWidth.call(this);
}

draw2d.GoalFigure.prototype.getGoalValue=function() {
	if (this.headerValue != null)
	  return this.headerValue;
	else
	  return "";
}

draw2d.GoalFigure.prototype.getPriority=function() {
	if (this.priority != null)
	  return this.priority;
	else
	  return "Normal";
}

draw2d.GoalFigure.prototype.getDocumentation=function() {
	if (this.documentation != null)
	  return this.documentation.replace(/&nbsp;/g,"").replace(/\n/g,"");
	else
	  return "";
}

draw2d.GoalFigure.prototype.toXMI=function() {
	var port = this.getPort("goalCompositionPort");
	var findParentGoal = false;
	if (port != null && port.getConnections) {
      var cSet = port.getConnections();
      findParentGoal = cSet.getSize() > 0;
	}
	
	if (findParentGoal)
	  //No XMI return
	  return "";
	else
      return this.toXMISecure("goals");
}

draw2d.GoalFigure.prototype.toXMISecure=function(tagName) {
	port = this.getPort("responsibility");
	var responsibility = "";
	if (port != null && port.getConnections) {
      var cSet = port.getConnections();
      for (var i=0; i < cSet.getSize(); i++) {
        var c = cSet.get(i);
        if (c.getTarget().getParent() == this)
          responsibility = responsibility + "#" + c.getSource().getParent().getId() + " ";
        else
          responsibility = responsibility + "#" + c.getTarget().getParent().getId() + " ";
      }
	}
	
	var xmi = '<'+tagName+' name="'+this.getGoalValue()+'" xmi:id="'+this.getId()+'"' +
			' responsible="'+responsibility+'"' +
			' priority="'+this.getPriority().replace(/ /g,"")+'"'+
			' x="'+this.getX()+'" y="'+this.getY()+'" width="'+this.getWidth()+'" height="'+this.getHeight()+'"' +
			' documentation="'+this.getDocumentation().replace(/</g,"&lt;")+'">\n';
	
	port = this.getPort("privilege");
	if (port != null && port.getConnections) {
      var cSet = port.getConnections();
      for (var i=0; i < cSet.getSize(); i++) {
        var c = cSet.get(i);
        if (c.toInternalXMI)
          xmi += c.toInternalXMI();
      }
	}

	var port = this.getPort("goalDecompositionPort");
	if (port != null && port.getConnections) {
      var cSet = port.getConnections();
      for (var i=0; i < cSet.getSize(); i++) {
        var c = cSet.get(i);
        if (c.getTarget().getParent() == this)
          xmi += c.getSource().getParent().toXMISecure("subgoals");
        else
          xmi += c.getTarget().getParent().toXMISecure("subgoals");
      }
	}
	
	xmi += '</'+tagName+'>\n'; 		
	return xmi;
}

draw2d.GoalFigure.prototype.onDoubleClick=function()
{
  var d = new draw2d.GoalDialog(this);
  this.getWorkflow().addFigure(d);
  d.show();
}

draw2d.GoalFigure.prototype.toShortString=function()
{
  return "Goal "+this.getGoalValue();
}

draw2d.GoalFigure.prototype.setProperty=function(/*:String*/ key,/*:String*/ value)
{
  if (key == "goalValue")
    this.setGoalValue(value);
  else if (key == "priority")
    this.setPriority(value);
  else if (key == "documentation")
    this.documentation = value;
}

draw2d.GoalFigure.prototype.getProperties=function() {
	return new Array("goalValue","priority");
}

draw2d.GoalFigure.prototype.paint=function() {
  draw2d.Node.prototype.paint.call(this);
  if (this.getGoalValue() == '')
    this.setGoalValue("New goal");
}

/**
 * Set the position of the object.
 *
 * @param {int} xPos The new x coordinate of the figure
 * @param {int} yPos The new y coordinate of the figure 
 **/
draw2d.GoalFigure.prototype.setPosition=function(/*:int*/ xPos , /*:int*/ yPos )
{
  draw2d.Figure.prototype.setPosition.call(this,xPos,yPos);
  if (this.SVGElement) {
    this.SVGElement.setAttribute("x",xPos);
    this.SVGElement.setAttribute("y",yPos);
  }
}


draw2d.GoalFigure.prototype.toSVG=function() {
  var rect = document.createElementNS("http://www.w3.org/2000/svg","rect");
  rect.setAttribute("x",this.getX());
  rect.setAttribute("y",this.getY());
  rect.setAttribute("width",this.getWidth());
  rect.setAttribute("height",this.getHeight());
  rect.setAttribute("fill","rgb(169,201,236)");
  rect.setAttribute("style","stroke-width:1;stroke:rgb(0,0,0);");
  this.SVGElement = rect;
  return rect;
}
