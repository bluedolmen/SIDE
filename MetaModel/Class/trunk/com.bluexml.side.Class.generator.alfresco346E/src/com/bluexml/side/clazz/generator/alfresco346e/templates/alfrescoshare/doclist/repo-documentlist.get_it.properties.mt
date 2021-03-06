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
<%if (eContainer() == null) {%><%getProperty("alf.share.paths.core.site-webscripts")%>org/alfresco/components/documentlibrary/repo-documentlist.get_it.properties<%}%>

<%script type="clazz.ClassPackage" name="messages" file="<%fileName%>"%>
## Buttons
button.folders.show=Mostra cartelle
button.folders.hide=Nascondi cartelle
button.view.simple=Vista semplice
button.view.detailed=Vista dettagliata

## Drop-down Menus
menu.select=Seleziona
menu.select.all=Tutti
menu.select.none=Nessuno
menu.select.invert=Inverti selezione
menu.select.folders=Cartelle
menu.select.documents=Documenti

## Document Details
details.link-to=Link a: {0}
details.created.on=Creato:
details.created.by=Creato da:
details.modified.on=Modificato:
details.modified.by=Modificato da:
details.editing-started.on=Modifica iniziata:
details.editing-started.by=Modifica avviata da:
details.by=Da:
details.version=Versione:
details.size=Dimensioni:
details.description=Descrizione:
details.description.none=(Nessuna)
details.comments=Commenti:
details.tags=Tag:
details.tags.none=(Nessuno)
details.categories=Categorie:
details.categories.none=(Nessuna)

## Details Banners
details.banner.editing=Questo documento \u00e8 bloccato dall'utente attuale per la modifica offline.
details.banner.lock-owner=Questo documento \u00e8 bloccato dall'utente attuale.
details.banner.locked=Questo documento \u00e8 bloccato da {0}.
details.banner.google-docs.link=Google Docs
details.banner.google-docs-owner=Questo documento \u00e8 stato caricato dall''utente attuale in {0} per la modifica.
details.banner.google-docs-locked=Questo documento \u00e8 stato caricato da {0} in {1} per la modifica.

## Actions - moved to slingshot.properties
actions.more=Segue...

## Tips
tip.active-workflow=Appartiene a {0} workflow attivi
tip.editing=Che sta modificando l'utente attuale
tip.favourite-document.add=Aggiungi documento ai preferiti
tip.favourite-document.remove=Rimuovi documento dai preferiti
tip.favourite-folder.add=Aggiungi cartella ai preferiti
tip.favourite-folder.remove=Rimuovi cartella dai preferiti
tip.google-docs-owner=Bloccato dall'utente attuale per la modifica con Google Docs&trade;
tip.google-docs-locked=Bloccato da {0} ({1}) per la modifica con Google Docs&trade;
tip.locked=Bloccato da {0} ({1})
tip.lock-owner=Bloccato dall'utente attuale
tip.rules=La cartella ha regole attive
tip.simple-workflow=Applicato un workflow semplice Approva/Respingi
tip.transferred-node=Trasferito da un altro repository

## Pop-up Messages
message.confirm.delete.title=Elimina file
message.confirm.delete=Eliminare ''{0}''?
message.delete.success=''{0}'' \u00e8 stato eliminato
message.delete.failure=Impossibile eliminare ''{0}''
message.details.success=Dettagli aggiornati
message.details.failure=Impossibile aggiornare i dettagli
message.edit-offline.success=\u00c8 ora possibile modificare ''{0}''
message.edit-offline.success.ie7=Scaricare il documento utilizzando il pulsante qui sotto.
message.edit-offline.failure=Impossibile modificare ''{0}''.
message.edit-cancel.success=La modifica di ''{0}'' \u00e8 stata annullata
message.edit-cancel.failure=Impossibile annullare la modifica di ''{0}''.
message.loading=Caricamento della raccolta documenti in corso...
message.error=Impossibile accedere alla raccolta documenti
message.empty=Nessun elemento
message.empty.subfolders=Nessun elemento. Scegliere ''{0}'' per vedere {1} sottocartelle qui.
message.empty.subfolders.link=Mostra cartelle
message.favourite.failure=Impossibile aggiornare l'elenco preferiti
message.simple-workflow.approved=Elemento contrassegnato come approvato
message.simple-workflow.rejected=Elemento contrassegnato come respinto
message.simple-workflow.failed=Impossibile completare l'azione del workflow.
message.checkout-google.inprogress=\u00c8 in corso il Check Out di ''{0}'' in Google Docs
message.checkout-google.success=\u00c8 stato eseguito il Check In di ''{0}'' in Google Docs
message.checkout-google.failure=Impossibile eseguire il Check In di ''{0}'' in Google Docs
message.checkout-google.failure=Impossibile eseguire il Check Out di ''{0}'' in Google Docs
message.checkin-google.inprogress=\u00c8 in corso il Check In di ''{0}'' da Google Docs
message.checkin-google.success=\u00c8 stato eseguito il Check In di ''{0}'' da Google Docs
message.checkin-google.failure=Impossibile eseguire il Check In di ''{0}'' da Google Docs

## File Upload (upload new version)
label.filter-description=Stesso tipo di {0}

## Edit Details Dialog
edit-details.title=Dettagli di {0}
edit-details.label.edit-metadata=Pagina Modifica tutti i metadati...

## Customize Dialog
customize.title=Personalizza
customize.header.actions=Azioni

# SIDE sort extension
label.sortby=Ordina per: Nome
search.sort.size=Dimensioni
search.sort.mimetype=Mimetype
search.sort.type=Tipo

## SIDE view labels
<%for (getAllReferencedAttributes()){%>
<%getPrefixedQualifiedName().replaceAll(":", "_")%>=<%getLabel()%>
<%}%>
