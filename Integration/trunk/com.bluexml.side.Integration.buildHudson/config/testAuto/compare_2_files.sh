#!/bin/bash

sed 's/date=\"........................UTC\" /date=\"AAAA-MM-DD HH:MM:SS.MMM UTC\" /g' $1 > res1.xml
sed 's/<date>........................UTC<\/date>/<date>AAAA-MM-DD HH:MM:SS.MMM UTC<\/date>/g' res1.xml > res11.xml
sed 's/<date>.......................UTC<\/date>/<date>AAAA-MM-DD HH:MM:SS.MMM UTC<\/date>/g' res11.xml > res111.xml
sed 's/<date>......................UTC<\/date>/<date>AAAA-MM-DD HH:MM:SS.MMM UTC<\/date>/g' res111.xml > res1111.xml
sed 's/date=\".......................UTC\" /date=\"AAAA-MM-DD HH:MM:SS.MMM UTC\" /g' res1111.xml > res11111.xml
sed 's/date=\"......................UTC\" /date=\"AAAA-MM-DD HH:MM:SS.MMM UTC\" /g' res11111.xml > res111111.xml
#sed 's/date=\".*\" \/><\/de/date=\"AAAA-MM-DD HH:MM:SS.MMM UTC\" \/><\/de/' res11.xml > res111.xml

sed 's/date=\"........................UTC\" /date=\"AAAA-MM-DD HH:MM:SS.MMM UTC\" /g' $2 > res2.xml
sed 's/<date>........................UTC<\/date>/<date>AAAA-MM-DD HH:MM:SS.MMM UTC<\/date>/g' res2.xml > res22.xml
sed 's/<date>.......................UTC<\/date>/<date>AAAA-MM-DD HH:MM:SS.MMM UTC<\/date>/g' res22.xml > res222.xml
sed 's/<date>......................UTC<\/date>/<date>AAAA-MM-DD HH:MM:SS.MMM UTC<\/date>/g' res222.xml > res2222.xml
sed 's/date=\".......................UTC\" /date=\"AAAA-MM-DD HH:MM:SS.MMM UTC\" /g' res2222.xml > res22222.xml
sed 's/date=\"......................UTC\" /date=\"AAAA-MM-DD HH:MM:SS.MMM UTC\" /g' res22222.xml > res222222.xml
#sed 's/date=\".*\" \/><\/de/date=\"AAAA-MM-DD HH:MM:SS.MMM UTC\" \/><\/de/' res22.xml > res222.xml


./diffxml/diffxml.sh -q res111111.xml res222222.xml > out.xml



if test -s out.xml
then
        echo "not empty"
        rm out.xml
        ./diffxml/diffxml.sh res111111.xml res222222.xml > out.xml
else
        echo "empty"
fi


rm res1.xml
rm res11.xml
rm res111.xml
rm res1111.xml
rm res11111.xml
rm res2.xml
rm res22.xml
rm res222.xml
rm res2222.xml
rm res22222.xml
