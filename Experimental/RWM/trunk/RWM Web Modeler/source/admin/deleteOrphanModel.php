<?
	require("../common/access_bdd.php");
	$idModel = $_GET['id'];
	$sql = "delete from models where not(id in (select idModel from versions));";
	$connect = mysql_connect($host,$user,$passwd);
	mysql_select_db($bdd);
	$result = mysql_query($sql);
	header('Location: index.php');
?>