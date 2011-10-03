echo "Build SIDE Labs RCP"

# configuration
WORKSPACE=/Users/davidabad/.hudson/jobs/SIDEPRODUCTFULLBuild/workspace
SIDEHOME_DEV=/Users/davidabad/workspaces/Workspace2.0/S-IDE

SIDEHOME=$SIDEHOME_DEV
#SIDEHOME=$WORKSPACE/S-IDE

BUILDERHOME=$WORKSPACE/buildAllMaven
POM_PATCHER_PROJECT=$SIDEHOME_DEV/Integration/trunk/com.bluexml.side.Integration.buildHudson/config/repositoryBuilderForSIDE
POM_IN=$WORKSPACE/superpom/pom.xml
MAVENREPO_ARCHIVE=m2repositoryForSIDE.zip
PROPERTIES=$WORKSPACE/buildHundson/config/build.davidabad.build_RCP_All.properties

# set constantes
POM_PATCHER=$POM_PATCHER_PROJECT/target/repositoryBuilderForSIDE-0.0.1-jar-with-dependencies.jar
WORKDIR=$BUILDERHOME/projects
POM_OUT=$WORKDIR/pom.xml
MAVENREPO=$BUILDERHOME/repository

# display all constantes
echo ==================
echo WORKSPACE=$WORKSPACE
echo SIDEHOME=$SIDEHOME
echo BUILDERHOME=$BUILDERHOME
echo WORKDIR=$WORKDIR
echo POM_PATCHER=$POM_PATCHER
echo POM_OUT=$POM_OUT
echo POM_IN=$POM_IN
echo MAVENREPO=$MAVENREPO
echo MAVENREPO_ARCHIVE=$MAVENREPO_ARCHIVE
echo PROPERTIES=$PROPERTIES
echo ==================

# install superpom
cd $WORKSPACE/superpom
#mvn install
# build patcher jar
cd $POM_PATCHER_PROJECT
#mvn clean package


# clean working directories
rm -rf $BUILDERHOME/*
mkdir -p $WORKDIR
mkdir -p $MAVENREPO
# copy ressources
cp $POM_PATCHER $WORKDIR

# patch superpom file (add as dependencies all extension used by side components)
echo "search products and build archives for each"
for f in `find $SIDEHOME -type f -name '*.product'`; do
  echo "process $f"
  cd $WORKDIR
  filename=`basename ${f}`
  parentname=`dirname ${f}`
  extension=${filename##*.}
  filename=${filename%.*}
  targetDir=$parentname/src/com/bluexml/side/Integration/eclipse/branding/$filename/repository
  if [ -d $targetDir ] ; then 
    mkdir -p $filename
    cd $filename
    echo "do job for $f"
    cp $POM_IN .
    echo "java -jar $POM_PATCHER patchPom $SIDEHOME pom.xml $f $PROPERTIES"
    java -jar $POM_PATCHER patchPom $SIDEHOME pom.xml $f $PROPERTIES
    echo "== build the archive=="
    echo "=== call maven ==="
    mkdir -p $MAVENREPO
    touch $MAVENREPO/fake.text
#    sh pom.sh $MAVENREPO
    echo "zip repository"
    target=$targetDir/$MAVENREPO_ARCHIVE
    cd $MAVENREPO
    if [ -f $target ] ; then
    	rm $target	
    fi
    zip -r $target .
  else
  	echo "no repipository package found $targetDir"
  fi
done
