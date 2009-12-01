<%--encoding=ISO-8859-1--%>
<%
metamodel http://www.w3.org/2002/xforms
%>
<%script type="xforms.XFormsDocumentRoot" name="createFile" file="output.xml"%>
<?xml version="1.0" encoding="ISO-8859-1"?>
<html
 xmlns="http://www.w3.org/1999/xhtml"
 xmlns:xf="http://www.w3.org/2002/xforms"
 xmlns:ev="http://www.w3.org/2001/xml-events"
 xmlns:xs="http://www.w3.org/2001/XMLSchema">
   <head>
      <title>Maquette des formulaires</title>
      <xf:model>
        <xf:instance xmlns="">
            <instance>
               <a_date/>
            </instance>
         </xf:instance>
        <xf:bind nodeset="a_date" type="xs:date"/>
      </xf:model>
      
            <style type="text/css">
      /* Feuille de style pour aligner les champs de saisie d'une XForms */
 
@namespace xf url("http://www.w3.org/2002/xforms");
 
/* impose une fonte sans empattement pour les libellés et les champs de saisie */
label, legend {
   font-family: Arial, Helvetica, sans-serif;
   font-weight: bold;
}
 
/* donne de l'air aux champs */
fieldset {
   padding: 5px;
   width: 400px;
}
 
/* Les libellés sont alignés à droite dans une colonne de 300 pixels de large */
xf|label {
   width: 300px;
   margin: 3px;
   text-align: right;
}
 
/* Les champs de saisie sont alignés à gauche dans une colonne */
xf|value {
   text-align: left;
}
 
/* Le groupe est affiché comme une table */
xf|group {
   display: table;
}
 
/* Chaque entrée est une ligne de la table */
xf|input {
   display: table-row;
}

xf|select1 {
   display: table-row;
}
 
/* Chaque libellé est une cellule de la ligne de saisie */
xf|input xf|label {
   display: table-cell;	
}

xf|select1 xf|label {
   display: table-cell;	
}
 
/* Chaque pseudo-élément value est aussi une cellule de ligne de saisie */
xf|input::value {
   display: table-cell;
}

xf|select1::value {
   display: table-cell;
}
</style>
   </head>
   <body>
      <%for (trigger){%>
      <xf:trigger>
        <xf:label><%label.text%></xf:label>
        <%for (toggle){%>
        <xf:toggle case="<%case%>" ev:event="<%event%>"/>
        <%}%>
      </xf:trigger>
      <%}%>
      <br />
      <br />
      
      <%for (switch){%>
      <xf:switch>
        <%for (case){%>
        <xf:case id="<%id%>">
          <%for (group){%>
          <xf:group>
            <fieldset>
              <legend><%label.nFirst().text%></legend>
            <%for (input){%>

            <%if (eClass().name == "InputType"){%>
            <xf:input <%if (cast("InputType").ref != null){%>ref="<%cast("InputType").ref%>"<%}%>>
              <xf:label><%label.text%></xf:label>
              <%for (cast("InputType").hint){%>
              <xf:hint><%text%></xf:hint>
              <%}%>
            </xf:input>
            <%}%>
              
            <%if (eClass().name == "SelectType"){%>
            <xf:select1 selection="closed">
              <xf:label><%label.text%></xf:label>
              <%for (cast("SelectType").item){%>
              <xf:item>
                <xf:label><%label.text%></xf:label>
              </xf:item>
              <%}%>
            </xf:select1>
            <%}%>
            
            <%if (eClass().name == "ButtonType"){%>
            <xf:button>
              <%for (cast("ButtonType").trigger){%>
              <xf:trigger>
                <xf:label><%label.text%></xf:label>
                <%for (toggle){%>
                <xf:toggle case="<%case%>" ev:event="<%event%>"/>
                <%}%>
              </xf:trigger>
              <%}%>
            </xf:button>
            <%}%>
            
            <%}%>
            </fieldset>
          </xf:group>
          <%}%>
        </xf:case>
        <%}%>
      </xf:switch>
      <%}%>
   </body>
</html>