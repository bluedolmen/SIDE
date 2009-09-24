#!/bin/bash

sed 's/date=\".*CEST\" /date=\"AAAA-MM-DD HH:MM:SS.MMM CEST\" /' $1 > res1.xml
sed 's/<date>.*date>/<date>AAAA-MM-DD HH:MM:SS.MMM CEST<\/date>/' res1.xml > res11.xml
sed 's/date=\".*\" \/><\/de/date=\"AAAA-MM-DD HH:MM:SS.MMM CEST\" \/><\/de/' res11.xml > res111.xml

sed 's/date=\".*CEST\" /date=\"AAAA-MM-DD HH:MM:SS.MMM CEST\" /' $2 > res2.xml
sed 's/<date>.*<\/date>/<date>AAAA-MM-DD HH:MM:SS.MMM CEST<\/date>/' res2.xml > res22.xml
sed 's/date=\".*\" \/><\/de/date=\"AAAA-MM-DD HH:MM:SS.MMM CEST\" \/><\/de/' res22.xml > res222.xml


./diffxml/diffxml.sh -q res111.xml res222.xml > out.xml 



if test -s out.xml
then        
	echo "not empty"
	rm out.xml
	./diffxml/diffxml.sh res111.xml res222.xml > out.xml
else
        echo "empty"
fi

#rm res1.xml
#rm res11.xml
#rm res111.xml
rm res2.xml
rm res22.xml
#rm res222.xml
