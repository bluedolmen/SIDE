TARGET=$1
echo `svn info | grep "Révision :"|sed -e 's/\(.*\): \(.*\)/\2/'` > $TARGET
