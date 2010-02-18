WORKSPACE=/Users/davidabad/Workspace2.0
SIDE_HOME=$WORKSPACE/S-IDE
BUILDER_HOME=$SIDE_HOME/Integration/trunk/com.bluexml.side.Integration.buildHudson/config/productBuilder
REPO_BULDER=$SIDE_HOME/Integration/trunk/com.bluexml.side.Integration.buildHudson/config/repositoryBuilderForSIDE/scripts/m2ArchiveBuilderForSIDE.sh

EclipseZIP=$BUILDER_HOME/eclipse3.5.1ForSIDE.zip
EclipseDeltaPack=$BUILDER_HOME/eclipse-3.5.1-delta-pack.zip
WORKDIR=$BUILDER_HOME/work
ECLIPSE_BUILDER=$WORKDIR/eclipse

#rm -rf $SIDE_HOME
#mkdir -p $SIDE_HOME
#cd $SIDE_HOME

#svn co http://www.bluexml.com/svn/private/S-IDE/MetaModel
#svn co http://www.bluexml.com/svn/private/S-IDE/Deployer
#svn co http://www.bluexml.com/svn/private/S-IDE/Util
#svn co http://www.bluexml.com/svn/private/S-IDE/Integration


# build mavenRepo and includes depndencies
sh $REPO_BULDER

## Eclipse preparation
echo "== Eclipse =="
rm -rf $WORKDIR
mkdir -p $WORKDIR
cd $WORKDIR
unzip $EclipseZIP
mkdir -p eclipse/deltapack
cd $ECLIPSE_BUILDER/deltapack
unzip $EclipseDeltaPack
echo "== copy plugins from SIDE source =="
cp -rf $SIDE_HOME/MetaModel/*/trunk/* $ECLIPSE_BUILDER/plugins
cp -rf $SIDE_HOME/Util/trunk/* $ECLIPSE_BUILDER/plugins
cp -rf $SIDE_HOME/Deployer/trunk/* $ECLIPSE_BUILDER/plugins
cp -rf $SIDE_HOME/Integration/trunk/com.bluexml.side.Integration.eclipse* $ECLIPSE_BUILDER/plugins
echo "== Add Fake feature includings all dependencies =="
#cp -rfv /Users/davidabad/Workspace2.0/TestRCP $ECLIPSE_BUILDER/plugins
echo "== copy features from SIDE source =="
find $SIDE_HOME -name *feature | xargs -I source cp -rfv source $ECLIPSE_BUILDER/features
# remove svn
#find $ECLIPSE_BUILDER/features -name .svn | xargs sudo rm -rf
#find $ECLIPSE_BUILDER/plugins -name .svn | xargs sudo rm -rf

echo "== clean previous build =="
rm -rf $BUILDER_HOME/dist
rm -rf $BUILDER_HOME/buildDirectory
mkdir -p $BUILDER_HOME/dist
mkdir -p $BUILDER_HOME/buildDirectory
echo "== run builder =="
cd $BUILDER_HOME
ant pde-build2 -Dworkspace=$WORKSPACE
