#!/bin/bash
# Prepare the directory where to build the SIDE release
# 1st parameter = source workspace path of the buildHuson project
# 2nd parameter = target workspace path of the buildHuson project where the build will be performed
# 3rd parameter = percent representing disk full; if not enough disk space (more thant this value), build is stopped
# 4th parameter = Integration module source directory for Maven
# 5th parameter = Maven working dir
# 6th parameter = directory where to build the build.jar of the build process com.bluexml.side.integration.buildHudson
if [ $# -eq 6 ]; then
  SOURCE_WORKSPACE=$1
  TARGET_WORKSPACE=$2
  FULL=$3
  MAVEN_SOURCE=$4
  MAVEN_TARGET=$5
  BUILD_JAR=$6
else
  echo "Usage: prepare_workspace.sh SOURCE_WORKSPACE TARGET_WORKSPACE FULL MAVEN_SOURCE MAVEN_TARGET BUILD_JAR"
  echo "       with SOURCE_WORKSPACE =  source workspace path of the buildHuson project"
  echo "            TARGET_WORKSPACE =  target workspace path of the buildHuson project where the build will be performed"
  echo "            FULL =  percent representing disk full; if not enough disk space (more thant this value), build is stopped"
  echo "            MAVEN_SOURCE =  Integration module source directory for Maven"
  echo "            MAVEN_TARGET =  Maven working dir"
  echo "            BUILD_JAR =  directory where to build the build.jar of the build process com.bluexml.side.integration.buildHudson "
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
mkdir bin
ant main
if [ -f Build.jar ]; then
  cp -f Build.jar $TARGET_WORKSPACE
else
 echo "Unable to build the jar file to compile all the SIDE source!"
 exit -3
fi

# give rights for all execute files
cd $TARGET_WORKSPACE
chmod +x -R *.sh
chmod +x -R */*.sh
chmod +x -R *.jar
chmod +x -R */*.jar
dos2unix *.sh
exit 0
