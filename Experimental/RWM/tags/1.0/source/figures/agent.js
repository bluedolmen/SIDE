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

draw2d.AgentFigure=function(/*:String*/ role)
{
  draw2d.ImageFigure.call(this,"figures/pictures/agent/agent.png");
  this.outputPort = null;
  this.setDimension(48,70);
  if (role != null)
    this.setRole(role);
  else
    this.setRole("New agent");
  this.setIsHuman(true);
  return this;
}

draw2d.AgentFigure.prototype = new draw2d.ImageFigure;
draw2d.AgentFigure.prototype .type = "AgentFigure";

draw2d.AgentFigure.prototype.setWorkflow=function(/*:draw2d.Workflow*/ workflow)
{
  draw2d.ImageFigure.prototype.setWorkflow.call(this,workflow);

  if(workflow!=null && this.outputPort==null)
  {
  	var imgFigure = new draw2d.ImageFigure("./figures/connection/ResponsibilityPort.png");
  	imgFigure.setBackgroundColor(null);
    this.ResponsibilityPort = new draw2d.ResponsibilityPort(imgFigure);
    this.ResponsibilityPort.setWorkflow(workflow);
    this.ResponsibilityPort.setName("responsibility");
    this.addPort(this.ResponsibilityPort,this.width/2,this.height);
  }
}

draw2d.AgentFigure.prototype.createHTMLElement=function()
{
    var item = draw2d.Node.prototype.createHTMLElement.call(this);
    item.className = "agentFigure";
    item.style.width=this.width+"px";
    item.style.height=this.height+"px";
    item.style.margin="0px";
    item.style.padding="0px";
    item.style.border="0px";
    item.style.fontSize="11px";
    item.style.fontFamily="sans-serif";
    item.style.fontWeight="bold";
    item.style.color="#17385B";
    item.style.verticalAlign="top";
    item.style.textAlign="center";
    item.style.backgroundRepeat="no-repeat";
    item.style.backgroundPosition="bottom center";
    if(this.url!=null)
      item.style.backgroundImage="url("+this.url+")";
    else
      item.style.backgroundImage="";
    var div = document.createElement("div");
    div.style.textAlign="center";
    div.style.width="300px";
    div.style.marginLeft=(48-300)/2.0;
    this.textNode = document.createTextNode(this.role);
    div.appendChild(this.textNode);
    item.appendChild(div);
    disableSelection(item);
    return item;
}

draw2d.AgentFigure.prototype.setRole=function(/*:String*/ role) {
  this.role = role;
  if (this.textNode != null)
  	this.textNode.nodeValue = role;
  if (this.elementList_name != null)
    this.elementList_name.innerHTML = role;
}

draw2d.AgentFigure.prototype.getRole=function() {
  if (this.role != null)
  	return this.role;
  else
    return "";
}

draw2d.AgentFigure.prototype.setIsHuman=function(/*:Boolean*/ isHuman) {
  this.isHuman = isHuman;
  if (!isHuman)
  	draw2d.ImageFigure.prototype.setImage.call(this,"figures/pictures/agent/server.png");
  else
    draw2d.ImageFigure.prototype.setImage.call(this,"figures/pictures/agent/agent.png");
}

draw2d.AgentFigure.prototype.getIsHuman=function() {
  if (this.isHuman != null)
    return this.isHuman;
  else
    return "";
}

draw2d.AgentFigure.prototype.onDoubleClick=function()
{
  var d = new draw2d.AgentDialog(this);
  this.getWorkflow().addFigure(d);
  d.show();
}

draw2d.AgentFigure.prototype.toShortString=function()
{
  return "Agent "+this.role;
}

draw2d.AgentFigure.prototype.setProperty=function(/*:String*/ key,/*:String*/ value)
{
  if (key == "role")
    this.setRole(value);
  else if (key == "documentation") {
    this.documentation = value;
    if (this.elementList_documentation != null)
      this.elementList_documentation.innerHTML = value;
  }
  else if (key == "isHuman")
    this.setIsHuman(value);
}

draw2d.AgentFigure.prototype.getProperties=function() {
	return new Array("role","isHuman");
}

/**
 * Set the position of the object.
 *
 * @param {int} xPos The new x coordinate of the figure
 * @param {int} yPos The new y coordinate of the figure 
 **/
draw2d.AgentFigure.prototype.setPosition=function(/*:int*/ xPos , /*:int*/ yPos )
{
  draw2d.Figure.prototype.setPosition.call(this,xPos,yPos);
  if (this.SVGElement) {
    this.SVGElement.setAttribute("x",xPos);
    this.SVGElement.setAttribute("y",yPos);
  }
}

draw2d.AgentFigure.prototype.toXMI=function() {
	var port = this.getPort("responsibility");
	var responsibility = "";
	if (port != null && port.getConnections) {
      var cSet = port.getConnections();
      for (var i=0; i < cSet.getSize(); i++) {
        var c = cSet.get(i);
        if (c.getTarget().getParent() == this)
          responsibility = responsibility + "#" + c.getSource().getParent().getId() + " ";
        else
          responsibility = responsibility + "#" + c.getTarget().getParent().getId() + " ";
      }
	}
	
	var xmi = '<members name="'+this.getRole()+'" xmi:id="'+this.getId()+'"' +
			' x="'+this.getX()+'" y="'+this.getY()+'" width="'+this.getWidth()+'" height="'+this.getHeight()+'"' +
			' isHuman="'+this.getIsHuman()+'" isResponsible="'+responsibility+'"' +
			' documentation="'+this.getDocumentation().replace(/</g,"&lt;")+'"/>\n';
	return xmi;
}

draw2d.AgentFigure.prototype.toSVG=function() {
  var rect = document.createElementNS("http://www.w3.org/2000/svg","rect");
  rect.setAttribute("x",this.getX());
  rect.setAttribute("y",this.getY());
  rect.setAttribute("width",this.getWidth());
  rect.setAttribute("height",this.getHeight());
  rect.setAttribute("fill","rgb(137,199,26)");
  rect.setAttribute("style","stroke-width:1;stroke:rgb(0,0,0);");
  this.SVGElement = rect;
  
  return rect;
}

draw2d.AgentFigure.prototype.getDocumentation=function() {
	if (this.documentation != null)
	  return this.documentation.replace(/&nbsp;/g,"").replace(/\n/g,"");
	else
	  return "";
}
