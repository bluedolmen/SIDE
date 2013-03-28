<%--
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
<%
metamodel http://www.kerblue.org/portal/1.0
import com.bluexml.side.portal.generator.alfresco.templates.services.ClazzService
import com.bluexml.side.clazz.service.alfresco.CommonServices
%>
<%script type="Portal" name="validatedFilename" post="trim()" %>
<%getProperty("alf.share.paths.web-ext.alf.components")%>/upload/flash-upload.get_fr.properties
<%script type="Portal" name="generator" file="<%validatedFilename%>"%>
header.singleUpload=Ajouter un fichier
header.multiUpload=Ajout de fichier(s)
header.singleUpdate=Mettre le fichier \u00e0 jour

label.browse=S\u00e9lectionner le(s) fichier(s) \u00e0 ajouter
label.multiUploadTip=Utiliser CTRL ou SHIFT(Maj) pour s\u00e9lectionner des fichiers multiples
label.singleUpdateTip=Cliquez sur l''ic\u00f4ne pour remplacer par une nouvelle version de {0}
label.noFiles=Pas de fichier \u00e0 afficher, cliquer sur l'ic\u00f4ne pour s\u00e9lectionner un ou des fichiers \u00e0 ajouter
label.noFlash=Vous devez poss\u00e9der Flash Player version 9.0.45 pour utiliser ce composant. Vous pouvez t\u00e9l\u00e9charger la derni\u00e8re version de Flash Player depuis \
  <a href="http://www.adobe.com/go/getflashplayer">Adobe Flash Player Download Center</a>.
label.success=Succ\u00e8s
label.failure=Echec
label.failure.413=Echec : Quota d\u00e9pass\u00e9
label.illegalChars=Echec : Le nom de fichier contient des caract\u00e8res ill\u00e9gaux
button.upload=Ajout de fichier(s)
label.uploadStatus=Statut : {0}/{1} ajout\u00e9(s) ({2} \u00e9chec(s))
message.cancelStatus=Ajout(s) annul\u00e9(s) ; {0} fichier(s) ajout\u00e9(s)
message.zeroByteFileSelected=Vous avez s\u00e9lectionn\u00e9 le fichier {0}. La taille de ce fichier est nulle et ce dernier ne peut pas \u00eatre ajout\u00e9.
message.flashError.title=Erreur de communication Flash
message.flashError.message=Le navigateur n'arrive pas \u00e0 communiquer avec l'injecteur Flash. Vous pouvez continuer en mode fichier unique ou rafra\u00eechir la page pour relancer Flash.

section.version=Informations de version
label.version=Cette version a :
label.minorVersion=changements mineurs
label.majorVersion=changements majeurs
label.minorVersion.more=changements mineurs ({0})
label.majorVersion.more=changements majeurs ({0})
label.comments=Commentaires

type.cm_content=Contenu
<%for (pageSet[ID.toLowerCase().trim() == "documentlibrary"].portlets.associationPortlet[name.toLowerCase().trim() == "uploadabletypes" && isPortletInternal != null]){%>
<%for (isPortletInternal.form.forms){%>
type.<%filter("form.ClassReference").real_class.getPrefixedQName("_")%>=<%filter("form.ClassReference").real_class.getLabel()%>
<%}%>
<%}%>
