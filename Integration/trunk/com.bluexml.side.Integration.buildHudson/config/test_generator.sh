#! /bin/bash
# launch the automatic tests of the acceleo template of all the generators
# 1st parameter = test directory where are store the standalone acceleo projects for all the s-ide generators Ex:/home/stager/buildAuto/Ankle/test
# 2nd parameter = Update-Site path Ex:/home/stager/share/SIDE/Update-Site/Ankle
# 3rd parameter = test delivery path where must be published the test results Ex:/home/stager/share/SIDE/test/Ankle
# 4th parameter = url to access test path where are store index.html and results file Ex:http://stager/share/SIDE/test/Ankle
if [ $# -eq 4 ]; then
  TEST_DIR=$1
  UPDATE_SITE=$2
  TEST_PUBLISH_DIR=$3
  TEST_URL=$4
else
  exit -2
fi

cd $TEST_DIR

INDEX=$TEST_DIR/index.html
echo "<html><head><title>S-IDE Automatic Generator tests</title></head>" > $INDEX
echo "<body><h1>S-IDE Automatic Generator tests</h1>" >>  $INDEX
echo "[INFO] $TEST_URL/index.html"
return=0
echo "<h2>Acceleo Generation Templates Test</h2>" >>  $INDEX
echo "<p>This tests consists in loading the Acceleo templates used by the generators." >>  $INDEX
echo "For each generators, a previous Acceleo standalone project has been generated from Eclipse.</p><ul>" >>  $INDEX
for i in *
do
  DIR=$i
  if [ -d $DIR ]; then
  	cp -R $UPDATE_SITE/plugins/* $DIR/acceleo/plugins
    cd $DIR
    ./run.sh >output.txt
#    if [ `ls src-gen | wc -l` -gt 0 ]
    nb_error=`awk '/Erreurs dans le fichier/ || /Exception/ || /existe pas/' output.txt | wc -l`
    nb_generate=`awk '/generate/' output.txt | wc -l`
    models=`ls model`
    if [ $nb_error -eq 0 ]
    then
      echo "<li>$DIR: Test Ok - <a href="$DIR/src-gen">Test Results</a> - <a href="$DIR/output.txt">Test log</a> - $nb_generate generated file(s) on model(s) <a href="$DIR/model">$models</a></li>" >>  $INDEX
    else
      echo "<li>$DIR: <font color="red">Test on ERROR</font></b> - <A href="$DIR/output.txt">$nb_error raised errors</a>" >>  $INDEX
      if [ `ls src-gen | wc -l` -gt 0 ]; then
        echo " - <a href="$DIR/src-gen">Test Results</a>" >>  $INDEX
      fi
      echo "- $nb_generate targeted generated file(s) but $nb_error on error  on model(s) <a href="$DIR/model">$models</a></li></li>" >>  $INDEX
      return=-1
    fi
    cat output.txt
    if [ ! -d $TEST_PUBLISH_DIR/$DIR ]; then
      mkdir $TEST_PUBLISH_DIR/$DIR
    fi
    if [ -d src-gen ]; then
      cp -R src-gen $TEST_PUBLISH_DIR/$DIR
    fi
    cp output.txt $TEST_PUBLISH_DIR/$DIR
    cp -R model $TEST_PUBLISH_DIR/$DIR
    cd ..
  fi
done
echo "</ul><hr>" >>  $INDEX
echo "<footer>BlueXML - Copyright 2009</footer></body></html>" >>  $INDEX
cp $INDEX $TEST_PUBLISH_DIR
exit $return