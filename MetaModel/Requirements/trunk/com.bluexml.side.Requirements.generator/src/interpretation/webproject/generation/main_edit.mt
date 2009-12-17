<%
metamodel http://www.bluexml.com/rwm/webproject/1.0/
%>
<%script type="WebProject.Component" name="dataPage_component_update_namefile"%>
<%if ((canCreate)||(canUpdate)){%><%eContainer().eContainer().name%>/edit_<%table.name%>_<%eContainer().name%><%}%>
<%-- condition can_update or can_create --%>
<%script type="WebProject.Component" name="dataPage_component" file="<%dataPage_component_update_namefile%>"%>
<html>
	<head>
		<title><%name%></title>
		<link rel="stylesheet" type="text/css" href="css/main.css">
	</head>
	<body class="body">
		<? 
			require("./mysql/mysql_util.php");
		?>
		<div id="data" class="box">
			<h2><%name%></h2>
			<%dataPage_component_create_update%>
		</div>
	</body>
</html>
<%script type="WebProject.Component" name="dataPage_component_sqlQueryUpdate"%>
$idObject = $_GET["idObject"];
$sql = "SELECT "
	." `<%properties[eClass().name.equalsIgnoreCase("ComponentAttribute") && canUpdate].cast("ComponentAttribute").field.name.sep("`,`")%>`"
	<%if (properties[eClass().name.equalsIgnoreCase("ComponentAttribute") && canUpdate].nSize()> 0){%>
	.","
	<%}%>
	." `<%table.primaryKey.name.split("`,`")%>`"
    ." FROM <%table.name%>"
	." WHERE `<%table.primaryKey.nFirst().name%>`=$idObject;";
