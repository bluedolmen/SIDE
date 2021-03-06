#!/bin/bash
# Prepare maven workspace and launch maven deploy
# 1st parameter =  workspace path of the buildHuson project
if [ $# -eq 2 ]; then
  WORKSPACE=$1
  WORKING_DIR=$2
else
  echo "Usage: launch_maven.sh WORKSPACE WORKING_DIR"
  echo "       with WORKSPACE =  the working directory -> workspace/work "
  echo "       with WORKING_DIR =  maven projects location"
  exit -2
fi

if [ -f delete_folder.pl ]; then
	cp delete_folder.pl $WORKSPACE/$WORKING_DIR
	cd $WORKSPACE/$WORKING_DIR
	perl delete_folder.pl pom.xml
	cp $WORKSPACE/superpom/pom.xml .
	
	echo "update superpom"
	perl -pi -le 'print "<modules>" if $. == 13' pom.xml
	perl -pi -le 'print "</modules>" if $. == 14' pom.xml
	
	# only add changed projects the changed projects are in the updatedMavenProjects file (generated by versionUpdater//ProjectVersionUpdater.java)
	f1=$WORKSPACE/projectList.txt
	f2=$WORKSPACE/updatedMavenProjects.txt
	
	ls -d * > $f1
	
	for s in `grep -xF -f $f1 $f2`; do
	    repertoire=$s
		name=`perl -e '"'$repertoire'"=~/([a-zA-Z0-9._]*)/; print $1';`
	  	res="<module>$name</module>"
	  	echo $repertoire $name $res
		perl -pi -le 'print "'$res'" if $. == 14' pom.xml
	done
	echo "superpom patched"
	echo "Maven deploy start"
	mvn clean deploy -e -P public | tee log_maven.log
	echo "Maven deploy done"

	if grep "BUILD SUCCESSFUL" log_maven.log ; then
		echo "Maven deploy success"
		sleep 1m
	else
	
		if grep "ERROR" log_maven.log ; then	
			echo "Maven deploy failed"
			exit -2
		else
			echo "Maven deploy no error"
		fi		
	fi	
	exit 0

else
   echo "Usage: delete_folder.pl must exist"
   exit -2
fi
