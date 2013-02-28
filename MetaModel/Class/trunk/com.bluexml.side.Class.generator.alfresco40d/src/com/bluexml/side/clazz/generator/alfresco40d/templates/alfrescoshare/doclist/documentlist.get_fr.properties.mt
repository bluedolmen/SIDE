<%
metamodel http://www.kerblue.org/class/1.0

import templates.alfrescoshare.uploadForm.file-upload-js-get-lib
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.service.alfresco.ClassServices
%>

<%--
  -- This file is a patched copy of Alfresco Share's
  -- Contains additional message for custom Types 
  --%>
  
<%script type="clazz.ClassPackage" name="fileName"%>
<%if (eContainer() == null) {%><%getProperty("alf.share.paths.core.site-webscripts")%>org/alfresco/components/documentlibrary/documentlist.get_fr.properties<%}%>

<%script type="clazz.ClassPackage" name="messages" file="<%fileName%>"%>
## Buttons
button.sort.ascending=Tri croissant
button.sort.descending=Tri d\u00e9croissant
button.folders.show=Afficher les dossiers
button.folders.hide=Cacher les dossiers

## Drop-down Menus
menu.select=S\u00e9lectionner
menu.select.all=Toutes
menu.select.none=Aucun
menu.select.invert=Inverser la s\u00e9lection
menu.select.folders=Dossiers
menu.select.documents=Documents

## Details Banners
details.banner.editing=Ce document est verrouill\u00e9 par vous pour une \u00e9dition hors ligne.
details.banner.lock-owner=Ce document est verrouill\u00e9 par vous.
details.banner.locked=Ce document est verrouill\u00e9 par {0} pour \u00e9dition.
details.banner.google-docs.link=Google Docs
details.banner.google-docs-owner=Ce document a \u00e9t\u00e9 ajout\u00e9 par vous \u00e0 {0} pour \u00e9dition.
details.banner.google-docs-locked=Ce document a \u00e9t\u00e9 ajout\u00e9 par {0} \u00e0 {1} pour \u00e9dition.

## Actions - moved to slingshot.properties
actions.more=Plus...

## Status Indicators
status.active-workflows=Associ\u00e9 \u00e0 {0} workflow(s) actif(s)
status.editing=Modifi\u00e9 par vous
status.google-docs-editing=Modifi\u00e9 par vous via Google Docs&trade;
status.google-docs-owner=Verrouill\u00e9 par vous pour \u00e9dition \u00e0 l'aide de Google Docs&trade;
status.google-docs-locked=Verrouill\u00e9 par {0} ({1}) pour \u00e9dition \u00e0 l''aide de Google Docs&trade;
status.locked=Verrouill\u00e9 par {0} ({1})
status.lock-owner=Verrouill\u00e9 par vous
status.rules=Des r\u00e8gles sont appliqu\u00e9es au dossier
status.simple-workflow=Approbation/Rejet simple du workflow appliqu\u00e9
status.exif=M\u00e9tadonn\u00e9es EXIF disponibles
status.geographic=M\u00e9tadonn\u00e9es de g\u00e9olocalisation disponibles
status.transferred-node=Transf\u00e9r\u00e9 depuis un autre entrep\u00f4t

## Tips
tip.insitu-rename=Renommer
tip.insitu-tag=Tag

## File Upload (upload new version)
label.filter-description=Type identique \u00e0 {0}

## Edit Details Dialog
edit-details.title=Modifier les propri\u00e9t\u00e9s : {0}
edit-details.label.edit-metadata=Toutes les propri\u00e9t\u00e9s...

## Help panels, including Drag and Drop
no.items.title=Aucun \u00e9l\u00e9ment de contenu
show.folders=Afficher {0} sous-dossier(s)
standard.upload.title=Cr\u00e9ez votre biblioth\u00e8que !
standard.upload.description=Ajouter des fichiers
dnd.drop.title=Effectuez un glisser-d\u00e9poser pour ajouter des fichiers !
dnd.drop.doclist.description=Faites glisser des fichiers de votre bureau et d\u00e9posez-les ici pour les ajouter.
dnd.drop.folder.description=Vous pouvez d\u00e9poser des fichiers dans des dossiers.
dnd.upload.description=Ajouter des fichiers
dnd.newfolder.description=Cr\u00e9er un dossier
other.options=Vous pouvez \u00e9galement...
dnd.upload.tooltip=<p>Vous pouvez \u00e9galement faire glisser des fichiers depuis votre bureau et les d\u00e9poser dans l'espace documentaire.</p>
<p>Si vous pr\u00e9f\u00e9rez proc\u00e9der par glisser-d\u00e9poser, fermez l'outil de t\u00e9l\u00e9chargement ci-dessous.</p>


## SIDE view labels
<%for (getAllReferencedAttributes()){%>
<%getPrefixedQualifiedName().replaceAll(":", "_")%>=<%getLabel()%>
<%}%>