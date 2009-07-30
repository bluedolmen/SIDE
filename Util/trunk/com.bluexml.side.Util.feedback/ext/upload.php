<?php
/**
 *  Move file send (in FILE[  ]) in  $attributeName and move it to upload/<y.m.d--H.i.s>--<CLIENT_IP>.zip
 **/
$attributeName = 'log';
if (isset($_FILES[$attributeName])){
	if ($_FILES[$attributeName]['error'] == 0 && ($_FILES[$attributeName]['type'] == 'application/zip' || $_FILES[$attributeName]['type'] == 'multipart/x-zip')) {
		move_uploaded_file($_FILES[$attributeName]['tmp_name'],'upload/'  . date("y.m.d--H.i.s") .  '--' . getClientIP() .'.zip');
	}
}

/**
 * Return client IP.
 **/
function getClientIP() {
	$IP = null;
	if (isset($_SERVER['HTTP_X_FORWARDED_FOR']))
		$IP = $_SERVER['HTTP_X_FORWARDED_FOR']; 
    elseif(isset($_SERVER['HTTP_CLIENT_IP']))   
		$IP = $_SERVER['HTTP_CLIENT_IP'];   
    else
		$IP = $_SERVER['REMOTE_ADDR'];
	return $IP;
}