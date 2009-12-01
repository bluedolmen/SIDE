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


draw2d.RelationShipPort=function()
{
  var img = new draw2d.ImageFigure("./figures/connection/RelationShipPort.png");
  draw2d.Port.call(this,img);
  this.setBackgroundColor(null);
  this.setName("relationship");
  img.setDimension(20,20);
}

draw2d.RelationShipPort.prototype = new draw2d.Port;
/** @private **/
draw2d.RelationShipPort.prototype.type="RelationShipPort";

draw2d.RelationShipPort.prototype.onDragEnter = function(/*:draw2d.Port*/ port) {
	var parent = draw2d.Port.prototype.getParent.call(this);
	if (parent instanceof draw2d.EntityFigure && port.getParent() instanceof draw2d.EntityFigure)
		if (port instanceof draw2d.RelationShipPort)
			draw2d.Port.prototype.onDragEnter.call(this,port);
}

draw2d.RelationShipPort.prototype.onDrop = function(/*:draw2d.Port*/ port) {
	var parent = draw2d.Port.prototype.getParent.call(this);
	if (parent instanceof draw2d.EntityFigure && port.getParent() instanceof draw2d.EntityFigure)
		if (port instanceof draw2d.RelationShipPort) {
			var command = new draw2d.CommandConnect(this.parentNode.workflow,port,this);
			command.setConnection(new draw2d.RelationShipConnection());
    		this.parentNode.workflow.getCommandStack().execute(command);
	 	}
}
