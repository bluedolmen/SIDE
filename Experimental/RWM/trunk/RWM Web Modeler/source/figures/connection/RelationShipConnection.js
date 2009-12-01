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
draw2d.RelationShipConnection=function()
{
  draw2d.SVGConnection.call(this);
  this.setLineWidth(2);
  this.setColor(new  draw2d.Color(213,101,213));
  this.ends = new Array(new draw2d.EndFigure(),new draw2d.EndFigure());
}

draw2d.RelationShipConnection.prototype = new draw2d.SVGConnection();

draw2d.RelationShipConnection.prototype.onDoubleClick=function() {
  draw2d.Line.prototype.onDoubleClick.call(this);
  
  var d = new draw2d.RelationShipDialog(this);
  this.getWorkflow().addFigure(d);
  d.show();  
}

draw2d.RelationShipConnection.prototype.getAssociationName=function() {
  if (this.associationName)
    return this.associationName  	
  else
    return "";
}

draw2d.RelationShipConnection.prototype.setAssociationName=function(/*:String*/ value) {
  this.associationName = value;
}

draw2d.RelationShipConnection.prototype.getProperties=function() {
	return new Array("name","minSource","maxSource","minTarget","maxTarget");
}

draw2d.RelationShipConnection.prototype.setProperty=function(/*:String*/ key,/*:String*/ value)
{
  if (key == "name")
    this.setAssociationName(value);
  else if (key == "documentation")
    this.documentation = value;
  else if (key == "minSource" && this.ends[0])
    this.ends[0].setMin(value);
  else if (key == "maxSource" && this.ends[0])
    this.ends[0].setMax(value);
  else if (key == "minTarget" && this.ends[1])
    this.ends[1].setMin(value);
  else if (key == "maxTarget" && this.ends[1])
    this.ends[1].setMax(value);
}

draw2d.RelationShipConnection.prototype.getOtherTarget=function(/*:Object*/ o) {
  var s = this.ends[0].target;
  var t = this.ends[1].target;
  if (s && t && o == s)
    return t;
  else if (s && t && o == t)
    return s;
  else
    return null;
}

draw2d.RelationShipConnection.prototype.setSource=function(/*Port*/ p) {
	this.ends[0].target = p.getParent(); 
	
	var max = draw2d.SVGConnection.prototype.getZOrder.call(this);
	var source = "0";
	if (p.getParent() != null)
	  source = p.getParent().getZOrder();
	
	if (parseInt(source) > parseInt(max))
	  max = source;
	
	draw2d.SVGConnection.prototype.setSource.call(this, p);
	draw2d.SVGConnection.prototype.setZOrder.call(this, max);
}

draw2d.RelationShipConnection.prototype.setTarget=function(/*Port*/ p) {
	this.ends[1].target = p.getParent();
	
	var max = draw2d.SVGConnection.prototype.getZOrder.call(this);
	var target = "0";
	if (p.getParent() != null)
	  target = p.getParent().getZOrder();
	
	if (parseInt(target) > parseInt(max))
	  max = target;
	
	draw2d.SVGConnection.prototype.setTarget.call(this, p);
	draw2d.SVGConnection.prototype.setZOrder.call(this, max);
}

draw2d.RelationShipConnection.prototype.toXMI=function() {
  var xmi = '<relations xmi:id="'+this.id+'" name="'+this.getAssociationName()+'"' +
  		' documentation="'+this.getDocumentation().replace(/</g,"&lt;")+'">';
  if (this.ends)
    for (var i=0; i<this.ends.size(); ++i)
      xmi += this.ends[i].toXMI();
  xmi += '</relations>\n';
  return xmi;
}

draw2d.RelationShipConnection.prototype.getDocumentation=function() {
	if (this.documentation != null)
	  return this.documentation.replace(/&nbsp;/g,"").replace(/\n/g,"");
	else
	  return "";
}
