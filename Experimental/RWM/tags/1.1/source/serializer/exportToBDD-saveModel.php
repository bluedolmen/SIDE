<!--
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
-->
<html>
<head/>
<body>
  <?
    $newId = null;

    require("../common/access_bdd.php");

    $connect = mysql_connect($host,$user,$passwd);
    mysql_select_db($bdd);
    
    $name = $_POST["name"];
    $description = $_POST["description"];
    $xmi = $_POST["xmi"];    
    
    $sql = "INSERT INTO models(name,description) VALUES (\"$name\",\"$description\");";
    mysql_query($sql);
    $idModel = mysql_insert_id();
    
    $sql = "INSERT INTO versions(majorVersion, minorVersion, comment, idModel, value) VALUES (1,0,\"Initial import\",$idModel,'$xmi');";
    mysql_query($sql);
    $id = mysql_insert_id();
  ?>
   <script>
     document.getElementById("paintarea").setAttribute("idModel","<? echo $id;?>");   
   </script>
   <center>
   <a class="nyroModalClose">
     <? echo "New model [1.0] saved. <br/> Click here to close."; ?>
   </a>
   </center>
  <?
    mysql_close($connect);
  ?>
</body>
</html>
