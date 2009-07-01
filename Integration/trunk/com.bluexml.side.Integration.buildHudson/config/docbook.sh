#/bin/bash
set echo on
logfile=$1
if [ "$logfileZ" = "Z" ]
then
 logfile=log_docbook.txt
fi

export WORKSPACE=/root/.hudson/jobs/Job_Test/workspace
export DIR_UPDATE_SITE=/home/stager/share/SIDE
export JAR_GENDOC=/home/stager/buildAuto/Gendoc.jar
#cd /home/stager/buildAuto
#cp $WORKSPACE/S-IDE/Utils/trunk/com.bluexml.side.Util.MetaModel.gendoc/modelspath.properties .
java -jar $JAR_GENDOC
return_code=0
jar_gendoc=$?
if [ $jar_gendoc -gt 0 ] 
then 
  return_code=-1
else

  mkdir $DIR_UPDATE_SITE/doc/MetaModel
  mv *.docbook $DIR_UPDATE_SITE/doc/MetaModel
  cd $DIR_UPDATE_SITE/doc/MetaModel
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
    mkdir $1
    docbook2html -w no-xml -w no-mixed -w no-should -w no-default -w no-undefined -w no-sgmldecl -w no-unclosed -w no-duplicate -w no-empty -w no-net -w no-min-tag -w no-unused-map -w no-unused-param -w no-notation-sysid $1.docbook -o $1
    docbook2html=$?
    if [ $docbook2html -gt 0 ] 
    then
      return_code=-2
      docbook_file="$docbook_file $1"
    fi
    if [ -f $1/index.html ]
    then
(
echo "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />"
cat $1/index.html
) > $1/index_tmp
      mv  $1/index_tmp $1/index.html
      echo "<LI><A href="$1/index.html">$1/index.html</A></LI>" >> index.html
    fi
   else
    return_code=-3
   fi
   done
  echo "</UL></body></html>" >> index.html
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

