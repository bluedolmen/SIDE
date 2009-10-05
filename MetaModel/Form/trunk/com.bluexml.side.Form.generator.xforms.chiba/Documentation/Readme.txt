Ce projet comporte des références vers du code source:

1- sur le projet xforms.generator.worker situé à http://www.bluexml.com/svn/private/generator/forms/trunk/generator/worker.
La référence est placée sur le dossier "xforms" (src/com/bluexml/xforms) grâce à la propriété
"svn:externals" configurée en indiquant le nom du dossier fils et son URL: 
"generator http://www.bluexml.com/svn/private/generator/forms/trunk/generator/worker/src/main/java/com/bluexml/xforms/generator"


2 - sur le projet xforms.controller à http://www.bluexml.com/svn/private/generator/forms/trunk/generator/controller
Référence placée sur le dossier "controller" (src/com/bluexml/xforms/controller) via svn:externals valant:
"messages http://www.bluexml.com/svn/private/generator/forms/trunk/generator/controller/src/main/java/com/bluexml/xforms/controller/messages"