<?
	require("../common/access_bdd.php");
	$idModel = $_GET['id'];
	$sql = "delete from versions where (select COUNT(*) = 0 from models m where m.id = idModel);";
	$connect = mysql_connect($host,$user,$passwd);
	mysql_select_db($bdd);
	$result = mysql_query($sql);
	header('Location: index.php');
?>