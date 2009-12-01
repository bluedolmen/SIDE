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

draw2d.PrivilegeConnection=function()
{
  draw2d.SVGConnection.call(this);
  this.sourcePort = null;
  this.targetPort = null;
  this.lineSegments = new Array();
  this.setColor(new  draw2d.Color(34,52,76));
  this.setLineWidth(2);
  
  this.privileges = new Array();
}

draw2d.PrivilegeConnection.prototype = new draw2d.SVGConnection();

draw2d.PrivilegeConnection.prototype.onDoubleClick=function() {
  draw2d.Line.prototype.onDoubleClick.call(this);
  
  var d = new draw2d.PrivilegeDialog(this);
  this.getWorkflow().addFigure(d);
  d.show();  
}


draw2d.PrivilegeConnection.prototype.setSource=function(/*Port*/ p) {
	var max = draw2d.Connection.prototype.getZOrder.call(this);
	var source = "0";
	if (p.getParent() != null)
	  source = p.getParent().getZOrder();
	
	if (parseInt(source) > parseInt(max))
	  max = source;
	
	draw2d.SVGConnection.prototype.setSource.call(this, p);
	draw2d.SVGConnection.prototype.setZOrder.call(this, max);
}

draw2d.PrivilegeConnection.prototype.setTarget=function(/*Port*/ p) {
	var max = draw2d.SVGConnection.prototype.getZOrder.call(this);
	var target = "0";
	if (p.getParent() != null)
	  target = p.getParent().getZOrder();
	
	if (parseInt(target) > parseInt(max))
	  max = target;
	
	draw2d.SVGConnection.prototype.setTarget.call(this, p);
	draw2d.SVGConnection.prototype.setZOrder.call(this, max);
}

draw2d.PrivilegeConnection.prototype.paint=function() {
	if (this.html.style.visibility != "hidden")
	  draw2d.SVGConnection.prototype.paint.call(this);
}

draw2d.PrivilegeConnection.prototype.getEntity=function() {
  var t = this.getTarget().getParent();
  var s = this.getSource().getParent();
  if (t && t instanceof draw2d.EntityFigure)
    return t;
  else if (s && s instanceof draw2d.EntityFigure)
    return s;
  else
    return null;
}

draw2d.PrivilegeConnection.prototype.getProperties=function() {
	return new Array("privileges");
}

draw2d.PrivilegeConnection.prototype.setProperty=function(/*:String*/ key,/*:String*/ value)
{
  if (key == "privileges") {
  	this.cleanPrivilege();
  	this.addAllPrivilege(value);
  } else if (key == "documentation")
    this.documentation = value;
}

draw2d.PrivilegeConnection.prototype.addPrivilege=function(/*:draw2d.Privilege*/ p) {
  this.privileges.push(p);
}

draw2d.PrivilegeConnection.prototype.addAllPrivilege=function(/*:Array<draw2d.Privilege>*/ a) {
  for (var i = 0; i < a.length; ++i)
    this.privileges.push(a[i]);
}

draw2d.PrivilegeConnection.prototype.getPrivilege=function(/*:String*/ objectId) {
  for (var i = 0; i < this.privileges.length; ++i) {
  	var p = this.privileges[i];
  	if ((p.object && p.object.id == objectId) || (p.objectId == objectId))
  	  return p;
  }
  return null;
}

draw2d.PrivilegeConnection.prototype.cleanPrivilege=function() {
  while (this.privileges.length > 0)
    this.privileges.pop();
}

draw2d.PrivilegeConnection.prototype.toInternalXMI=function() {
  var xmi = '<privilegeGroup entryPoint="#'+this.getEntity().id+'"' +
  		' documentation="'+this.getDocumentation().replace(/</g,"&lt;")+'">\n';
  for (var i = 0; i < this.privileges.length; ++i) {
  	var p = this.privileges[i];
  	if (p.object) {
  	  if (p.object instanceof draw2d.EntityFigure) {
  	  	if (p.create)
  	  	  xmi += '<privileges element="#'+p.object.id+'" category="create"/>\n';
  	  	if (p.read)
  	  	  xmi += '<privileges element="#'+p.object.id+'" category="read"/>\n';
  	  	if (p.update)
  	  	  xmi += '<privileges element="#'+p.object.id+'" category="update"/>\n';
  	  	if (p.del)
  	  	  xmi += '<privileges element="#'+p.object.id+'" category="delete"/>\n';
  	  } else {
  	  	if (p.read)
  	  	  xmi += '<privileges element="#'+p.object.id+'" category="read"/>\n';
  	  	if (p.update)
  	  	  xmi += '<privileges element="#'+p.object.id+'" category="update"/>\n';
  	  }
  	}
  }
  xmi += '</privilegeGroup>\n';
  return xmi;
}

draw2d.PrivilegeConnection.prototype.getDocumentation=function() {
	if (this.documentation != null)
	  return this.documentation.replace(/&nbsp;/g,"").replace(/\n/g,"");
	else
	  return "";
}
