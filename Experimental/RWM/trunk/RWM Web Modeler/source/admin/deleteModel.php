<?
	require("../common/access_bdd.php");
	$idModel = $_GET['id'];
	$sql = "DELETE FROM versions WHERE idModel = $idModel";
	$connect = mysql_connect($host,$user,$passwd);
	mysql_select_db($bdd);
	$result = mysql_query($sql);
	
	$sql = "DELETE FROM models WHERE id = $idModel";
	$result = mysql_query($sql);
	
	header('Location: index.php');
?>