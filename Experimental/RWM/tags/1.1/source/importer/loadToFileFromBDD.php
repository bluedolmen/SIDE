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
  <center>
  <div style="display:none;">
    <button class="nyroModalClose" id="closeButton">close button</button>
  </div>
  </center>
  <?
    require("../common/access_bdd.php");

    $connect = mysql_connect($host,$user,$passwd);
    mysql_select_db($bdd);
    
    $xmi = "";
    
    if ($_GET["idModel"]) {
      $idModel = $_GET["idModel"];     	
      
      $majorVersion = "1";
      $sql = "SELECT MAX(majorVersion) as max FROM versions WHERE idModel=$idModel";
      $result = mysql_query($sql);
      if ($result) {
        $row = mysql_fetch_assoc($result);
        if ($row['max'] != null)
          $majorVersion = $row['max'];
      }

      $minorVersion = "0";
      $sql = "SELECT MAX(minorVersion) as max FROM versions WHERE idModel=$idModel AND majorVersion=$majorVersion";
      $result = mysql_query($sql);
      if ($result) {
        $row = mysql_fetch_assoc($result);
        if ($row['max'] != null)
          $minorVersion = $row['max'];
      }
      
      $sql = "SELECT id, value FROM versions WHERE minorVersion=$minorVersion AND majorVersion=$majorVersion AND idModel=$idModel";
      $result = mysql_query($sql);
      if ($result) {
        $row = mysql_fetch_assoc($result);
        if ($row['value'] != null)
          $xmi = $row['value'];
        $idActualModel = $row['id'];
      }
    } if ($_GET["idMajorVersion"] && $_GET["idMajorVersionModel"]) {
      $idModel = $_GET["idMajorVersionModel"];
      $majorVersion = $_GET["idMajorVersion"];  	
      
      $minorVersion = "0";
      $sql = "SELECT MAX(minorVersion) as max FROM versions WHERE idModel=$idModel AND majorVersion=$majorVersion";
      $result = mysql_query($sql);
      if ($result) {
        $row = mysql_fetch_assoc($result);
        if ($row['max'] != null)
          $minorVersion = $row['max'];
      }
    
      $sql = "SELECT id, value FROM versions WHERE minorVersion=$minorVersion AND majorVersion=$majorVersion AND idModel=$idModel";
      $result = mysql_query($sql);
      if ($result) {
        $row = mysql_fetch_assoc($result);
        if ($row['value'] != null)
          $xmi = $row['value'];
        $idActualModel = $row['id'];
      }
    } else if ($_GET["idVersion"]) {
      $id = $_GET["idVersion"];
      $idActualModel = $id;   	
      
      $sql = "SELECT minorVersion, majorVersion, value FROM versions WHERE id=$id";
      $result = mysql_query($sql);
      if ($result) {
        $row = mysql_fetch_assoc($result);
        if ($row['value'] != null)
          $xmi = $row['value'];
        $minorVersion = $row['minorVersion'];
        $majorVersion = $row['majorVersion'];
      }
    }
   
    mysql_close($connect);
  ?>
   <center>
   <div id="closebtn"> 
     <a class="nyroModalClose" onclick="window.parent.loadModel('./importer/loadToXMIFromBDD.php?id=<?echo $idActualModel;?>','<?echo $idActualModel;?>');">
       <? echo "Version loaded [$majorVersion.$minorVersion] <br/> Click here to close."; ?>
     </a>
   </div>
  </center>
</body>
