/* this script gets the number of documents of a certain types under a site */
var site = url.templateArgs.siteName;
var type = args.type;
if (type == null) type = "cm:content";

var nodes = search.luceneSearch("+PATH:\"/app:company_home/st:sites/cm:"+site+"/cm:documentLibrary//*\" +TYPE:\""+type+"\" -TYPE:\"cm:thumbnail\" -TYPE:\"cm:failedThumbnail\" -TYPE:\"cm:systemfolder\" -TYPE:\"fm:forums\" -TYPE:\"fm:forum\" -TYPE:\"fm:topic\" -TYPE:\"fm:post\"");
model.number = nodes.length;