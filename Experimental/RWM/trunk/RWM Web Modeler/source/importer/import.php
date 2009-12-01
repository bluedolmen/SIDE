<?
/***
   RWM : Requirements Web Modeler
   Copyright (C) 2008 (BlueXML / IRCCyN)

   This program is free software; you can redistribute it and/or
   modify it under the terms of the GNU General Public License
   as published by the Free Software Foundation; either version 2
   of the License, or (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program; if not, write to the Free Software
   Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
***/
?>
<html>
<head>
</head>
<body>
<?
	if (isset($_POST['import'])) {
		if ($_FILES['model']['error'] > 0) {
			echo "Erreur lors du tranfsert (".$_FILES['model']['error'].")";
		} else {
    		require("../common/access_bdd.php");
    		$connect = mysql_connect($host,$user,$passwd);
		    mysql_select_db($bdd);
			
			//Save data
			$fileName = $_FILES['model']['tmp_name'];	
			$file = fopen($fileName,"r"); 
			while(!feof($file))
			{
		  		$contents .= fgets($file);
			}
			
			$sql = "INSERT INTO models (name) VALUES (\"".$_POST['name']."\");";
			mysql_query($sql);
			$idModel = mysql_insert_id();
			
			$contents = str_replace("\"","\\\"",$contents);
			$sql = "INSERT INTO versions (value,idModel,minorVersion,majorVersion,comment) VALUES (\"$contents\",$idModel,0,1,\"Initial import\");";
			mysql_query($sql);
			
			fclose($file);
?>
	<script language="Javascript">
		$.nyroModalRemove();
	</script>
<?
		}
	} else {
?>
		<form method="post" action="importer/import.php" class="nyroModal" enctype="multipart/form-data">
			<input type="hidden" name="import" value="1"/>
			<input type="hidden" name="MAX_FILE_SIZE" value="1000000" />
			<table cellspacing=5 border=0 width="100%">
			
			<tr>
				<th>Name :</th>
				<td><input type="text" name="name" size="50"/></td>
			</tr>
			
			<tr>
				<td>File :</td>
				<td><input type="file" name="model" size="50"/></td>
			</tr>
			
			<tr>
				<td colspan=2 align='center'><input type="submit" value="Importer"/></td>
			</tr>
			
			</table>
		</form>
<?
	}
?>
<body>
</html>