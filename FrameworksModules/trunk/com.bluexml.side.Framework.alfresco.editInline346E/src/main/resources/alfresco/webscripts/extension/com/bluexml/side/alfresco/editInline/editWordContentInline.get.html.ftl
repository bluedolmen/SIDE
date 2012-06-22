<html>
	<head>
		<title>SIDE Inline Edition</title>
		<script type="text/javascript">
			function Close(){window.open("","_self").close();}
		</script>
	</head>
	<body>
		<applet id="editDoc" name="editDoc" codebase="${url.context}/applet" code="com.bluexml.side.alfresco.applet.EditingDocument.class"
			height="80px" width="300px"
			archive="editContentInline-${project.version}.jar" mayscript="true">
			<param name="ticket" value="${ticket}" />
			<param name="webdavUrl" value="${webdavUrl}" />
			<param name="mode" value="${args.mode}" />
			<param name="mime" value="${args.mime}" />
		</applet>
	</body>
</html>