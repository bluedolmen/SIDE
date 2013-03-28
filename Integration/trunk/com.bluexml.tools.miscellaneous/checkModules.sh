#!/bin/bash

showUsage=0
if [ $# -ne 3 ]; then
showUsage=1
fi
if [ "$1" = "-h" ]; then
showUsage=1
fi
if [ ! -d "$2" -or ! -d "$3" ]; then
showUsage=1
fi
if [ $showUsage -eq 1 ]; then
echo "Scan source directory for maven project and log \"compile\" scoped dependencies than matches existing jar in weabpp WEB-INF/lib"
echo "USAGE :"
echo "checkModules.sh <filter> <ALFRESCO_LIB_HOME> <FRAMEWORK_SRC_HOME>"
echo "example :>bash checkModules.sh 40d ~/servers/Alfresco/tomcat/webapps/alfresco/WEB-INF/lib /Users/davidabad/SIDE_SVN/FrameworksModules/trunk"
else

ALF_VERSION=$1
ALFRESCO_LIB_HOME=$2
FRAMEWORK_SRC_HOME=$3

EXEC_HOME=`pwd`
rm -f $EXEC_HOME/summary.txt
touch $EXEC_HOME/summary.txt
LIBS=`ls $ALFRESCO_LIB_HOME | grep ".jar"`

for d in `find $FRAMEWORK_SRC_HOME -type d -maxdepth 1 -and ! -name "${FRAMEWORK_SRC_HOME##*/}" -and -name "*$ALF_VERSION*"`
do
cd $d
pwd
#echo "START__________________`pwd`____________________" >> $EXEC_HOME/summary.txt
MATCHES="0"
mvn dependency:tree > $EXEC_HOME/tree.txt

for l in $LIBS
do
LIB=`echo $l | sed -E "s/([^-]*(-[a-zA-Z\.]*)*)(-[0-9\.]*)?\.jar/\1/"`

R=`grep "$LIB" $EXEC_HOME/tree.txt`
if [ ! "$R" == "" ] && [ "`echo $R | grep provided`" == "" ]
then
if [ "MATCHES" == "0" ];then echo "--------------------------------------------------" >> $EXEC_HOME/summary.txt;fi
MATCHES="1"
echo "MATCHES project : `pwd`" >> $EXEC_HOME/summary.txt
echo "MATCHES $l :" >> $EXEC_HOME/summary.txt
echo "$R" >> $EXEC_HOME/summary.txt
else
printf "."
fi
done
if [ "MATCHES" == "1" ];then echo "--------------------------------------------------" >> $EXEC_HOME/summary.txt;fi
#echo "END____________________`pwd`__________________" >> $EXEC_HOME/summary.txt
done
fi