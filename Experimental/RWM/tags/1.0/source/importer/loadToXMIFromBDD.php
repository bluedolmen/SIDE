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
    header("Content-Type: text/xml");
    require("../common/access_bdd.php");
    $connect = mysql_connect($host,$user,$passwd);
    mysql_select_db($bdd);
    if ($_GET["id"]) {
      $idModel = $_GET["id"];     	
      $sql = "SELECT value FROM versions WHERE id=$idModel";
      $result = mysql_query($sql);
      if ($result) {
        $row = mysql_fetch_assoc($result);
        if ($row['value'] != null)
          $xmi = $row['value'];
      }
      echo $xmi;
    }
    mysql_close($connect);
?>
