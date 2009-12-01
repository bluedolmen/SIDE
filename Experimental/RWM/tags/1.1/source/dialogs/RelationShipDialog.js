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

draw2d.RelationShipDialog=function(/*Figure:*/ fig) {
	this.figure = fig;
	draw2d.RWMDialog.call(this, fig);
	return this;
}

draw2d.RelationShipDialog.prototype = new draw2d.RWMDialog;
draw2d.RelationShipDialog.prototype.type = "RelationShipDialog";

draw2d.RelationShipDialog.prototype.createTab=function() {
	var tabs = draw2d.RWMDialog.prototype.createTab.call(this);
	
     var oName = new Ext.form.TextField({
       fieldLabel: 'Name',
       name: 'name',
       id:'name',
       value: this.figure.getAssociationName()
     });
     oName.setWidth(200);
     
     var general = new Ext.form.FieldSet({
       title: 'General information',
       autoHeight:true,
       collapsible: true,
       items: [oName],
       labelWidth: 150
     });
     
     var source = null;
     if (this.figure.ends[0].target) {
     	var min = new Ext.form.TextField({
	       fieldLabel: 'Minimum cardinality',
	       name: 'minSource',
	       id:'minSource',
	       value: this.figure.ends[0].getMin(),
	       regex: /(\d+|\*)/
	     });
	     
	    var max = new Ext.form.TextField({
	       fieldLabel: 'Maximum cardinality',
	       name: 'maxSource',
	       id:'maxSource',
	       value: this.figure.ends[0].getMax(),
	       regex: /(\d+|\*)/
	     });
     	
	     source = new Ext.form.FieldSet({
	       title: this.figure.ends[0].target.getEntityName(),
	       collapsible: true,
	       autoHeight:true,
	       items: [min,max],
	       labelWidth: 150
	     });
     }
     
     var target = null;
     if (this.figure.ends[1].target) {
     	var min = new Ext.form.TextField({
	       fieldLabel: 'Minimum cardinality',
	       name: 'minTarget',
	       id:'minTarget',
	       value: this.figure.ends[1].getMin(),
	       regex: /(\d+|\*)/
	     });
	     
	    var max = new Ext.form.TextField({
	       fieldLabel: 'Maximum cardinality',
	       name: 'maxTarget',
	       id:'maxTarget',
	       value: this.figure.ends[1].getMax(),
	       regex: /(\d+|\*)/
	     });
       var target = new Ext.form.FieldSet({
         title: this.figure.ends[1].target.getEntityName(),
         collapsible: true,
         autoHeight:true,
         items: [min, max],
         labelWidth: 150
       });     	
     }

	var tab = new Ext.Panel({
	  title:'RelationShip',
      layout:'fit',
      items: [general,source,target]
	});

   tabs.insert(0,tab);
    
    return tabs;
}
