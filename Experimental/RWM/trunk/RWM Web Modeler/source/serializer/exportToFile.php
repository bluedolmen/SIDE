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
$i = 0;
while ($i == 0 || file_exists($myFile) ) {
  $i++;
  $myFile = sprintf("output/saved_file_%02d.ecore.xml",$i);
}
$xmi = str_replace("\\\"","\"",$_POST["xmi"]);
echo $xmi;
$fh = fopen($myFile, 'w') or die("can't open file");
if ($xmi != null)
  fwrite($fh, $xmi);
fclose($fh);
?>
