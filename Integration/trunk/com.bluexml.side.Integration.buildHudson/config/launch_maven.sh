#!/bin/bash
# Prepare maven workspace and launch maven deploy
# 1st parameter =  workspace path of the buildHuson project
if [ $# -eq 2 ]; then
  WORKSPACE=$1
  REPOSITORY_COPY=$2
else
  echo "Usage: launch_maven.sh WORKSPACE REPOSITORY_COPY"
  echo "       with WORKSPACE =  workspace path of the buildHuson project"
  echo "       with REPOSITORY_COPY =  workspace path of SIDE sources (certainly equals to WORKSPACE)"
  exit -2
fi

if [ -f launch_maven_subProcess.sh ]; then
	chmod +x launch_maven_subProcess.sh
	mkdir -p $WORKSPACE/../buildAuto/Ankle/maven_mojo_workspace
	chmod -R 755 $WORKSPACE/../buildAuto/Ankle/maven_mojo_workspace
	cp -R $REPOSITORY_COPY/S-IDE/Integration/trunk/* $WORKSPACE/../buildAuto/Ankle/maven_mojo_workspace
	
	echo "deploy mojos"
	./launch_maven_subProcess.sh $WORKSPACE maven_mojo_workspace
	
	mkdir -p $WORKSPACE/../buildAuto/Ankle/maven_workspace
	chmod -R 755 $WORKSPACE/../buildAuto/Ankle/maven_workspace
	cp -R $REPOSITORY_COPY/S-IDE/FrameworksModules/trunk/* $WORKSPACE/../buildAuto/Ankle/maven_workspace
	cp -R $REPOSITORY_COPY/S-IDE/MetaModel/Form/trunk/com.bluexml.side.Form.XFormsUtils $WORKSPACE/../buildAuto/Ankle/maven_workspace
	echo "deploy other m2 projects"
	./launch_maven_subProcess.sh $WORKSPACE maven_workspace

else
  echo "Usage: launch_maven_subProcess must exist"
fi






