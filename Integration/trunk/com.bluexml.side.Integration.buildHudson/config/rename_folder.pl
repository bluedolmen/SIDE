#! /usr/bin/perl;
use File::Copy;
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
	
	#recherche des repertoire contant le fichier $pom
	opendir(RPT,"$rep") or die "Can not open the folder $rep";
	while($file_name=readdir(RPT)){
		if ($file_name eq $pom){
			$delete="false";
		}
	}
	closedir(RPT);
	
	#supprime les repertoire ne contenant pas de fichier $pom
	if ($delete eq "true") {
		use File::Path;
		rmtree([$rep],0,1) or "Can not delete the folder $rep";
		
		
	}
	#else {
                #@nomrepertoire=split(/\./,$rep);
                #$rep =~ s/.*\.(.+)$/$1/;
                #$taille = $#nomrepertoire-1;
                #$repracine=$nomrepertoire[$taille];
                #if (!( -d "$repracine")) {
                #    mkdir ("$repracine",0755) || die ("Err. Cr. répertoire \n");
                #}
                #mkdir ("$repracine/$rep",0755) || die ("Err. Cr. répertoire \n");
                #move ("$nom","$repracine/$rep") or die("Impossible de copier $repertoire1 $!");
				#rename("$nom", "$rep") || die "Fail $!";
	#}


}

#======================================================
# Nombre d'arguments : 1
# Argument(s)        : un r√©pertoire ($repertoire)
# Retourne           : Tableau de fichier (@fichiers)
#======================================================
sub ListersFichiers {
  my ( $repertoire ) = @_;
  my @fichiers;
  
  # Ouverture d'un r√©pertoire
  opendir (my $FhRep, $repertoire) 
    or die "impossible d'ouvrir le r√©pertoire $repertoire\n";
  
  # Liste fichiers et r√©pertoire sauf (. et ..)
  my @Contenu = grep { !/^\.\.?$/ } readdir($FhRep);
  
  # Fermeture du r√©pertoire
  closedir ($FhRep);
  
  # On r√©cup√®re tous les fichiers
  foreach my $nom ( @Contenu ) {
    # Fichiers
    
    if ( -d "$repertoire/$nom") {
      push ( @fichiers, "$repertoire/$nom" );  
    }
    # Repertoires
    #elsif ( -d "$repertoire/$nom") {
      # recursivit√©
    #  push ( @fichiers, ListersFichiers("$repertoire/$nom") );
    #}
  }
  
  return @fichiers;
}