<%script type="WebProject.Component" name="dataPage_component_create_update"%>
	<? if (isset($_GET["<%table.name%>"])) { ?>	
		<div id="component_create">
			<?
				$fromUrl = $_SERVER['HTTP_REFERER'];
				if (strpos($fromUrl,'?')) {
					$param = substr($fromUrl,strpos($fromUrl,'?'),strlen($fromUrl)-1);
				} else {
					$param = "";
				}
				$url = "<%eContainer().name%>".$param;
			?>
			<form method="POST" action="<? echo $url; ?>">
			<table class="table_edit" class="list-table">
			<?
				mysql_connect($host, $user, $password);
				mysql_select_db($db);
				if (isset($_GET["idObject"])) {
					<%dataPage_component_sqlQueryUpdate%>
					$result = mysql_query($sql);
				 	$data = mysql_fetch_array($result);
				 }
			?>
			<%for (properties[eClass().name.equalsIgnoreCase("ComponentAttribute") && canUpdate]){%>
				<tr>
					<td class="property_name"><%name%></td>
					<td><input 
							type="text" 
							name="<%cast("ComponentAttribute").field.name%>"
							<? if (isset($_GET["idObject"])) {?>
							value="<? echo $data['<%cast("ComponentAttribute").field.name%>']?>"
							<? } ?>
						/></td>
				</tr>
			<%}%>
			<?
				$allJoinTable = array(); 
			?>
			<%for (properties[eClass().name.equalsIgnoreCase("ComponentRelationShip") && canUpdate
				&& !(current("Component").precedingSibling().table.nContains(cast("ComponentRelationShip").idRight.eContainer())
					|| current("Component").precedingSibling().table.nContains(cast("ComponentRelationShip").idLeft.eContainer()))
				]){%>
				<?
					<%if (current("Component").table == cast("ComponentRelationShip").idLeft.eContainer()){%>
					$idSource = "<%current("ComponentRelationShip").idLeft.name%>";
					$idTarget = "<%current("ComponentRelationShip").idRight.name%>";
					$tableSource = "<%current("ComponentRelationShip").idLeft.eContainer().name%>";
					$tableTarget = "<%current("ComponentRelationShip").idRight.eContainer().name%>";
					$attributes = "`<%current("ComponentRelationShip").idRight.eContainer().fields.name.sep("`,`")%>`";
						<%if (current("ComponentRelationShip").manyRight){%>
					$multiple = 1;
						<%}else{%>
					$multiple = 0;
						<%}%>
						<%if (current("ComponentRelationShip").mandatoryLeft){%>
					$manySource = 1;
						<%}else{%>
					$manySource = 0;
						<%}%>
					<%}else{%>
					$idSource = "<%current("ComponentRelationShip").idRight.name%>";
					$idTarget = "<%current("ComponentRelationShip").idLeft.name%>";
					$tableSource = "<%current("ComponentRelationShip").idRight.eContainer().name%>";
					$tableTarget = "<%current("ComponentRelationShip").idLeft.eContainer().name%>";
					$attributes = "`<%current("ComponentRelationShip").idLeft.eContainer().fields.name.sep("`,`")%>`";
						<%if (current("ComponentRelationShip").manyLeft){%>
					$multiple = 1;
						<%}else{%>
					$multiple = 0;
						<%}%>
						<%if (current("ComponentRelationShip").mandatoryRight){%>
					$manySource = 1;
						<%}else{%>
					$manySource = 0;
						<%}%>
					<%}%>
					$joinTable = "<%cast("ComponentRelationShip").idLeft.eContainer().name%>2<%cast("ComponentRelationShip").idRight.eContainer().name%>";
					$allJoinTable[] = $joinTable;
				?>
				<tr><td class="relationship_name" colspan="2">
					<%name%>
					</td>
				</tr>
				<tr><td colspan="2">
					<?
						$sql = "SELECT t.$attributes ";
						if (isset($_GET["idObject"])) {
							$sql .= " ,EXISTS(SELECT * FROM $joinTable j WHERE ";
							$sql .= " t.`$idTarget` = j.`$idTarget` AND j.`$idSource`=\"".$_GET["idObject"]."\" ) as selected";
						}
						$sql .= " FROM $tableTarget t;";
						$result = mysql_query($sql);
						$attributesA = split(",",$attributes);
					?>
					<select <? if ($multiple) echo "multiple"; ?> name="<?echo $joinTable."__".$idTarget;?>[]">
					<?
				 		while ($data2 = mysql_fetch_array($result)) {
				 			if ($data2['selected']) {
				 				echo "<option selected value=\"".$data2[$idTarget]."\">";
				 			} else {
				 				echo "<option value=\"".$data2[$idTarget]."\">";
				 			}
				 			$label = "";
				 			foreach($attributesA as $fieldName) {
							 	$fieldName = str_replace("`","",$fieldName);
				 				$label .= "($fieldName=".$data2[$fieldName].")";
				 			}
				 			if (strlen($label) > 85) {
				 				$label = substr($label,0,82);
				 				$label .= "...";
				 			}
				 			echo "$label\n";
				 		}
					?>
					<input type="hidden" name="<?echo "multiple__".$joinTable;?>" value="<?echo $manySource?>"/>
					</select>
					<%if (current("Component").table == cast("ComponentRelationShip").idLeft.eContainer()){%>
						<%cast("ComponentRelationShip").idRight.eContainer().push()%>
					<%}else{%>
						<%cast("ComponentRelationShip").idLeft.eContainer().push()%>
					<%}%>
					<%if (current("Component").followingSibling()[table == peek() && canCreate].nSize() > 0){%>
					<div class="createObject">
						<a href="edit_<%peek().name%>_<%current("Component").followingSibling()[table == peek() && canCreate].nFirst().eContainer().name%>?<%peek().name%>=1">
							Create a <i><%current("Component").followingSibling()[table == peek() && canCreate].nFirst().name%></i></a>
					</div>
					<%}%>
					<%pop()%>
				</td></tr>
			<%}%>	
			</table>
			<input type="hidden" name="table_name" value="<%table.name%>"/>
			<input type="hidden" name="table_fields" value="<%properties[canUpdate].cast("ComponentAttribute").field.name.sep(";")%>"/>
			<input type="hidden" name="table_relationships" value="<? echo implode(';',$allJoinTable)?>"/>
			<input type="hidden" name="idFieldName" value="<% table.primaryKey.nFirst().name %>"/>
			<? if (isset($_GET["idObject"])) {?>
				<input type="hidden" name="updateObject" value="<? echo $data['<% table.primaryKey.nFirst().name %>']?>"/>
				<input type="submit" value="Update"/>
			<? } else { ?>
				<input type="hidden" name="createObject" value="1"/>			
				<input type="submit" value="Create"/>
			<? } ?>			
			</form>
		</div>
	<? } ?>