/* this script gets the number of documents of a certain types under a site */
var site = url.templateArgs.siteName;
var type = args.type;
if (type == null) type = "cm:content";

var nodes = search.luceneSearch("PATH:\"/app:company_home/st:sites/cm:"+site+"/cm:documentlibrary//*\" +TYPE:\""+type+"\"");
model.number = nodes.length;
