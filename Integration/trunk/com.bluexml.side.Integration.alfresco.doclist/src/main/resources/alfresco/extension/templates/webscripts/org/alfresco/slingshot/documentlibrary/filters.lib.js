<import resource="classpath:/alfresco/extension/templates/webscripts/org/alfresco/slingshot/documentlibrary/doclistSearch.lib.js">
var Filters =
{
   TYPE_MAP:
   {
      "documents": '+(TYPE:"{http://www.alfresco.org/model/content/1.0}content" OR TYPE:"{http://www.alfresco.org/model/application/1.0}filelink" OR TYPE:"{http://www.alfresco.org/model/content/1.0}folder")',
      "folders": '+TYPE:"{http://www.alfresco.org/model/content/1.0}folder"',
      "images": "-TYPE:\"{http://www.alfresco.org/model/content/1.0}thumbnail\" +@cm\\:content.mimetype:image/*"
   },

   getFilterParams: function Filter_getFilterParams(filter, parsedArgs, optional)
   {
      var filterParams =
      {
         query: "+PATH:\"" + parsedArgs.parentNode.qnamePath + "/*\"",
         limitResults: null,
         sortBy: "@{http://www.alfresco.org/model/content/1.0}name",
         sortByAscending: true,
         variablePath: false
      };

      optional = optional || {};

      // Max returned results specified?
      var argMax = args.max;
      if ((argMax !== null) && !isNaN(argMax))
      {
         filterParams.limitResults = argMax;
      }

      var favourites = optional.favourites;
      if (typeof favourites == "undefined")
      {
         favourites = [];
      }

      // Create query based on passed-in arguments
      var filterId = String(filter),
         filterData = String(args.filterData),
         filterQuery = "";

      // Common types and aspects to filter from the UI
      filterQueryDefaults = " -ASPECT:\"{http://www.alfresco.org/model/content/1.0}workingcopy\"";
      filterQueryDefaults += " -TYPE:\"{http://www.alfresco.org/model/content/1.0}thumbnail\"";
      filterQueryDefaults += " -TYPE:\"{http://www.alfresco.org/model/content/1.0}systemfolder\"";
      filterQueryDefaults += " -TYPE:\"{http://www.alfresco.org/model/forum/1.0}forums\"";
      filterQueryDefaults += " -TYPE:\"{http://www.alfresco.org/model/forum/1.0}forum\"";
      filterQueryDefaults += " -TYPE:\"{http://www.alfresco.org/model/forum/1.0}topic\"";
      filterQueryDefaults += " -TYPE:\"{http://www.alfresco.org/model/forum/1.0}post\"";

      switch (filterId)
      {
         case "all":
            filterQuery = "+PATH:\"" + parsedArgs.rootNode.qnamePath + "//*\"";
            filterQuery += " -TYPE:\"{http://www.alfresco.org/model/content/1.0}folder\"";
            filterParams.query = filterQuery + filterQueryDefaults;
            break;

         case "recentlyAdded":
         case "recentlyModified":
         case "recentlyCreatedByMe":
         case "recentlyModifiedByMe":
            var onlySelf = (filterData.indexOf("ByMe")) > 0 ? true : false,
               dateField = (filterData.indexOf("Created") > 0) ? "created" : "modified",
               ownerField = (dateField == "created") ? "creator" : "modifier";

            // Default to 7 days - can be overridden using "days" argument
            var dayCount = 7,
               argDays = args.days;
            if ((argDays !== null) && !isNaN(argDays))
            {
               dayCount = argDays;
            }

            // Default limit to 50 documents - can be overridden using "max"
			// argument
            if (filterParams.limitResults === null)
            {
               filterParams.limitResults = 50;
            }

            var date = new Date();
            var toQuery = date.getFullYear() + "\\-" + (date.getMonth() + 1) + "\\-" + date.getDate();
            date.setDate(date.getDate() - dayCount);
            var fromQuery = date.getFullYear() + "\\-" + (date.getMonth() + 1) + "\\-" + date.getDate();

            filterQuery = "+PATH:\"" + parsedArgs.rootNode.qnamePath;
            if (parsedArgs.nodeRef == "alfresco://sites/home")
            {
               // Special case for "Sites home" pseudo-nodeRef
               filterQuery += "/*/cm:documentLibrary";
            }
            filterQuery += "//*\"";
            filterQuery += " +@cm\\:" + dateField + ":[" + fromQuery + "T00\\:00\\:00.000 TO " + toQuery + "T23\\:59\\:59.999]";
            if (onlySelf)
            {
               filterQuery += " +@cm\\:" + ownerField + ":" + person.properties.userName;
            }
            filterQuery += " -TYPE:\"{http://www.alfresco.org/model/content/1.0}folder\"";

            filterParams.sortBy = "@{http://www.alfresco.org/model/content/1.0}" + dateField;
            filterParams.sortByAscending = false;
            filterParams.variablePath = true;
            filterParams.query = filterQuery + filterQueryDefaults;
            break;

         case "editingMe":
            filterQuery = "+PATH:\"" + parsedArgs.rootNode.qnamePath + "//*\"";
            filterQuery += " +ASPECT:\"{http://www.alfresco.org/model/content/1.0}workingcopy\"";
            filterQuery += " +@cm\\:workingCopyOwner:" + person.properties.userName;

            filterParams.variablePath = true;
            filterParams.query = filterQuery;
            break;

         case "editingOthers":
            filterQuery = "+PATH:\"" + parsedArgs.rootNode.qnamePath + "//*\"";
            filterQuery += " +ASPECT:\"{http://www.alfresco.org/model/content/1.0}workingcopy\"";
            filterQuery += " -@cm\\:workingCopyOwner:" + person.properties.userName;

            filterParams.variablePath = true;
            filterParams.query = filterQuery;
            break;

         case "favouriteDocuments":
            var foundOne = false;

            for (var favourite in favourites)
            {
               if (foundOne)
               {
                  filterQuery += " OR ";
               }
               foundOne = true;
               filterQuery += "ID:\"" + favourite + "\"";
            }
            filterParams.variablePath = true;
            filterParams.query = filterQuery.length > 0 ? "+PATH:\"" + parsedArgs.rootNode.qnamePath + "//*\" +(" + filterQuery + ")" : "+ID:\"\"";
            break;

         case "node":
            filterParams.query = "+ID:\"" + parsedArgs.rootNode.nodeRef + "\"";
            break;

         case "tag":
            filterParams.variablePath = true;
            filterParams.query = "+PATH:\"" + parsedArgs.rootNode.qnamePath + "//*\" +PATH:\"/cm:taggable/cm:" + search.ISO9075Encode(filterData) + "/member\"";
            break;
         // SIDE start
         case "metadata":
        	 var type,properties;
        	 
        	 var searchObj=eval('('+parsedArgs.args.search+')');
        	 properties=searchObj.properties;
        	 type=searchObj.type;
        	 var logicalOperator=" AND ";
			 if (logicalOperator.toLowerCase() == "or") {
				 logicalOperator=" OR ";
			 }
        	 var query='+TYPE:"'+type+'"';
        	 var propQuery="";
        	 
        	 /*for (var prop in properties) {
        		 if (properties[prop] != "") {
        			 propQuery+="@"+prop;
        			 propQuery+=":"+properties[prop];        		 
        			 propQuery+=operator;
        		 }
        	 }
        	 */
        	 propQuery= Filters.parseAdvancedSearch(searchObj);
        	 
        	 if (propQuery !=="") {
        		 var ind=propQuery.lastIndexOf(logicalOperator);
        		 propQuery=propQuery.substring(0,ind);
        		 propQuery=propQuery.replace(/([{}])/g,"\\$1");
        		 propQuery=propQuery.replace(/(http):/g,"$1\\:");
        		 query+=" +(";
        		 query+=propQuery
	        	 query+=") ";
        	 }
        	 
        	 
        	 filterParams.query=query;
        	 
        	 break;
         case "fullTextSearch":
        	 var siteId = (args.site !== undefined) ? args.site : null;
        	 var containerId = (args.container !== undefined) ? args.container : null;
        	 var term = (args.term !== undefined) ? args.term : null;
        	 var maxResults = (args.maxResults !== undefined) ? parseInt(args.maxResults, 10) : DEFAULT_MAX_RESULTS;
        	 
        	 filterParams.query= getSearchQuery(term, maxResults, siteId, containerId);
        	 break;
             // SIDE end
         default:
            filterQuery = "+PATH:\"" + parsedArgs.parentNode.qnamePath + "/*\"";
            filterParams.query = filterQuery + filterQueryDefaults;
            break;
      }

      // Specialise by passed-in type
      filterParams.query += " " + (Filters.TYPE_MAP[parsedArgs.type] || "");

      return filterParams;
   },
   
   parseAdvancedSearch : function (json) {
	   const operators=[];
	   const simpleFieldTypes=["string", "char", "boolean"];
	   const boundedFieldTypes=["Date", "DateTime", "short", "byte", "int", "long", "double", "float"];
	   const suportedTypes= simpleFieldTypes.concat(boundedFieldTypes);
	   const unsuportedTypes=["time","object"];
	   
	   /*json ={type:"{http://www.bluexml.com/model/content/DigitizationProcess/1.0}com_bluexml_side_models_liste_Document",
			   operator:"and",
			   fields:{
			   	"{http://www.bluexml.com/model/content/DigitizationProcess/1.0}com_bluexml_side_models_liste_Document_Libelle":{
			   		type:"String",
			   		operator:"contains",
			   		values:["ssd"]},
			   	"{http://www.bluexml.com/model/content/DigitizationProcess/1.0}com_bluexml_side_models_liste_Document_Numero":{type:"String",operator:"is",values:[""]},
			   	"{http://www.bluexml.com/model/content/DigitizationProcess/1.0}com_bluexml_side_models_liste_Document_Observation":{type:"String",operator:"istartsWith",values:[""]},
			   	"{http://www.bluexml.com/model/content/DigitizationProcess/1.0}com_bluexml_side_models_liste_Document_DateNumerisation":{
			   		type:"DateTime",
			   		operator:"between",
			   		values:["2010-02-24T12:26:14.144+01:00","2010-02-24T12:26:14.161+01:00"]},
			   	"{http://www.bluexml.com/model/content/DigitizationProcess/1.0}com_bluexml_side_models_liste_Document_Auteur":{type:"String",operator:"is",values:[""]}
			   }
			  };
		*/
	   var type=json.type;
	   var logicalOperator=" AND ";
	   if (logicalOperator.toLowerCase() == "or") {
		   logicalOperator=" OR ";
	   }
	   var properties=json.fields;
	   
	   
	   var propQuery="";
	   for (var prop in properties) {
		 var att=properties[prop];
  		 if (att.values.length >0 && att.values[0] != "") {
  			var dataType=att.type.toLowerCase();
  			var comparator=att.operator.toLowerCase();
  			var values=att.values;
  			
  			var propQuery_local=" @"+prop;
  			
  			switch (dataType) {
  			// simple
				case "string":
					var valuePart="";
					switch (comparator) {
					case "contains":
						valuePart="*"+values[0]+"*";
						break;
					case "icontains":
						valuePart="*"+values[0]+"*";
						break;
					case "startswith":
						valuePart=values[0]+"*";
						break;
					case "istartswith":
						valuePart=values[0]+"*";
						break;
					case "endswith":
						valuePart="*"+values[0];
						break;
					case "iendswith":
						valuePart="*"+values[0];
						break;
					case "empty":
						// property is not set how to query this ?
						break;
					case "is":
						valuePart=values[0];
						break;
					default:
						break;
					}					
					propQuery+=propQuery_local+":"+valuePart;
					break;
				case "char":	
					break;
				case "boolean":	
					propQuery+=propQuery_local+":"+values[0];
					break;
			// bounded
				case "datetime":
				case "date":
					var min="MIN";
					var max="MAX";
					values[0]=values[0].replace(/-/g,'\-');
					if (values.length >1) {
						values[1]=values[1].replace(/-/g,'\-');
					}
					switch (comparator) {						
						case "between":
							min=values[0];
							max=values[1];
							propQuery+=propQuery_local+":"+"["+min+" TO "+max+"]";
							break;
						case "before":
							max=values[0];
							propQuery+=propQuery_local+":"+"["+min+" TO "+max+"]";
							break;
						case "after":
							min=values[0];
							propQuery+=propQuery_local+":"+"["+min+" TO "+max+"]";
							break;
						case "exactly":
							propQuery+=propQuery_local+":"+values[0];
							break;
						case "empty":
							// property is not set how to query this ?
							break;	
						default:
							break;
					}
					
					break;
				case "enums":
					var query="";
					switch (comparator) {
					case "oneof":
						if (values.length >1) {
							query=" +(";
						}
						for (var c=0;c<values.length;c++) {
							query+=propQuery_local+":"+values[c];
							query+=" OR ";
						}
					
						var ind=query.lastIndexOf(" OR ");
						query=query.substring(0,ind);
						if (values.length >1) {
							query+=")";
						}
					
						break;
					case "allbut":
						if (values.length >1) {
							query=" -(";
						}
						for (var c=0;c<values.length;c++) {
							query+=propQuery_local+":"+values[c];
							query+=" OR ";
						}
						var ind=query.lastIndexOf(" OR ");
						query=query.substring(0,ind);
						
						if (values.length >1) {
							query+=")";
						}
						break;
					case "none":
						// property is not set how to query this ?
						break;
						
					default:
						break;
					}
					propQuery+=query;
					break;
				case "byte":
				case "int":	
				case "long":
				case "double":
				case "float":
				case "short":
					var min="MIN";
					var max="MAX";
					
					switch (comparator) {
					case "between":
						min=values[0];
						max=values[1];
						propQuery+=propQuery_local+":"+"["+min+" TO "+max+"]";
						break;
					case "below":
						max=values[0];
						propQuery+=propQuery_local+":"+"["+min+" TO "+max+"]";
						break;
					case "above":
						min=values[0];
						propQuery+=propQuery_local+":"+"["+min+" TO "+max+"]";
						break;
					case "exactly":
						if (value[0].indexOf("\.")) {
							value[0]=value[0].replace(/([^.]*\.[^.]*)/,'"$1"');
						}
						propQuery+=propQuery_local+":"+values[0];
						break;
					case "empty":
						// property is not set how to query this ?
						break;
					default:
						break;
					}
					break;
				
			// not managed
				case "time":	
					break;
				case "object":	
					break;
				default:
					break;
			}
  			
  			        		 
  			propQuery+=logicalOperator;
  		 }
  	 }
	   return propQuery;
   }
};
