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

draw2d.PrivilegeDialog=function(/*Figure:*/ fig) {
	this.figure = fig;
	draw2d.RWMDialog.call(this, fig);
	return this;
}

draw2d.PrivilegeDialog.prototype = new draw2d.RWMDialog;
draw2d.PrivilegeDialog.prototype.type = "PrivilegeDialog";

createJson=function(/*:Entity*/ e, /*:Array*/ json, /*:Ext.grid.EditorGridPanel*/ grid) {
  if (e && (grid==null || grid.store.find("objectId",e.id) == -1)) {
  	json.push([e,e.id,e.getEntityName(),null,e.id,e.getEntityName(),false,false,false,false,true,false,null]);
      
    if (e.attributes) {
      for (var i=0; i<e.attributes.length; ++i)
        json.push([e.attributes[i],e.attributes[i].id,e.getEntityName() + " > " + e.attributes[i].getName(),e,e.id,e.getEntityName(),false,false,false,false,false,false,null]);
    }
        
    var relation = e.getRelationShip();
    for (var i=0; i<relation.length; ++i) {
      r = relation[i];
      if (grid==null || grid.store.find("objectId",r.id) == -1) {
        var target = r.getOtherTarget(e);
        json.push([r,r.id,e.getEntityName() + " > " + r.getAssociationName(),e,e.id,e.getEntityName(),false,false,false,false,false,true,target.id]);
      }
    }
  }
}

