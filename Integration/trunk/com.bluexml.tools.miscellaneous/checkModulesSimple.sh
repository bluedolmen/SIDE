#!/bin/bash
showUsage=0
if [ $# -ne 2 ]; then
showUsage=1
fi
if [ "$1" = "-h" ]; then
showUsage=1
fi
if [ ! -d "$2" ]; then
showUsage=1
fi
if [ $showUsage -eq 1 ]; then

echo "USAGE :"
echo "checkModules.sh <filter> <FRAMEWORK_SRC_HOME>"
echo "example :>bash checkModules.sh 40d ~/servers/Alfresco/tomcat/webapps/alfresco/WEB-INF/lib /Users/davidabad/SIDE_SVN/FrameworksModules/trunk"
fi



ALF_VERSION=$1
FRAMEWORK_SRC_HOME=$2

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

R=`grep "compile" $EXEC_HOME/tree.txt`
if [ ! "$R" == "" ]
then
if [ "$MATCHES" == "0" ];then echo "MATCHES ------------------------`pwd`--------------------------" >> $EXEC_HOME/summary.txt;fi
echo "MATCHES $R"
MATCHES="1"
echo "MATCHES project : `pwd`" >> $EXEC_HOME/summary.txt
echo "$R" >> $EXEC_HOME/summary.txt
else
printf "."
fi
if [ "$MATCHES" == "1" ];then echo "MATCHES END------------------------`pwd`--------------------------" >> $EXEC_HOME/summary.txt;fi
#echo "END____________________`pwd`__________________" >> $EXEC_HOME/summary.txt
done
