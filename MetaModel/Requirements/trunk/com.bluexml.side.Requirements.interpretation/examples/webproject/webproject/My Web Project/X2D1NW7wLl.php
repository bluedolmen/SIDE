<html>
	<head>
		<title>Changement des params</title>
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
			<h1>Changement des params</h1>
					<? if (isset($_GET['viewNomenclature'])) { ?>
					<div id="component">
						<div class="component_title">
						<h2>Nomenclature</h2>
						</div>
								<table>
							<tr>
							</tr>
							<?
									$nbPreviousTable = "Nomenclature";
								
								if (isset($pkPreviousTable)) {
									$sql = "SELECT "
										." "
										." id_7Sarf3VL5T"
										." FROM Nomenclature as s"
										." WHERE ("
										."     SELECT COUNT(*)"
										."     WHERE t.id_7Sarf3VL5T = s.id_7Sarf3VL5T"
										."     AND t.$pkPreviousTable = \"".$_GET["id$nbPreviousTable"]."\") > 0;";
								} else {
									$sql = "SELECT "
										." "
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