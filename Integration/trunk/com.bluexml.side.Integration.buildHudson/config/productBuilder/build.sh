WORKSPACE=/Users/davidabad/.hudson/jobs/SIDE_Enterprise_Product_Builder/workspace
EclipseZIP=/Users/davidabad/Archive/eclipse3.5.1ForSIDE.zip
EclipseDeltaPack=/Users/davidabad/Archive/eclipse-3.5.1-delta-pack.tar.gz



SIDE_HOME=$WORKSPACE/S-IDE
BUILDER_HOME=$SIDE_HOME/Integration/trunk/com.bluexml.side.Integration.buildHudson/config/productBuilder
#BUILDER_HOME=/Users/davidabad/Workspace2.0/S-IDE/Integration/trunk/com.bluexml.side.Integration.buildHudson/config/productBuilder

WORKDIR=$WORKSPACE/work

ECLIPSE_BUILDER=$WORKDIR/eclipse
ECLIPSE_TOBUILD=$WORKSPACE/sources

SIDE_BUILDS=$WORKSPACE/dist
SIDE_BUILDS_PUBLIC=/Users/davidabad/Desktop/dist

echo "=========="
echo WORKSPACE			=$WORKSPACE 
echo SIDE_HOME			=$SIDE_HOME
echo BUILDER_HOME		=$BUILDER_HOME
echo EclipseZIP			=$EclipseZIP
echo EclipseDeltaPack	=$EclipseDeltaPack
echo WORKDIR			=$WORKDIR
echo ECLIPSE_BUILDER	=$ECLIPSE_BUILDER
echo ECLIPSE_TOBUILD	=$ECLIPSE_TOBUILD
echo "=========="

## Eclipse preparation

echo "== Eclipse =="
rm -rf $WORKDIR
mkdir -p $WORKDIR
cd $WORKDIR
unzip $EclipseZIP
mkdir -p eclipse/deltapack
cd $ECLIPSE_BUILDER/deltapack
tar -xvzf $EclipseDeltaPack

echo "== copy plugins from SIDE source =="
rm -rf $ECLIPSE_TOBUILD
mkdir -p $ECLIPSE_TOBUILD/plugins
cp -rf $SIDE_HOME/MetaModel/*/trunk/* $ECLIPSE_TOBUILD/plugins
cp -rf $SIDE_HOME/Util/trunk/* $ECLIPSE_TOBUILD/plugins
cp -rf $SIDE_HOME/Deployer/trunk/* $ECLIPSE_TOBUILD/plugins
cp -rf $SIDE_HOME/Integration/trunk/com.bluexml.side.Integration.eclipse* $ECLIPSE_TOBUILD/plugins

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
ant pde-build2

## copy side RCP
echo "== Copy builds to shared folder =="
rm -rf $SIDE_BUILDS_PUBLIC
mkdir -p $SIDE_BUILDS_PUBLIC
cp -rf $SIDE_BUILDS/* $SIDE_BUILDS_PUBLIC
chmod a+r $SIDE_BUILDS_PUBLIC/*