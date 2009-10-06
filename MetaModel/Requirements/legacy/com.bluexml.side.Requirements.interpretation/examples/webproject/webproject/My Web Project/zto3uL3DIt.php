<html>
	<head>
		<title>Synchronization des nomenclatures</title>
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
			<h1>Synchronization des nomenclatures</h1>
					<div id="component">
	<div class="component_title">
	<h2>Nomenclature</h2>
	</div>
			<table>
		<tr>
			<th>quantité par composant</th>
		</tr>
		<?
				$nbPreviousTable = "Nomenclature";
			
			if (isset($pkPreviousTable)) {
				$sql = "SELECT "
					." quantitparcomposant"
					.","
					." id_7Sarf3VL5T"
					." FROM Nomenclature as s"
					." WHERE ("
					."     SELECT COUNT(*)"
					."     WHERE t.id_7Sarf3VL5T = s.id_7Sarf3VL5T"
					."     AND t.$pkPreviousTable = \"".$_GET["id$nbPreviousTable"]."\") > 0;";
			} else {
				$sql = "SELECT "
					." quantitparcomposant"
					.","
					." id_7Sarf3VL5T"
				    ." FROM Nomenclature;";
			}
			mysql_connect($host, $user, $password);
			mysql_select_db($db);
			
			$result = mysql_query($sql);
			while ($data = mysql_fetch_array($result) )
			{
				$tableName = "Nomenclature";
				if (isset($_GET["id".$tableName]) && $_GET["id".$tableName]==$data["id_7Sarf3VL5T"]) {
					echo "<tr class=\"line_selected\">\n";
				} else {
					echo "<tr>\n";
				};
		?>
						<td><? echo $data['quantitparcomposant']; ?></td>
			</tr>
		<?			
			}
			mysql_close();
		?>
	</table>
</div>
					<? if (isset($_GET['viewComposant'])) { ?>
					<div id="component">
						<div class="component_title">
						<h2>Composant</h2>
						</div>
								<table>
							<tr>
								<th>description</th>
								<th>fournisseur</th>
								<th>référence fournisseur</th>
								<th>référence interne</th>
							</tr>
							<?
									$nbPreviousTable = "Nomenclature";
									$pkPreviousTable = "id_7Sarf3VL5T";
								
								if (isset($pkPreviousTable)) {
									$sql = "SELECT "
										." description,fournisseur,rfrencefournisseur,rfrenceinterne"
										.","
										." id_T9OM7CuvcS"
										." FROM Composant as s"
										." WHERE ("
										."     SELECT COUNT(*)"
										."     WHERE t.id_T9OM7CuvcS = s.id_T9OM7CuvcS"
										."     AND t.$pkPreviousTable = \"".$_GET["id$nbPreviousTable"]."\") > 0;";
								} else {
									$sql = "SELECT "
										." description,fournisseur,rfrencefournisseur,rfrenceinterne"
										.","
										." id_T9OM7CuvcS"
									    ." FROM Composant;";
								}
								mysql_connect($host, $user, $password);
								mysql_select_db($db);
								
								$result = mysql_query($sql);
								while ($data = mysql_fetch_array($result) )
								{
									$tableName = "Composant";
									if (isset($_GET["id".$tableName]) && $_GET["id".$tableName]==$data["id_T9OM7CuvcS"]) {
										echo "<tr class=\"line_selected\">\n";
									} else {
										echo "<tr>\n";
									};
							?>
											<td><? echo $data['description']; ?></td>
											<td><? echo $data['fournisseur']; ?></td>
											<td><? echo $data['rfrencefournisseur']; ?></td>
											<td><? echo $data['rfrenceinterne']; ?></td>
								</tr>
							<?			
								}
								mysql_close();
							?>
						</table>
					</div>
					<? } ?>
		</div>
	</body>
</html>