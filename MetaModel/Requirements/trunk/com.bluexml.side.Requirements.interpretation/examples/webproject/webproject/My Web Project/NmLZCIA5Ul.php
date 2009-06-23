<html>
	<head>
		<title>Initialisation de la structure</title>
		<link rel="stylesheet" type="text/css" href="css/main.css">
	</head>
	<body>
		<? 
			require("./mysql/mysql_util.php");
			
			function appendParameter($url, $paramName, $paramValue) {
				$tmp = removeParameter($url,$paramName);
				$separator = "?";
				if (strpos($tmp,"?")!=false)
  					$separator = "&";
 				return $tmp . $separator . "$paramName=$paramValue";
			}
			
			function removeParameter($url, $paramName) {
 				$tmp = ereg_replace("$paramName=([a-zA-Z0-9])*","",$url);
 				$tmp = ereg_replace("\&\&","&",$tmp);
 				$tmp = ereg_replace("\?\&","?",$tmp);
 				return $tmp;
			}
		?>
		<div id="data">
			<h1>Initialisation de la structure</h1>
					<div id="component">
	<div class="component_title">
	<h2>Nomenclature ERP</h2>
	</div>
		<div class="component_create">
		<a href="edit_NomenclatureERP_NmLZCIA5Ul.php?viewNomenclatureERP=1&NomenclatureERP=1">Create a new <i>Nomenclature ERP</i></a>
		</div>
</div>
					<? if (isset($_GET['viewComposantERP'])) { ?>
					<div id="component">
						<div class="component_title">
						<h2>Composant ERP</h2>
						</div>
								<table>
							<tr>
								<th>Edit</th>
							</tr>
							<?
									$nbPreviousTable = "NomenclatureERP";
									$pkPreviousTable = "id_1e1eT4uesR";
								
								if (isset($pkPreviousTable)) {
									$sql = "SELECT "
										." "
										." id_cVQ46PIOKe"
										." FROM ComposantERP as s"
										." WHERE ("
										."     SELECT COUNT(*)"
										."     WHERE t.id_cVQ46PIOKe = s.id_cVQ46PIOKe"
										."     AND t.$pkPreviousTable = \"".$_GET["id$nbPreviousTable"]."\") > 0;";
								} else {
									$sql = "SELECT "
										." "
										." id_cVQ46PIOKe"
									    ." FROM ComposantERP;";
								}
								mysql_connect($host, $user, $password);
								mysql_select_db($db);
								
								$result = mysql_query($sql);
								while ($data = mysql_fetch_array($result) )
								{
									$tableName = "ComposantERP";
									if (isset($_GET["id".$tableName]) && $_GET["id".$tableName]==$data["id_cVQ46PIOKe"]) {
										echo "<tr class=\"line_selected\">\n";
									} else {
										echo "<tr>\n";
									};
							?>
									<td><a href="edit_ComposantERP_NmLZCIA5Ul.php?ComposantERP=1&idObject=<? echo $data['id_cVQ46PIOKe']; ?>">Edit</a></td>
								</tr>
							<?			
								}
								mysql_close();
							?>
						</table>
							<div class="component_create">
							<a href="edit_ComposantERP_NmLZCIA5Ul.php?viewComposantERP=1&ComposantERP=1">Create a new <i>Composant ERP</i></a>
							</div>
					</div>
					<? } ?>
		</div>
	</body>
</html>