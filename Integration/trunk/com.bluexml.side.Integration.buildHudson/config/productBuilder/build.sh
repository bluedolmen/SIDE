WORKSPACE=/Users/davidabad/.hudson/jobs/SIDE_Enterprise_Product_Builder/workspace
#WORKSPACE=/Users/davidabad/Workspace2.0
SIDE_HOME=$WORKSPACE/S-IDE
#BUILDER_HOME=$SIDE_HOME/Integration/trunk/com.bluexml.side.Integration.buildHudson/config/productBuilder
BUILDER_HOME=/Users/davidabad/Workspace2.0/S-IDE/Integration/trunk/com.bluexml.side.Integration.buildHudson/config/productBuilder

REPO_BULDER=$SIDE_HOME/Integration/trunk/com.bluexml.side.Integration.buildHudson/config/repositoryBuilderForSIDE/scripts/m2ArchiveBuilderForSIDE.sh

EclipseZIP=/Users/davidabad/Archive/eclipse3.5.1ForSIDE.zip
EclipseDeltaPack=/Users/davidabad/Archive/eclipse-3.5.1-delta-pack.tar.gz
WORKDIR=$BUILDER_HOME/work
ECLIPSE_BUILDER=$WORKDIR/eclipse
ECLIPSE_TOBUILD=$WORKSPACE/sources
echo "=========="
echo WORKSPACE			=$WORKSPACE 
echo SIDE_HOME			=$SIDE_HOME
echo BUILDER_HOME		=$BUILDER_HOME
echo REPO_BULDER		=$REPO_BULDER
echo EclipseZIP			=$EclipseZIP
echo EclipseDeltaPack	=$EclipseDeltaPack
echo WORKDIR			=$WORKDIR
echo "=========="


#cd $WORKSPACE
#svn co http://www.bluexml.com/svn/private/S-IDE
#rm -rf $SIDE_HOME
#mkdir -p $SIDE_HOME

#cd $SIDE_HOME
#echo "== Update S-IDE =="
#svn update


# build mavenRepo and includes depndencies
#sh $REPO_BULDER

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
# remove svn
#find $ECLIPSE_BUILDER/features -name .svn | xargs sudo rm -rf
#find $ECLIPSE_BUILDER/plugins -name .svn | xargs sudo rm -rf

echo "== clean previous build =="
rm -rf $BUILDER_HOME/dist
mkdir -p $BUILDER_HOME/dist
echo "== run builder =="
cd $BUILDER_HOME
echo $BUILDER_HOME
ant pde-build2
