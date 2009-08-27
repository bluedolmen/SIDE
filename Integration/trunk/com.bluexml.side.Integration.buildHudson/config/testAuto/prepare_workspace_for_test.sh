#! /bin/bash
# first test disk space: if full, stop build
# 1st parameter = source workspace path
# 2nd parameter = target workspace path
# 3rd parameter = percent representing disk full
if [ $# -eq 3 ]; then
  SOURCE_WORKSPACE=$1
  TARGET_WORKSPACE=$2
  FULL=$3
else
  exit -2
fi

# delete workingcopy repository
rm -rf $TARGET_WORKSPACE

#$DF_HOME=`df -k | grep -i /home | awk '{print $5}'|cut -d"%" -f1 |while read LINE; do
DF_HOME=`df -k | grep -i /home | awk '{print $5}'|cut -d"%" -f1`
if [ $DF_HOME -gt $FULL ]; then
 echo "The Filesystem /home on `hostname` is full (more than $FULL)"
 exit -1
fi
# prepare workspace: copy build.properties, script, jar into working space
cp -R $SOURCE_WORKSPACE/ $TARGET_WORKSPACE

exit 0