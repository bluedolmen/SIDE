#! /usr/bin/perl;
use File::Copy;
#================================================
# delete folders which don't include a file/directory called $pom
# search my be recursive 
# 
#================================================
if ($ARGV[0] eq '') {
	print "usage : perl delete_folder.pl <file/directory name> [recursive]\n";
	print "   <file/directory name> : file or directory name to search\n";
	print "   [recursive]           : optional parameter default false no recursion\n";
}


my $Repertoire = ".";
$pom=$ARGV[0];
$recursive="false";
if ($ARGV[1] eq "recursive") {
	$recursive="true";
	print "recursive is on\n";
}

my @LesFichiers = ListersFichiers($Repertoire,$recursive);


foreach my $nom ( @LesFichiers ) {
	$delete="true";
	$rep=$nom;
	
	
	#supprime les repertoire ne contenant pas de fichier $pom
	if ($delete eq "true") {
		use File::Path;
		rmtree([$rep],0,1) or "Can not delete the folder $rep";
		
		
	}
}

#======================================================
# Nombre d'arguments : 1
# Argument(s)        : un répertoire ($repertoire)
# Retourne           : Tableau de fichier (@fichiers)
#======================================================
sub ListersFichiers {
  my ( $repertoire ) = @_[0];
  my ( $recur ) = @_[1];
  
  my @fichiers;
  
  # Ouverture d'un répertoire
  opendir (my $FhRep, $repertoire) or die "impossible d'ouvrir le répertoire $repertoire\n";
  
  # Liste fichiers et répertoire sauf (. et ..)
  my @Contenu = grep { !/^\.\.?$/ } readdir($FhRep);
  
  # Fermeture du répertoire
  closedir ($FhRep);
  
  # On récupère tous les fichiers
  foreach my $nom ( @Contenu ) {
    # Fichiers
    
    if ( -d "$repertoire/$nom" && Filter("$repertoire/$nom", $pom) eq "true") {
      push ( @fichiers, "$repertoire/$nom" );
    }
    # Repertoires
    elsif ( -d "$repertoire/$nom" && $recur eq "true") {
      # recursivité
      push ( @fichiers, ListersFichiers("$repertoire/$nom") );
    }
  }
  
  return @fichiers;
}

sub Filter {
	my ( $rep ) = @_[0];
	my ( $pom ) = @_[1];
#	print "start $rep $pom \n";
	if ($rep!~/$pom/) {
		#recherche des repertoire contant le fichier $pom
		opendir(RPT,"$rep") or die "Can not open the folder $rep";
		while($file_name=readdir(RPT)){
			if ($file_name eq $pom){
				closedir(RPT);
				return "false";			
			} else {
			
			}
		}
		closedir(RPT);
		print "found for deletion : $rep\n";
		return "true";		
	} else {
		return "false";
	}
}