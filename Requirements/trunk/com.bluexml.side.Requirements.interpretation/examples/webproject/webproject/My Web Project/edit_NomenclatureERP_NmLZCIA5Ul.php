<html>
	<head>
		<title>Nomenclature ERP</title>
		<link rel="stylesheet" type="text/css" href="css/main.css">
	</head>
	<body>
		<? 
			require("./mysql/mysql_util.php");
		?>
		<div id="data">
			<h1>Nomenclature ERP</h1>
				<? if (isset($_GET["NomenclatureERP"])) { ?>	
					<div id="component_create">
						<?
							$fromUrl = $_SERVER['HTTP_REFERER'];
							if (strpos($fromUrl,'?')) {
								$param = substr($fromUrl,strpos($fromUrl,'?'),strlen($fromUrl)-1);
							} else {
								$param = "";
							}
							$url = "NmLZCIA5Ul.php".$param;
						?>
						<form method="POST" action="<? echo $url; ?>">
						<table class="table_edit">
						<?
							mysql_connect($host, $user, $password);
							mysql_select_db($db);
							if (isset($_GET["idObject"])) {
								$idObject = $_GET["idObject"];
								$sql = "SELECT "
									." eststable,quantitparcomposant"
									.","
									." id_1e1eT4uesR"
								    ." FROM NomenclatureERP"
									." WHERE id_1e1eT4uesR=$idObject;";
								$result = mysql_query($sql);
							 	$data = mysql_fetch_array($result);
							 }
						?>
							<tr>
								<td class="property_name">est stable</td>
								<td><input 
										type="text" 
										name="eststable"
										<? if (isset($_GET["idObject"])) {?>
										value="<? echo $data['eststable']?>"
										<? } ?>
									/></td>
							</tr>
							<tr>
								<td class="property_name">quantité par composant</td>
								<td><input 
										type="text" 
										name="quantitparcomposant"
										<? if (isset($_GET["idObject"])) {?>
										value="<? echo $data['quantitparcomposant']?>"
										<? } ?>
									/></td>
							</tr>
						<?
							$allJoinTable = array(); 
						?>
						</table>
						<input type="hidden" name="table_name" value="NomenclatureERP"/>
						<input type="hidden" name="table_fields" value="eststable;quantitparcomposant"/>
						<input type="hidden" name="table_relationships" value="<? echo implode(';',$allJoinTable)?>"/>
						<input type="hidden" name="idFieldName" value="id_1e1eT4uesR"/>
						<? if (isset($_GET["idObject"])) {?>
							<input type="hidden" name="updateObject" value="<? echo $data['id_1e1eT4uesR']?>"/>
							<input type="submit" value="Update"/>
						<? } else { ?>
							<input type="hidden" name="createObject" value="1"/>			
							<input type="submit" value="Create"/>
						<? } ?>			
						</form>
					</div>
				<? } ?>
		</div>
	</body>
</html>