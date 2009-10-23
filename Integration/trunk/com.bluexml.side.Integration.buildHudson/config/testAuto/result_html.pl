#! /usr/bin/perl;

$file=$ARGV[0];
$file_out=$ARGV[1];

open(OUTF,">$file_out");
print OUTF "<html><head><title>S-IDE Automatic Tests</title></head>\n";
print OUTF "<body><h1>S-IDE Automatic Generator tests</h1>\n";
print OUTF "Note:<br>";
print OUTF "- for all tests, click on the title to load the set of tested models, including the application model.<br>";
print OUTF "- for all tests, click on the duration to load the generated side-report.xml file.<br>";
print OUTF "- with a failed test comes a 'diff' file which contains what is necessary to perform as operations (add, delete) to produce the side-report.xml reference file from the side-report.xml of the failed test.<br>";
print OUTF "<br>";
print OUTF "<TABLE BORDER>\n";
print OUTF "<TR><TH>Test Title</TH><TH>Main MM</TH><TH>Main Techno</TH><TH>Release of Main Techno</TH><TH>Main SIDE generator</TH><TH>duration (s)</TH><TH>Reference Report - Difference</TH></TR>";

$i=1;
open(F,$file) || die "Can not open the file $file";
while (<F>){
  if (/File =/){
    print OUTF "<TR>\n";
    $line=$_;
    $line=~ /testCase\//;
    $line=$';
    $line=~ /\/My.application/;
    $line=$`;
 
    my @values = split ('\/',$line);
    $taille= scalar @values;
    $cpt=1;
    $testtitle=$values[4];
    $nametc=$values[4];
    $nametc=~ /[TC][TC]....[0-9]*/;
    $nametc=$&;
    if (-z "result_$nametc.xml") {
      print OUTF "<TD><font color=\"#008000\"><A style=\"color:#008000\" HREF=\"../../com.bluexml.side.Integration.testAuto/generation/testCase/$line\" target=\"_blank\">$testtitle</a></font></TD>\n";
    }
    else {
      print OUTF "<TD><font color=\"#FF0000\"><A style=\"color:#FF0000\" HREF=\"../../com.bluexml.side.Integration.testAuto/generation/testCase/$line\" target=\"_blank\">$testtitle</a></font></TD>\n";
    }
    
   
    foreach my $val (@values) {
       
      if ($cpt<$taille){
        $val=~s/_/ /g;
        print OUTF "<TD>$val</TD>\n";
      }
      $cpt=$cpt+1;
    }
  
  }
  if (/Time/){
    $time=$_;
    $time=~ /Time /;
    $time=$';
    print OUTF "<TD><A HREF=\"$nametc\/side-report.xml\" title=\"side-report.xml\" target=\"_blank\">$time</a></TD>\n";
    if (!(-z "result_$nametc.xml")) {
      print OUTF "<TD> <A HREF=\"$nametc\/TC_ref_$i.xml\" target=\"_blank\">Reference</a> - <A HREF=\"$nametc\/result_$nametc.xml\" target=\"_blank\">diff</a></TD>";
    }
    

  
    print OUTF "</TR>\n";
    $i=$i+1;
   }
}

print OUTF "</TABLE>\n";
print OUTF "</body>\n";
print OUTF "</html>\n";

close F;
close OUTF;
