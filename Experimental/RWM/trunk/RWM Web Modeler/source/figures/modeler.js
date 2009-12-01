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

draw2d.Modeler=function(/*:String*/id)
{
  draw2d.Workflow.call(this, id);
  this.listOfAgents = new Array();
  this.listOfEntities = new Array();
  this.listOfGoals = new Array();
  this.view = true;
  this.p_showConnectors = true;
}

draw2d.Modeler.prototype = new draw2d.Workflow;
draw2d.Modeler.prototype.type="Modeler";

draw2d.Modeler.prototype.showConnectionLine=function(/*:int*/ x1  ,/*:int*/ y1 ,/*:int*/ x2,/*:int*/ y2 )
{
  draw2d.Workflow.prototype.showConnectionLine.call(this,x1,y1,x2,y2);
  this.connectionLine.setZOrder(10000);
}

draw2d.Modeler.prototype.clean=function()
{
  this.listOfAgents.clear();
  this.listOfEntities.clear();
  this.listOfGoals.clear();
}

draw2d.Modeler.prototype.addFigure=function(/*draw2d.Figure*/ figure , xPos, yPos) {
  draw2d.Workflow.prototype.addFigure.call(this, figure, xPos, yPos);
  var fig = this.getBestCompartmentFigure(xPos,yPos,figure);
  if (fig)
    fig.addChild(figure);
  addFigureToOverview(figure);
  
  if (figure instanceof draw2d.AgentFigure) {
  	this.refreshListOfAgents(figure);
  }
  else if (figure instanceof draw2d.EntityFigure) {
  	this.refreshListOfEntities(figure);
  }
  else if (figure instanceof draw2d.GoalConnection) {
  	var source;
  	var target;
  	
  	var fig1 = figure.getSource().getParent(); 
  	var fig2 = figure.getTarget().getParent(); 
  	
  	if (fig1 != null && fig2 != null) {
  		if (figure.getSource().getName() == 'goalDecompositionPort') {
  			source = fig1;
  			target = fig2;
  		} else {
  			source = fig2;
  			target = fig1;
  		}
  		source.childGoals.remove(target);
  		source.childGoals.push(target);
  		target.parentGoals.remove(source);
  		target.parentGoals.push(source);
  		
  		//Try to remove the target because it's not a main goal
  		this.listOfGoals.remove(target);
  		//Try to remove the source by default
  		this.listOfGoals.remove(source);
  		if (source.parentGoals.size() == 0) {
  			//Add source goal if it's a main goal 
  			this.listOfGoals.push(source);
  		}
  	}
  	if (this.view)
  		this.refreshListOfGoals();
  }
  else if (figure instanceof draw2d.GoalFigure) {
  	//Try to remove the goals
  	this.listOfGoals.remove(figure);
  	if (figure.parentGoals.size() == 0) {
  		//Add goal if it's a main goal 
  		this.listOfGoals.push(figure);
  	}
  	if (this.view)
  		this.refreshListOfGoals();
  }
  
  //Setting the view of connectors
  this.showConnectorsOfFigure(this.p_showConnectors,figure);
}

draw2d.Modeler.prototype.removeFigure = function(/*:draw2d.Figure*/ figure) {
  removeFigureToOverview(figure);
  
  if (figure instanceof draw2d.AgentFigure) {
    this.listOfAgents.remove(figure);
    this.refreshVisualListOfAgents();
  } else if (figure instanceof draw2d.EntityFigure) {
    this.listOfEntities.remove(figure);
    this.refreshVisualListOfEntities();
  }
  
  if (figure instanceof draw2d.GoalConnection) {
  	var source;
  	var target;
  	
  	var fig1 = figure.getSource().getParent(); 
  	var fig2 = figure.getTarget().getParent(); 
  	
  	if (fig1 != null && fig2 != null) {
  		if (figure.getSource().getName() == 'goalDecompositionPort') {
  			source = fig1;
  			target = fig2;
  		} else {
  			source = fig2;
  			target = fig1;
  		}
  		source.childGoals.remove(target);
  		target.parentGoals.remove(source);
  		
  		//Try to remove the target because it's not a main goal
  		this.listOfGoals.remove(target);
  		if (target.parentGoals.size() == 0) {
  			//Add target goal if it's a main goal 
  			this.listOfGoals.push(target);
  		}
  		//Try to remove the source by default
  		this.listOfGoals.remove(source);
  		if (source.parentGoals.size() == 0) {
  			//Add source goal if it's a main goal 
  			this.listOfGoals.push(source);
  		}
  	}
  	this.refreshListOfGoals();
  }
  
  draw2d.Workflow.prototype.removeFigure.call(this, figure);
}

