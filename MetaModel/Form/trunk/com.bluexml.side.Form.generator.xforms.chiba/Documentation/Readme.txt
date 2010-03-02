Ce projet comporte une référence externe sur le serveur SVN:

1- sur le projet com.bluexml.side.Framework.xforms.alfresco.controller
La référence est placée sur le pdossier du projet et pointe vers le dossier "xsd" (src/main/xsd) grâce à la propriété
"svn:externals" configurée en indiquant le nom du dossier fils et son URL: 
"xsd http://www.bluexml.com/svn/private/S-IDE/FrameworksModules/trunk/com.bluexml.side.Framework.xforms.alfresco.controller/src/main/java/com/bluexml/xforms/controller/binding"

Cette référence permet de partager le schéma utilisé pour générer les classes de lecture/écriture 
du fichier mapping.xml. Les classes doivent être générées à chaque build en exécutant le script Ant.

Pour cela, placer les fichiers suivants dans un dossier et renseigner ce dossier dans la propriété lib.xjc (dans le fichier build.properties):
activation-1.1.jar
jaxb-api-2.1.jar
jaxb-impl-2.1.12.jar
jaxb-xjc-2.1.12.jar
stax-api-1.0.1.jar