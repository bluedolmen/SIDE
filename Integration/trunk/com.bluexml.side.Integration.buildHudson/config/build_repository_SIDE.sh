#!/bin/bash
# Prepare maven workspace and launch maven deploy
# 1st parameter =  workspace path of the buildHuson project
if [ $# -eq 2 ]; then
  WORKSPACE=$1
  REPOSITORY_COPY=$2
else
  echo "Usage: launch_maven.sh WORKSPACE REPOSITORY_COPY"
  echo "       with WORKSPACE =  workspace path of the buildHuson project"
  echo "       with REPOSITORY_COPY =  workspace copy path of the S-IDE source"
  exit -2
fi

# configuration
SIDEHOME=$WORKSPACE/../buildAuto/Ankle/repositoryCopy/S-IDE
BUILDERHOME=$WORKSPACE/../buildAuto/Ankle/buildAllMaven
POM_PATCHER=$WORKSPACE/../buildAuto/Ankle/repositoryBuilderForSIDE/target/repositoryBuilderForSIDE-0.0.1-jar-with-dependencies.jar
POM_IN=$WORKSPACE/../../superpom/workspace/superpom/pom.xml
MAVENREPO_ARCHIVE=$WORKSPACE/S-IDE/Util/trunk/com.bluexml.side.Util.dependencies/src/com/bluexml/side/util/dependencies/mavenRepository/m2repositoryForSIDE.zip


# set constantes
WORKDIR=$BUILDERHOME/projects
POM_OUT=$WORKDIR/pom.xml
MAVENREPO=$BUILDERHOME/repository

# display all constantes
echo ==================
echo SIDEHOME=$SIDEHOME
echo BUILDERHOME=$BUILDERHOME
echo WORKDIR=$WORKDIR
echo POM_PATCHER=$POM_PATCHER
echo POM_OUT=$POM_OUT
echo POM_IN=$POM_IN
echo MAVENREPO=$MAVENREPO
echo MAVENREPO_ARCHIVE=$MAVENREPO_ARCHIVE
echo ==================


cp -R $WORKSPACE/S-IDE/Integration/trunk/com.bluexml.side.Integration.buildHudson/config/repositoryBuilderForSIDE $WORKSPACE/../buildAuto/Ankle/
cd $WORKSPACE/../buildAuto/Ankle/repositoryBuilderForSIDE
mvn package

cd $WORKSPACE

# clean working directories
rm -rf $BUILDERHOME/*
mkdir -p $WORKDIR
mkdir -p $MAVENREPO
# copy ressources
cp $POM_PATCHER $WORKDIR
cp $POM_IN $POM_OUT
cd $WORKDIR;
# patch superpom file (add as dependencies all extension used by side components)
java -jar $POM_PATCHER $SIDEHOME $POM_OUT
# build archive of all maven requirements
mvn dependency:go-offline -P public -Dmaven.repo.local=$MAVENREPO
cd $MAVENREPO

if [ -f $MAVENREPO_ARCHIVE ] ; then
	rm $MAVENREPO_ARCHIVE	
fi


zip -r $MAVENREPO_ARCHIVE .


if [ -f $MAVENREPO_ARCHIVE ] ; then
	echo "Build repository SIDE success"
else
	echo "Build repository SIDE failed"
	exit -1
	
fi

exit 0
