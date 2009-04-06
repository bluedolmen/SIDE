<html>
	<head>
		<title>Vérif des params</title>
		<link rel="stylesheet" type="text/css" href="css/main.css">
	</head>
	<body>
		<? 
			require("./mysql/mysql_util.php");
			
			function appendParameter($url, $paramName, $paramValue) {
				$tmp = removeParameter($url,$paramName);
				$separator = "?";
				if (strpos($tmp,"?")!=false)
  					$separator = "&";
 				return $tmp . $separator . "$paramName=$paramValue";
			}
			
			function removeParameter($url, $paramName) {
 				$tmp = ereg_replace("$paramName=([a-zA-Z0-9])*","",$url);
 				$tmp = ereg_replace("\&\&","&",$tmp);
 				$tmp = ereg_replace("\?\&","?",$tmp);
 				return $tmp;
			}
		?>
		<div id="data">
			<h1>Vérif des params</h1>
		</div>
	</body>
</html>