draw2d.Modeler.prototype.onMouseMove=function(/*:int*/ x ,/*:int*/ y) {
  if(this.dragging==true && this.panning==true) {
  	refreshOverviewVisiblePart();
  }
  draw2d.Workflow.prototype.onMouseMove.call(this,x,y);
}

draw2d.Modeler.prototype.showConnectors=function(/*:visible*/ visible) {
  this.p_showConnectors = visible; 
  for (var i = 0; i < this.figures.getSize(); ++i) {
  	var fig = this.figures.get(i);
	this.showConnectorsOfFigure(visible,fig);
  }
}

draw2d.Modeler.prototype.showConnectorsOfFigure=function(/*:visible*/ visible, /*:draw2d.Figure*/ fig) {
  	if (fig.getPorts) {
  	  var ports = fig.getPorts();
  	  for (var j = 0; j < this.figures.getSize(); ++j) {
  	    var port = ports.get(j);
  	    if (port && port.currentUIRepresentation) {
  	      if (visible)
  	        port.currentUIRepresentation.html.style.visibility = "";
  	      else
  	        port.currentUIRepresentation.html.style.visibility = "hidden";
    	}
      }
  	}	
}


draw2d.Modeler.prototype.removeDialogFromFigure = function(/*:draw2d.Figure*/ figure)
{
    this.figures.remove(figure);
    this.lines.remove(figure);
    this.dialogs.remove(figure);

    if(this.currentSelection == figure)
      this.setCurrentSelection(null);

    this.setDocumentDirty();
}

draw2d.Modeler.prototype.refreshListOfAgents=function(/*:draw2d.AgentFigure*/ agent) {
	this.listOfAgents.remove(agent);
	this.listOfAgents.push(agent);
	if (this.view)
		this.refreshVisualListOfAgents();
}

draw2d.Modeler.prototype.refreshVisualListOfAgents=function() {
	var div = document.getElementById('list_of_agents');
	while (div.childNodes.length > 0) {
	  div.removeChild(div.firstChild);	
	}
	
	var list = document.createElement("ul");
	list.className = "mktree";
	div.appendChild(list);
	for (i = 0; i < this.listOfAgents.length; ++i) {
		var agent = this.listOfAgents[i];
		var li = document.createElement("li");
		li.style.fontSize = "10pt";
		var a  = document.createElement("a");
        a.href = "javascript:draw2d.Modeler.scrollTo('"+agent.id+"')";
        a.innerHTML = agent.getRole();
        agent.elementList_name = a;
        li.appendChild(a);
		var ul = document.createElement("ul");
		li.appendChild(ul);
		var doc = document.createElement("li");
		doc.style.fontSize = "9pt";
		ul.appendChild(doc);
		var italic = document.createElement("i");
		italic.appendChild(document.createTextNode(agent.getDocumentation()));
		agent.elementList_documentation = italic;
		//italic.innerHTML = this.listOfAgents[i].getDocumentation();
		doc.appendChild(italic);
		list.appendChild(li);
	}
	processList(list);
}

draw2d.Modeler.prototype.refreshListOfEntities=function(/*:draw2d.EntityFigure*/ agent) {
	this.listOfEntities.remove(agent);
	this.listOfEntities.push(agent);
	if (this.view)
		this.refreshVisualListOfEntities();
}

