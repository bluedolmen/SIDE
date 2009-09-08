#! /bin/bash
# first test disk space: if full, stop build
# 1st parameter = source workspace path
# 2nd parameter = target workspace path
# 3rd parameter = percent representing disk full
if [ $# -eq 5 ]; then
  SOURCE_WORKSPACE=$1
  TARGET_WORKSPACE=$2
  FULL=$3
  MAVEN_SOURCE=$4
  MAVEN_TARGET=$5
else
  exit -2
fi

# delete workingcopy repository
rm -rf $TARGET_WORKSPACE/workingcopy
rm -rf $MAVEN_TARGET

mkdir $MAVEN_TARGET

#$DF_HOME=`df -k | grep -i /home | awk '{print $5}'|cut -d"%" -f1 |while read LINE; do
DF_HOME=`df -k | grep -i /home | awk '{print $5}'|cut -d"%" -f1`
if [ $DF_HOME -gt $FULL ]; then
 echo "The Filesystem /home on `hostname` is full (more than $FULL)"
 exit -1
fi
# prepare workspace: copy build.properties, script, jar into working space
cp -R $SOURCE_WORKSPACE/* $TARGET_WORKSPACE
cp -R $MAVEN_SOURCE/* $MAVEN_TARGET

# give rights for all execute files
cd $TARGET_WORKSPACE
chown -R stager test_generator.sh
chown -R stager docbook.sh
chown -R stager Gendoc.jar

chmod +x test_generator.sh
chmod +x docbook.sh
chmod +x Gendoc.jar
exit 0