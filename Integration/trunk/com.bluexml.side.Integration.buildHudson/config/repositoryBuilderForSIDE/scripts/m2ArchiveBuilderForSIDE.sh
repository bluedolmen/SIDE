# configuration
SIDEHOME=/Users/davidabad/Workspace2.0/S-IDE
BUILDERHOME=/Users/davidabad/Workspace2.0/buildAllMaven
POM_PATCHER=/Users/davidabad/Workspace2.0/repositoryBuilderForSIDE/target/repositoryBuilderForSIDE-0.0.1-jar-with-dependencies.jar
POM_IN=/Users/davidabad/Workspace2.0/superpom/pom.xml
MAVENREPO_ARCHIVE=/Users/davidabad/Workspace2.0/S-IDE/Util/trunk/com.bluexml.side.Util.dependencies/src/com/bluexml/side/util/dependencies/mavenRepository/m2repositoryForSIDE.zip


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


# clean working directories
#rm -rf $BUILDERHOME/*
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
zip -r $MAVENREPO_ARCHIVE .
