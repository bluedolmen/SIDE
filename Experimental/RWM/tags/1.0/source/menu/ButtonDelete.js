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

draw2d.ButtonDelete=function(/*:draw2d.PaletteWindow*/ palette)
{
  draw2d.Button.call(this,palette,16,16);
}

draw2d.ButtonDelete.prototype = new draw2d.Button;
/** @private */
draw2d.ButtonDelete.prototype.type="ButtonDelete";


draw2d.ButtonDelete.prototype.execute=function()
{
  var cs = this.palette.workflow.getCommandStack();
  var selection = this.palette.workflow.getCurrentSelection();
  
  if (selection instanceof draw2d.OrganizationFigure) {
  	  var childrens = selection.getAllChildrens(1);
	  for(var i=0;i< childrens.length;i++)
	  {
	    var child = childrens[i];
	    if (!(child instanceof draw2d.ToolPalette) && child.workflow != null)
	    	cs.execute(new draw2d.CommandDelete(child));
	  }
  }
  
  cs.execute(new draw2d.CommandDelete(selection));
  draw2d.ToolGeneric.prototype.execute.call(this);
}

draw2d.Button.prototype.getImageUrl=function()
{
  if(this.enabled)
    return "menu/"+this.type+".png";
  else
    return "menu/"+this.type+"_disabled.png";
}
