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

draw2d.EntityFigure=function()
{
  this.cornerWidth  = 25;
  this.cornerHeight = 20;
  this.originalHeight = -1;
  draw2d.Node.call(this);
  this.setColor(null);
  this.setDimension("200","50");
}

draw2d.EntityFigure.prototype = new draw2d.Node;
draw2d.EntityFigure.prototype.type = "EntityFigure";

draw2d.EntityFigure.prototype.createHTMLElement=function() {
    var item = document.createElement('div');
    item.id        = this.id;
    item.style.position="absolute";
    item.style.left   = this.x+"px";
    item.style.top    = this.y+"px";
    item.style.height = this.width+"px";
    item.style.width  = this.height+"px";
    item.style.margin = "0px";
    item.style.padding= "0px";
    item.style.outline= "none";
    item.style.zIndex = ""+draw2d.Figure.ZOrderBaseIndex;
	item.className = "dialog";

	var minimize = document.createElement("div");
	minimize.className = "alphacube_minimize";
	item.appendChild(minimize);
	this.minimize = minimize;
	
	var add = document.createElement("div");
	add.className = "alphacube_add";
	item.appendChild(add);
	this.add = add;

	item.appendChild(this.createHeaderHTMLElement());
	item.appendChild(this.createContentHTMLElement());
	item.appendChild(this.createFooterHTMLElement());

   disableSelection(item);
    return item;
}


draw2d.EntityFigure.prototype.createHeaderHTMLElement=function() {
	var table = document.createElement("table");
	table.className = "top table_window";
	this.disableTextSelection(table);
		
	var tbody = document.createElement("tbody");
	table.appendChild(tbody);
	
	var tr = document.createElement("tr");
	tbody.appendChild(tr);
	
	var td = document.createElement("td");
	td.className = "alphacube_nw top_draggable";
	tr.appendChild(td);
	
	td = document.createElement("td");
	td.className = "alphacube_n";
	tr.appendChild(td);
	
	var div = document.createElement("div");
	div.className = "alphacube_title title_window top_draggable";
	td.appendChild(div);
	this.header = div;
	
	td = document.createElement("td");
	td.className = "alphacube_ne top_draggable";
	tr.appendChild(td);
	
	return table;
}

draw2d.EntityFigure.prototype.createContentHTMLElement=function() {
	var table = document.createElement("table");
	table.className = "mid table_window";
		
	var tbody = document.createElement("tbody");
	table.appendChild(tbody);
	
	var tr = document.createElement("tr");
	tbody.appendChild(tr);
	
	var td = document.createElement("td");
	td.className = "alphacube_w";
	tr.appendChild(td);
	
	td = document.createElement("td");
	td.className = "alphacube_content";
	tr.appendChild(td);
	
	var div = document.createElement("div");
	div.className = "alphacube_content";
	div.style.width = this.width - 24;
	this.textarea = div;
	td.appendChild(div);
	
	td = document.createElement("td");
	td.className = "alphacube_e";
	tr.appendChild(td);
	
	return table;
}

draw2d.EntityFigure.prototype.createFooterHTMLElement=function() {
	var table = document.createElement("table");
	table.className = "bot table_window";
		
	var tbody = document.createElement("tbody");
	table.appendChild(tbody);
	
	var tr = document.createElement("tr");
	tbody.appendChild(tr);
	
	var td = document.createElement("td");
	td.className = "alphacube_sw bottom_draggable";
	tr.appendChild(td);
	
	td = document.createElement("td");
	td.className = "alphacube_s bottom_draggable";
	tr.appendChild(td);
	
	var div = document.createElement("div");
	div.className = "status_bar";
	div.style.width = "1px";
	td.appendChild(div);
	
	td = document.createElement("td");
	td.className = "alphacube_sizer bottom_draggable";
	tr.appendChild(td);
	
	return table;
}


draw2d.EntityFigure.prototype.setWorkflow=function(/*:draw2d.Workflow*/ workflow)
{
  draw2d.Node.prototype.setWorkflow.call(this,workflow);

  if(workflow!=null && this.portTop==null)
  {
  	this.portTop = new draw2d.PrivilegePort();
    this.portTop.setWorkflow(workflow);
    this.addPort(this.portTop,this.width/2,-8);
  	
    this.portRight = new draw2d.RelationShipPort();
    this.portRight.setWorkflow(workflow);
    this.addPort(this.portRight,this.width-5,this.height/2);

    this.portLeft = new draw2d.RelationShipPort();
    this.portLeft.setWorkflow(workflow);
    this.addPort(this.portLeft,-5,this.height/2);
  }
}

draw2d.EntityFigure.prototype.getEntityName=function()
{
  if (this.entityName != null)
    return this.entityName;
  else
	return "";
}

