#!/bin/sh


commitProject() {
echo "Share New Project $1"
SVN_ROOT=http://svn.bluexml.com/private/S-IDE/FrameworksModules/trunk
PROJECT_PATH=$1
PROJECT_NAME=${PROJECT_PATH:2}
echo "svn mkdir --parents -m \"Initial import.\" $SVN_ROOT/$PROJECT_NAME"
if test ! -d $PROJECT_NAME
then
svn mkdir --parents -m "Initial import." $SVN_ROOT/$PROJECT_NAME
echo "svn checkout $SVN_ROOT/$PROJECT_NAME -r HEAD --depth=immediates --force"
svn checkout $SVN_ROOT/$PROJECT_NAME -r HEAD --depth=immediates --force
else
echo "project exists $PROJECT_NAME"
fi
cd $PROJECT_NAME
pwd
echo "svn propset svn:ignore \"target\" ."
svn propset svn:ignore "target" .
for f in `find . ! -path "./target*" -and ! -name "." -and ! -path "./.svn*"`
do
svn add -N $f;
done
svn commit -m "Initial import."
cd ..
}

for d in `find . -type d -maxdepth 1 -and ! -name "."`
do
commitProject $d
done