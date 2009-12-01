<?
function count_files($folder, $subfolders)
{
     // on rajoute le / à la fin du nom du dossier s'il ne l'est pas
     if(substr($folder, -1) != '/')
        $folder .= '/';
     
     // $ext est un tableau?
     $array = 0;
     if(is_array($ext))
        $array = 1;

     // ouverture du répertoire
     $rep = @opendir($folder);
     if(!$rep)
        return -1;
        
     $nb_files = 0;
     // tant qu'il y a des fichiers
     while($file = readdir($rep))
     {
        // répertoires . et ..
        if($file == '.' || $file == '..')
         continue;
        
        // si c'est un répertoire et qu'on peut le lister
        if(is_dir($folder . $file) && $subfolders)
            // on appelle la fonction
         $nb_files += count_files($folder . $file, $ext, 1);
        // Incrémentation du nombre de fichiers
        else
         $nb_files++;
     }
     
     // fermeture du rep
     closedir($rep);
     return $nb_files;
}

function getFirstFile($folder)
{
     // on rajoute le / à la fin du nom du dossier s'il ne l'est pas
     if(substr($folder, -1) != '/')
        $folder .= '/';
        
     // ouverture du répertoire
     $rep = @opendir($folder);
     if(!$rep)
        return -1;
     
     $firstFile = "";
     while($file = readdir($rep))
     {
     	// répertoires . et ..
        if($file == '.' || $file == '..')
         continue;
        $firstFile = $file;
        break;
     }
     
     // fermeture du rep
     closedir($rep);
     return $firstFile;
}

function removedir($folder)
{
     // on rajoute le / à la fin du nom du dossier s'il ne l'est pas
     if(substr($folder, -1) != '/')
        $folder .= '/';

     // ouverture du répertoire
     $rep = @opendir($folder);
     if(!$rep)
        return -1;

     // tant qu'il y a des fichiers
     while($file = readdir($rep))
     {
        // répertoires . et ..
        if($file == '.' || $file == '..')
         continue;
        
        // si c'est un répertoire et qu'on peut le lister
        if(is_dir($folder . $file) && $subfolders)
            // on appelle la fonction
	        removedir($folder . $file);
        else
	       	unlink($folder . $file);
     }
     
     // fermeture du rep
     closedir($rep);
     rmdir($folder);
}

?>