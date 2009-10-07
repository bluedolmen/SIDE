TARGET=$1
svn info | grep "Révision :"|sed -e 's/\(.*\): \(.*\)/\2/' > $TARGET
