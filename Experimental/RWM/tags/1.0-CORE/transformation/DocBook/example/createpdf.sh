xsltproc  --output myfile.fo --stringparam fop1.extensions 1  ./xsl/fo/docbook.xsl docbook_out.xml;
sudo ./fop-0.95/fop -fo myfile.fo -pdf myfile.pdf