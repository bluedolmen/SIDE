#! /bin/bash
# After preparation of the directory where to build the SIDE Labs release, 
# this script updates the source of SIDE to keep only what is useful for the Labs release.
# In particular, all what is License management is removed.
# 1st parameter = SIDE source directory
# 2nd parameter = Build directory
if [ $# -eq 2 ]; then
  SOURCE_PATH=$1
  BUILD_PATH=$2
else
  echo "Usage: remove_security.sh SOURCE_PATH BUILD_PATH"
  echo "       with SOURCE_PATH =  source workspace path of SIDE"
  echo "            BUILD_PATH =  directory where the build will be performed"
  exit -1
fi

# build the openSourcePublication project to change license header of source file
#cd $BUILD_PATH
#mkdir buildLicense
#cp -R $SOURCE_PATH/S-IDE/Integration/trunk/com.bluexml.side.Integration.openSourcePublication/* $BUILD_PATH/buildLicense
#cd $BUILD_PATH/buildLicense
#mkdir bin
#ant main
#if [ -f openSourceLicenseHeader.jar ]; then
#  chmod +x openSourceLicenseHeader.jar
#else
# echo "Unable to build the jar file to change the License and copyright header in source files!"
# exit -2
#fi


cd $BUILD_PATH
# remove projects not to integrate in labs release
# rm -rf $SOURCE_PATH/S-IDE/Integration
#for f in `ls $SOURCE_PATH/S-IDE/Integration/trunk | grep -v -e 'com.bluexml.side.Integration.eclipse.*' | grep -v -e 'com.bluexml.side.Integration.alfresco.sql' `
#do
#     rm -rf $SOURCE_PATH/S-IDE/Integration/trunk/$f
#done
rm -rf $SOURCE_PATH/S-IDE/Util/trunk/com.bluexml.side.Util.security
rm -rf $SOURCE_PATH/S-IDE/Experimental
rm -rf $SOURCE_PATH/S-IDE/Integration/trunk/com.bluexml.side.Integration.eclipse.builder
rm -rf $SOURCE_PATH/S-IDE/MetaModel/Workflow/trunk/com.bluexml.side.Workflow.modeler.actions

# delete svn folder
echo "Delete svn folder"
for f in `find $SOURCE_PATH -type d -name ".svn"`; do
	rm -rf $f
done


perl -ni -e 'print unless /com.bluexml.side.Integration.eclipse.builder/' $SOURCE_PATH/S-IDE/MetaModel/Common/trunk/com.bluexml.side.Common.core.feature/feature.xml
rm $SOURCE_PATH/S-IDE/Integration/trunk/com.bluexml.side.Integration.eclipse.branding/splash.bmp
cp $SOURCE_PATH/S-IDE/Integration/trunk/com.bluexml.side.Integration.eclipse.branding/splash-LABS.bmp $SOURCE_PATH/S-IDE/Integration/trunk/com.bluexml.side.Integration.eclipse.branding/splash.bmp

echo "Process java file to remove reference to package security"
for f in `find $SOURCE_PATH -type f -name "*.java"`; do
	
	# find the text and replace it
	perl -p -i -e 's/com.bluexml.side.util.security.preferences.SWTResourceManager/com.swtdesigner.SWTResourceManager/g' $f
	perl -p -i -e 's/implements Checkable/ /g' $f
	perl -p -i -e 's/, Checkable \{/ \{/g' $f

	# delete line having the pattern 'com.bluexml.side.Util.security'
	perl -ni -e 'print unless /com.bluexml.side.Util.security/' $f
	perl -ni -e 'print unless /com.bluexml.side.util.security./' $f
	perl -ni -e 'print unless /SidePreferences.getKeys/' $f
	
	
	if grep -n "public boolean check[ ]*(" $f
	then		
		# delete every line betwen the pattern 'public boolean check' and '}'
		perl -pi -e 'if(/public boolean check/../\}/){s/^.*$//s unless /(public boolean check|\})/}' $f		
		line1=`grep -n "public boolean check" $f`
		num=`expr match "$line1" '.*\(^[0-9]*\)'`
		num1=$(($num+1))
		perl -pi -e 'print "return true;\n" if $. == "'$num1'"' $f
		#sed -e ''$num','$num1'd' $f > e.txt
		#mv e.txt $f
	fi
	if grep -n "public static Boolean checkElementValidity" $f
	then		
		# delete every line betwen the pattern 'public boolean check' and '}'
		perl -pi -e 'if(/public static Boolean checkElementValidity/../return null;/){s/^.*$//s unless /(public static Boolean checkElementValidity|return null;)/}' $f		
		line1=`grep -n "public static Boolean checkElementValidity" $f`
		num=`expr match "$line1" '.*\(^[0-9]*\)'`
		num1=$(($num+1))
		perl -pi -e 's/return null/return true/ if $. == "'$num1'"' $f
		#sed -e ''$num','$num1'd' $f > e.txt
		#mv e.txt $f
	fi
done

echo "Process feature.xml file to remove reference to package security"
for f in `find $SOURCE_PATH -type f -name "feature.xml"`; do

	perl -0 -p -i -e 's/( *)<plugin( *)(\s+)( *)id="com.bluexml.side.Util.security"[^<]*//sg' $f
done

echo "Process xml file to remove reference to package security"
for f in `find $SOURCE_PATH -type f -name "*.xml"`; do
	# delete line having the pattern 'com.bluexml.side.Util.security'
	perl -ni -e 'print unless /com.bluexml.side.Util.security/' $f
done

echo "Process Manifest file to remove reference to package security"
for f in `find $SOURCE_PATH -type f -name "*.MF"`; do
	# delete line having the pattern 'com.bluexml.side.Util.security'
	perl -ni -e 'print unless /com.bluexml.side.Util.security/' $f
done

# modify header of the source file with license mention and copyright using the openSourcePublication project
echo "Modify header of source file using the openSourcePublication project"
cd $BUILD_PATH/labs
#java -jar ../buildLicense/openSourceLicenseHeader.jar $SOURCE_PATH
chmod +x add_licence.sh
./add_licence.sh LICENSE-notices $SOURCE_PATH


# modify header of the source file with license mention and copyright using the openSourcePublication project
echo "Update build.properties used by the buildHudson build jar in order to point on"
echo "SIDE-Labs instead of SIDE-Alfresco and Build_SIDE_Labs instead of Build_SIDE"
cd $BUILD_PATH
perl -p -i -e 's/SIDE-Alfresco/SIDE-Labs/g' build.properties
perl -p -i -e 's/Build_SIDE/Build_SIDE_Labs/g' build.properties
perl -p -i -e 's/projectExcluded=(.*)$/projectExcluded=Util&com.bluexml.side.Util.security,Integration&com.bluexml.side.Integration.eclipse.builder,MetaModel/Workflow&com.bluexml.side.Workflow.modeler.actions,Integration&com.bluexml.side.Integration.standAlone.metamodel.documentation,Integration&com.bluexml.side.Integration.standAlone/g' build.properties

echo "Build & Source updating performed for Labs"
