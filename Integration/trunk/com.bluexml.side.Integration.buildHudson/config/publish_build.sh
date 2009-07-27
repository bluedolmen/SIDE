# this script will publish the automatic build
# parameters:
# dest=devel or public: if devel, on secured devel update site path; public on public update site
# rev_build_src_nb=the revision and build number to copy; this will take <src_dir>/<build_src_number>
# src_dir=the folder where the update site is
if [ $# -ne 3 ]; then
  echo "Usage: publish_build.sh [devel|public] <rev_build_src_nb> <source_dir>"
  echo "       with devel to copy on protected update_site "
  echo "            public to copy on public update_site www.bluexml.com/update-site/SIDE/public/2.0"
  echo "            build_src_nb the build # to copy (under stager:<src_dir>/<build_src_number>)"
  echo "        build is copied under www.bluexml.com:/data/www/virtuals/b/l/u/bluexml.com/www/html/static/update-site/SIDE/$dest dir"
  exit -2
else
  dest=$1
  rev_build_src_nb=$2
  src_dir=$3
  # set ssh-agent env var to copy on gimly www.bluexml.com
  ssh_agent_pid=`ps -C ssh-agent -o pid=`
  ssh_agent_ppid=`ps -C ssh-agent -o ppid=`
  if [ $ssh_agent_pid -gt 0 ];then
     ssh_agent_pid=`echo $ssh_agent_pid | sed -e 's/^ *//' -e 's/ *$//'`
     ssh_agent_ppid=`echo $ssh_agent_ppid | sed -e 's/^ *//' -e 's/ *$//'`
     ssh_agent_dir=`ls /tmp|grep ssh-|grep $ssh_agent_ppid`
     SSH_AUTH_SOCK=/tmp/$ssh_agent_dir/agent.$ssh_agent_ppid; export SSH_AUTH_SOCK;
      SSH_AGENT_PID=$ssh_agent_pid; export SSH_AGENT_PID;
  else
     echo " ERROR: ssh-agent is not started; unable to publish local update site on www.bluexml.com update site"
     exit -1
  fi
  if [ $dest = "devel" ] || [ $dest = "public" ]; then
    if [ ! -d $src_dir/$rev_build_src_nb ]; then
      echo " ERROR: $src_dir/$rev_build_src_nb does not exist: no build $rev_build_src_nb "
      echo " No build publication performed"
      echo " build number must be one of : "
      ls $src_dir
      exit -1
    else
      if [ $dest = "public" ]; then
        scp -r $src_dir/$rev_build_src_nb/* root@www.bluexml.com:/data/www/virtuals/b/l/u/bluexml.com/www/html/static/update-site/SIDE/$dest/2.0
      else
        scp -r $src_dir/$rev_build_src_nb root@www.bluexml.com:/data/www/virtuals/b/l/u/bluexml.com/www/html/static/update-site/SIDE/$dest
      fi
    fi
  else
    echo " ERROR: first parameter must be devel or public. enter publish_build for usage"
    echo " No build publication performed"
    exit -1
  fi
fi

    