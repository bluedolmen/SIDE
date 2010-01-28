<b>List of comments:</b><br/>

<?
	require("mysql/mysql_config.php");

	$elementId = $_GET["elementId"];
	
	$sql = 	"SELECT *".
			"FROM `annotation`".
			"WHERE elementId=\"$elementId\" ORDER BY id;";

	$database = mysql_connect($host, $user, $password);  
	$db_instance = mysql_select_db ($db, $database) ;
	$req = mysql_query($sql);
	while($data = mysql_fetch_assoc($req))
    {
    	$data['annotation'] = str_replace("\n","<br/>",$data['annotation']);
    	$data['comment'] = str_replace("\n","<br/>",$data['comment']);
	    echo '<b>['.$data['id'].']</b> Created by '.$data['author'].' <i>('.$data['date'].')</i> :<br/><b>Annotation :</b> '.$data['annotation'].'<br/><b>Comment :</b>'.$data['comment'].'<br/><br/>';
    }
?>