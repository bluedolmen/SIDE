{
	"comments":[
<?
	require("mysql_config.php");

	$elementId = $_GET["elementId"];
	
	$sql = 	"SELECT *".
			"FROM `annotation`".
			"WHERE elementId=\"$elementId\" ORDER BY id;";

	$database = mysql_connect($host, $user, $password);  
	$db_instance = mysql_select_db ($db, $database) ;
	$req = mysql_query($sql);
	$first = true;
	while($data = mysql_fetch_assoc($req))
    {
    	$data['annotation'] = str_replace("\n","<br/>",$data['annotation']);
    	$data['comment'] = str_replace("\n","<br/>",$data['comment']);
		if (!$first)
			echo ',';
		echo '{'."\n";    	
    	echo '"id":"'.$data['id'].'",'."\n";
    	echo '"author":"'.$data['author'].'",'."\n";
    	echo '"date":"'.$data['date'].'",'."\n";
    	echo '"annotation":"'.$data['annotation'].'",'."\n";
    	echo '"comment":"'.$data['comment'].'"'."\n";
    	echo '}';
    	$first = false;
    }
?>
]
}