#! /bin/bash
# first test disk space: if full, stop build
# 1st parameter = source workspace path
# 2nd parameter = target workspace path
# 3rd parameter = percent representing disk full
if [ $# -eq 6 ]; then
  SOURCE_WORKSPACE=$1
  TARGET_WORKSPACE=$2
  FULL=$3
  MAVEN_SOURCE=$4
  MAVEN_TARGET=$5
  BUILD_JAR=$6
else
  exit -2
fi

# delete workingcopy repository
rm -rf $TARGET_WORKSPACE/workingcopy
rm -rf $MAVEN_TARGET
rm -rf $BUILD_JAR

mkdir $MAVEN_TARGET
mkdir $BUILD_JAR

#$DF_HOME=`df -k | grep -i /home | awk '{print $5}'|cut -d"%" -f1 |while read LINE; do
DF_HOME=`df -k | grep -i /home | awk '{print $5}'|cut -d"%" -f1`
if [ $DF_HOME -gt $FULL ]; then
 echo "The Filesystem /home on `hostname` is full (more than $FULL)"
 exit -1
fi
# prepare workspace: copy build.properties, script, jar into working space
cp -R $SOURCE_WORKSPACE/* $TARGET_WORKSPACE
cp -R $MAVEN_SOURCE/* $MAVEN_TARGET
cp -R $SOURCE_WORKSPACE/../* $BUILD_JAR

cd $BUILD_JAR
rm -f buildForLinux.properties
rm -f buildForWindows.properties
rm -rf config
jar cvfm Build.jar MANIFEST.MF .
cp -f Build.jar $BUILD_JAR/..

# give rights for all execute files
cd $TARGET_WORKSPACE
chown -R stager test_generator.sh
chown -R stager docbook.sh
chown -R stager Gendoc.jar

chmod +x test_generator.sh
chmod +x docbook.sh
chmod +x Gendoc.jar
exit 0