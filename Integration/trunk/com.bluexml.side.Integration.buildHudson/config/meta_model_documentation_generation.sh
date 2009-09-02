#! /bin/bash
#  launch the generation of the Metamodel documtentation in  opendoc
# 1st parameter = WORKSPACE_PATH the up to date hudson workspace
# 2nd parameter = PLUGINS_WORK_PATH  the path to to plugin directory of the eclipse build wich will be su
# 3rd parameter = PLUGINS_UPDATE_SITE_PATH the up to date directory wich containes last builded jars
# 4th  parameter = WOKSPACE_JAR_PATH  the path to the workspace wich will be used to launch the jar
# 5th parameter = METAMODEL_DIR the path where to find metamodles to generate
# 6th parameter = TARGET_DIR the local work path in wich will first be generated the document
# 7th parameter =FINAL_TARGET_DIR the path wehre to deploy .odt generated files as well as the index.html refering to them
#

GENERATED_FILE_SUFFIX="ecore.odt"
INDEX_FILE_NAME="index.html"
JAR_NAME="com.bluexml.side.Integration.standAlone.metamodel.documentation"
#checking arguments
if [ $# -eq 7 ]; then
  WORKSPACE_PATH=$1
  PLUGINS_WORK_PATH=$2
  PLUGINS_UPDATE_SITE_PATH=$3
  WOKSPACE_JAR_PATH=$4
  METAMODEL_DIR=$5
  TARGET_DIR=$6
  FINAL_TARGET_DIR=$7
else
  echo "cannot launch task, wrong number og arguments"
  exit -2
fi

#move the last jar com.bluexml.side.Integration.standAlone.metamodel.documentation build  to  testAuto peclipse plugins directory
echo "moving last builded com.bluexml.side.Integration.standAlone.metamodel.documentation jar"
cd $PLUGINS_WORK_PATH
rm ./com.bluexml.side.Integration.standAlone.metamodel.documentation*.jar
cp $PLUGINS_UPDATE_SITE_PATH/$JAR_NAME*.jar ./

#copy meta model ecore to local eclipse project
echo "copying meta models "
cp $WORKSPACE_PATH/S-IDE/MetaModel/Form/trunk/com.bluexml.side.Form/model/Forms.ecore                     $METAMODEL_DIR
cp $WORKSPACE_PATH/S-IDE/MetaModel/Class/trunk/com.bluexml.side.Class/model/Class.ecore                   $METAMODEL_DIR
cp $WORKSPACE_PATH/S-IDE/MetaModel/View/trunk/com.bluexml.side.View/model/View.ecore                      $METAMODEL_DIR
cp $WORKSPACE_PATH/S-IDE/MetaModel/Workflow/trunk/com.bluexml.side.Workflow/model/Workflow.ecore          $METAMODEL_DIR
cp $WORKSPACE_PATH/S-IDE/MetaModel/Portal/trunk/com.bluexml.side.Portal/model/Portal.ecore                $METAMODEL_DIR
cp $WORKSPACE_PATH/S-IDE/MetaModel/Application/trunk/com.bluexml.side.Application/model/application.ecore $METAMODEL_DIR

# calling the jar
echo "generating documentation ....."
cd $PLUGINS_WORK_PATH
java -Xms512M -Xmx1024M -jar org.eclipse.equinox.launcher_1.0.101.R34x_v20081125.jar -application $JAR_NAME.application -data $WOKSPACE_JAR_PATH  $METAMODEL_DIR  $TARGET_DIR
echo "moving generated files"
cp $METAMODEL_DIR/test/doc/*.odt $FINAL_TARGET_DIR
cd $FINAL_TARGET_DIR
echo "creating index file"
echo "<html><head><title>S-IDE Metamodel Documentation</title></head>" > $INDEX_FILE_NAME
echo "<body><H1>S-IDE Metamodel Documents list</H1><UL>" >> $INDEX_FILE_NAME
for i in *$GENERATED_FILE_SUFFIX
do
  echo "<LI><A href="$i">$i</A></LI>" >> $INDEX_FILE_NAME
done
echo "</UL><footer>BlueXML - Copyright 2009</footer></body></html>" >>  $INDEX_FILE_NAME
echo "finished meta model documentation"
echo " "
return_code=0