draw2d.Modeler.prototype.refreshVisualListOfEntities=function() {
	var div = document.getElementById('list_of_entities');
	while (div.childNodes.length > 0) {
	  div.removeChild(div.firstChild);	
	}
	
	var list = document.createElement("ul");
	list.className = "mktree";
	div.appendChild(list);
	for (i = 0; i < this.listOfEntities.length; ++i) {
		var entity = this.listOfEntities[i];
		var li = document.createElement("li");
		li.style.fontSize = "10pt";
		var a  = document.createElement("a");
        a.href = "javascript:draw2d.Modeler.scrollTo('"+entity.id+"')";
        a.innerHTML = entity.getEntityName();
        entity.elementList_name = a;
        li.appendChild(a);
		var ul = document.createElement("ul");
		li.appendChild(ul);
		var doc = document.createElement("li");
		doc.style.fontSize = "9pt";
		ul.appendChild(doc);
		var italic = document.createElement("i");
		italic.appendChild(document.createTextNode(entity.getDocumentation()));
		entity.elementList_documentation = italic;
		//italic.innerHTML = this.listOfAgents[i].getDocumentation();
		doc.appendChild(italic);
		list.appendChild(li);
	}
	processList(list);
}

draw2d.Modeler.prototype.refreshListOfGoals=function() {
	var div = document.getElementById('list_of_goals');
	
	while (div.childNodes.length > 0) {
	  div.removeChild(div.firstChild);	
	}
	var list = document.createElement("ul");
	list.className = "mktree";
	div.appendChild(list);	

	for (var i = 0; i < this.listOfGoals.length; ++i) {
		var goal = this.listOfGoals[i];
		var li = this.createTree(goal);
		list.appendChild(li);
	}
	processList(list);
}

draw2d.Modeler.prototype.createTree=function(/*:draw2d.GoalFigure*/ goal) {
	var li = document.createElement("li");
	li.style.fontSize = "10pt";
	var a  = document.createElement("a");
    a.href = "javascript:draw2d.Modeler.scrollTo('"+goal.id+"')";
    a.innerHTML = goal.getGoalValue();
    goal.elementList.push(a);
    li.appendChild(a);
    
    if (goal.childGoals.size() > 0) {
		var ul = document.createElement("ul");
		li.appendChild(ul);
		
		for (var i = 0; i < goal.childGoals.size(); ++i) {
			var el = this.createTree(goal.childGoals[i]);
			ul.appendChild(el);
		}
    }
    return li;
}


draw2d.Modeler.scrollTo= function(/*: String */ id)
{
  var figure =workflow.getFigure(id);
  workflow.scrollTo(figure.getX()-draw2d.Modeler.screenWidth()/2,figure.getY()-draw2d.Modeler.screenHeight()/2);
}


draw2d.Modeler.screenWidth=function ()
{
  var myWidth = 0;
  if( typeof( window.innerWidth ) == 'number' ) {
    //Non-IE
    myWidth = window.innerWidth;
  } else if( document.documentElement && ( document.documentElement.clientWidth || document.documentElement.clientHeight ) ) {
    //IE 6+ in 'standards compliant mode'
    myWidth = document.documentElement.clientWidth;
  } else if( document.body && ( document.body.clientWidth || document.body.clientHeight ) ) {
    //IE 4 compatible
    myWidth = document.body.clientWidth;
  }
  return myWidth;
}

draw2d.Modeler.screenHeight=function ()
{
  var myHeight = 0;
  if( typeof( window.innerWidth ) == 'number' ) {
    //Non-IE
    myHeight = window.innerHeight;
  } else if( document.documentElement && ( document.documentElement.clientWidth || document.documentElement.clientHeight ) ) {
    //IE 6+ in 'standards compliant mode'
    myHeight = document.documentElement.clientHeight;
  } else if( document.body && ( document.body.clientWidth || document.body.clientHeight ) ) {
    //IE 4 compatible
    myHeight = document.body.clientHeight;
  }
  return myHeight;
}

draw2d.Modeler.prototype.refreshOverviewTab=function(/*:boolean*/ _view) {
	if (!(_view)) {
		this.view = _view;
	} else if (!(this.view)) {
		this.view = _view;
		this.refreshVisualListOfAgents();
		this.refreshVisualListOfEntities();
		this.refreshListOfGoals();
	}
}