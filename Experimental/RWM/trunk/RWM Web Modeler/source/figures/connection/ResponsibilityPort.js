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


draw2d.ResponsibilityPort=function(/*:draw2d.Figure*/ uiRepresentation)
{
  draw2d.Port.call(this, uiRepresentation);
  this.setDimension(20,20);
}

draw2d.ResponsibilityPort.prototype = new draw2d.Port;
/** @private **/
draw2d.ResponsibilityPort.prototype.type="ResponsibilityPort";

draw2d.ResponsibilityPort.prototype.paint=function() {
	this.currentUIRepresentation.paint();
	this.currentUIRepresentation.html.style.backgroundColor=null;
}

draw2d.ResponsibilityPort.prototype.onDragEnter = function(/*:draw2d.Port*/ port) {
	var parent = draw2d.Port.prototype.getParent.call(this);
	if ((parent instanceof draw2d.AgentFigure && port.getParent() instanceof draw2d.GoalFigure) ||
		(parent instanceof draw2d.GoalFigure && port.getParent() instanceof draw2d.AgentFigure))
		if (port instanceof draw2d.ResponsibilityPort)
			draw2d.Port.prototype.onDragEnter.call(this,port);
}

draw2d.ResponsibilityPort.prototype.onDrop = function(/*:draw2d.Port*/ port) {
	var parent = draw2d.Port.prototype.getParent.call(this);
	if ((parent instanceof draw2d.AgentFigure && port.getParent() instanceof draw2d.GoalFigure) ||
		(parent instanceof draw2d.GoalFigure && port.getParent() instanceof draw2d.AgentFigure))
		if (port instanceof draw2d.ResponsibilityPort) {
			var command = new draw2d.CommandConnect(this.parentNode.workflow,port,this);
			command.setConnection(new draw2d.ResponsibilityConnection());
    		this.parentNode.workflow.getCommandStack().execute(command);
	 	}
}