draw2d.PrivilegeDialog.prototype.createTab=function() {
	var tabs = draw2d.RWMDialog.prototype.createTab.call(this);
	
	var xg = Ext.grid;

    var json = [];
    var finded = false
    for (var i=0; i<this.figure.privileges.length; ++i) {
    	var p = this.figure.privileges[i];
    	if (p.object && p.object instanceof draw2d.EntityFigure) {
    	  createJson(p.object,json,null);
    	  finded = true;
    	}
    }   
	
	if (!finded)
	  createJson(this.figure.getEntity(),json,null);
	
	// shared reader
    var reader = new Ext.data.ArrayReader({}, [
       {name: 'object'},
       {name: 'objectId'},
       {name: 'objectName'},
       {name: 'objectOwner'},
       {name: 'objectOwnerId'},
       {name: 'objectOwnerName'},
       {name: 'create', type: 'bool'},
       {name: 'read', type: 'bool'},
       {name: 'update', type: 'bool'},
       {name: 'delete', type: 'bool'},
       {name: 'isEntity', type: 'bool'},
       {name: 'isRelation', type: 'bool'},
       {name: 'linkedEntityId'}
    ]);
    
    Ext.grid.CheckColumn = function(config){
    Ext.apply(this, config);
    if(!this.id){
        this.id = Ext.id();
    }
    this.renderer = this.renderer.createDelegate(this);
};

Ext.grid.CheckColumn.prototype ={
    init : function(grid){
        this.grid = grid;
        this.grid.on('render', function(){
            var view = this.grid.getView();
            view.mainBody.on('mousedown', this.onMouseDown, this);
        }, this);
    },

    onMouseDown : function(e, t){
        if(t.className && t.className.indexOf('x-grid3-cc-'+this.id) != -1){
            e.stopEvent();
            var index = this.grid.getView().findRowIndex(t);
            var record = this.grid.store.getAt(index);
            var newValue = !record.data[this.dataIndex];
            record.set(this.dataIndex, newValue);
            //Add new entity
            if (newValue && record.data.isRelation) {
              var entity = record.data.object.getOtherTarget(record.data.objectOwner);
              if (entity) {
              	var json = [];
              	var records = [];
                createJson(entity,json,this.grid);
                this.grid.store.add(reader.readRecords(json).records);
                index = this.grid.store.find("objectId",entity.id);
                if (index>-1) {
                  record = this.grid.store.getAt(index);
                  record.set("read",true);
                }
              }
            } else if (!newValue && record.data.isRelation) {
              var e = record.data.object.getOtherTarget(record.data.objectOwner);
              if (e) {
            	var nb = 0;
            	var cont = true;
            	var lastIndex = null;
            	
            	while (cont) {
                  var index = null;
            	  if (lastIndex)
            	    index = this.grid.store.find("linkedEntityId",e.id,lastIndex+1);
            	  else
            	    index = this.grid.store.find("linkedEntityId",e.id);
            	    
            	  if (lastIndex)
            	    if  (index > lastIndex)
            	      lastIndex = index;
            	    else
              	      lastIndex = -1;
            	  else
            	    lastIndex = index;
            	    
            	  if (lastIndex > -1) {
            	  	var record = this.grid.store.getAt(lastIndex);
            	  	if (record.data.read || record.data.update)
            	  	  nb += 1;
            	  }
            	  
            	  cont = (lastIndex > -1) && (nb < 2);
            	}
            	
				if (nb == 0) {
				  if (window.confirm('Do you want really to delete privilege on '+e.getEntityName()+' ?')) {
				    var cont = true;
				    var lastIndex = null;
				    while (cont) {
			          lastIndex = this.grid.store.find("objectOwnerId",e.id);
				  	  if (lastIndex > -1)
				  	    this.grid.store.remove(this.grid.store.getAt(lastIndex));
				  	  cont = (lastIndex > -1);
				    }
				  } else {
				  	var index = this.grid.getView().findRowIndex(t);
                    var record = this.grid.store.getAt(index);
				    record.set(this.dataIndex, !newValue);
				  }
			    }            	  
              }
            }
         }
    },

    renderer : function(v, p, record){
    	if (record.data.isEntity || this.dataIndex == 'read' || this.dataIndex == 'update') {
          p.css += ' x-grid3-check-col-td'; 
          return '<div class="x-grid3-check-col'+(v?'-on':'')+' x-grid3-cc-'+this.id+'">&#160;</div>';
    	} else
    	  return "";
    }
};

   var createCheckColumn = new Ext.grid.CheckColumn({header: "Create", width: 20, dataIndex: 'create'});
   var readCheckColumn = new Ext.grid.CheckColumn({header: "Read", width: 20, dataIndex: 'read'});
   var updateCheckColumn = new Ext.grid.CheckColumn({header: "Update", width: 20, dataIndex: 'update'});
   var deleteCheckColumn = new Ext.grid.CheckColumn({header: "Delete", width: 20, dataIndex: 'delete'});

   var myStore = new Ext.data.GroupingStore({
            reader: reader,
            data: json,
            sortInfo:{field: 'objectName', direction: "ASC"},
            groupField:'objectOwnerName'
        });
    
    var refreshDataFromModel = function(/*:Ext.data.Record*/ record, /*:draw2d.PrivilegeConnection*/ c) {
    	if (c) {
    		var p = c.getPrivilege(record.data.objectId);
    		if (p) {
    			record.data.create = p.create;
    			record.data.read = p.read;
    			record.data.update = p.update;
    			record.data.del = p.del;
    		}
    	}
    };
    
    myStore.addListener("add", function(/*:Store*/ store, /*:Ext.data.Record[]*/ records, /*:Number*/ index ) {
    	for (var i = 0; i < records.length; ++i) {
    		refreshDataFromModel(records[i], this.figure);
    	}
    });

    var grid = new xg.EditorGridPanel({
        store: myStore,

        columns: [
            {id:'objectName',header: "Object Name", width: 80, sortable: true, dataIndex: 'objectName'},
            {id:'objectOwnerName',header: "Object Owner Name", width: 80, sortable: true, dataIndex: 'objectOwnerName', hidden: true},
            createCheckColumn,
            readCheckColumn,
            updateCheckColumn,
            deleteCheckColumn
        ],

        view: new Ext.grid.GroupingView({
            forceFit:true,
            enableGroupingMenu: false,
            groupTextTpl: '{text} ({[values.rs.length]} {[values.rs.length > 1 ? "Items" : "Item"]})'
        }),

        frame:true,
        title: 'Privilege',
        id: 'privileges',
        plugins:[createCheckColumn,
            readCheckColumn,
            updateCheckColumn,
            deleteCheckColumn],
        clicksToEdit:1
    });
   grid.createJson = this.createJson;
   grid.getCustomValue = function() {
   	var privileges = new Array();
   	var size = this.store.data.length;
   	for (var i=0; i<size; ++i) {
   	  var record = this.store.getAt(i);
   	  if (record.data.object) {
   	  	if (record.data.object instanceof draw2d.EntityFigure && (record.data.create || record.data.read || record.data.update || record.data.del)) {
   	      var p = new draw2d.Privilege(record.data.object,record.data.create,record.data.read,record.data.update,record.data.del);
   	      privileges.push(p); 
   	  	} else if (!(record.data.object instanceof draw2d.EntityFigure) && (record.data.read || record.data.update)) {
   	      var p = new draw2d.Privilege(record.data.object,false,record.data.read,record.data.update,false);
   	      privileges.push(p); 
   	  	}
   	  }
   	}
   	return privileges;
   }

   for (var i = 0; i < myStore.getCount(); ++i) {
    refreshDataFromModel(myStore.getAt(i),this.figure);
   }

   tabs.insert(0,grid);
    
    return tabs;
}