draw2d.EntityFigure.prototype.getRelationShip=function()
{
  var result = new Array();
  for (var i = 0; i < this.getPorts().size; ++i) {
  	var port = this.getPorts().get(i);
  	if (port instanceof draw2d.RelationShipPort) {
  	  var connections = port.getConnections();
  	  for (var j = 0; j < connections.size; ++j) {
  	  	var connection = connections.get(j);
  	  	if (connection instanceof draw2d.RelationShipConnection) {
  	  	  var s = connection.ends[0].target;
  	  	  var t = connection.ends[1].target;
  	  	  if ((s && this == s) || (t && this == t))
  	  	    result.push(connection);
  	  	}
  	  }
  	}
  }
  return result;
}

draw2d.EntityFigure.prototype.getRelationShipTarget=function()
{
  var result = new Array();
  var connections = this.getRelationShip();
  for (var i = 0; i < connections.length; ++i) {
    var connection = connections[i];
    result.push(connection.getOtherTarget(this));
  }
  return result;
}

draw2d.EntityFigure.prototype.setEntityName=function(/*:String*/ value) {
  this.entityName = value;
  while (this.header.firstChild) 
  {
    this.header.removeChild(this.header.firstChild);
  };
  if (this.elementList_name != null)
    this.elementList_name.innerHTML = value;
  this.header.appendChild(document.createTextNode(value));
}

draw2d.EntityFigure.prototype.paint=function() {
  draw2d.Node.prototype.paint.call(this);
  if (this.getEntityName() == '')
    this.setEntityName("New entity");
}

draw2d.EntityFigure.prototype.setDimension=function(/*:int*/ w, /*:int*/ h )
{
  draw2d.Node.prototype.setDimension.call(this,w, h);

  if (this.textarea != null) {
  	this.textarea.style.width = w - 14 +"px";
  	this.textarea.style.height = h-25-7 +"px";
  }
  
  if(this.portRight!=null)
    this.portRight.setPosition(this.width-5, this.height/2);
  if(this.portLeft!=null)
    this.portLeft.setPosition(-5, this.height/2);
  if(this.portTop!=null)
    this.portTop.setPosition(this.width/2, -8);
    
  if (this.SVGElement) {
    this.SVGElement.setAttribute("width",w);
    this.SVGElement.setAttribute("height",h);
  }
}

/**
 * Set the position of the object.
 *
 * @param {int} xPos The new x coordinate of the figure
 * @param {int} yPos The new y coordinate of the figure 
 **/
draw2d.EntityFigure.prototype.setPosition=function(/*:int*/ xPos , /*:int*/ yPos )
{
  draw2d.Figure.prototype.setPosition.call(this,xPos,yPos);
  if (this.SVGElement) {
    this.SVGElement.setAttribute("x",xPos);
    this.SVGElement.setAttribute("y",yPos);
  }
}

draw2d.EntityFigure.prototype.toggle=function()
{	
  // collapse
  if(this.originalHeight==-1)
  {
	this.collapse();
	this.minimize.className = "alphacube_maximize";
  }
  // expand
  else
  {
	this.expand();
	this.minimize.className = "alphacube_minimize";
  }
}

draw2d.EntityFigure.prototype.collapse=function()
{
  this.add.style.display = "none";
  this.portRight.html.style.display = "none";
  this.portLeft.html.style.display = "none";
	
  //Get all connections
  /*var ports = this.getPorts();
  var connections = new Array();
  for(var j=0; j<ports.getSize(); j++) {
    var port = ports.get(j);
    if(port && port.getConnections) {
      var connects = port.getConnections();
      for (var k=0; k < connects.getSize(); k++) {
      	var c = connects.get(k);
      	  if (c.getSource().getParent().html.style.visibility != "hidden" && c.getTarget().getParent().html.style.visibility != "hidden")
            connections = connections.concat(c);
      }
    }
  }*/
	
  this.originalHeight=this.height;
  this.setDimension(this.width, 25+7);
  this.setResizeable(false);
  
  /*for (var i = 0; i < connections.length; ++i)
    connections[i].getHTMLElement().style.visibility = "hidden";*/
}

draw2d.EntityFigure.prototype.expand=function()
{	
  this.setDimension(this.width, this.originalHeight);
  this.originalHeight=-1;
  this.add.style.display = "";
  this.portRight.html.style.display = "";
  this.portLeft.html.style.display = "";
  this.setResizeable(true);

  //Get all connections
  /*var ports = this.getPorts();
  var connections = new Array();
  for(var j=0; j<ports.getSize(); j++) {
    var port = ports.get(j);
    if(port && port.getConnections) {
      var connects = port.getConnections();
      for (var k=0; k < connects.getSize(); k++) {
      	var c = connects.get(k);
      	  if (c.getSource().getParent().html.style.visibility != "hidden" && c.getTarget().getParent().html.style.visibility != "hidden")
            connections = connections.concat(c);
      }
    }
  }
    
	 
  for (var i = 0; i < connections.length; ++i) {
	connections[i].getHTMLElement().style.visibility = "visible";
	connections[i].paint();
  }*/
}

