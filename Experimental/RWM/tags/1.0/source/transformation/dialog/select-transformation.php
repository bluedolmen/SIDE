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
  <head>
	<script src="../../library/mktree/mktree.js"></script>
	<link rel="stylesheet" type="text/css" href="../../library/mktree/mktree.css" />
  </head>
  <body style="font-family:tahoma,arial,helvetica;font-size:11px;">
  	<ul class="mktree">
  		<?
  			require("../../common/access_bdd.php");
  			$idModel = $_GET["id"];
  			$connect = mysql_connect($host,$user,$passwd);
    		mysql_select_db($bdd);
    		$sql = "SELECT * FROM transformations ORDER BY label;";
    		$result = mysql_query($sql); 
    		while($row = mysql_fetch_array($result)) {
    			echo "<li style=\"font-align:center;\">".$row['label'];
    			echo "<ul>";
    			echo "<li><i>Description : </i>".$row['description'];
    			echo "<li><a href=\"../exec_transformation.php?idTransformation=".$row['idTransformation']."&idModel=".$idModel."\">View the model</a>";
    			echo " / <a href=\"../exec_transformation.php?idTransformation=".$row['idTransformation']."&idModel=".$idModel."&download=true\">Download the model</a>";
    			echo "<li><i>Generator(s)</i>";
    			echo "<ul>";
    			$sql = "SELECT g.idGenerator, t.idTransformation, g.label, g.generationMustBeDownloaded FROM generator g, metamodels m, transformations t WHERE m.idMM = g.idMM AND t.idTargetMM = m.idMM AND t.idTransformation = ".$row['idTransformation'].";";
    			
    			$result2 = mysql_query($sql);
    			while($row2 = mysql_fetch_array($result2)) {
    				echo "<li><a href=\"../generation/exec_generation.php?idTransformation=".$row2['idTransformation']."&idGeneration=".$row2['idGenerator']."&idModel=".$idModel."\">".$row2["label"]."</a>";
    			}
    			echo "</ul>";
    			echo "</ul>";
    		}
  		?>
  	</ul>
  </body>
</html>