# this script will publish the automatic build
# parameters:
# update-site_dest=the update site dir where is defined the update site on which to transfer the build; this can be a remote host and in this case the update-site must be of the form "host@update-site_dir"
#   ATTENTION: normally, the update-site_dest must end by SIDE-Alfresco, SIDE-Alfresco-devel, SIDE-Labs or SIDE-Labs-devel: the www.bluexml.com/Update-Site/SIDE update-site SIDE-Alfresco-devel & SIDE-Labs-devel are restricted to BlueXML R&D team: they are automatically updated each time an automatic build of SIDE-Alfresco or SIDE-Labs are built; the www.bluexml.com/Update-Site/SIDE update-site SIDE-Alfresco & SIDE-Labs are public.
# update-site_src=the folder where the local update site is; for instance, on stager it is of the form: /share/Update-Site/SIDE-Alfresco/<build #> or /share/Update-Site/SIDE-Labs/<build #>, this update-site being automatically created by the Hudson nighlty build.
if [ $# -ne 2 ]; then
  echo "Usage: publish_build.sh <update_site_dest> <update_site_src>"
  echo "       with update_site_dest the directory of the destination update site like www.bluexml.com:/data/www/virtuals/b/l/u/bluexml.com/www/html/static/update-site/SIDE/SIDE-Alfresco-devel where to copy "
  echo "            update_site_src the directory of the source update site like /share/Update-Site/SIDE-Alfresco/<build #> from which to copy "
  echo " Example: "
  echo "    transfert on remote update-site:"
  echo "      publish_build.sh /home/stager/share/Update-Site/SIDE-Alfresco/Ankle/9441-651 root@www.bluexml.com:/data/www/virtuals/b/l/u/bluexml.com/www/html/static/update-site/SIDE/SIDE-Alfresco-devel/9441-651"
  echo "    transfert on local update-site:"
  echo "      publish_build.sh /home/stager/share/Update-Site/SIDE-Alfresco/Ankle/9441-651 /data/www/virtuals/b/l/u/bluexml.com/www/html/static/update-site/SIDE/SIDE-Alfresco-devel/9441-651"
  exit -2
else
  dest_dir=$1
  src_dir=$2
  if [ ! -d $src_dir ]; then
    echo " ERROR: $src_dir does not exist locally "
    echo "        No build publication performed"
    exit -1
  else
    # search for the @ character toknow if remote or local site; if @ presente, remote site: it is necessary to perform an scp
    # and before to ensure that ssh agent is running and to set ssh environment
    if_remote=`echo $dest_dir | cut -f2- -d\ | sed -e 's/./& /g' | grep -n "@" | cut -f1 -d:`
    if [ $if_remote -eq 1 ]; then
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
       echo " ERROR: ssh-agent is not started; unable to publish local update site on remote update site"
       exit -1
      fi
      scp -i ~/.ssh/id_rsa_gimli -r $src_dir $dest_dir
      scp -i ~/.ssh/id_rsa_gimli -r $src_dir/* $dest_dir
    else
      cp -r $src_dir $dest_dir
      cp -r $src_dir/* $dest_dir
    fi
  fi
fi

    