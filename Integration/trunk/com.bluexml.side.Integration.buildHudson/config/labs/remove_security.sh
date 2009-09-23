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
cd $BUILD_PATH
# remove projects not to integrate in labs release
rm -rf $SOURCE_PATH/S-IDE/Integration
rm -rf $SOURCE_PATH/S-IDE/Util/trunk/com.bluexml.side.Util.security
rm -rf $SOURCE_PATH/S-IDE/Experimental

# delete svn folder
for f in `find $SOURCE_PATH -type d -name ".svn"`; do
	rm -rf $f
done

for f in `find $SOURCE_PATH -type f -name "*.java"`; do
	
	# find the text and replace it
	perl -p -i -e 's/com.bluexml.side.util.security.preferences.SWTResourceManager/com.swtdesigner.SWTResourceManager/g' $f
	perl -p -i -e 's/implements Checkable/ /g' $f
	perl -p -i -e 's/, Checkable \{/ \{/g' $f

	# delete line having the pattern 'com.bluexml.side.Util.security'
	perl -ni -e 'print unless /com.bluexml.side.Util.security/' $f
	perl -ni -e 'print unless /com.bluexml.side.util.security./' $f
	
	
	if grep -n "public boolean check" $f
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

for f in `find $SOURCE_PATH -type f -name "feature.xml"`; do

	perl -0 -p -e 's/( *)<plugin( *)(\s+)( *)id="com.bluexml.side.Util.security"[^<]*//sg' $f
done

for f in `find $SOURCE_PATH -type f -name "*.xml"`; do
	# delete line having the pattern 'com.bluexml.side.Util.security'
	perl -ni -e 'print unless /com.bluexml.side.Util.security/' $f
done

for f in `find $SOURCE_PATH -type f -name "*.MF"`; do
	# delete line having the pattern 'com.bluexml.side.Util.security'
	perl -ni -e 'print unless /com.bluexml.side.Util.security/' $f
done

# modify header of the source file with license mention and copyright using the openSourcePublication project
cd $BUILD_PATH
mkdir buildLicense
cp -R $SOURCE_PATH/SIDE/Integration/trunk/com.bluexml.side.Integration.openSourcePublication $BUILD_PATH/buildLicense
cd $BUILD_PATH/buildLicense
mkdir bin
ant main
if [ -f openSourceLicenseHeader.jar ]; then
  chmod +x openSourceLicenseHeader.jar
  cd ../labs 
  java -jar ../buildLicense/openSourceLicenseHeader.jar $SOURCE_PATH
else
 echo "Unable to build the jar file to change the License and copyright header in source files!"
 exit -2
fi

