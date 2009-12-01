<!--
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
-->
<html>
<head/>
<body>
  <div id="choice">
    <form>
      <div id="saveModelXMI" style="visibility:hidden;">
        <textarea name="xmi" id="xmiExportFormValueTmp" style="overflow:hidden;"></textarea>
        <input type="text" id="idVersionModel" name="idVersionModel"/>
      </div>
    </form>
    <script>
      document.getElementById('xmiExportFormValueTmp').appendChild(document.getElementById('xmiExportFormValue').firstChild);
    </script>
  </div>
  
  <script>
    Ext.onReady(function(){
      var isMajorChange = new Ext.form.Checkbox({
        fieldLabel: 'Is a major change',
        name:'major',
        checked: false,
        id: 'majorVersion'
      });
      
      var comment = new Ext.form.TextArea({
        fieldLabel: 'Comment',
        id: 'comment',
        width:200
      });
      
      var saveNewVersionButton = new Ext.Button({
        text: 'Save as new version'
      });
    
      var saveNewVersion = new Ext.form.FieldSet({
       	title: 'Save as new version',
       	autoHeight:true,
       	collapsible: true,
       	labelWidth: 150,
       	hidden: !document.getElementById("paintarea").hasAttribute("idModel"),
        items: [isMajorChange,comment,saveNewVersionButton]        	
      });
    
      var name = new Ext.form.TextField({
        fieldLabel: 'Name',
        id: 'name',
        allowBlank: false,
        width:200
      });
    
      var description = new Ext.form.TextArea({
        fieldLabel: 'Description',
        id: 'description',
        width:200
      });
      
      var saveNewModelButton = new Ext.Button({
        text: 'Save as new model'
      });
    
      var saveNewModel = new Ext.form.FieldSet({
       	title: 'Save as new model',
       	autoHeight:true,
       	collapsible: true,
       	labelWidth: 150,
       	collapsed: document.getElementById("paintarea").hasAttribute("idModel"),
       	items: [name,description,saveNewModelButton]
      });
      
      var toggle = function(/*:Ext.panel*/ p) {
          if (p == saveNewVersion)
            if (p.collapsed)
              saveNewModel.expand();
            else
              saveNewModel.collapse();
          else
            if (p.collapsed)
              saveNewVersion.expand();
            else
              saveNewVersion.collapse();
      }
      
      saveNewVersion.addListener("collapse",toggle);
      saveNewVersion.addListener("expand",toggle);
      saveNewModel.addListener("collapse",toggle);
      saveNewModel.addListener("expand",toggle);
    
      var p = new Ext.form.FormPanel({
        el: 'choice',
        layout: 'fit',
        width: 400,
        height: 200,
        border: false,
        items: [saveNewVersion,saveNewModel]
      });
      
      var prepareSubmit = function() {
        p.getForm().getEl().dom.className += " nyroModal";

        var input = document.createElement("input");
        input.setAttribute("type","submit");
        input.id = p.getForm().getEl().id + "-submit";
        input.style.visibility = "hidden";
        p.getForm().getEl().appendChild(input);
        
        document.getElementById('idVersionModel').setAttribute("value",document.getElementById('paintarea').getAttribute("idModel"));
        
        p.getForm().getEl().appendChild(document.getElementById("saveModelXMI"));
        document.getElementById("saveModelXMI").appendChild(document.getElementById("idVersionModel"));
        
        $('#'+p.getForm().getEl().parent().id+' form').nyroModal({minHeight:30});
      };
      
      var submit = function() {
        if (saveNewVersion.collapsed || saveNewVersion.hidden)
          p.getForm().getEl().dom.action = "./serializer/exportToBDD-saveModel.php";
        else
          p.getForm().getEl().dom.action = "./serializer/exportToBDD-saveVersion.php";
      
        document.getElementById(p.getForm().getEl().id+"-submit").click();
      };
      
      p.addListener("render",prepareSubmit);
      saveNewVersionButton.addListener("click",submit);
      saveNewModelButton.addListener("click",submit);
      
      p.render();
    });
  </script>
  
</body>
