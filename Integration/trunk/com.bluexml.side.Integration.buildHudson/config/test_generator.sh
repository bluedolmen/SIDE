TEST_DIR=/home/stager/share/SIDE/test
INDEX=/home/stager/share/SIDE/test/index.html
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
