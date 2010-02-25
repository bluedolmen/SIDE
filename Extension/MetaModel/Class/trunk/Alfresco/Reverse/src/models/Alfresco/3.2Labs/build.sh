#!/bin/sh
cat *.xml > allModels.xml
echo '<models>' | cat - allModels.xml > allModels.xml.1
cat allModels.xml.1 - | echo '</models>'  > allModels.xml.2
mv allModels.xml.2 allModels.xml
rm allModels.xml.1
