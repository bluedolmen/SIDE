<?
	require("../common/access_bdd.php");
	$idVersion = $_GET['id'];
	$sql = "DELETE FROM versions WHERE id = $idVersion";
	$connect = mysql_connect($host,$user,$passwd);
	mysql_select_db($bdd);
	$result = mysql_query($sql);
	header('Location: index.php');
?>