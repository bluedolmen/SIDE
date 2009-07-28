#! /bin/bash
# launch the generation of the Metamodel documtentation in docbook and html
# 1st parameter = execution directory where is put docbook.sh and gendoc 
# 2nd parameter = target documentation path where the docbook and html must be stored
# 3rd parameter = source path of the gendoc project
if [ $# -eq 3 ]; then
  EXEC_DIR=$1
  DOC_DIR=$2
  GENDOC_SRC=$3
else
  exit -2
fi

return_code=0
#cd $GENDOC_SRC/src/com/bluexml/side/Util/MetaModel/gendoc
#CLASSDIR=".:$GENDOC_SRC/src:$GENDOC_SRC/org.eclipse.emf.common_2.4.0.v200902171115.jar:$GENDOC_SRC/org.eclipse.emf.ecore_2.4.2.v200902171115.jar:$GENDOC_SRC/org.eclipse.emf.ecore.xmi_2.4.1.v200902171115.jar"
#for i in *.java
#do
#  javac -cp $CLASSDIR $i
#done
#for i in *.class
#do
#  mv $i $EXEC_DIR/gendoc/com/bluexml/side/Util/MetaModel/gendoc
#done

#cd $EXEC_DIR/gendoc

#jar cmf META-INF/MANIFEST.MF ../Gendoc.jar .
cd $EXEC_DIR
var[0]=palette.classDiagram=/root/.hudson/jobs/Build_S-IDE/workspace/S-IDE/MetaModel/Class/trunk/com.bluexml.side.Class.modeler/model/ClasseDiagram.diagramconfigurator
var[1]=palette.workflow=/root/.hudson/jobs/Build_S-IDE/workspace/S-IDE/MetaModel/Workflow/trunk/com.bluexml.side.Workflow.modeler/model/Workflow.diagramconfigurator
var[2]=forms.ecore=/root/.hudson/jobs/Build_S-IDE/workspace/S-IDE/MetaModel/Form/trunk/com.bluexml.side.Form/model/Forms.ecore
for i in `seq 0 2`; do
echo ${var[i]}
echo ${var[i]} > modelspath.properties
java -jar Gendoc.jar
done
jar_gendoc=$?
if [ -f gendoc.log ]; then
  cat gendoc.log
fi
if [ $jar_gendoc -gt 0 ] 
then 
  return_code=-1
else

  if [ ! -d $DOC_DIR/MetaModel ]; then
    mkdir $DOC_DIR/MetaModel
  fi
  mv *.docbook $DOC_DIR/MetaModel
  cd $DOC_DIR/MetaModel
  echo "<html><head><title>S-IDE Metamodel Documentation</title></head>" > index.html
  echo "<body><H1>S-IDE Metamodel Documents list</H1><UL>" >> index.html
  for i in *.docbook
  do
IFS="."
set $i
IFS="
"
  if [ ! "$1Z" = "Z" ]
  then
    if [ ! -d $1 ]; then
      mkdir $1
    fi
    docbook2html -w no-xml -w no-mixed -w no-should -w no-default -w no-undefined -w no-sgmldecl -w no-unclosed -w no-duplicate -w no-empty -w no-net -w no-min-tag -w no-unused-map -w no-unused-param -w no-notation-sysid $1.docbook -o $1
    docbook2html=$?
#    if [ $docbook2html -gt 0 ] 
#     then
#      return_code=-2
#      docbook_file="$docbook_file $1"
#    fi
    if [ -f $1/index.html ]
    then
(
echo "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />"
cat $1/index.html
) > $1/index_tmp
      mv  $1/index_tmp $1/index.html
      echo "<LI><A href="$1/index.html">$1/index.html</A> (<A href="$1.docbook">$1.docbook</A>)</LI>" >> index.html
    fi
   else
    return_code=-3
   fi
   done
  echo "</UL><footer>BlueXML - Copyright 2009</footer></body></html>" >>  index.html
fi
if [ $return_code -lt 0 ]
then
  echo "ERROR GENERATION DOC METAMODEL"
  if [ $return_code -gt -2 ]
  then
    echo "  Gendoc on error"
  else
  if [ $return_code -gt -3 ]
  then
    echo "  Docbook2html on error: $docbook_file"
  else
    echo "  Docbook2html on error: no access to metamodels"
  fi 
  fi 
fi
