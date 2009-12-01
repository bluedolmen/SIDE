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

draw2d.AttributeFigure=function(/*:String*/ oName, /*:String*/ oType)
{
  this.attributeName = oName;
  this.attributeType = oType;
}

draw2d.AttributeFigure.prototype.type = "AttributeFigure";

draw2d.AttributeFigure.prototype.createHTMLElement=function() {
  this.allTds = new Array();
	
  var div = document.createElement("div");
  div.style.position = "relative";
  div.style.top = "10px";
  div.id = draw2d.Figure.prototype.generateUId.call(this);
  this.id = div.id;
  this.html = div;
  
  var table = document.createElement("table");
  table.style.width = "100%";
  table.style.height = "20px";
  table.setAttribute("cellspacing","0");
  table.setAttribute("cellpadding","0");
  div.appendChild(table);
    
  var tr = document.createElement("tr");
  table.appendChild(tr);
  
  var left = document.createElement("td");
  left.className = "td_attribute_text_left";
  this.left = left;
  tr.appendChild(left);

  var attributeType = document.createElement("td");
  var img = document.createElement("img");
  img.setAttribute("src","./figures/pictures/attribute/text.png");
  img.style.verticalAlign = "middle";
  attributeType.appendChild(img);
  attributeType.className = "td_attribute_text";
  attributeType.style.width = "25px";
  this.imgType = img;
  tr.appendChild(attributeType);
  this.allTds.push(attributeType);
  
  var content = document.createElement("td");
  content.className = "td_attribute_text td_attribute";
  this.content = content;
  
  var spanName = document.createElement("span");
  spanName.className = "td_attribute_name";
  spanName.appendChild(document.createTextNode(this.attributeName));
  content.appendChild(spanName);
  this.spanName = spanName;
  
  tr.appendChild(content);
  this.cell = content;

  var edit = document.createElement("td");
  var img = document.createElement("img");
  img.setAttribute("src","./figures/pictures/attribute/edit.png");
  img.style.verticalAlign = "middle";
  edit.appendChild(img);
  edit.className = "td_attribute_text";
  edit.style.width = "20px";
  edit.style.cursor = "pointer";
  tr.appendChild(edit);
  this.allTds.push(edit);
  
  var del = document.createElement("td");
  img = document.createElement("img");
  img.setAttribute("src","./figures/pictures/attribute/delete.png");
  img.style.verticalAlign = "middle";
  del.appendChild(img);
  del.className = "td_attribute_text";
  del.style.width = "20px";
  del.style.cursor = "pointer";
  tr.appendChild(del);
  this.allTds.push(del);
  
  var right = document.createElement("td");
  right.className = "td_attribute_text_right";
  this.right = right;
  tr.appendChild(right);
  
  this.setAttributeType(this.getAttributeType());
  
  return div;
}

draw2d.AttributeFigure.prototype.setWidth=function(/*:int*/ width) {
  this.width = width-30;
  if (this.cell)
    this.cell.style.width = this.width;
}

draw2d.AttributeFigure.prototype.toShortString=function()
{
  return "Attribute "+this.getName();
}

draw2d.AttributeFigure.prototype.setProperty=function(/*:String*/ key,/*:String*/ value)
{
  if (key == "name")
    this.setName(value);
  else if (key == "documentation")
    this.documentation = value;
  else if (key == "typeText" && value) {
    this.setAttributeType("text");
  } else if (key == "typeNumber" && value) {
    this.setAttributeType("number");
  } else if (key == "typeDateTime" && value) {
    this.setAttributeType("DateTime");
  } else if (key == "typeOther" && value) {
    this.setAttributeType("other"); 
  }
}

draw2d.AttributeFigure.prototype.getProperties=function() {
	return new Array("name","typeText","typeNumber","typeDateTime","typeOther");
}

draw2d.AttributeFigure.prototype.setName=function(/*:String*/ oName) {
  this.attributeName = oName;
  if (this.spanName)
    this.spanName.innerHTML=oName;
}

draw2d.AttributeFigure.prototype.setAttributeType=function(/*:String*/ oType) {
  this.attributeType = oType;
  if (this.allTds && this.left && this.right && this.content && this.imgType)
    if (oType == 'text') {
      for (var i = 0; i < this.allTds.length; ++i)
        this.allTds[i].className = "td_attribute_text";
      this.left.className = "td_attribute_text_left";
      this.right.className = "td_attribute_text_right";
      this.content.className = "td_attribute_text td_attribute";
      this.imgType.setAttribute("src","./figures/pictures/attribute/text.png");
    } else if (oType == 'number') {
      for (var i = 0; i < this.allTds.length; ++i)
        this.allTds[i].className = "td_attribute_number";
      this.left.className = "td_attribute_number_left";
      this.right.className = "td_attribute_number_right";
      this.content.className = "td_attribute_number td_attribute";
      this.imgType.setAttribute("src","./figures/pictures/attribute/number.png");
    } else if (oType == 'DateTime') {
      for (var i = 0; i < this.allTds.length; ++i)
        this.allTds[i].className = "td_attribute_dateTime";
      this.left.className = "td_attribute_dateTime_left";
      this.right.className = "td_attribute_dateTime_right";
      this.content.className = "td_attribute_dateTime td_attribute";
      this.imgType.setAttribute("src","./figures/pictures/attribute/date.png");
    } else if (oType == 'other') {
      for (var i = 0; i < this.allTds.length; ++i)
        this.allTds[i].className = "td_attribute_other";
      this.left.className = "td_attribute_other_left";
      this.right.className = "td_attribute_other_right";
      this.content.className = "td_attribute_other td_attribute";
      this.imgType.setAttribute("src","./figures/pictures/attribute/other.png");
    }  
}

draw2d.AttributeFigure.prototype.getName=function() {
  if (this.attributeName != null)
    return this.attributeName;
  else
    return "";
}

draw2d.AttributeFigure.prototype.getAttributeType=function() {
  if (this.attributeType != null)
    return this.attributeType;
  else
    return "text";
}

draw2d.AttributeFigure.prototype.getXMIAttributeType=function() {
  if (!this.attributeType)
    return "Other";
  else if (this.attributeType == 'text')
    return "TextualValue";
  else if (this.attributeType == 'DateTime')
    return "TemporalValue";
  else if (this.attributeType == 'number')
    return "NumericalValue";
  else
    return "Other";
}


draw2d.AttributeFigure.prototype.toXMI=function() {
	var xmi = '<attributes name="'+this.getName()+'" type="'+this.getXMIAttributeType()+'" xmi:id="'+this.id+'"' +
			' documentation="'+this.getDocumentation().replace(/</g,"&lt;")+'"/>\n';
	return xmi;
}

draw2d.AttributeFigure.prototype.getDocumentation=function() {
	if (this.documentation != null)
	  return this.documentation.replace(/&nbsp;/g,"").replace(/\n/g,"");
	else
	  return "";
}
