<html>
	<head>
		<title>Composant ERP</title>
		<link rel="stylesheet" type="text/css" href="css/main.css">
	</head>
	<body>
		<? 
			require("./mysql/mysql_util.php");
		?>
		<div id="data">
			<h1>Composant ERP</h1>
				<? if (isset($_GET["ComposantERP"])) { ?>	
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
									." description,reffournisseur,refinterne,fournisseur"
									.","
									." id_cVQ46PIOKe"
								    ." FROM ComposantERP"
									." WHERE id_cVQ46PIOKe=$idObject;";
								$result = mysql_query($sql);
							 	$data = mysql_fetch_array($result);
							 }
						?>
							<tr>
								<td class="property_name">description</td>
								<td><input 
										type="text" 
										name="description"
										<? if (isset($_GET["idObject"])) {?>
										value="<? echo $data['description']?>"
										<? } ?>
									/></td>
							</tr>
							<tr>
								<td class="property_name">ref fournisseur</td>
								<td><input 
										type="text" 
										name="reffournisseur"
										<? if (isset($_GET["idObject"])) {?>
										value="<? echo $data['reffournisseur']?>"
										<? } ?>
									/></td>
							</tr>
							<tr>
								<td class="property_name">ref interne</td>
								<td><input 
										type="text" 
										name="refinterne"
										<? if (isset($_GET["idObject"])) {?>
										value="<? echo $data['refinterne']?>"
										<? } ?>
									/></td>
							</tr>
							<tr>
								<td class="property_name">fournisseur</td>
								<td><input 
										type="text" 
										name="fournisseur"
										<? if (isset($_GET["idObject"])) {?>
										value="<? echo $data['fournisseur']?>"
										<? } ?>
									/></td>
							</tr>
						<?
							$allJoinTable = array(); 
						?>
						</table>
						<input type="hidden" name="table_name" value="ComposantERP"/>
						<input type="hidden" name="table_fields" value="description;reffournisseur;refinterne;fournisseur"/>
						<input type="hidden" name="table_relationships" value="<? echo implode(';',$allJoinTable)?>"/>
						<input type="hidden" name="idFieldName" value="id_cVQ46PIOKe"/>
						<? if (isset($_GET["idObject"])) {?>
							<input type="hidden" name="updateObject" value="<? echo $data['id_cVQ46PIOKe']?>"/>
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