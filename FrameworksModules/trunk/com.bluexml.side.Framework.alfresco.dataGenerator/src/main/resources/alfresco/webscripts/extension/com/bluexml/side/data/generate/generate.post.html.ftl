<html>
	<style type="text/css">
    	body {font-family: Tahoma;}
    	h2 {text-align: center; color: white;  background-color: blue;}
    	p#content {text-align: center;}
    </style>
	<body>
		<h2>SIDE-Alfresco: Random Metadata and document Loading for Test</h2>
		<#if error?exists>
			<p id="content">The test data loading has failed and no data have been loaded under Alfresco.<br>
			   Have a look to the Alfresco logs to identify the problem and fix it.<br>
			   You can retry through the <A href="http://localhost:8080/alfresco/service/data/form/fillparameters">Random Metadata and document Loading webscript</a>.</p>
		<#else>
			<p id="content">The test data have been successfully loaded under Alfresco.<br>
			   You can load other test data through the <a href="http://localhost:8080/alfresco/service/data/form/fillparameters">Random Metadata and document Loading webscript</a>.</p>
		</#if>
	</body>
</html>