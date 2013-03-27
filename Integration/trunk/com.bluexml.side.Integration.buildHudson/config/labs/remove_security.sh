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

#rm -rf $SOURCE_PATH/S-IDE/Util/trunk/com.bluexml.side.Util.security.enterprise

echo "Process java file to remove reference to package security"

for f in `find $SOURCE_PATH -type f -name "*.java"`; do
    # find the text and replace it
    perl -p -i -e 's/com.bluexml.side.util.security.preferences.SWTResourceManager/com.swtdesigner.SWTResourceManager/g' $f
    perl -p -i -e 's/implements Checkable/ /g' $f
    perl -p -i -e 's/, Checkable \{/ \{/g' $f

    # delete line having the pattern 'com.bluexml.side.Util.security'
    perl -ni -e 'print unless /com.bluexml.side.Util.security.enterprise/' $f
    perl -ni -e 'print unless /SidePreferences.getKeys/' $f
    perl -ni -e 'print unless /com.bluexml.side.util.security.SecurityHelper/' $f
    perl -ni -e 'print unless /com.bluexml.side.util.security.preferences.SidePreferences/' $f

    if grep -n "public boolean check[ ]*(" $f
    then
        echo "remove enterprise check from $f"
        # delete every line betwen the pattern 'public boolean check' and '}'
        perl -pi -e 'if(/public boolean check[ ]*\(/../return/){s/^.*$//s unless /(public boolean check[ ]*\()/}' $f
        line1=`grep -n "public boolean check[ ]*(" $f`
        num=`echo $line1 | sed -n 's/\([0-9]*\).*/\1/p'`
        echo "num# $num"
        num1=$(($num+1))
        perl -pi -e 'print "\t\treturn true;\n" if $. == "'$num1'"' $f
    fi

    if grep -n "public boolean checkOption[ ]*(" $f
    then
        echo "remove enterprise checkOption "
        # delete every line betwen the pattern 'public boolean check' and '}'
        perl -pi -e 'if(/public boolean checkOption[ ]*\(/../return/){s/^.*$//s unless /(public boolean checkOption[ ]*\()/}' $f
        line1=`grep -n "public boolean checkOption[ ]*(" $f`
        num=`echo $line1 | sed -n 's/\([0-9]*\).*/\1/p'`
        echo "num# $num"
        num1=$(($num+1))
        perl -pi -e 'print "\t\treturn true;\n" if $. == "'$num1'"' $f
    fi

    if grep -n "public static Boolean checkElementValidity" $f
    then
        echo "remove checkElementValidity from $f"
        # delete every line betwen the pattern 'public boolean check' and '}'
        perl -pi -e 'if(/public static Boolean checkElementValidity/../return null;/){s/^.*$//s unless /(public static Boolean checkElementValidity|return null;)/}' $f
        line1=`grep -n "public static Boolean checkElementValidity" $f`
        num=`echo $line1 | sed -n 's/\([0-9]*\).*/\1/p'`
        num1=$(($num+1))
        perl -pi -e 's/return null/\t\treturn true/ if $. == "'$num1'"' $f
    fi
done

echo "Process feature.xml file to remove reference to package security"
for f in `find $SOURCE_PATH -type f -name "feature.xml"`; do
    echo "fix $f"
    perl -0 -p -i -e 's/( *)<plugin( *)(\s+)( *)id="com.bluexml.side.Util.security.enterprise"[^<]*//sg' $f
done

echo "Process xml file to remove reference to package security"
for f in `find $SOURCE_PATH -type f -name "*.xml"`; do
    # delete line having the pattern 'com.bluexml.side.Util.security'
    echo "fix $f"
    perl -ni -e 'print unless /com.bluexml.side.Util.security.enterprise/' $f
done

echo "Process Manifest file to remove reference to package security"
for f in `find $SOURCE_PATH -type f -name "*.MF"`; do
    # delete line having the pattern 'com.bluexml.side.Util.security'
    echo "fix $f"
    perl -ni -e 'print unless /com.bluexml.side.Util.security.enterprise/' $f
done

# modify header of the source file with license mention and copyright using the openSourcePublication project
echo "Modify header of source file using the openSourcePublication project"
cd $BUILD_PATH/labs
chmod +x add_licence.sh
#$BUILD_PATH/labs/add_licence.sh LICENSE-notices $SOURCE_PATH
