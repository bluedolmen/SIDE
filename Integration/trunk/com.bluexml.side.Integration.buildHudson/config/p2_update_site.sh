if [ $# -eq 4 ]; then
  eclipse_launcher=$1
  new_update_site=$2
  update_site=$3
  url=$4
else
  echo "Usage: launch_maven.sh WORKSPACE REPOSITORY_COPY"
  echo "       with eclipse_launcher =   path of the plugin org.eclipse.equinox.launcher_1.0.200.v20090520.jar"
  echo "       with new_update_site =   path of the new update site"
  echo "       with plugins =   path of the update_site"
  exit -2
fi

#/var/opt/hudson/eclipse/Topcased-3.0.1/plugins/org.eclipse.equinox.launcher_1.0.200.v20090520.jar

mkdir $new_update_site
mkdir $new_update_site/plugins
cp -r $update_site/features $new_update_site


for f in `find $update_site/plugins -type f -name "*.jar" | sort -n `; do
   echo "Process $f"

   java -jar $eclipse_launcher -application org.eclipse.update.core.siteOptimizer -jarProcessor -verbose -processAll -repack -outputDir $new_update_site/plugins  $f

done


cp $update_site/site.xml $new_update_site
perl -pi -e 's/<site>/<site pack200="true" digestURL="$url" >/g' $new_update_site/site.xml

echo "Generate digest.zip"
java -jar $eclipse_launcher -application org.eclipse.update.core.siteOptimizer -digestBuilder  -digestOutputDir=$new_update_site -siteXML=$new_update_site/site.xml -jarProcessor -pack -outputDir $new_update_site $new_update_site

echo "Generate content.jar and artifact.jar"
java -jar $eclipse_launcher -application org.eclipse.equinox.p2.metadata.generator.EclipseGenerator -updateSite $new_update_site site file:$new_update_site/site.xml -metadataRepository file:$new_update_site -metadataRepositoryName "SIDE Update Site" -artifactRepository file:$new_update_site -artifactRepositoryName "SIDE Artifacts" -compress -reusePack200Files -noDefaultIUs -vmargs -Xmx256M
