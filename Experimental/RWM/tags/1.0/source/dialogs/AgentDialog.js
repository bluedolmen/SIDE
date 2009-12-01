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

draw2d.AgentDialog=function(/*Figure:*/ fig) {
	this.figure = fig;
	draw2d.RWMDialog.call(this, fig);
	return this;
}

draw2d.AgentDialog.prototype = new draw2d.RWMDialog;
draw2d.AgentDialog.prototype.type = "AgentDialog";

draw2d.AgentDialog.prototype.createTab=function() {
	var tabs = draw2d.RWMDialog.prototype.createTab.call(this);
	
     var role = new Ext.form.TextField({
       fieldLabel: 'Role',
       name: 'role',
       id:'role',
       value: this.figure.role
     });
     role.setWidth(200);
     
     var isHuman = new Ext.form.Checkbox({
     	fieldLabel: 'Is human',
     	name: 'isHuman',
     	id:'isHuman',
     	checked: this.figure.isHuman
     });

	var tab = new Ext.Panel({
	  title:'Agent',
      layout:'form',
      items: [role, isHuman]
	});

   tabs.insert(0,tab);
    
    return tabs;
}
