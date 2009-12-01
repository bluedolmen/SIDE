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

draw2d.XMISerializer=function()
{
}

/** @private **/
draw2d.XMISerializer.prototype.type="XMISerializer";

/**
 * Return the draw2d document as XML
 *
 * @param {Document} document The Draw2D document
 * @type String
 * @see ToolSave
 **/
draw2d.XMISerializer.prototype.toXML=function(/*:Document*/ document)
{
  var xmi = '<?xml version="1.0" encoding="UTF-8"?>\n';
  xmi = xmi + '<rwm:RequirementsDefinition xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:rwm="http://rwm" xmi:id="_TnQCYKb7Edyg0-Q2ek6f8g" name="MyRequirements">';
  
  var figures = document.getFigures();
  var allConnections = new Array();
  for(var i=0;i< figures.getSize(); i++)
  {
    var figure = figures.get(i);
    if (figure.toXMI != null)
      xmi = xmi + figure.toXMI();
      
    if (figure instanceof draw2d.Node && figure.getPorts) {
      for (var j = 0; j < figure.getPorts().size; ++j) {
      	var port = figure.getPorts().get(j);
      	if (port.getConnections) {
      	  for (var k = 0; k < port.getConnections().size; ++k) {
      	  	var c = port.getConnections().get(k);
      	  	if (c.toXMI && allConnections.indexOf(c) == -1) {
      	  	  xmi += c.toXMI();
      	  	  allConnections.push(c);
      	  	}
      	  }
      	}
      }
    }
  }

  xmi = xmi +'</rwm:RequirementsDefinition>\n';
  
  xmi = xmi.replace(/'/g,"\'");
  xmi = xmi.replace(/&/g,"&amp;");
  
  return xmi;
}
