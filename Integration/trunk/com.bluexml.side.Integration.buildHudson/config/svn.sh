CHEMIN=$1
TARGET=$2
cd $CHEMIN
echo `svn info | grep "Révision :"|sed -e 's/\(.*\): \(.*\)/\2/'` > $TARGET
