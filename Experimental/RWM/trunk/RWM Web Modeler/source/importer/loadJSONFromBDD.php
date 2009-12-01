[
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
    require("../common/access_bdd.php");
    	
    $connect = mysql_connect($host,$user,$passwd);
    mysql_select_db($bdd);
    
    if ($_POST["node"] == "source")
      $sql = 'SELECT CONCAT("source/",id) as id, name, "folder" as cls, "false" as leaf,'
      .' CONCAT("./importer/loadToFileFromBDD.php?idModel=",id) as href'
      .' FROM models;';
    else if (substr($_POST["node"],0,6) == "source") {
      $index = ord('/');
      $array = count_chars($_POST["node"], 1);
      if ($array[$index] == 1) {
        $id = $_POST["node"];
        $posIdModel = strpos($id,"/") + 1;
        $idModel = substr($id,$posIdModel,strlen($id)-$posIdModel);
        
        $sql = 'SELECT CONCAT(majorVersion,".x") as name, CONCAT("source/",idModel,"/",majorVersion) as id,'
          .' "folder" as cls, "false" as leaf, CONCAT("./importer/loadToFileFromBDD.php?idMajorVersion=",majorVersion,"&idMajorVersionModel=",idModel) as href'
          .' FROM versions WHERE idModel='.$idModel.' GROUP BY majorVersion ORDER BY majorVersion DESC;';
      } else {
        $id = $_POST["node"];
        $posIdModel = strpos($id,"/") + 1;
        $id = substr($id,$posIdModel,strlen($id)-$posIdModel);
        $posIdModel = strpos($id,"/");
        $idModel = substr($id,0,$posIdModel);
        $majorVersion = substr($id,$posIdModel+1,strlen($id)-$posIdModel-1);
             
        $sql = 'SELECT CONCAT(majorVersion,".",minorVersion) as name, CONCAT("source/",idModel,"/",majorVersion,"/",minorVersion) as id,'
          .' "file" as cls, "true" as leaf, CONCAT("./importer/loadToFileFromBDD.php?idVersion=",id) as href'
          .' FROM versions WHERE idModel='.$idModel.' AND majorVersion ='.$majorVersion.' ORDER BY majorVersion DESC, minorVersion DESC;';
      }
    }

    $result = mysql_query($sql);
    $firstRow = true;
    while ($row = mysql_fetch_assoc($result)) {
      if ($firstRow == false)
        echo ",";
      else
        $firstRow = false;
      echo '{"text":"'.$row["name"].'","id":"'.$row["id"].'","cls":"'.$row["cls"].'","leaf":'.$row["leaf"].',"href":"'.$row["href"].'"}';
    }
  ?>
]
