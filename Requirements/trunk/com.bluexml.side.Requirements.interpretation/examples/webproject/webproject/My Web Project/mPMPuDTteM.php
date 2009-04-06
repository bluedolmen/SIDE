<html>
	<head>
		<title>Impacter les modifs de l'ERP vers la CAO</title>
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
			<h1>Impacter les modifs de l'ERP vers la CAO</h1>
					<div id="component">
	<div class="component_title">
	<h2>Composant ERP</h2>
	</div>
			<table>
		<tr>
			<th>description</th>
			<th>ref fournisseur</th>
			<th>ref interne</th>
			<th>fournisseur</th>
		</tr>
		<?
				$nbPreviousTable = "ComposantERP";
			
			if (isset($pkPreviousTable)) {
				$sql = "SELECT "
					." description,reffournisseur,refinterne,fournisseur"
					.","
					." id_cVQ46PIOKe"
					." FROM ComposantERP as s"
					." WHERE ("
					."     SELECT COUNT(*)"
					."     WHERE t.id_cVQ46PIOKe = s.id_cVQ46PIOKe"
					."     AND t.$pkPreviousTable = \"".$_GET["id$nbPreviousTable"]."\") > 0;";
			} else {
				$sql = "SELECT "
					." description,reffournisseur,refinterne,fournisseur"
					.","
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
						<td><? echo $data['description']; ?></td>
						<td><? echo $data['reffournisseur']; ?></td>
						<td><? echo $data['refinterne']; ?></td>
						<td><? echo $data['fournisseur']; ?></td>
			</tr>
		<?			
			}
			mysql_close();
		?>
	</table>
</div>
		</div>
	</body>
</html>