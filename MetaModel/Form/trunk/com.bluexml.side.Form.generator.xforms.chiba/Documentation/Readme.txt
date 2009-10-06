Ce projet comporte des références vers du code source situées dans des branches externes du serveur SVN:

1- sur le projet xforms.generator.worker situé à http://www.bluexml.com/svn/private/generator/forms/trunk/generator/worker.
La référence est placée sur le dossier "xforms" (src/com/bluexml/xforms) grâce à la propriété
"svn:externals" configurée en indiquant le nom du dossier fils et son URL: 
"generator http://www.bluexml.com/svn/private/generator/forms/trunk/generator/worker/src/main/java/com/bluexml/xforms/generator"


2 - sur le projet xforms.controller à http://www.bluexml.com/svn/private/generator/forms/trunk/generator/controller
Référence placée sur le dossier "controller" (src/com/bluexml/xforms/controller) via svn:externals valant:
"messages http://www.bluexml.com/svn/private/generator/forms/trunk/generator/controller/src/main/java/com/bluexml/xforms/controller/messages"

Ces références ont été mises en place le 1er octobre et le 5 octobre 2009. Elles devraient avoir disparu environ une semaine après.

====
WEBAPP CHIBA
====
Le dossier webapp contient une webapp Chiba customisée qui DOIT être celle utilisée pour les formulaires générées.
A noter: les fichiers présents dans ce dossier sont issus du packaging Maven du projet "xforms" avec le profil "buildEmptyWar".
Il est situé à l'adresse http://www.bluexml.com/svn/private/generator/forms/trunk/examples/xforms 