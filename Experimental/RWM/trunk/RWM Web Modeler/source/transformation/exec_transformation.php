<?php
header("Content-Type: text/xml");
if (isset($_GET["download"])) {
	header("Content-Disposition: attachment; filename=\"out.ecore\"");
}
require("../common/access_bdd.php");

$tmp_in_model = tempnam ("./out", "rwm_model_");
$tmp_out_model = tempnam ("./out", "out_model_");

$handle_in = fopen($tmp_in_model, "w");
chmod($tmp_in_model, 0666);
chmod($tmp_out_model, 0666);

if (isset($_GET["idVersion"])) {
	$idModel = $_GET["idVersion"];
} else {
	$connect = mysql_connect($host,$user,$passwd);
	mysql_select_db($bdd);
	
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
      
    $sql = "SELECT id FROM versions WHERE minorVersion=$minorVersion AND majorVersion=$majorVersion AND idModel=$idModel";
    $result = mysql_query($sql);
    if ($result) {
      $row = mysql_fetch_assoc($result);
      $idModel = $row['id'];
    }
}

$serverUrl = "http://".$_SERVER['SERVER_NAME'].$_SERVER['PHP_SELF'];
$serverUrl = substr($serverUrl,0, strrpos($serverUrl,"/"));

$ch = curl_init($serverUrl."/../importer/loadToXMIFromBDD.php?id=$idModel");
curl_setopt($ch, CURLOPT_FILE, $handle_in);
curl_setopt($ch, CURLOPT_HEADER, 0);
curl_exec($ch);
curl_close($ch);
fclose($handle_in);

//Get parameters of the transformation
$idTransformation = $_GET["idTransformation"];
$mm ="";
$mm_name ="";
$asm = "";
$sql = "SELECT m.mm_file as mm, m.name as mm_name, asm FROM transformations t, metamodels m WHERE t.idTargetMM = m.idMM AND idTransformation = $idTransformation;";
$connect = mysql_connect($host,$user,$passwd);
mysql_select_db($bdd);
$result = mysql_query($sql);
if ($result) {
	$row = mysql_fetch_assoc($result);
	$mm = $row['mm'];
	$mm_name = $row['mm_name'];
	$asm = $row['asm'];
}

//$cmd = "java -classpath atl-runtime.jar:lib/org.eclipse.core.runtime_3.3.100.v20070530.jar:lib/org.eclipse.emf.common_2.3.2.v200802051830.jar:lib/org.eclipse.emf.ecore_2.3.2.v200802051830.jar:lib/org.eclipse.emf.ecore.xmi_2.3.2.v200802051830.jar:lib/org.eclipse.equinox.common_3.3.0.v20070426.jar:lib/org.eclipse.gmt.tcs.injector_0.0.1.jar:lib/org.eclipse.m2m.atl.drivers.emf4atl_2.0.0.v200806101117.jar:lib/org.eclipse.m2m.atl.drivers.mdr4atl.jar:lib/org.eclipse.m2m.atl.engine.jar:lib/org.eclipse.m2m.atl.engine.vm_2.0.0.v200806101117.jar org.rwm.Main ".str_replace(" ","\ ",$tmp_in_model)." ".str_replace(" ","\ ",$tmp_out_model)." ./metamodels/$mm ./transformations/$asm $mm_name";

$cmd = "java -classpath atlcommandline.jar:lib/ACG-parser.jar:lib/emf4atl.jar:lib/org.eclipse.gmt.tcs.injector.jar:lib/ATL-parser.jar:lib/engine.jar:lib/org.eclipse.m2m.atl.jar:lib/antlr.jar:lib/equinox.common.jar:lib/osgi.jar:lib/common.jar:lib/equinox.registry.jar:lib/resources.jar:lib/dsls.jar:lib/mdr4atl.jar:lib/runtime.jar:lib/ecore.jar:lib/vm.jar:lib/ecore.xmi.jar:lib/org.eclipse.gmt.tcs.extractor.jar org.atl.commandline.Main --trans ./transformations/$asm --in IN=\"$tmp_in_model\" RWM=./metamodels/rwm/rwm.ecore EMF --out OUT=\"$tmp_out_model\" $mm_name=./metamodels/$mm EMF";

exec($cmd);

$handle_out = fopen($tmp_out_model, "r");
while ($line = fgets($handle_out)) {
	echo $line;
}

unlink($tmp_in_model);
unlink($tmp_out_model);
?>