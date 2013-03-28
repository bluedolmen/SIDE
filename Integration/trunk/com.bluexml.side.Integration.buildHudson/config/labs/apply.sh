SOURCE_PATH=/Volumes/Data/SVN/side/opensource/HEAD
#SOURCE_PATH=/Volumes/Data/SVN/side/opensource/HEAD/S-IDE/MetaModel/Class/trunk/com.bluexml.side.Class.generator.alfresco.enterprise
BUILD_PATH=/Volumes/Data/SVN/side/opensource/HEAD/S-IDE/Integration/trunk/com.bluexml.side.Integration.buildHudson/config


echo "patch sources in S-IDE/MetaModel"
sh $BUILD_PATH/labs/remove_security.sh $SOURCE_PATH/S-IDE/MetaModel $BUILD_PATH;

echo "patch sources in S-IDE/Deployer"
sh $BUILD_PATH/labs/remove_security.sh $SOURCE_PATH/S-IDE/Deployer $BUILD_PATH;

echo "patch sources in S-IDE/Integration"
sh $BUILD_PATH/labs/remove_security.sh $SOURCE_PATH/S-IDE/Integration $BUILD_PATH;

echo "patch sources in S-IDE/Util"
sh $BUILD_PATH/labs/remove_security.sh $SOURCE_PATH/S-IDE/Util $BUILD_PATH;


# modify header of the source file with license mention and copyright using the openSourcePublication project
echo "Modify header of source file using the openSourcePublication project"
cd $BUILD_PATH/labs
chmod +x add_licence.sh
./add_licence.sh LICENSE-notices $SOURCE_PATH