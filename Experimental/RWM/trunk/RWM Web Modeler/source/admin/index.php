<?
	require("../common/access_bdd.php");
	$connect = mysql_connect($host,$user,$passwd);
	mysql_select_db($bdd);
?>
<html>
<head>
	<link rel="stylesheet" href="main.css">
	<title>Administration</title>
</head>
<body>
<h1>Administration des mod&egrave;les</h1>

<?
	$sql = "select count(*) as nb from versions where (select COUNT(*) = 0 from models where id = idModel);";
	$result = mysql_query($sql);
	$row = mysql_fetch_assoc($result);
	$nb = $row['nb'];
?>
<u> Nombre de versions de mod&egrave;le orphelines : <?echo $nb;?> (<a href="./deleteOrphanVersion.php">delete</a>)</u><br/>

<?
	$sql = "select count(*) as nb from models m where (select COUNT(*) = 0 from versions where idModel = m.id);";
	$result = mysql_query($sql);
	$row = mysql_fetch_assoc($result);
	$nb = $row['nb'];
?>
<u> Nombre de mod&egrave;les orphelins : <?echo $nb;?> (<a href="./deleteOrphanModel.php">delete</a>)</u>

<br/>
<br/>
<table>
<tr>
	<th>id</th>
	<th>nom</th>
	<th>description</th>
	<th></th>
</tr>
<?
	$sql = "SELECT * FROM models;";
	$result = mysql_query($sql);
	while ($row = mysql_fetch_assoc($result)) {
		echo "<tr>";
		echo "<td rowspan=2>".$row['id']."</td>";
		echo "<td>".$row['name']."</td>";
		echo "<td>".$row['description']."</td>";
		echo "<td rowspan=2><a href=\"./deleteModel.php?id=".$row['id']."\"><img width=25 src='./pictures/delete.png'/></td>";
		echo "</tr>";
		echo "<tr>";
		echo "<td colspan=2>";
		//Liste des versions
		$idModel = $row['id'];
?>
		<table>
		<tr>
			<th>id</th>
			<th>version</th>
			<th>commentaire</th>
			<th></th>
		</tr>
<?
		$sql = "SELECT * FROM versions WHERE idModel=$idModel;";
		$result2 = mysql_query($sql);
		while ($row2 = mysql_fetch_assoc($result2)) {
			echo "<tr>";
			echo "<td width='50px'>".$row2['id']."</td>";
			echo "<td width='50px'>".$row2['majorVersion'].".".$row2['minorVersion']."</td>";
			echo "<td width='300px'>".$row2['comment']."</td>";
			echo "<td width='50px'><a href=\"./deleteVersion.php?id=".$row2['id']."\"><img width=15 src='./pictures/delete.png'/></a></td>";
			echo "</tr>";
		}
?>
		</table>
<?
		
		echo "</td>";
		echo "</tr>";
	}
?>
</table>
</body>
</html>