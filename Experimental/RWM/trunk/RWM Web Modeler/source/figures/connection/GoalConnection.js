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

draw2d.GoalConnection=function()
{
  draw2d.SVGConnection.call(this);
  this.sourcePort = null;
  this.targetPort = null;
  this.lineSegments = new Array();
  this.setColor(new  draw2d.Color(75,189,14));
  this.setLineWidth(2);
  this.setPaintDecorator(true);
}

draw2d.GoalConnection.prototype = new draw2d.SVGConnection();

draw2d.GoalConnection.prototype.setSource=function(/*Port*/ p) {
	var max = draw2d.Connection.prototype.getZOrder.call(this);
	var source = "0";
	if (p.getParent() != null)
	  source = p.getParent().getZOrder();
	
	if (parseInt(source) > parseInt(max))
	  max = source;
	
	draw2d.SVGConnection.prototype.setSource.call(this, p);
	draw2d.SVGConnection.prototype.setZOrder.call(this, max);
}

draw2d.GoalConnection.prototype.setTarget=function(/*Port*/ p) {
	var max = draw2d.Connection.prototype.getZOrder.call(this);
	var target = "0";
	if (p.getParent() != null)
	  target = p.getParent().getZOrder();
	
	if (parseInt(target) > parseInt(max))
	  max = target;
	
	draw2d.SVGConnection.prototype.setTarget.call(this, p);
	draw2d.SVGConnection.prototype.setZOrder.call(this, max);
}

draw2d.GoalConnection.prototype.paint=function() {
	if (this.html.style.visibility != "hidden")
	  draw2d.SVGConnection.prototype.paint.call(this);
}

/**
 * Return the recalculated position of the start point if we have set an anchor.
 * 
 * @type draw2d.Point
 **/
 draw2d.GoalConnection.prototype.getStartPoint= function()
 {
  if(this.isMoving==false) {
     if (this.sourcePort.getName() == 'goalDecompositionPort')
       return this.sourceAnchor.getLocation(this.targetAnchor.getReferencePoint());
     else
       return this.targetAnchor.getLocation(this.sourceAnchor.getReferencePoint());     
  } else
     return draw2d.Line.prototype.getStartPoint.call(this);
 }


/**
 * Return the recalculated position of the start point if we have set an anchor.
 *
 * @type draw2d.Point
 **/
 draw2d.GoalConnection.prototype.getEndPoint= function()
 {
  if(this.isMoving==false) {
     if (this.sourcePort.getName() == 'goalDecompositionPort')
       return this.targetAnchor.getLocation(this.sourceAnchor.getReferencePoint());     
     else
       return this.sourceAnchor.getLocation(this.targetAnchor.getReferencePoint());
  } else
     return draw2d.Line.prototype.getEndPoint.call(this);
 }
