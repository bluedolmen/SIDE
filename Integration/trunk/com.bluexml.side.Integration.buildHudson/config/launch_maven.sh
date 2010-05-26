#!/bin/bash
# Prepare maven workspace and launch maven deploy
# 1st parameter =  workspace path of the buildHuson project
if [ $# -eq 2 ]; then
  WORKSPACE=$1
  REPOSITORY_COPY=$2
else
  echo "Usage: launch_maven.sh WORKSPACE"
  echo "       with WORKSPACE =  workspace path of the buildHuson project"
  exit -2
fi

cp -R $REPOSITORY_COPY/S-IDE/Integration/trunk/* $WORKSPACE/../buildAuto/Ankle/maven_workspace
cp -R $REPOSITORY_COPY/S-IDE/FrameworksModules/trunk/* $WORKSPACE/../buildAuto/Ankle/maven_workspace
cp -R $REPOSITORY_COPY/S-IDE/MetaModel/Form/trunk/com.bluexml.side.Form.utils $WORKSPACE/../buildAuto/Ankle/maven_workspace
chmod -R 755 $WORKSPACE/../buildAuto/Ankle/maven_workspace


cp $WORKSPACE/../buildAuto/Ankle/delete_folder.pl $WORKSPACE/../buildAuto/Ankle/maven_workspace
cd $WORKSPACE/../buildAuto/Ankle/maven_workspace
perl delete_folder.pl pom.xml
cp $WORKSPACE/superpom/pom.xml .

echo "update superpom"
perl -pi -le 'print "<modules>" if $. == 12' pom.xml
perl -pi -le 'print "</modules>" if $. == 13' pom.xml
for s in `ls -d */ `; do
    repertoire=$s
	name=`perl -e '"'$repertoire'"=~/([a-zA-Z0-9.]*)/; print $1';`
  	res="<module>$name</module>"
  	echo $repertoire $name $res
	perl -pi -le 'print "'$res'" if $. == 13' pom.xml
done
echo "superpom patched"
echo "Maven deploy start"
mvn clean deploy -e -P public > log_maven.log
echo "Maven deploy done"

sleep 5m

tail -100 log_maven.log

if grep "BUILD SUCCESSFUL" log_maven.log ; then
	echo "Maven deploy success"
else

	if grep "ERROR" log_maven.log ; then	
		echo "Maven deploy failed"
		exit 1
	else
		echo "Maven deploy no error"
	fi
	
fi

exit 0
