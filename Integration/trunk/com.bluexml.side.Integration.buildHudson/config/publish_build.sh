# this script will publish the automatic build
# parameters:
# dest=devel or public: if devel, on secured devel update site path; public on public update site
# rev_build_src_nb=the revision and build number to copy; this will take /home/stager/share/SIDE/Update_site/<build_src_number>
#
if [ $# -ne 2 ]; then
  echo "Usage: publish_build.sh [devel|public] <rev_build_src_nb> <target_dir>"
  echo "       with devel to copy on protected update_site "
  echo "            public to copy on public update_site "
  echo "            build_src_nb the build # to copy (under stager:/home/stager/share/SIDE/Update_site/<build_src_number>)"
  echo "        build is copied under www.bluexml.com:/data/www/virtuals/b/l/u/bluexml.com/www/html/static/update-site/SIDE/$dest dir"
  exit -2
else
  dest=$1
  rev_build_src_nb=$2
  src_dir=/home/stager/share/SIDE/Update-Site/Ankle
echo dest=$dest - $rev_build_src_nb
#  if [ $dest = "devel" || $dest = "public" ]; then
  if [ $dest = "devel" ]; then
    if [ ! -d $src_dir/$rev_build_src_nb ]; then
      echo " ERROR: $src_dir/$rev_build_src_nb does not exist: no build $rev_build_src_nb "
      echo " No build publication performed"
      echo " build number must be one of : "
      ls $src_dir
      exit -1
    else 
SSH_AUTH_SOCK=/tmp/ssh-uvGWL23781/agent.23781; export SSH_AUTH_SOCK;
SSH_AGENT_PID=23782; export SSH_AGENT_PID;
      scp -r $src_dir/$rev_build_src_nb root@www.bluexml.com:/data/www/virtuals/b/l/u/bluexml.com/www/html/static/update-site/SIDE/$dest
    fi
  else 
    echo " ERROR: first parameter must be devel or public. enter "publish_build" for usage"
    echo " No build publication performed"
    exit -1
  fi
fi

