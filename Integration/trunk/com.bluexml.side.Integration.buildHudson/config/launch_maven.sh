#!/bin/bash
# Prepare maven workspace and launch maven deploy
# 1st parameter =  workspace path of the buildHuson project
if [ $# -eq 1 ]; then
  WORKSPACE=$1
else
  echo "Usage: launch_maven.sh WORKSPACE"
  echo "       with WORKSPACE =  workspace path of the buildHuson project"
  exit -2
fi

if [ -f launch_maven_subProcess.sh ]; then
	chmod +x launch_maven_subProcess.sh
	mkdir -p $WORKSPACE/work/maven_mojo_workspace
	cp -R $WORKSPACE/S-IDE/Integration/trunk/* $WORKSPACE/work/maven_mojo_workspace
	chmod -R 755 $WORKSPACE/work/maven_mojo_workspace	
	
	echo "deploy mojos"
	./launch_maven_subProcess.sh $WORKSPACE maven_mojo_workspace
	
	mkdir -p $WORKSPACE/work/maven_workspace

	cp -R $WORKSPACE/S-IDE/FrameworksModules/trunk/* $WORKSPACE/work/maven_workspace
	cp -R $WORKSPACE/S-IDE/MetaModel/Form/trunk/com.bluexml.side.Form.XFormsUtils $WORKSPACE/work/maven_workspace
	chmod -R 755 $WORKSPACE/work/maven_workspace
	
	echo "deploy other m2 projects"	
	./launch_maven_subProcess.sh $WORKSPACE maven_workspace

else
  echo "Usage: launch_maven_subProcess must exist"
  exit -2
fi
