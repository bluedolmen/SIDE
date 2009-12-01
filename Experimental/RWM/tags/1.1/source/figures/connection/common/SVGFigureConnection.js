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

draw2d.SVGFigureConnection=function(/*:draw2d.Point*/ p1, /*:draw2d.Point*/ p2)
{
  draw2d.Figure.call(this);
  this.locate(p1,p2);
  this.paintDecorator = true;
}

draw2d.SVGFigureConnection.prototype = new draw2d.SVGFigure;

draw2d.SVGFigureConnection.prototype.locate=function(/*:draw2d.Point*/ p1, /*:draw2d.Point*/ p2) {
  if(p1 && p2) {
  	this.width = Math.abs(p1.x-p2.x);
  	this.height = Math.abs(p1.y-p2.y);

  	this.startPoint = new draw2d.Point(p1.x-this.getPosition().x,p1.y-this.getPosition().y);
  	this.endPoint = new draw2d.Point(p2.x-this.getPosition().x,p2.y-this.getPosition().y);
  	
  	if (this.paintDecorator) {
  	  if (this.endPoint.x+7 > this.width)
  	    this.endPoint.x = this.width-7;
  	  if (this.endPoint.x-7 < 0)
  	    this.endPoint.x = 7;
  	  if (this.endPoint.y+7 > this.height)
  	    this.endPoint.y = this.height-7;
  	  if (this.endPoint.y-7 < 0)
  	    this.endPoint.y = 7;
  	}

  	this.coeffA = (this.startPoint.y - this.endPoint.y)/(this.startPoint.x - this.endPoint.x);
  	this.coeffB = this.startPoint.y - this.coeffA*this.startPoint.x;
    
    if (p1.x < p2.x) {
      if (p1.y < p2.y)
        this.decreasing = true;
      else
        this.decreasing = false;
    } else {
      if (p1.y < p2.y)
        this.decreasing = false;
      else
        this.decreasing = true;
    }

    this.setDimension(this.width+7, this.height+7);
    draw2d.Figure.prototype.setPosition.call(this,Math.min(p1.x,p2.x),Math.min(p1.y,p2.y));
    this.paint();
  }	
}

draw2d.SVGFigureConnection.prototype.paint=function()
{
  this.html.style.cursor = "default";
  this.context.clearRect(0, 0,this.width+10, this.height+10);
  this.context.fillStyle = "rgba(200,0,0,0.3)";
  
  if (this.getColor()) {
    var color = this.getColor();
    this.context.strokeStyle = "rgb("+color.getRed()+","+color.getGreen()+","+color.getBlue()+")";
  } else
    this.context.strokeStyle = "rgb(0,200,0)";
    
  this.context.lineWidth = this.stroke;
  
  this.context.beginPath();
  if (this.width <= 5) {
  	this.context.moveTo(1,0);
    this.context.lineTo(1,this.height);
  } else if (this.height <= 5) {
  	this.context.moveTo(0,1);
    this.context.lineTo(this.width,1);
  } else {
  	if (this.startPoint && this.endPoint) {
      this.context.moveTo(this.startPoint.x,this.startPoint.y);
      this.context.lineTo(this.endPoint.x,this.endPoint.y);
  	}
  }
  this.context.closePath();
  this.context.stroke();
  this._paintDecorator();
  this.context.fill();
}

draw2d.SVGFigureConnection.prototype._paintDecorator=function() {
  if (this.paintDecorator) {
  	if (this.getColor()) {
      var color = this.getColor();
      this.context.fillStyle = "rgb("+color.getRed()+","+color.getGreen()+","+color.getBlue()+")";
    } else
      this.context.fillStyle = "rgb(0,200,0)";
  	
  	this.context.arc(this.endPoint.x, this.endPoint.y, 6, 0, 2 * Math.PI, false);
  }
}

draw2d.SVGFigureConnection.prototype.containsPoint= function(/*:draw2d.Point*/ p1, /*:draw2d.Point*/ p2, /*:int*/ px, /*:int*/ py) {
  this.coeffA = (p1.y - p2.y)/(p1.x - p2.x);
  this.coeffB = p1.y - this.coeffA*p1.x;
  if (this.coeffA && this.coeffB)
    return (Math.abs(py - this.coeffA*px - this.coeffB)< 3) 
      && ((px - p1.x)*(px - p2.x) <= 0)
      && ((py - p1.y)*(py - p2.y) <= 0);
  return false;
}
