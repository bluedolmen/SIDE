#! /usr/bin/perl;

#================================================
# rename folder with the last part
# com.bluexml.side.Integration.alfresco.cmisFacetMap into cmisFacetMap
#================================================

my $Repertoire = ".";
$pom=$ARGV[0];

my @LesFichiers = ListersFichiers($Repertoire);
open (POMF,">>$pom") or die "Cannot open the file $pom";


foreach my $nom ( @LesFichiers ) {
	$delete="true";
	$rep=$nom;
	opendir(RPT,"$rep") or die "Can not open the folder $rep";
	while($file_name=readdir(RPT)){
		if ($file_name eq $pom){
			$delete="false";
		}
	}
	closedir(RPT);
	if ($delete eq "true") {
		use File::Path;
		rmtree([$rep],0,1) or "Can not delete the folder $rep";
		
		
	}
	else {
		$rep =~ s/.*\.(.+)$/$1/;
		rename("$nom", "$rep") || die "Fail $!";
	}


}

#======================================================
# Nombre d'arguments : 1
# Argument(s)        : un répertoire ($repertoire)
# Retourne           : Tableau de fichier (@fichiers)
#======================================================
sub ListersFichiers {
  my ( $repertoire ) = @_;
  my @fichiers;
  
  # Ouverture d'un répertoire
  opendir (my $FhRep, $repertoire) 
    or die "impossible d'ouvrir le répertoire $repertoire\n";
  
  # Liste fichiers et répertoire sauf (. et ..)
  my @Contenu = grep { !/^\.\.?$/ } readdir($FhRep);
  
  # Fermeture du répertoire
  closedir ($FhRep);
  
  # On récupère tous les fichiers
  foreach my $nom ( @Contenu ) {
    # Fichiers
    
    if ( -d "$repertoire/$nom") {
      push ( @fichiers, "$repertoire/$nom" );  
    }
    # Repertoires
    #elsif ( -d "$repertoire/$nom") {
      # recursivité
    #  push ( @fichiers, ListersFichiers("$repertoire/$nom") );
    #}
  }
  
  return @fichiers;
}

