<?php
require("../../common/access_bdd.php");
include("./lib-php/functions.php");

//Create a temporary file
$tmp_out_model = tempnam ("../out", "out_model_");
$tmp_out_model_name = "../out/".basename($tmp_out_model);

//Create a temporary directory
$tmp_out_directory = $tmp_out_model . "-d";
mkdir($tmp_out_directory);
chmod($tmp_out_directory, 0777);
chmod($tmp_out_model, 0666);
$handle_in = fopen($tmp_out_model, "w");

//Load the content of the model
$ch = curl_init($server_url."/transformation/exec_transformation.php?idModel=".$_GET["idModel"]."&idTransformation=".$_GET["idTransformation"]);
curl_setopt($ch, CURLOPT_FILE, $handle_in);
curl_setopt($ch, CURLOPT_HEADER, 0);
curl_exec($ch);
curl_close($ch);
fclose($handle_in);

//Get the path of the template model
//Get parameters of the transformation
$idTransformation = $_GET["idTransformation"];
$idGeneration = $_GET["idGeneration"];
$templateModel = "";
$mm = "";
$generationMustBeDownloaded = 0;
$sql = "SELECT m.mm_file as mm, g.generationMustBeDownloaded, t.fileName as templateModel FROM generator g, metamodels m, templateModels t "
	."WHERE m.idMM = g.idMM AND g.idGenerator = $idGeneration AND t.idGenerator = g.idGenerator;";
$connect = mysql_connect($host,$user,$passwd);
mysql_select_db($bdd);
$result = mysql_query($sql);
if ($result) {
	while ($row = mysql_fetch_assoc($result)) {
		$templateModel = $row['templateModel'];
		$mm = $row['mm'];
		$generationMustBeDownloaded = $row['generationMustBeDownloaded'];

		$cmd = "java -Dfile.encoding=ISO-8859-1 -classpath org.rwm.acceleo.jar:lib/fr.obeo.acceleo.template_3.0.0.200809122321.jar:lib/fr.obeo.acceleo.tools_3.0.0.200809122321.jar:lib/org.acceleo.engine.jar:lib/org.acceleo.engine_3.0.0.qualifier.jar:lib/org.acceleo.gen.jar:lib/org.acceleo.gen_3.0.0.qualifier.jar:lib/org.acceleo.parser.jar:lib/org.acceleo.parser_3.0.0.qualifier.jar:lib/org.acceleo.tools.jar:lib/org.eclipse.emf.common_2.3.2.v200802051830.jar:lib/org.eclipse.emf.ecore.xmi_2.3.2.v200802051830.jar:lib/org.eclipse.emf.ecore_2.3.2.v200802051830.jar org.rwm.m2t.Main ../$templateModel ".str_replace(" ","\ ",$tmp_out_model_name)." ../$mm ".str_replace(" ","\ ",$tmp_out_directory);
		exec($cmd);
	}
}

//Execute ANT scripts
$sql = "SELECT s.fileName FROM antScript s WHERE s.idGenerator=$idGeneration;";
$result = mysql_query($sql);
while ($row = mysql_fetch_assoc($result)) {
	$cmd = "ant -Dgenerated_dir=\"".realpath($tmp_out_directory)."\" -f ".$row["fileName"];
	exec($cmd);
}

//Close database
mysql_close($connect);

$nb_generated_files = count_files($tmp_out_directory,true);
if ($nb_generated_files == 1) {
	$the_file_generated = getFirstFile($tmp_out_directory);
	if ($generationMustBeDownloaded == 1) {
		header("Content-Disposition: attachment; filename=\"$the_file_generated\"");
	}
	$handle_out = fopen($tmp_out_directory."/".$the_file_generated, "r");
	while ($line = fgets($handle_out)) {
		echo $line;
	}
} else if ($nb_generated_files > 1) {
	require ("./lib-php/zip.lib.php");
	//Creation of the zip file
	$zip = new zipfile() ;
	$folder = $tmp_out_directory."/";

	scan_dir($folder,$zip,strlen($folder));

	$archive = $zip->file();
	header('Content-Type: application/x-zip');
	header('Content-Disposition: inline; filename=generated-files.zip') ;
	echo $archive ;
} else {
	echo "No file generated.";
}

//Delete temporary directory
removedir($tmp_out_directory);
unlink($tmp_out_model);

function scan_dir($folder,$zip,$min_length) {
	//Opening folder
    $rep = @opendir($folder);
    while($file = readdir($rep))
    {
    	if($file == '.' || $file == '..')
        	continue;
		$filename = $folder.$file;
		$filename_sub = substr($filename,$min_length,strlen($filename)-$min_length);

		if (is_dir($filename)) {
			scan_dir($filename."/",$zip,$min_length);
		} else {
			$fp = fopen($filename,'r');
			$content = fread($fp, filesize($filename));
			fclose($fp);
			
			//Add the file
			$zip->addfile($content, $filename_sub);
		}
     }
     
     //Closing the folder
     closedir($rep);
}
?>