<?
	require("mysql_config.php");

	$author = $_GET["author"];
	$annotation = $_GET["annotation"];
	$date = date("Y-m-d");
	$elementId = $_GET["elementId"]; 
	
	if ($annotation != null && strlen($annotation) > 0) {
		$sql = "INSERT INTO `annotation` (".
					"`elementId` ,".
					"`author` ,".
					"`annotation` ,".
					"`date`".
				") VALUES (".
					"'$elementId', '$author', '$annotation', '$date'".
				");";

		$database = mysql_connect($host, $user, $password);  
		$db_instance = mysql_select_db ($db, $database) ;
		if (mysql_query ($sql)) {
			?>
				Annotation saved!
			<?
			exit;
		} else {
			die ('Error SQL !'.$sql.'<br />'.mysql_error());
		}
		
	}
?>