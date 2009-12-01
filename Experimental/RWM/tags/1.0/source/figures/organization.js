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

draw2d.OrganizationFigure=function()
{
  this.cornerWidth  = 25;
  this.cornerHeight = 20;

  draw2d.CompartmentFigure.call(this);
  this.setDimension(250,150);
  this.originalHeight =-1;
  this.setColor(null);
  this.setName("New organization");
}

/** base class of my example double click figure 
 * You can use circle, oval,.....too
 **/
draw2d.OrganizationFigure.prototype = new draw2d.CompartmentFigure;
draw2d.OrganizationFigure.prototype.type="OrganizationFigure";


/**
 * Create the HTML for the round corner object.
 * Each corner of the figure is a DIV with a corresponding background image.
 * 
 * @private
 **/
draw2d.OrganizationFigure.prototype.createHTMLElement=function()
{
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
	minimize.className = "bluelighting_minimize";
	item.appendChild(minimize);
	this.minimize = minimize;

	item.appendChild(this.createHeaderHTMLElement());
	item.appendChild(this.createContentHTMLElement());
	item.appendChild(this.createFooterHTMLElement());

    disableSelection(item);
    return item;
}

draw2d.OrganizationFigure.prototype.createHeaderHTMLElement=function() {
	var table = document.createElement("table");
	table.className = "top table_window";
	this.disableTextSelection(table);
		
	var tbody = document.createElement("tbody");
	table.appendChild(tbody);
	
	var tr = document.createElement("tr");
	tbody.appendChild(tr);
	
	var td = document.createElement("td");
	td.className = "bluelighting_nw top_draggable";
	tr.appendChild(td);
	
	td = document.createElement("td");
	td.className = "bluelighting_n";
	tr.appendChild(td);
	
	var div = document.createElement("div");
	div.className = "bluelighting_title title_window top_draggable";
	td.appendChild(div);
	this.header = div;
	
	td = document.createElement("td");
	td.className = "bluelighting_ne top_draggable";
	tr.appendChild(td);
	
	return table;
}

draw2d.OrganizationFigure.prototype.createContentHTMLElement=function() {
	var table = document.createElement("table");
	table.className = "mid table_window";
		
	var tbody = document.createElement("tbody");
	table.appendChild(tbody);
	
	var tr = document.createElement("tr");
	tbody.appendChild(tr);
	
	var td = document.createElement("td");
	td.className = "bluelighting_w";
	tr.appendChild(td);
	
	td = document.createElement("td");
	td.className = "bluelighting_content";
	tr.appendChild(td);
	
	var div = document.createElement("div");
	div.className = "bluelighting_content";
	div.style.width = this.width - 24;
	this.textarea = div;
	td.appendChild(div);
	
	td = document.createElement("td");
	td.className = "bluelighting_e";
	tr.appendChild(td);
	
	return table;
}

draw2d.OrganizationFigure.prototype.createFooterHTMLElement=function() {
	var table = document.createElement("table");
	table.className = "bot table_window";
		
	var tbody = document.createElement("tbody");
	table.appendChild(tbody);
	
	var tr = document.createElement("tr");
	tbody.appendChild(tr);
	
	var td = document.createElement("td");
	td.className = "bluelighting_sw bottom_draggable";
	tr.appendChild(td);
	
	td = document.createElement("td");
	td.className = "bluelighting_s bottom_draggable";
	tr.appendChild(td);
	
	var div = document.createElement("div");
	div.className = "status_bar";
	div.style.width = "1px";
	td.appendChild(div);
	
	td = document.createElement("td");
	td.className = "bluelighting_sizer bottom_draggable";
	tr.appendChild(td);
	
	return table;
}

/**
 * Adjuste the corner DIV elements to the new dimension of the figure.
 * Additional the ports must be adjust to the new height/width of the figure.
 *
 **/
