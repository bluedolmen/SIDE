<import resource="classpath:alfresco/extension/templates/webscripts/org/alfresco/slingshot/search/search.lib.js">

/**
 * Return Search results with the given search terms
 * Terms are split on whitespace characters.
 * 
 * AND, OR and NOT are supported - as their Lucene equivalent.
 */
function getSearchQuery(term, maxResults, siteId, containerId)
{
   var path = SITES_SPACE_QNAME_PATH;
   if (siteId !== null && siteId.length > 0)
   {
      path += "cm:" + search.ISO9075Encode(siteId) + "/";
   }
   else
   {
      path += "*/";
   }
   if (containerId !== null && containerId.length > 0)
   {
      path += "cm:" + search.ISO9075Encode(containerId) + "/";
   }
   else
   {
      path += "*/";
   }
	
   var luceneQuery = "";
   if (term !== null && term.length !== 0)
   {
      // TODO: Perform smarter term processing. For now we simply split on whitespace
      //       which ignores quoted phrases that may be present.
      var terms = term.split(/\s/),
         i, j, t;
      
      for (i = 0, j = terms.length; i < j; i++)
      {
         t = terms[i];
         // remove quotes - TODO: add support for quoted terms later
         t = t.replace(/\"/g, "");
         if (t.length !== 0)
         {
            switch (t.toLowerCase())
            {
               case "and":
                  if (i < j - 1 && terms[i + 1].length !== 0)
                  {
                     luceneQuery += "AND ";
                  }
                  break;
               
               case "or":
                  break;
               
               case "not":
                  if (i < j - 1 && terms[i + 1].length !== 0)
                  {
                     luceneQuery += "NOT ";
                  }
                  break;
               
               default:
                  luceneQuery += "(TEXT:\"" + t + "\"" +        // full text
                                 " @cm\\:name:\"" + t + "\"" +  // name property
                                 " @lnk\\:title:\"" + t + "\"" +  // link title
                                 " @lnk\\:description:\"" + t + "\"" +  // link description
                                 " PATH:\"/cm:taggable/cm:" + search.ISO9075Encode(t) + "/member\"" + // tags
                                 ") ";
            }
         }
      }
   }
   
   
   
   // if we processed the search terms, then suffix the PATH query
   if (luceneQuery.length !== 0)
   {
      luceneQuery = "+PATH:\"" + path + "/*\" +(" + luceneQuery + ")";
      luceneQuery += " -TYPE:\"{http://www.alfresco.org/model/content/1.0}thumbnail\"";
      
   }
   else
   {
      return null;
   }
   
   return luceneQuery;
}

function getSavedSearchQueryDef(filterdata) {
	var data = eval('(' + filterdata + ')');
	var node = search.findNode(data.nodeRef);
	
	var queryString = "";
	if (data.dataProperty == "cm:content") {
		queryString = node.content;
	} else {
		queryString = node.properties[data.dataProperty];
	}
	logger.log("getSavedSearchQuery " + queryString);
	
	var savedSearch = eval('(' + queryString + ')');
	var filterNodetype = node.typeShort; 
	return getSearchDef(savedSearch, filterNodetype);
}


function getSearchDef(params, filterNodetype)
{
   var ftsQuery = "",
      term = params.term,
      tag = params.tag,
      formData = params.query;
   
   // Simple keyword search and tag specific search
   if (term !== null && term.length !== 0)
   {
	  // TAG is now part of the default macro
      ftsQuery = term + " ";
   }
   else if (tag !== null && tag.length !== 0)
   {
	  // Just look for tag
      ftsQuery = "TAG:" + tag +" ";
   }
   
   // Advanced search form data search.
   // Supplied as json in the standard Alfresco Forms data structure:
   //    prop_<name>:value|assoc_<name>:value
   //    name = namespace_propertyname|pseudopropertyname
   //    value = string value - comma separated for multi-value, no escaping yet!
   // - underscore represents colon character in name
   // - pseudo property is one of any cm:content url property: mimetype|encoding|size
   // - always string values - interogate DD for type data
   if (formData !== null && formData.length !== 0)
   {
      var formQuery = "",
          formJson = jsonUtils.toObject(formData);
      
      
      var repoSearch = params.repo;
      var searchPath = getSearchPath(formJson);      
      if (searchPath != null) {
    	  repoSearch = true;
      }
      
      
      formQuery = buildFormQuery(formJson);
      
      
      
      if (formQuery.length !== 0 || ftsQuery.length !== 0 || repoSearch)
      {
         // extract data type for this search - advanced search query is type
			// specific
         ftsQuery = 'TYPE:"' + formJson.datatype + '"' +
                    (formQuery.length !== 0 ? ' AND (' + formQuery + ')' : '') +
                    (ftsQuery.length !== 0 ? ' AND (' + ftsQuery + ')' : '');
      }
   }
   
   if (ftsQuery.length !== 0 || repoSearch)
   {
      // we processed the search terms, so suffix the PATH query
      var path = null;
      if (!repoSearch)
      {
         path = SITES_SPACE_QNAME_PATH;
         if (params.siteId !== null && params.siteId.length > 0)
         {
            path += "cm:" + search.ISO9075Encode(params.siteId) + "/";
         }
         else
         {
            path += "*/";
         }
         if (params.containerId !== null && params.containerId.length > 0)
         {
            path += "cm:" + search.ISO9075Encode(params.containerId) + "/";
         }
         else
         {
            path += "*/";
         }
      } else if (searchPath != undefined && searchPath != ''){    	  
    	  path = searchPath;
    	  if (getSearchSubdirectories(formJson)) {
    		  path += "/";
    	  }
      }
      
      if (path != null)
      {
         ftsQuery = 'PATH:"' + path + '/*" AND (' + ftsQuery + ') ';
      }
      ftsQuery = '(' + ftsQuery + ') AND -TYPE:"cm:thumbnail"';
      
      // sort field - expecting field to in one of the following formats:
      //  - short QName form such as: cm:name
      //  - pseudo cm:content field starting with "." such as: .size
      //  - any other directly supported search field such as: TYPE
      var sortColumns = [];
      var sortParam = params.sort;
      if (sortParam != null && sortParam.length != 0)
      {
    	 var sort = sortParam;
         var asc = true;
         var separator = sort.indexOf("|");
         if (separator != -1)
         {
            sort = sort.substring(0, separator);
            asc = (sortParam.substring(separator + 1) == "true");
         }
         var column;
         if (sort.charAt(0) == '.')
         {
            // handle pseudo cm:content fields
            column = "@{http://www.alfresco.org/model/content/1.0}content" + sort;
         }
         else if (sort.indexOf(":") != -1)
         {
            // handle attribute field sort
            column = "@" + utils.longQName(sort);
         }
         else
         {
            // other sort types e.g. TYPE
            column = sort;
         }
         sortColumns.push(
         {
            column: column,
            ascending: asc
         });
      }
      
      // perform fts-alfresco language query
      var queryDef = {
         query: ftsQuery,
         language: "fts-alfresco",
         page: {maxItems: params.maxResults},
         templates: QUERY_TEMPLATES,
         defaultField: "keywords",
         onerror: "no-results",
         sort: sortColumns 
      };
      if (logger.isLoggingEnabled()) {
    	 logger.log("search.lib.js queryDef:"+queryDef.toSource());  
      }
      
      return queryDef;
      
   }
   return null;
}
