<import resource="classpath:/alfresco/extension/templates/webscripts/org/alfresco/slingshot/search/search.lib.js">
var Filters =
{
   /**
    * Type map to filter required types.
    * NOTE: "documents" filter also returns folders to show UI hint about hidden folders.
    */
   TYPE_MAP:
   {
      "documents": '+(TYPE:"content" OR TYPE:"app:filelink" OR TYPE:"folder")',
      "folders": '+(TYPE:"folder" OR TYPE:"app:folderlink")',
      "images": '+@cm\\:content.mimetype:image/*'
   },

   /**
    * Types that we want to suppress from the resultset
    */
   IGNORED_TYPES:
   [
      "cm:systemfolder",
      "fm:forums",
      "fm:forum",
      "fm:topic",
      "fm:post"
   ],

   /**
    * Encode a path with ISO9075 encoding
    *
    * @method iso9075EncodePath
    * @param path {string} Path to be encoded
    * @return {string} Encoded path
    */
   iso9075EncodePath: function Filter_iso9075EncodePath(path)
   {
      var parts = path.split("/");
      for (var i = 1, ii = parts.length; i < ii; i++)
      {
         parts[i] = "cm:" + search.ISO9075Encode(parts[i]);
      }
      return parts.join("/");
   },

   /**
    * Create filter parameters based on input parameters
    *
    * @method getFilterParams
    * @param filter {string} Required filter
    * @param parsedArgs {object} Parsed arguments object literal
    * @param optional {object} Optional arguments depending on filter type
    * @return {object} Object literal containing parameters to be used in Lucene search
    */
   getFilterParams: function Filter_getFilterParams(filter, parsedArgs, optional)
   {
      var filterParams =
      {
         query: "+PATH:\"" + parsedArgs.pathNode.qnamePath + "/*\"",
         limitResults: null,
         sort: [
         {
            column: "@cm:name",
            ascending: true
         }],
         language: "lucene",
         templates: null,
         variablePath: true
      };

      optional = optional || {};
// @migration@ voir si les modif dans doclob est encore util
      // Sorting parameters specified?
      var sortAscending = args.sortAsc,
         sortField = args.sortField;

      if (sortAscending == "false")
      {
         filterParams.sort[0].ascending = false;
      }
      if (sortField !== null)
      {
         filterParams.sort[0].column = (sortField.indexOf(":") != -1 ? "@" : "") + sortField;
      }

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
      var filterData = String(args.filterData),
         filterQuery = "";

      // Common types and aspects to filter from the UI - known subtypes of cm:content and cm:folder
      var filterQueryDefaults = ' -TYPE:"' + Filters.IGNORED_TYPES.join('" -TYPE:"') + '"';

      switch (String(filter))
      {
         case "all":
            filterQuery = "+PATH:\"" + parsedArgs.rootNode.qnamePath + "//*\"";
            filterQuery += " +TYPE:\"cm:content\"";
            filterParams.query = filterQuery + filterQueryDefaults;
            break;

         case "recentlyAdded":
         case "recentlyModified":
         case "recentlyCreatedByMe":
         case "recentlyModifiedByMe":
            var onlySelf = (filter.indexOf("ByMe")) > 0 ? true : false,
               dateField = (filter.indexOf("Modified") > 0) ? "modified" : "created",
               ownerField = (dateField == "created") ? "creator" : "modifier";

            // Default to 7 days - can be overridden using "days" argument
            var dayCount = 7,
               argDays = args.days;
            if ((argDays !== null) && !isNaN(argDays))
            {
               dayCount = argDays;
            }

            // Default limit to 50 documents - can be overridden using "max" argument
            if (filterParams.limitResults === null)
            {
               filterParams.limitResults = 50;
            }

            var date = new Date();
            var toQuery = date.getFullYear() + "\\-" + (date.getMonth() + 1) + "\\-" + date.getDate();
            date.setDate(date.getDate() - dayCount);
            var fromQuery = date.getFullYear() + "\\-" + (date.getMonth() + 1) + "\\-" + date.getDate();

            filterQuery = this.constructPathQuery(parsedArgs);
            filterQuery += " +@cm\\:" + dateField + ":[" + fromQuery + "T00\\:00\\:00.000 TO " + toQuery + "T23\\:59\\:59.999]";
            if (onlySelf)
            {
               filterQuery += " +@cm\\:" + ownerField + ":\"" + person.properties.userName + '"';
            }
            filterQuery += " +TYPE:\"cm:content\"";

            filterParams.sort = [
            {
               column: "@cm:" + dateField,
               ascending: false
            }];
            filterParams.query = filterQuery + filterQueryDefaults;
            break;

         case "editingMe":
            filterQuery = this.constructPathQuery(parsedArgs);
            filterQuery += " +((+ASPECT:\"workingcopy\"";
            filterQuery += " +@cm\\:workingCopyOwner:\"" + person.properties.userName + '")';
            filterQuery += " OR (+@cm\\:lockOwner:\"" + person.properties.userName + '"';
            filterQuery += " +@cm\\:lockType:\"WRITE_LOCK\"))";
            filterParams.query = filterQuery;
            break;

         case "editingOthers":
            filterQuery = this.constructPathQuery(parsedArgs);
            filterQuery += " +((+ASPECT:\"workingcopy\"";
            filterQuery += " -@cm\\:workingCopyOwner:\"" + person.properties.userName + '")';
            filterQuery += " OR (-@cm\\:lockOwner:\"" + person.properties.userName + '"';
            filterQuery += " +@cm\\:lockType:\"WRITE_LOCK\"))";
            filterParams.query = filterQuery;
            break;

         case "favourites":
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
            
            if (filterQuery.length > 0)
            {
               filterQuery = "+(" + filterQuery + ") " + this.constructPathQuery(parsedArgs);
            }
            else
            {
               // empty favourites query
               filterQuery = "+ID:\"\"";
            }
            
            filterParams.query = filterQuery;
            break;

         case "node":
            filterParams.variablePath = false;
            filterParams.query = "+ID:\"" + parsedArgs.nodeRef + "\"";
            break;

         case "tag":
            // Remove any trailing "/" character
            if (filterData.charAt(filterData.length - 1) == "/")
            {
               filterData = filterData.slice(0, -1);
            }
            filterQuery = this.constructPathQuery(parsedArgs);
            filterParams.query = filterQuery + " +PATH:\"/cm:taggable/cm:" + search.ISO9075Encode(filterData) + "/member\"";
            break;
         // SIDE start
         case "metadata":
        	 var type,aspect,notAspects,path;
        	 var searchObj = {};
        	 if (parsedArgs.args.search) {
        		 logger.log("parsedArgs.args.search :" + parsedArgs.args.search);
        		 searchObj = eval('('+parsedArgs.args.search+')');
        	 }
        	 if (filterData != null && filterData != "" && filterData != "null") {
        		 logger.log("filterData :" + filterData);
        		 searchObj = eval('('+filterData+')');
        	 }
        	 
        	 type = searchObj.type;
        	 aspect = searchObj.aspect;
        	 notAspects = searchObj.notAspects;
        	 path = searchObj.path;
        	 
        	 
        	 var logicalOperator = " AND ";
        	 if (searchObj.operator && searchObj.operator.toLowerCase() == "or") {
            	logicalOperator = " OR ";
             }
        	  
        	 var query = "";
        	 var query_type = "";
        	 var query_aspect = "";
        	 var query_path = "";
        	 var query_notAspects = "";
        	 
        	 if (type) {
        		 query_type = '+TYPE:"' + type + '"';
        	 }
        	 if (aspect) {
        		 query_aspect = '+ASPECT:"' + aspect + '"';
        	 }
        	 
        	 if (notAspects) {
        		 for (var c=0 ; c<notAspects.length ; c++) {
        			 query_notAspects += ' -ASPECT:"' + notAspects[c] + '"'; 
        		 }        		 
        	 }
        	 
        	 if (path) {
        		 query_path = '+PATH:"' + path + '"';
        	 }
        	 
        	 query = query_type + " " + query_aspect + " " + query_path + " " + query_notAspects + " ";
        	 
        	 
        	 var propQuery="";
        	 
        	 /*
				 * for (var prop in properties) { if (properties[prop] != "") {
				 * propQuery+="@"+prop; propQuery+=":"+properties[prop];
				 * propQuery+=operator; } }
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
        	 //TODO replace this by calling search.lib.js and set filterParams as queryDef 
        	 filterParams.query= getSearchQuery(term, maxResults, siteId, containerId);
        	 break;
             // SIDE end
         case "category":
            // Remove any trailing "/" character
            if (filterData.charAt(filterData.length - 1) == "/")
            {
               filterData = filterData.slice(0, -1);
            }
            filterParams.query = "+PATH:\"/cm:generalclassifiable" + Filters.iso9075EncodePath(filterData) + "/member\"";
            break;
         case "savedSearch":
        	filterParams = getSavedSearchQueryDef(filterData);
        	break;
         default: // "path"
            filterParams.variablePath = false;
            filterQuery = "+PATH:\"" + parsedArgs.pathNode.qnamePath + "/*\"";
            filterParams.query = filterQuery + filterQueryDefaults;
            break;
      }

      // Specialise by passed-in type
      if (filterParams.query !== "" && filter != "savedSearch")
      {
         filterParams.query += " " + (Filters.TYPE_MAP[parsedArgs.type] || "");
      }

      return filterParams;
   },
   
   constructPathQuery: function constructPathQuery(parsedArgs)
   {
      var pathQuery = "";
      if (parsedArgs.nodeRef != "alfresco://company/home")
      {
         pathQuery = "+PATH:\"" + parsedArgs.rootNode.qnamePath;
         if (parsedArgs.nodeRef == "alfresco://sites/home")
         {
            pathQuery += "/*/cm:documentLibrary";
         }
         pathQuery += "//*\"";
      }
      return pathQuery;
   },
   
   parseAdvancedSearch : function (json) {
		const operators = [];
		const simpleFieldTypes = [ "string", "char", "boolean" ];
		const boundedFieldTypes = [ "Date", "DateTime", "short", "byte", "int", "long", "double", "float" ];
		const suportedTypes = simpleFieldTypes.concat(boundedFieldTypes);
		const unsuportedTypes = [ "time", "object" ];

		/*
		 * example : json
		 * ={type:"{http://www.bluexml.com/model/content/DigitizationProcess/1.0}com_bluexml_side_models_liste_Document",
		 * operator:"and", fields:{
		 * "{http://www.bluexml.com/model/content/DigitizationProcess/1.0}com_bluexml_side_models_liste_Document_Libelle":{
		 * type:"String", operator:"contains", values:["ssd"]},
		 * "{http://www.bluexml.com/model/content/DigitizationProcess/1.0}com_bluexml_side_models_liste_Document_Numero":{type:"String",operator:"is",values:[""]},
		 * "{http://www.bluexml.com/model/content/DigitizationProcess/1.0}com_bluexml_side_models_liste_Document_Observation":{type:"String",operator:"istartsWith",values:[""]},
		 * "{http://www.bluexml.com/model/content/DigitizationProcess/1.0}com_bluexml_side_models_liste_Document_DateNumerisation":{
		 * type:"DateTime", operator:"between",
		 * values:["2010-02-24T12:26:14.144+01:00","2010-02-24T12:26:14.161+01:00"]},
		 * "{http://www.bluexml.com/model/content/DigitizationProcess/1.0}com_bluexml_side_models_liste_Document_Auteur":{type:"String",operator:"is",values:[""]} } };
		 */
		var type = json.type;
		var logicalOperator = " AND ";
		if (json.operator && json.operator.toLowerCase() == "or") {
			logicalOperator = " OR ";
		}
		var properties = json.fields;

		var propQuery = "";
		for ( var prop in properties) {
			var att = properties[prop];
			if (att.values.length > 0 && att.values[0] != "") {
				var dataType = att.type.toLowerCase();
				var comparator = att.operator.toLowerCase();
				var values = att.values;

				var propQuery_local = " @" + prop.replace(":","\\:");

				switch (dataType) {
				// simple
				case "string":
					var valuePart = "";
					switch (comparator) {
					case "contains":
						valuePart = "*" + values[0] + "*";
						break;
					case "icontains":
						valuePart = "*" + values[0] + "*";
						break;
					case "startswith":
						valuePart = values[0] + "*";
						break;
					case "istartswith":
						valuePart = values[0] + "*";
						break;
					case "endswith":
						valuePart = "*" + values[0];
						break;
					case "iendswith":
						valuePart = "*" + values[0];
						break;
					case "empty":
						// property is not set how to query this ?
						break;
					case "is":
						valuePart = values[0];
						break;
					default:
						break;
					}
					propQuery += propQuery_local + ":" + '"' + valuePart + '"';
					break;
				case "char":
					break;
				case "boolean":
					propQuery += propQuery_local + ":" + values[0];
					break;
				// bounded
				case "datetime":
				case "date":
					var min = "MIN";
					var max = "MAX";
					values[0] = values[0].replace(/-/g, '\-');
					if (values.length > 1) {
						values[1] = values[1].replace(/-/g, '\-');
					}
					switch (comparator) {
					case "between":
						min = values[0];
						max = values[1];
						propQuery += propQuery_local + ":" + "[" + min + " TO " + max + "]";
						break;
					case "before":
						max = values[0];
						propQuery += propQuery_local + ":" + "[" + min + " TO " + max + "]";
						break;
					case "after":
						min = values[0];
						propQuery += propQuery_local + ":" + "[" + min + " TO " + max + "]";
						break;
					case "exactly":
						propQuery += propQuery_local + ":" + values[0];
						break;
					case "empty":
						// property is not set how to query this ?
						break;
					default:
						break;
					}

					break;
				case "enums":
					var query = "";
					switch (comparator) {
					case "oneof":
						if (values.length > 1) {
							query = " +(";
						}
						for ( var c = 0; c < values.length; c++) {
							query += propQuery_local + ":" + '"' + values[c] + '"';
							query += " OR ";
						}

						var ind = query.lastIndexOf(" OR ");
						query = query.substring(0, ind);
						if (values.length > 1) {
							query += ")";
						}

						break;
					case "allbut":
						if (values.length > 1) {
							query = " -(";
						}
						for ( var c = 0; c < values.length; c++) {
							query += propQuery_local + ":" + '"' + values[c] + '"';
							query += " OR ";
						}
						var ind = query.lastIndexOf(" OR ");
						query = query.substring(0, ind);

						if (values.length > 1) {
							query += ")";
						}
						break;
					case "none":
						// property is not set how to query this ?
						break;

					default:
						break;
					}
					propQuery += query;
					break;
				case "byte":
				case "int":
				case "long":
				case "double":
				case "float":
				case "short":
					var min = "MIN";
					var max = "MAX";

					switch (comparator) {
					case "between":
						min = values[0];
						max = values[1];
						propQuery += propQuery_local + ":" + "[" + min + " TO " + max + "]";
						break;
					case "below":
						max = values[0];
						propQuery += propQuery_local + ":" + "[" + min + " TO " + max + "]";
						break;
					case "above":
						min = values[0];
						propQuery += propQuery_local + ":" + "[" + min + " TO " + max + "]";
						break;
					case "exactly":
						if (value[0].indexOf("\.")) {
							value[0] = value[0].replace(/([^.]*\.[^.]*)/, '"$1"');
						}
						propQuery += propQuery_local + ":" + values[0];
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

				propQuery += logicalOperator;
			}
		}
		return propQuery;
	}
};