draw2d.OrganizationFigure.prototype.setDimension=function(/*:int*/ w, /*:int*/ h )
{
  draw2d.Node.prototype.setDimension.call(this,w, h);

  // Adjust the different layer/div/img object of the figure
  //
  /*if(this.top_left!=null)
  {
    this.top_right.style.left  = (this.width-this.cornerWidth)+"px";
    this.bottom_right.style.left  = (this.width-this.cornerWidth)+"px";
    this.bottom_right.style.top  = (this.height-this.cornerHeight)+"px";
    this.bottom_left.style.top  = (this.height-this.cornerHeight)+"px";

    this.textarea.style.width  = (this.width-2)+"px";
    this.textarea.style.height  = (this.height-this.cornerHeight*2)+"px";

    this.header.style.width  = (this.width-this.cornerWidth*2)+"px";

    this.footer.style.width  = (this.width-this.cornerWidth*2)+"px";
    this.footer.style.top  = (this.height-this.cornerHeight)+"px";
  }*/
  if (this.textarea != null) {
  	this.textarea.style.width = w - 24+"px";
  	this.textarea.style.height = h-28-15+"px";
  }
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
draw2d.OrganizationFigure.prototype.setPosition=function(/*:int*/ xPos , /*:int*/ yPos )
{
  draw2d.Figure.prototype.setPosition.call(this,xPos,yPos);
  if (this.SVGElement) {
    this.SVGElement.setAttribute("x",xPos);
    this.SVGElement.setAttribute("y",yPos);
  }
}

/**
 * Utility function to set the content of the figure.
 *
 **/
draw2d.OrganizationFigure.prototype.setContent=function(/*:String*/ content)
{
   this.textarea.innerHTML=content;
}

draw2d.OrganizationFigure.prototype.setName=function(/*:String*/ oName) {
  this.organizationName = oName;
  this.header.innerHTML=oName;
}

draw2d.OrganizationFigure.prototype.getName=function() {
  if (this.organizationName != null)
    return this.organizationName;
  else
    return "";
}

/**
 * The round corner figure can only be dragged with the title bar.
 * REASON: Unable to use the scrollbar of the textarea DIV.
 *
 * @param {int} x x position of the mouse in the window
 * @param {int} y y position of the mouse in the window
 * @returns Returns [true] if the window can be draged. False in the other case
 * @type boolean
 **/
draw2d.OrganizationFigure.prototype.onDragstart = function(/*:int*/ x, /*:int*/ y)
{
  	
  var originalResult = draw2d.Node.prototype.onDragstart.call(this,x,y);
  // no titlebar => no drag drop
  // Reson: The titlebar is the DragDrop handle.
  //
  if(this.header==null)
    return false;

  // expand/collapse
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


/**
 * Only the header has the Drag&Drop cursor.
 *
 * @param {boolean} flag The flag which handles the drag drop behaviour of this window.
 *
 **/
draw2d.OrganizationFigure.prototype.setCanDrag=function(/*:boolean*/flag)
{
  draw2d.Node.prototype.setCanDrag.call(this,flag);
  this.html.style.cursor="";
  if(this.header==null)
    return;

  if(flag)
    this.header.style.cursor="move";
  else
    this.header.style.cursor="";
}

/**
 * Get all connections
 */
draw2d.OrganizationFigure.prototype.getAllChildrens=function(/*:AlphaParameter*/ alpha) {
	var childrens = new Array();
	for(var i=0;i< this.children.getSize();i++)
	  {
	     var child = this.children.get(i);
	     childrens.push(child);
	     if (child instanceof draw2d.OrganizationFigure) {
	     	var subChildrens = child.getAllChildrens(alpha);
	     	for(var j=0;j< subChildrens.length;j++)
	     		childrens.push(subChildrens[j]);
	     }
	  }
	  return childrens;
}

/**
 * Toggle the expand/collapse state of the figure.
 *
 **/
draw2d.OrganizationFigure.prototype.toggle=function()
{	
  // collapse
  if(this.originalHeight==-1)
  {
	this.collapse();
	this.minimize.className = "bluelighting_maximize";
  }
  // expand
  else
  {
	this.expand();
	this.minimize.className = "bluelighting_minimize";
  }
}

draw2d.OrganizationFigure.prototype.isExpanded=function() {
	return this.originalHeight == -1;
}

draw2d.OrganizationFigure.prototype.hide=function() {
	 for(var i=0;i< this.children.getSize();i++)
	  {
	     var child = this.children.get(i);
	     if (child.hide)
	     	child.hide();
	     else
	     	child.html.style.visibility = "hidden";
	  }
	this.html.style.visibility = "hidden"
}

draw2d.OrganizationFigure.prototype.show=function() {
		 for(var i=0;i< this.children.getSize();i++)
	  {
	     var child = this.children.get(i);
	     if (child.show)
	     	child.show();
	     else
	     	if (this.isExpanded())
	     		child.html.style.visibility = "visible"
	  }
	this.html.style.visibility = "visible"
}

/**
 * Toggle the expand/collapse state of the figure.
 *
 **/
draw2d.OrganizationFigure.prototype.collapse=function()
{
  //Get all connections
  var ports = this.getPorts();
  var connections = new Array();
  var childrens =  this.getAllChildrens();
  for(var i=0;i< childrens.length;i++) {
  	var child = childrens[i];
  	if (child.ports != null)
  	  for(var j=0; j<child.ports.getSize(); j++) {
  	    var ports = child.ports.get(j);
        if(ports && ports.getConnections) {
      	  var connects = ports.getConnections();
      	  for (var k=0; k < connects.getSize(); k++)
            connections = connections.concat(connects.get(k));
        }
	  }
  }
	
  var childrens = this.children;
  this.originalHeight=this.height;
  this.setDimension(this.width, 28+15);
  this.setResizeable(false);

  for(var i=0;i< childrens.getSize();i++) {
    var child = childrens.get(i);
	child.html.style.visibility = "hidden";
	if (child.SVGElement)
	  child.SVGElement.style.visibility = "hidden";
	if (child instanceof draw2d.OrganizationFigure) {
	  child.hide();
	}
  }
  
  for (var i = 0; i < connections.length; ++i)
    connections[i].getHTMLElement().style.visibility = "hidden";
}

/**
 * Toggle the expand/collapse state of the figure.
 *
 **/
draw2d.OrganizationFigure.prototype.expand=function()
{	
  var childrens = this.children;
  this.setDimension(this.width, this.originalHeight);
  this.originalHeight=-1;
  this.setResizeable(true);
     
  for(var i=0;i< childrens.getSize();i++) {
    var child = childrens.get(i);
	child.html.style.visibility = "visible";
	if (child.SVGElement)
	  child.SVGElement.style.visibility = "visible";
	if (child instanceof draw2d.OrganizationFigure) {
	  child.show();
	}
  }

  //Get all connections
  var ports = this.getPorts();
  var connections = new Array();
  var childrens =  this.getAllChildrens();
  for(var i=0;i< childrens.length;i++) {
    var child = childrens[i];
    if (child.ports != null)
      for(var j=0; j<child.ports.getSize(); j++) {
        var ports = child.ports.get(j);
        if(ports && ports.getConnections) {
      	  var connects = ports.getConnections();
      	  for (var k=0; k < connects.getSize(); k++) {
      	    var c = connects.get(k);
      	    if (c.getSource().getParent().html.style.visibility != "hidden" && c.getTarget().getParent().html.style.visibility != "hidden")
              connections = connections.concat(c);
      	  }
        }
      }
  }
	 
  for (var i = 0; i < connections.length; ++i) {
	connections[i].getHTMLElement().style.visibility = "visible";
	connections[i].paint();
  }
}

draw2d.OrganizationFigure.prototype.toXMI=function() {
	var xmi = '<sub name="'+this.getName()+'" xmi:id="'+this.getId()+'"' +
			' x="'+this.getX()+'" y="'+this.getY()+'" width="'+this.getWidth()+'" height="'+this.getHeight()+'"' +
			' documentation="'+this.getDocumentation().replace(/</g,"&lt;")+'">\n';
	var figures = this.getChildren();
  	for(var i=0;i< figures.getSize(); i++)
  	{
    	var figure = figures.get(i);
    	if (figure.toXMI != null)
    	  xmi = xmi + figure.toXMI();
  	}
	xmi = xmi + "</sub>\n";
	return xmi;
}

draw2d.OrganizationFigure.prototype.onDoubleClick=function()
{
  var d = new draw2d.OrganizationDialog(this);
  this.getWorkflow().addFigure(d);
  d.show();
}

draw2d.OrganizationFigure.prototype.toShortString=function()
{
  return "Organization "+this.getName();
}

draw2d.OrganizationFigure.prototype.setProperty=function(/*:String*/ key,/*:String*/ value)
{
  if (key == "name")
    this.setName(value);
  else if (key == "documentation")
    this.documentation = value;
}

draw2d.OrganizationFigure.prototype.getProperties=function() {
	return new Array("name");
}

draw2d.OrganizationFigure.prototype.toSVG=function() {
  var group = document.createElementNS("http://www.w3.org/2000/svg","g");
	
  var rect = document.createElementNS("http://www.w3.org/2000/svg","rect");
  rect.setAttribute("x",this.getX());
  rect.setAttribute("y",this.getY());
  rect.setAttribute("width",this.getWidth());
  rect.setAttribute("height",this.getHeight());
  rect.setAttribute("fill","rgb(228,239,253)");
  rect.setAttribute("style","stroke-width:1;stroke:rgb(0,0,0);");
  this.SVGElement = rect;
  group.appendChild(rect);
  
  if (this.isExpanded()) {
    var figures = this.children;
    figures.sort(function sortByZIndex(a,b) {
	  if (a.getZOrder && a.getZOrder() && b.getZOrder && b.getZOrder()) {
  	    return b.getZOrder() - a.getZOrder();
   	  } else
  	    return 0;
    });
  
    for(var i=0;i< figures.getSize(); i++)
    {
      var figure = figures.get(i);
      if (figure.toSVG != null)
        group.appendChild(figure.toSVG());
    }
  }
  
  return group;
}

draw2d.OrganizationFigure.prototype.getDocumentation=function() {
	if (this.documentation != null)
	  return this.documentation.replace(/&nbsp;/g,"").replace(/\n/g,"");
	else
	  return "";
}

draw2d.OrganizationFigure.prototype.addChild = function(/*:draw2d.Figure*/ figure)
{
  // The child of a compartment is always above the compartment
  //
  figure.setZOrder(this.getZOrder()+1);
  figure.setParent(this);

  // Add the element to the child array
  //
  if (this.children.indexOf(figure) == -1)
    this.children.add(figure);
}
