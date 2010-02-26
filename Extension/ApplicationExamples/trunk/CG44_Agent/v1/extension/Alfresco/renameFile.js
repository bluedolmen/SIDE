var index = document.parent.children.length;
var matricule = document.parent.name;

if (index <= 9)
	index = "00" + index;
else if (index <= 99)
	index = "0" + index;

var name = matricule+"_"+index+".pdf";

document.properties["cm:name"] = name;
document.properties["cm:title"] = "";
document.properties["cm:author"] = matricule;
document.save();