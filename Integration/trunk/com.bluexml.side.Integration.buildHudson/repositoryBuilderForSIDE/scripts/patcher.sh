#!/bin/bash
if [ $# -eq 1 ]; then
  REPOSITORY=$1
else
  echo "Usage: patcher.sh REPOSITORY"
  echo "Must be launch in folder where pom.xml exist (superpom)"
  echo "       with REPOSITORY = maven.repo.local absolute path"
  exit -2
fi
echo " add maven-dependency-plugin in maven.repo.local"
mkdir addMavenPlugin
cp pom.xml addMavenPlugin
cd addMavenPlugin

perl -pi -le 'print "<dependencies>" if $. == 12' pom.xml
perl -pi -le 'print "   <dependency>" if $. == 13' pom.xml
perl -pi -le 'print "      <groupId>org.apache.maven.plugins</groupId>" if $. == 14' pom.xml
perl -pi -le 'print "      <artifactId>maven-dependency-plugin</artifactId>" if $. == 15' pom.xml
perl -pi -le 'print "      <version>2.0</version>" if $. == 16' pom.xml
perl -pi -le 'print "      <type>maven-plugin</type>" if $. == 17' pom.xml
perl -pi -le 'print "      <scope>compile</scope>" if $. == 18' pom.xml
perl -pi -le 'print "   </dependency>" if $. == 19' pom.xml
perl -pi -le 'print "</dependencies>" if $. == 20' pom.xml
echo mvn dependency:go-offline -P public -Dmaven.repo.local=$REPOSITORY
mvn dependency:go-offline -P public -Dmaven.repo.local=$REPOSITORY
echo " job's done"