echo "== build Maven repository archives =="
WORKSPACE=/Users/davidabad/.hudson/jobs/SIDEPRODUCTFULLBuild/workspace
WK=$WORKSPACE/mavenarchivestmp
SIDEHOME=$WORKSPACE/S-IDE
POM_PATCHER_PROJECT=$WORKSPACE/repositoryBuilderForSIDE
POM_PATCHER=$POM_PATCHER_PROJECT/target/repositoryBuilderForSIDE-0.0.1-jar-with-dependencies.jar
POM_IN=$WORKSPACE/superpom/pom.xml
PROPERTIES=$WORKSPACE/buildHundson/config/build.davidabad.build_RCP_All.properties

#SVN_AUTH="--username build-auto --password build.auto"

# get sources and apply script for labs
echo $WORKSPACE
cd $WORKSPACE

rm -rf $POM_PATCHER_PROJECT
cp -rf S-IDE/Integration/trunk/com.bluexml.side.Integration.buildHudson/config/repositoryBuilderForSIDE .
cd $POM_PATCHER_PROJECT
#mvn clean package

cd $WORKSPACE
rm -rf $WK
mkdir -p $WK
echo "search products and build archives for each"
for f in `find . -type f -name '*.product'`; do
  filename=`basename ${f}`
  extension=${filename##*.}
  filename=${filename%.*}
  mkdir -p $filename
  cd $filename
  echo "do job for $f"
  cp $POM_IN .
  java -jar $POM_PATCHER $SIDEHOME patchPom pom.xml $f $PROPERTIES
done


echo "must run this for each product but the archive"
echo "repo archives disabled"
