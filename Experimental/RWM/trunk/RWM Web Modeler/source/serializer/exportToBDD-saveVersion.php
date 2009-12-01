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
    
    $id = $_POST["idVersionModel"];
    $comment = $_POST["comment"];
    $xmi = $_POST["xmi"];
    $major = false;
    if ($_POST["major"] && $_POST["major"] == "on")
      $major = true;
    
    $idModel = "0";
    
    $sql = "SELECT idModel FROM versions WHERE id=$id";
    $result = mysql_query($sql);
    if ($row = mysql_fetch_assoc($result))
      $idModel = $row['idModel'];
      
    $sql = "SELECT id FROM versions WHERE idModel=$idModel AND majorVersion=(SELECT MAX(majorVersion) FROM versions WHERE idModel=$idModel)".
      " AND minorVersion=(SELECT MAX(minorVersion) FROM versions WHERE idModel=$idModel AND majorVersion=(SELECT MAX(majorVersion) FROM versions WHERE idModel=$idModel))";
    
    $result = mysql_query($sql);
    if ($row = mysql_fetch_assoc($result))
      $id = $row['id'];
    
    if ($major == true)
      $sql = "SELECT majorVersion+1 as major, 0 as minor, idModel FROM versions WHERE id=$id";
    else
      $sql = "SELECT majorVersion as major, minorVersion+1 as minor, idModel FROM versions WHERE id=$id";

    $result = mysql_query($sql);
    if ($result) {
      if ($row = mysql_fetch_assoc($result)) {
        $minorVersion = $row['minor'];
        $majorVersion = $row['major'];
        $idModel = $row['idModel'];
      }
        
      $sql = "INSERT INTO versions(majorVersion, minorVersion, comment, idModel, value) VALUES ($majorVersion,$minorVersion,\"$comment\",$idModel,'$xmi');";
      mysql_query($sql);
      
      $id = mysql_insert_id();
  ?>
   <script>
     document.getElementById("paintarea").setAttribute("idModel","<? echo $id;?>");   
   </script>
   <center>
   <a class="nyroModalClose">
     <? echo "New version [$majorVersion.$minorVersion] saved. <br/> Click here to close."; ?>
   </a>
   </center>
  <?
    }
    mysql_close($connect);
  ?>
</body>
</html>
