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

draw2d.RWMDialog=function(/*Figure:*/ fig) {
	draw2d.Dialog.call(this);
	
	if (fig) {
		this.figure = fig;
		this.dialogId = "dialog-"+this.figure.id;
		this.win.id = this.dialogId;
		this.tabsId = "tabs-dialog"+this.figure.id;
	}
	return this;
}

draw2d.RWMDialog.prototype = new draw2d.Dialog;
draw2d.RWMDialog.prototype.type = "RWMDialog";

draw2d.RWMDialog.prototype.createHTMLElement=function()
{
	var win = document.createElement("div");
	this.win = win;
    return win;
}

draw2d.RWMDialog.prototype.createTab=function() {
     var identifier = new Ext.form.TextField({
       fieldLabel: 'Identifier',
       name: 'identifier',
       value: this.figure.id,
       readOnly: true,
       enabled:false
     });
     identifier.setWidth(200);

    var documentation = new Ext.form.HtmlEditor({
        id:'documentation',
        fieldLabel:'Documentation',
        value: this.figure.documentation
     });

	var tabs = new Ext.TabPanel({
        border:false,
        defaults:{bodyStyle:'padding:10px'}, 
        activeTab: 0,
            items:[{
                title:'Information',
                layout:'form',
                items: identifier
            },
            {
            	cls:'x-plain',
                title:'Documentation',
                layout:'fit',
                items: documentation
            }]
        });
        
    return tabs;
}
 
draw2d.RWMDialog.prototype.show=function()
{
//	if (this.tabs == null)
// 	  this.tabs = this.createTab();
    var tabs = this.createTab();
    var oThis = this;
	
     win = new Ext.Window({
                el:this.dialogId,
                id:this.dialogId,
                layout:'fit',
                width:600,
                height:400,
                modal:true,
                resizable:false,
                closeAction:'close',
                shadow:false,
                frame:true,
               
            buttons: [{
                    text:'Submit',
                    handler:function(){
                    	var properties = win.figure.getProperties();
                    	if (properties != null && win.figure.setProperty != null) {
                    		while (properties.length > 0) {
                    			var p = properties.pop();
                    			var field = win.findById(p);
                    			if (field.getCustomValue)
                    			  win.figure.setProperty(p,field.getCustomValue());
                    			else
                    			  win.figure.setProperty(p,field.getValue());
                    		}
                    	}
                    	//Documentation
                    	var field = win.findById("documentation");
                    	win.figure.setProperty("documentation",field.getValue());
                    	//Close window
                    	var closeButton = null;
                    	for (i=0; i<win.buttons.length;i++) {
                    		var b = win.buttons[i];
                    		if (b.text == 'Close')
                    		  closeButton = b; 
                    	}
                    	if (closeButton != null)
                    		closeButton.handler.call();
                    }
                },{
                    text: 'Close',
                    handler: function(){
                        win.close();
                        workflow.removeDialogFromFigure(oThis);
                    }
                }]
            });
     win.figure = this.figure;
     win.addListener('close', function removeDialog() {
     	workflow.removeDialogFromFigure(oThis);
     })
     win.add(tabs);
     
      	//Set name
	if (this.figure.toShortString)
	  win.setTitle(this.figure.toShortString());
 
    win.show();
    win.el.setStyle("zIndex","11000");
   }
