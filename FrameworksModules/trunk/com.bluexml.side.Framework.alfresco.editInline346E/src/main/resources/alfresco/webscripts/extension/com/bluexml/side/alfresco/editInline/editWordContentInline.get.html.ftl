<html>
	<head>
		<title>SIDE Inline Edition</title>
		<script type="text/javascript">
			function Close(){window.open("","_self").close();}
		</script>
	</head>
	<body>
		<p> Ne pas fermer la fen&euml;tre avant d'avoir ferm&eacute; votre document et avoir attendu 2 secondes.</p>
		<applet id="editDoc" name="editDoc" codebase="${url.context}/applet" code="com.bluexml.side.alfresco.applet.EditingDocument.class"
			height="80" width="300"
			archive="editContentInline-${project.version}.jar" mayscript="true">
			<param name="ticket" value="${ticket}" />
			<param name="webdavUrl" value="${webdavUrl}" />
			<param name="mode" value="${args.mode}" />
			<param name="mime" value="${args.mime}" />
		</applet>
	</body>
</html>