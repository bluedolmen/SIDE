#! /bin/bash
# launch the automatic tests of the acceleo template of all the generators
# 1st parameter = test directory where are store the standalone acceleo projects for all the s-ide generators
# 2nd parameter = Update-Site path
if [ $# -eq 2 ]; then
  TEST_DIR=$1
  UPDATE_SITE=$2
else
  exit -2
fi

cd $TEST_DIR

INDEX=$TEST_DIR/index.html
echo "<html><head><title>S-IDE Automatic Generator tests</title></head>" > $INDEX
echo "<body><h1>S-IDE Automatic Generator tests</h1><ul>" >>  $INDEX

for i in *
do
DIR=$i
  if [ -d $DIR ]; then
  	cp -R $UPDATE_SITE/plugins/* $DIR/acceleo/plugins
    cd $DIR
    ./run.sh
    if [ `ls src-gen | wc -l` -gt 0 ]
    then
      echo "<li><a href="$DIR/src-gen">$DIR Test</a></li>" >>  $INDEX
      #if [ ! -d $TEST_DIR/$DIR ]; then
      #  mkdir $TEST_DIR/$DIR
      #fi
      #cp -R $TEST_DIR/$DIR/src-gen $TEST_DIR/$DIR
    else
      echo "<li>$DIR Test on ERROR</li>" >>  $INDEX
    fi
    cd ..
  fi
done
echo "</ul></body></html>" >>  $INDEX