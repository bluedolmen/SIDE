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

draw2d.SVGConnection=function() {
  draw2d.Connection.call(this);
  this.setSourceAnchor(new draw2d.ChopboxConnectionAnchor());
  this.setTargetAnchor(new draw2d.ChopboxConnectionAnchor());
  this.setPaintDecorator(false);
}

draw2d.SVGConnection.prototype = new draw2d.Connection;
draw2d.SVGConnection.prototype.type="SVGConnection";

draw2d.SVGConnection.prototype.paint=function()
{
  if (! this.SVGElement) {
    this.SVGElement = new draw2d.SVGFigureConnection(this.getStartPoint(),this.getEndPoint());
    this.SVGElement.setColor(this.getColor());
    this.html.appendChild(this.SVGElement.html);
    this.SVGElement.paintDecorator = this.paintDecorator;
  }
  this.SVGElement.locate(this.getStartPoint(),this.getEndPoint());
  //this.SVGElement.setZOrder(99);
  this.SVGElement.setLineWidth(this.stroke);
  //this.setZOrder(99);
}

draw2d.SVGConnection.prototype.setPaintDecorator= function(/*:bool*/ value) {
  this.paintDecorator = value;
  if (this.SVGElement)
    this.SVGElement.paintDecorator = value;
}

draw2d.SVGConnection.prototype.containsPoint= function(/*:int*/ px, /*:int*/ py)
{
  return this.SVGElement.containsPoint(this.sourceAnchor.getLocation(this.targetAnchor.getReferencePoint()),
      this.targetAnchor.getLocation(this.sourceAnchor.getReferencePoint()),px,py);
}

draw2d.SVGConnection.prototype.onOtherFigureMoved=function(/*:draw2d.Figure*/ figure) {
  draw2d.Connection.prototype.onOtherFigureMoved.call(this,figure);
  this.SVGElement.locate(this.getStartPoint(),this.getEndPoint());
}
