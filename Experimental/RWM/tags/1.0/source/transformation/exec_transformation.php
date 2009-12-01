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

$ch = curl_init($server_url."/importer/loadToXMIFromBDD.php?id=".$_GET["idModel"]);
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

$cmd = "java -classpath atl-runtime.jar:lib/org.eclipse.core.runtime_3.3.100.v20070530.jar:lib/org.eclipse.emf.common_2.3.2.v200802051830.jar:lib/org.eclipse.emf.ecore_2.3.2.v200802051830.jar:lib/org.eclipse.emf.ecore.xmi_2.3.2.v200802051830.jar:lib/org.eclipse.equinox.common_3.3.0.v20070426.jar:lib/org.eclipse.gmt.tcs.injector_0.0.1.jar:lib/org.eclipse.m2m.atl.drivers.emf4atl_2.0.0.v200806101117.jar:lib/org.eclipse.m2m.atl.drivers.mdr4atl.jar:lib/org.eclipse.m2m.atl.engine.jar:lib/org.eclipse.m2m.atl.engine.vm_2.0.0.v200806101117.jar org.rwm.Main ".str_replace(" ","\ ",$tmp_in_model)." ".str_replace(" ","\ ",$tmp_out_model)." $mm $asm $mm_name";
exec($cmd);

$handle_out = fopen($tmp_out_model, "r");
while ($line = fgets($handle_out)) {
	echo $line;
}

unlink($tmp_in_model);
unlink($tmp_out_model);
?>