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

draw2d.AttributeDialog=function(/*Figure:*/ fig) {
	this.figure = fig;
	draw2d.RWMDialog.call(this, fig);
	return this;
}

draw2d.AttributeDialog.prototype = new draw2d.RWMDialog;
draw2d.AttributeDialog.prototype.type = "AttributeDialog";

draw2d.AttributeDialog.prototype.createTab=function() {
	var tabs = draw2d.RWMDialog.prototype.createTab.call(this);
	
     var oName = new Ext.form.TextField({
       fieldLabel: 'Name',
       name: 'name',
       id:'name',
       value: this.figure.attributeName,
       allowBlank: false
     });
     oName.setWidth(200);
     
     var oTypeText = new Ext.form.Radio({
    	fieldLabel: 'Is a textual value',
     	name: 'type',
     	id: 'typeText',
     	checked: this.figure.attributeType=='text'
     });
     
     var oTypeNumber = new Ext.form.Radio({
     	fieldLabel: 'Is a numerical value',
     	name: 'type',
     	id: 'typeNumber',
     	checked: this.figure.attributeType=='number'
     })
     
     var oTypeDateTime = new Ext.form.Radio({
     	fieldLabel: 'Is a temporal value',
     	name: 'type',
     	id: 'typeDateTime',
     	checked: this.figure.attributeType=='DateTime'
     })
     
     var oTypeOther = new Ext.form.Radio({
     	fieldLabel: 'Is an other type of value',
     	name: 'type',
     	id: 'typeOther'
     	,
     	checked: this.figure.attributeType=='other'
     })
     
	var tab = new Ext.Panel({
	  title:'Attribute',
      layout:'form',
      labelWidth: 150,
      items: [oName,oTypeText,oTypeNumber,oTypeDateTime,oTypeOther]
	});

   tabs.insert(0,tab);
    
    return tabs;
}
