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

function refreshOverview() {
  var serializer = new draw2d.SVGSerializer();
  var svg = serializer.toSVG(workflow.getDocument());
  var embed = document.embeds["OverviewMapEmbed"];
  var svgDoc = embed.getSVGDocument();
  if (svgDoc) 
    var el = svgDoc.firstChild;
  if (el != null) {
   	while (el.childNodes[0])
      el.removeChild(el.childNodes[0]);
    while (svg.childNodes[0])
      el.appendChild(svg.childNodes[0]);
  }
  refreshOverviewVisiblePart(workflow);
}

function addFigureToOverview(figure) {
  if (figure.toSVG)
    refreshOverview();
}

function removeFigureToOverview(figure) {
  if (figure.SVGElement && figure.SVGElement.parentNode)
    figure.SVGElement.parentNode.removeChild(figure.SVGElement);
}

function refreshOverviewVisiblePart(workflow) {
  var embed = document.embeds["OverviewMapEmbed"];
  var svgDoc = embed.getSVGDocument();
  if (svgDoc) 
    var el = svgDoc.getElementById("OverviewMapSVG_visiblePart");
  if (el != null) {
    el.setAttribute("x",workflow.getScrollLeft());
    el.setAttribute("y",workflow.getScrollTop());
    el.setAttribute("width",workflow.scrollArea.offsetWidth);
    el.setAttribute("height",workflow.scrollArea.offsetHeight);
  }
}

function moveOverview(event,workflow) {
  if (event.clientX && event.clientY) {
  	var embed = document.embeds["OverviewMapEmbed"];
  	var x = event.clientX * workflow.getWidth() / embed.width - workflow.scrollArea.offsetWidth / 2;
  	var y = event.clientY * workflow.getHeight() / embed.height - workflow.scrollArea.offsetHeight / 2;
  	workflow.scrollTo(x,y);
  }
}
