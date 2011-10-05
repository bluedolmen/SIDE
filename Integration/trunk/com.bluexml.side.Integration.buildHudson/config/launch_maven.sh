#!/bin/bash
# Prepare maven workspace and launch maven deploy
# 1st parameter =  directory where do the jobs
# 2nd parameter = directory where found SIDE sources (workspace/S-IDE)
if [ $# -eq 2 ]; then
  WORKSPACE=$1
  SIDE_SRC=$2
else
  echo "Usage: launch_maven.sh WORKSPACE"
  echo "       with WORKSPACE =  workspace path of the buildHuson project"
  exit -2
fi

if [ -f launch_maven_subProcess.sh ]; then
	chmod +x launch_maven_subProcess.sh
	mkdir -p $WORKSPACE/maven_mojo_workspace
	cp -R $SIDE_SRC/Integration/trunk/* $WORKSPACE/maven_mojo_workspace
	chmod -R 755 $WORKSPACE/maven_mojo_workspace	
	
	echo "deploy mojos"
	./launch_maven_subProcess.sh $WORKSPACE maven_mojo_workspace
	
	mkdir -p $WORKSPACE/maven_workspace

	cp -R $SIDE_SRC/FrameworksModules/trunk/* $WORKSPACE/maven_workspace
	cp -R $SIDE_SRC/MetaModel/Form/trunk/com.bluexml.side.Form.XFormsUtils $WORKSPACE/maven_workspace
	chmod -R 755 $WORKSPACE/maven_workspace
	
	echo "deploy other m2 projects"	
	./launch_maven_subProcess.sh $WORKSPACE maven_workspace

else
  echo "Usage: launch_maven_subProcess must exist"
  exit -2
fi
