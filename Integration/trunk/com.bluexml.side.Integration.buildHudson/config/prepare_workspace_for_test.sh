#! /bin/bash
# first test disk space: if full, stop build
# 1st parameter = source workspace path
# 2nd parameter = target workspace path
# 3rd parameter = percent representing disk full
if [ $# -eq 4 ]; then
  SOURCE_WORKSPACE=$1
  TARGET_WORKSPACE=$2
  FULL=$3
  OUTPUT_FILE=$4
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
cp -R $SOURCE_WORKSPACE/* $TARGET_WORKSPACE

# create file for the result
touch $OUTPUT_FILE
cho "<html><head><title>S-IDE Automatic Tests</title></head>" >> $OUTPUT_FILE
echo "<body><h1>S-IDE Automatic Generator tests</h1>" >> $OUTPUT_FILE

exit 0