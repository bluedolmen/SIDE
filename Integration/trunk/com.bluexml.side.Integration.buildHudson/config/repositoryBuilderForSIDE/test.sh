echo "Build SIDE Labs RCP"

# configuration
WORKSPACE=/Users/davidabad/Workspace2.0
SIDEHOME=$WORKSPACE/S-IDE
BUILDERHOME=$WORKSPACE/buildAllMaven
POM_PATCHER_PROJECT=$SIDEHOME/Integration/trunk/com.bluexml.side.Integration.buildHudson/config/repositoryBuilderForSIDE
POM_IN=$WORKSPACE/superpom/pom.xml
MAVENREPO_ARCHIVE_DIR=$SIDEHOME/Util/trunk/com.bluexml.side.Util.dependencies.repository/src/com/bluexml/side/util/dependencies/repository/mavenRepository
MAVENREPO_ARCHIVE=$MAVENREPO_ARCHIVE_DIR/m2repositoryForSIDE.zip


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
echo ==================

# install superpom
cd $WORKSPACE/superpom
mvn install
# build patcher jar
cd $POM_PATCHER_PROJECT
mvn clean package


# clean working directories
rm -rf $BUILDERHOME/*
mkdir -p $WORKDIR
mkdir -p $MAVENREPO
# copy ressources
cp $POM_PATCHER $WORKDIR
cp $POM_IN $POM_OUT
cp $POM_PATCHER_PROJECT/scripts/patcher.sh $WORKDIR
cd $WORKDIR;
# start to include in repo some requiremenets that can't be included in same project
sh patcher.sh $MAVENREPO
# patch superpom file (add as dependencies all extension used by side components)
echo java -jar $POM_PATCHER $SIDEHOME -patchPom $POM_OUT -makeDot $MAVENREPO_ARCHIVE_DIR/dependencies.dot
java -jar $POM_PATCHER $SIDEHOME -patchPom $POM_OUT -makeDot $MAVENREPO_ARCHIVE_DIR/dependencies.dot