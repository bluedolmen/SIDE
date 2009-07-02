#! /bin/bash
# launch the automatic tests of the acceleo template of all the generators
# 1st parameter = test directory where are store the standalone acceleo projects for all the s-ide generators
if [ $# -eq 1 ]; then
  TEST_DIR=$1
else
  exit -2
fi
INDEX=$TEST_DIR/index.html
echo "<html><head><title>S-IDE Automatic Generator tests</title></head>" > $INDEX
echo "<body><H1>S-IDE Automatic Generator tests</H1><UL>" >>  $INDEX
cd /home/stager/buildAuto/test
for i in *
do
DIR=$i
  if [ -d $DIR ]; then
    cd $DIR
    ./run.sh
    if [ `ls src_gen | wc -l` -gt 0 ]
    then
      echo "<li><A href="src_gen">$DIR Test</li>" >>  $INDEX
      if [ ! -d $TEST_DIR/$DIR ]; then
        mkdir $TEST_DIR/$DIR
      fi
      cp -R $DIR/src_gen $TEST_DIR/$DIR
    else
      echo "<li>$DIR Test on ERROR</li>" >>  $INDEX
    fi
    cd ..
  fi
done
echo "</ul></body></html>" >>  $INDEX