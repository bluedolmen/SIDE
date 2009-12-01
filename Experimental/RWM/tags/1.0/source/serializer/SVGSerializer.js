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
draw2d.SVGSerializer=function()
{
}

/** @private **/
draw2d.SVGSerializer.prototype.type="SVGSerializer";

/**
 * Return the draw2d document as XML
 *
 * @param {Document} document The Draw2D document
 * @type String
 * @see ToolSave
 **/
draw2d.SVGSerializer.prototype.toSVG=function(/*:Document*/ doc)
{
  var svg = document.createElementNS("http://www.w3.org/2000/svg","svg");
  svg.setAttribute("width","12cm");
  svg.setAttribute("height","4cm");
  svg.setAttribute("viewbox","0 0 3000 3000");
  svg.setAttribute("preserveAspectRatio","xMinYMin meet");
  svg.setAttribute("version","1.1");
  
  		
  var figures = doc.getFigures().clone();
  figures.addAll(doc.getLines());
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
      svg.appendChild(figure.toSVG());
    /*else if (figure instanceof draw2d.Line) {
      var color = "rgb("+figure.getColor().getRed()+","+figure.getColor().getGreen()+","+figure.getColor().getBlue()+")";
      for(var j=0; j<figure.lineSegments.getSize(); j++) {
      	var segment = figure.lineSegments.get(j);
        var rect = document.createElementNS("http://www.w3.org/2000/svg","rect");
        if (segment.start.y === segment.end.y) {
          rect.setAttribute("x",Math.min(segment.start.x,segment.end.x));
          rect.setAttribute("y",segment.start.y);
          rect.setAttribute("width",Math.abs(segment.end.x - segment.start.x));
          rect.setAttribute("height","2");
          rect.setAttribute("fill",color);
        } else if (segment.start.x === segment.end.x) {
          rect.setAttribute("x",segment.start.x);
          rect.setAttribute("y",Math.min(segment.start.y,segment.end.y));
          rect.setAttribute("width","2");
          rect.setAttribute("height",Math.abs(segment.end.y - segment.start.y));
          rect.setAttribute("fill",color);
        }
        svg.appendChild(rect);
      }
    }*/
  }

  var visiblePart = document.createElementNS("http://www.w3.org/2000/svg","rect");
  visiblePart.setAttribute("x","0");
  visiblePart.setAttribute("y","0");
  visiblePart.setAttribute("width","500");
  visiblePart.setAttribute("height","500");
  visiblePart.setAttribute("style","stroke-width:1;stroke:rgb(0,0,255);fill-opacity:0.2;");
  visiblePart.setAttribute("fill","rgb(135,206,250)");
  visiblePart.id = "OverviewMapSVG_visiblePart";
  svg.appendChild(visiblePart);
  
  return svg;
}
