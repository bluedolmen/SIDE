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
import com.bluexml.side.portal.generator.alfresco40d.templates.upload.upload-config-lib
%>
<%script type="Portal" name="validatedFilename" post="trim()" %>
<%getProperty("alf.share.paths.web-ext.alf.components")%>/upload/flash-upload.get_nl.properties
<%script type="Portal" name="generator" file="<%validatedFilename%>"%>
header.singleUpload=Bestand uploaden
header.multiUpload=Bestand(en) uploaden
header.singleUpdate=Bestand bijwerken

label.browse=Selecteer een of meer bestanden om te uploaden
label.multiUploadTip=Gebruik CTRL of SHIFT om meerdere bestanden te selecteren
label.singleUpdateTip=Klik op pictogram om een nieuwe versie van {0} te uploaden
label.noFiles=Geen bestanden om weer te geven, klik op pictogram om een of meer bestanden te selecteren om te uploaden
label.noFlash=U hebt een Flash Player van versie 9.0.45 nodig om dit onderdeel te kunnen gebruiken. U kunt de laatste versie van Flash Player downloaden via het \ 
  <a href="http://www.adobe.com/nl/getflashplayer">downloadcentrum voor Adobe Flash Player</a>.
label.success=Geslaagd
label.failure=Mislukt
label.failure.413=Mislukt: quota overschreden
label.illegalChars=Mislukt: bestandsnaam bevat ongeldige tekens
button.upload=Bestand(en) uploaden
label.uploadStatus=Status: {0}/{1} ge\u00fcpload ({2} mislukt)
message.cancelStatus=Upload(s) geannuleerd; {0} bestand(en) ge\u00fcpload
message.zeroByteFileSelected=U hebt bestand {0} geselecteerd. Het heeft een grootte van 0 bytes en kan niet worden ge\u00fcpload.
message.flashError.title=Flash-communicatiefout
message.flashError.message=De browser kan niet communiceren met de Flash Uploader. U kunt doorgaan met uploaden in de modus voor \u00e9\u00e9n bestand of u vernieuwt de pagina om Flash opnieuw te starten.

section.version=Versie-informatie
label.version=Deze versie heeft:
label.minorVersion=kleine wijzigingen
label.majorVersion=grote wijzigingen
label.minorVersion.more=kleine wijzigingen ({0})
label.majorVersion.more=grote wijzigingen ({0})
label.comments=Opmerkingen

type.cm_content=Content
<%i18nGenerator%>
