#WORKSPACE=/Users/davidabad/workspaces/Workspace2.0
WORKSPACE=/Volumes/Data/migrationIndigo/workspace
#EclipseZIP=/Users/davidabad/Archive/eclipse3.5.1ForSIDE.zip
#EclipseDeltaPack=/Users/davidabad/Archive/eclipse-3.5.1-delta-pack.tar.gz



SIDE_HOME=$WORKSPACE/S-IDE
#BUILDER_HOME=$SIDE_HOME/Integration/trunk/com.bluexml.side.Integration.buildHudson/config/productBuilder
BUILDER_HOME=/Users/davidabad/workspaces/Workspace2.0/S-IDE/Integration/trunk/com.bluexml.side.Integration.buildHudson/config/productBuilder

WORKDIR=$WORKSPACE/work

ECLIPSE_TOBUILD=$WORKSPACE/sources

echo "=========="
echo WORKSPACE			=$WORKSPACE
echo SIDE_HOME			=$SIDE_HOME
echo BUILDER_HOME		=$BUILDER_HOME
echo WORKDIR			=$WORKDIR
echo ECLIPSE_TOBUILD	=$ECLIPSE_TOBUILD
echo "=========="

echo "== delete WORKDIR =="
rm -rf $WORKDIR
mkdir -p $WORKDIR

echo "== delete sources to build=="
rm -rf $ECLIPSE_TOBUILD

echo "== copy plugins from SIDE source =="
mkdir -p $ECLIPSE_TOBUILD/plugins
cp -rf $SIDE_HOME/MetaModel/*/trunk/* $ECLIPSE_TOBUILD/plugins
cp -rf $SIDE_HOME/Util/trunk/* $ECLIPSE_TOBUILD/plugins
cp -rf $SIDE_HOME/Deployer/trunk/* $ECLIPSE_TOBUILD/plugins
cp -rf $SIDE_HOME/Integration/trunk/com.bluexml.side* $ECLIPSE_TOBUILD/plugins

echo "== copy features from SIDE source =="
mkdir -p $ECLIPSE_TOBUILD/features
for f in `find $SIDE_HOME -type d -name *feature`; do
     cp -rfv $f $ECLIPSE_TOBUILD/features
done


## Building
echo "== clean previous build =="
rm -rf $BUILDER_HOME/dist
mkdir -p $BUILDER_HOME/dist
echo "== run builder =="
cd $BUILDER_HOME
echo $BUILDER_HOME
ant pde-build2 -Dworkspace=$WORKSPACE
