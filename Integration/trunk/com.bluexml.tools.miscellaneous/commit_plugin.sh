#!/bin/bash


commitProject() {

echo "Share New Project $1"

PROJECT_PATH=$1
PROJECT_NAME=${PROJECT_PATH##*/}
echo "ProjectName :$PROJECT_NAME"
TYPE=`echo $PROJECT_NAME | sed -E "s/com.bluexml.side.([^\.]*).*/\1/"`
if [ "$TYPE" == "Util" ] || [ "$TYPE" == "Deployer" ]
then
SVN_ROOT=http://svn.bluexml.com/private/S-IDE/$TYPE/trunk
elif [ "$TYPE" == "Framework" ]
then
SVN_ROOT=http://svn.bluexml.com/private/S-IDE/FrameworksModules/trunk
else
SVN_ROOT=http://svn.bluexml.com/private/S-IDE/MetaModel/$TYPE/trunk
fi

if [ "$TYPE" == "Framework" ]
then
IGNORE="target"
else
IGNORE="bin"
fi
echo "SVN_ROOT : $SVN_ROOT"
cd $PROJECT_PATH/..
if test ! -d $PROJECT_NAME/.svn
then
echo "project must be created :"
echo "svn mkdir --parents -m \"Initial import.\" $SVN_ROOT/$PROJECT_NAME"
svn mkdir --parents -m "Initial import." $SVN_ROOT/$PROJECT_NAME
echo "svn checkout $SVN_ROOT/$PROJECT_NAME -r HEAD --depth=immediates --force"
svn checkout $SVN_ROOT/$PROJECT_NAME -r HEAD --depth=immediates --force
else
echo "project is a working copy : $PROJECT_NAME"
fi

cd $PROJECT_NAME
pwd
echo "svn propset svn:ignore \"$IGNORE\" ."
svn propset svn:ignore $IGNORE .

for f in `find . ! -path "./$IGNORE*" -and ! -name "." -and ! -path "./.svn*"`
do
echo "svn add -N $f;"
svn add -N $f;
done

echo "svn commit -m \"Initial import.\""
svn commit -m "Initial import."
}

# use readlink to ensure than the commitProject have absolute
SCRIPT_HOME=`readlink -f $1`
echo "SEARCH in find $SCRIPT_HOME -type d -maxdepth 1 -and ! -name \"${SCRIPT_HOME##*/}\""
for d in `find $SCRIPT_HOME -type d -maxdepth 1 -and ! -name "${SCRIPT_HOME##*/}"`
do
commitProject $d
done
