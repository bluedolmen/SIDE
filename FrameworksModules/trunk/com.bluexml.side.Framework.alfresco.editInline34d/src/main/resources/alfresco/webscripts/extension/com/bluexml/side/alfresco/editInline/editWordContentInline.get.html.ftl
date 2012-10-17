<html>
	<head>
		<title>SIDE Inline Edition</title>
		<script type="text/javascript">
			function Close(){window.open("","_self").close();}
		</script>
	</head>
	<body>
        <p>Le temps de chargement du document dans votre &eacute;diteur peut prendre quelques secondes suivant sa taille.</p>
        <p>Merci d'attendre le temps n&eacute;cessaire.</p>
        <p />
		<p>A la fin de l'&eacute;dition de votre document, NE PAS fermer la fen&euml;tre avant d'avoir ferm&eacute; votre document et avoir attendu 5 secondes.</p>
		<p>Ceci est n&eacute;cessaire pour laisser le temps de sauvegarder les derni&egrave;res modifications de votre document dans Alfresco.</p>
		<p>Merci de votre compr&eacute;hension. </p>
		<hr/>
        <p>The loading duration of the document in your editor may take some seconds according to its size.</p>
        <p>Please, wait the necessary time.</p>
        <p />
		<p>After closing your editor, DO NOT close this window before closing your document and waiting for 5 seconds.</p>
		<p>This is required to let the necessary time to save the last updates of your document in Alfresco</p>
		<p>Thanks you for your understanding. </p>
		<applet id="editDoc" name="editDoc" codebase="http://${publicHost}/${context}" code="com.bluexml.side.alfresco.applet.EditingDocument.class"
			height="1" width="1"
			archive="editContentInline.jar" mayscript="true">
			<param name="ticket" value="${ticket}" />
			<param name="webdavUrl" value="${webdavUrl}" />
			<param name="mode" value="${args.mode}" />
			<param name="mime" value="${args.mime}" />
		</applet>
	</body>
</html>