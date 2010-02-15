#SIDE_HOME=/Users/davidabad/Workspace2.0/S-IDE
#BUILDER_HOME=$SIDE_HOME/Integration/trunk/com.bluexml.side.Integration.buildHudson/config/productBuilder

BUILDER_HOME=.
SIDE_HOME=$BUILDER_HOME/../../../../..

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

## Eclipse preparation
rm -rf $ECLIPSE_BUILDER
mkdir -p $ECLIPSE_BUILDER
cd $WORKDIR
unzip $EclipseZIP
mkdir -p eclipse/deltapack
cd $ECLIPSE_BUILDER/deltapack
unzip $EclipseDeltaPack
# copy plugins
cp -r $SIDE_HOME/MetaModel/*/trunk/* $ECLIPSE_BUILDER/plugins
cp -r $SIDE_HOME/Util/trunk/* $ECLIPSE_BUILDER/plugins
cp -r $SIDE_HOME/Deployer/trunk/* $ECLIPSE_BUILDER/plugins
cp -r $SIDE_HOME/Integration/trunk/com.bluexml.side.Integration.eclipse* $ECLIPSE_BUILDER/plugins

# clean previous build
rm -rf $BUILDER_HOME/dist
rm -rf $BUILDER_HOME/buildDirectory
mkdir -p $BUILDER_HOME/dist
mkdir -p $BUILDER_HOME/buildDirectory
# run builder
cd $BUILDER_HOME
ant pde-build2
