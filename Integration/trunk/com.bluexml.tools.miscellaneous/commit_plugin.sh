#!/bin/sh


commitProject() {

echo "Share New Project $1"
SVN_ROOT=http://svn.bluexml.com/private/S-IDE/MetaModel/$2/trunk
PROJECT_PATH=$1
PROJECT_NAME=${PROJECT_PATH##*/}
echo "ProjectName :$PROJECT_NAME"
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
echo "svn propset svn:ignore \"bin\" ."
svn propset svn:ignore "bin" .

for f in `find . ! -path "./bin*" -and ! -name "." -and ! -path "./.svn*"`
do
echo "svn add -N $f;"
svn add -N $f;
done

echo "svn commit -m \"Initial import.\""
svn commit -m "Initial import."
}

path=$1
type=$2

commitProject $path $type
