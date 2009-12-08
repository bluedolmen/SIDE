<?
	require("mysql_config.php");
	
	/*foreach($_POST as $key=>$value) {
		echo "$key=>$value<br/>";
	}*/
	
	if ($_POST["createObject"]) {
		if (strlen($_POST["table_fields"]) == 0) {
			$fields = array();
		} else {
			$fields = split(";",$_POST["table_fields"]);
		}
		$table_name = $_POST["table_name"];
		
		$sql = "INSERT INTO $table_name(";

		foreach ($fields as $field_name) {
			$sql .= "$field_name,";
		}
		
		//Delete the last comma
		if (strrpos($sql,",") == strlen($sql)-1)
			$sql = substr($sql,0,strlen($sql)-1);
		
		$sql .= ") VALUES (";
		
		foreach ($fields as $field_name) {
			$sql .= "'$_POST[$field_name]',";
		}
		
		//Delete the last comma
		if (strrpos($sql,",") == strlen($sql)-1)
			$sql = substr($sql,0,strlen($sql)-1);

		$sql .= ");";

		$database = mysql_connect($host, $user, $password);  
		$db_instance = mysql_select_db ($db, $database) ;
		mysql_query ($sql) or die ('Error SQL !'.$sql.'<br />'.mysql_error());
		
		createAllRelationShips(mysql_insert_id());
		
		mysql_close();
	}
	
	if ($_POST["updateObject"]) {
		$fields = split(";",$_POST["table_fields"]);
		$table_name = $_POST["table_name"];
		
		$idObject = $_POST["updateObject"];
		
		$sql = "UPDATE $table_name SET ";

		foreach ($fields as $field_name) {
			$sql .= " $field_name = '$_POST[$field_name]',";
		}
		
		//Delete the last comma
		$sql = substr($sql,0,strlen($sql)-1);
		
		$sql .= " WHERE `".$_POST["idFieldName"]."` = $idObject;";

		$database = mysql_connect($host, $user, $password);  
		$db_instance = mysql_select_db ($db, $database) ;
		
		deleteAllRelationShips();
		createAllRelationShips($_POST["updateObject"]);
		
		if (strlen($_POST["table_fields"]) > 0) {
			mysql_query ($sql) or die ('Error SQL !'.$sql.'<br />'.mysql_error());
		}
		mysql_close();  
	}
	
	if ($_GET["deleteObject"]) {
		$table_name = $_GET["table_name"];
		$idObject = $_GET["idObject"];
		
		$sql = "DELETE FROM $table_name ";
		$sql .= " WHERE `".$_GET["field_name"]."` = $idObject;";

		$database = mysql_connect($host, $user, $password);  
		$db_instance = mysql_select_db ($db, $database) ;
		mysql_query ($sql) or die ('Error SQL !'.$sql.'<br />'.mysql_error());
		mysql_close();  
	}
	
	function deleteRelationShip($joinTable, $paramName, $paramValue) {
		if (strlen($paramValue) > 0) {
			//Delete links
			$sql = "DELETE FROM $joinTable ";
			$sql .= " WHERE `$paramName`=$paramValue;";
			mysql_query ($sql) or die ('Error SQL !'.$sql.'<br />'.mysql_error());
		}
	}
	
	function deleteAllRelationShips() {
		$relationships = explode(";",$_POST["table_relationships"]);
		foreach($relationships as $index=>$joinTable) {
			if (strlen($joinTable) > 0)
				deleteRelationShip($joinTable,$_POST["idFieldName"],$_POST["updateObject"]);
		}
	}
	
	function createRelationShip($joinTable, $paramName1, $paramValue1, $paramName2, $paramValue2) {
		//Create link
		$sql = "INSERT INTO $joinTable(`$paramName1`,`$paramName2`)";
		$sql .= " VALUES(\"$paramValue1\",\"$paramValue2\");";
		mysql_query ($sql) or die ('Error SQL !'.$sql.'<br />'.mysql_error());
	}
	
	function createAllRelationShips($idValue) {
		$relationships = explode(";",$_POST["table_relationships"]);
		foreach($relationships as $index=>$joinTable) {
			if (strlen($joinTable) > 0) {
				$multiple = $_POST["multiple__".$joinTable];
				foreach($_POST as $key=>$value) {
					if (substr_compare($joinTable,$key,0,strlen($joinTable)) == 0) {
						$selectName = $key;
					}
				}
				$idTarget = substr($selectName,strlen($joinTable)+2,strlen($selectName));
				$newValues = $_POST[$selectName];
				if (isset($newValues)) {
					foreach($newValues as $index2=>$paramValue) {
						if ($multiple == 0) {
							deleteRelationShip($joinTable,$idTarget,$paramValue);
						}
						createRelationShip($joinTable,$_POST["idFieldName"],$idValue,$idTarget,$paramValue);
					}
				}
			}
		}
	}
?>