draw2d.EntityFigure.prototype.onDragstart = function(/*:int*/ x, /*:int*/ y)
{
  var originalResult = draw2d.Node.prototype.onDragstart.call(this,x,y);
  // no titlebar => no drag drop
  // Reson: The titlebar is the DragDrop handle.
  //
  if(this.header==null)
    return false;

  //add attribute
  if(y<=25 && y>=5 && x<=30 && x>=10)
  {
    this.addNewAttribute();
    return false;
  }
  
  //remove attribute
  if (x>=this.width-37 && x < this.width-17) {
  	if (y > 28) {
  	  var newy = y - 28;
  	  var id = Math.floor(newy/23);
  	  if (this.attributes && id < this.attributes.length && id >= 0 && confirm("Do you want really to delete this attribute ?")) {
  	    this.attributes.splice(id,1);
  	    this.refreshAttributes();
  	  }
  	  return false;
  	}
  }
  
  //edit attribute
  if (x>=this.width-57 && x < this.width-37) {
  	if (y > 28) {
  	  var newy = y - 28;
  	  var id = Math.floor(newy/23);
  	  if (this.attributes)
  	    var d = new draw2d.AttributeDialog(this.attributes[id]);
  	  if (d) {
        this.getWorkflow().addFigure(d);
        d.show();
  	  }
      return false;
  	}
  }
  
  //expand/collapse
  if(y<this.cornerHeight && x<this.width && x>(this.width-this.cornerWidth))
  {
    this.toggle();
    return false;
  }

  // DragDrop check if the figure is expanded.
  // Return only true if the user klicks into the header.
  //
  if(this.originalHeight==-1)
  {
   if(this.canDrag)
      return true;
  }
  // DragDrop check if the figure is collapsed.
  // Return only true if the user klicks into the header.
  //
  else
  {
     return originalResult;
  }
}

draw2d.EntityFigure.prototype.addNewAttribute = function() {
  if (this.attributes == null)
    this.attributes = new Array();
    
  var attr = new draw2d.AttributeFigure("newAttribute","text");
  attr.setWidth(this.width-14);
  this.attributes.push(attr);
  this.refreshAttributes();
}

draw2d.EntityFigure.prototype.addAttribute = function(/*:draw2d.AttributeFigure*/ attr) {
  if (this.attributes == null)
    this.attributes = new Array();
    
  attr.setWidth(this.width-14);
  this.attributes.push(attr);
  this.refreshAttributes();
}

draw2d.EntityFigure.prototype.refreshAttributes = function() {
  while (this.textarea.firstChild)
    this.textarea.removeChild(this.textarea.firstChild);
  
  for (var i=0; i<this.attributes.length; ++i) {
  	var div = this.attributes[i].createHTMLElement();
  	div.style.top = ((i+1)*3)+"px";
  	this.textarea.appendChild(div);
  }
  
  if (this.height < this.attributes.length*23 + 25 + 7)
    this.setDimension(this.width,this.attributes.length*23 + 25 + 7);
}

draw2d.EntityFigure.prototype.onDoubleClick=function()
{
  var d = new draw2d.EntityDialog(this);
  this.getWorkflow().addFigure(d);
  d.show();
}

draw2d.EntityFigure.prototype.getProperties=function() {
	return new Array("name");
}

draw2d.EntityFigure.prototype.setProperty=function(/*:String*/ key,/*:String*/ value)
{
  if (key == "name")
    this.setEntityName(value);
  else if (key == "documentation") {
    this.documentation = value;
    if (this.elementList_documentation != null)
      this.elementList_documentation.innerHTML = value;
  }
}

draw2d.EntityFigure.prototype.toXMI=function() {
	var xmi = '<entities name="'+this.getEntityName()+'" xmi:id="'+this.getId()+'"' +
			' x="'+this.getX()+'" y="'+this.getY()+'" width="'+this.getWidth()+'" height="'+this.getHeight()+'"' +
			' documentation="'+this.getDocumentation().replace(/</g,"&lt;")+'">\n';
	
	if (this.attributes)		
      for (var i = 0; i < this.attributes.length; ++i) {
        xmi += this.attributes[i].toXMI();
      }
			
    xmi += '</entities>\n';
	return xmi;
}

draw2d.EntityFigure.prototype.toSVG=function() {
  var rect = document.createElementNS("http://www.w3.org/2000/svg","rect");
  rect.setAttribute("x",this.getX());
  rect.setAttribute("y",this.getY());
  rect.setAttribute("width",this.getWidth());
  rect.setAttribute("height",this.getHeight());
  rect.setAttribute("fill","rgb(245,245,245)");
  rect.setAttribute("style","stroke-width:1;stroke:rgb(0,0,0);");
  this.SVGElement = rect;
  return rect;
}

draw2d.EntityFigure.prototype.getDocumentation=function() {
	if (this.documentation != null)
	  return this.documentation.replace(/&nbsp;/g,"").replace(/\n/g,"");
	else
	  return "";
}
