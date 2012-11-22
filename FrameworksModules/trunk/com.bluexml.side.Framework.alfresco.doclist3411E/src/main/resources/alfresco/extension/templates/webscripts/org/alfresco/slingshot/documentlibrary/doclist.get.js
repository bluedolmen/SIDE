<import resource="classpath:/alfresco/templates/webscripts/org/alfresco/slingshot/documentlibrary/evaluator.lib.js">
<import resource="classpath:/alfresco/extension/templates/webscripts/org/alfresco/slingshot/documentlibrary/filters.lib.js">
<import resource="classpath:/alfresco/templates/webscripts/org/alfresco/slingshot/documentlibrary/parse-args.lib.js">

/**
 * Main entry point: Create collection of documents and folders in the given
 * space
 * 
 * @method getDoclist
 */
function getDoclist()
{
   // Use helper function to get the arguments
   var parsedArgs = ParseArgs.getParsedArgs();
   if (parsedArgs === null)
   {
      return;
   }

// SIDE
   parsedArgs.args=args;
   
   var filter = args.filter,
      items = [];

   // Try to find a filter query based on the passed-in arguments
   var allNodes = [],
      favourites = Common.getFavourites(),
      filterParams = Filters.getFilterParams(filter, parsedArgs,
      {
         favourites: favourites
      }),
      query = filterParams.query,
      allSites = (parsedArgs.nodeRef == "alfresco://sites/home");

	if (logger.isLoggingEnabled())
      logger.log("doclist.get.js - NodeRef: " + parsedArgs.nodeRef + " Query: " + query);
   // Query the nodes - passing in sort and result limit parameters
   var totalItemCount = filterParams.limitResults ? parseInt(filterParams.limitResults, 10) : -1;
   // For all sites documentLibrary query we pull in all available results and post filter
   if (totalItemCount === 0) totalItemCount = -1;
   else if (allSites) totalItemCount = -1;
   if (query !== "" || filter == "savedSearch")
   {
	   // SIDE override sort parameters using sort parameter (see share config
		// Search)
	   /**
		 * see search.lib.js#getSearchResults
		 */
		if (args.sorting) 
		{
			
			var sortColumns = [];
			var sortParam = args.sorting;
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
			
			filterParams.sort = sortColumns;
		}
		
	  var queryDef = null;
	  if (filter == "savedSearch") {
		  queryDef = filterParams;
	  } else {
		  queryDef = {
	         query: query,
	         language: filterParams.language,
	         page: {
	        	 	maxItems: totalItemCount
	         },
	         sort: filterParams.sort,
	         templates: filterParams.templates,
	         namespace: (filterParams.namespace ? filterParams.namespace : null)
		 };
			 
	  }
	  logger.log("doclist queryDef :" + queryDef.toSource());
      allNodes = search.query(queryDef);
   }

	   if (logger.isLoggingEnabled())
      logger.log("doclist.get.js - query results: " + allNodes.length);

   // Generate the qname path match regex required for all sites 'documentLibrary' results match
   var pathRegex;
   if (allSites)
   {
      // escape the forward slash characters in the qname path
      // TODO: replace with java.lang.String regex match for performance
      var pathMatch = new String(parsedArgs.rootNode.qnamePath).replace(/\//g, '\\/') + "\\/.*\\/cm:documentLibrary\\/.*";
      pathRegex = new RegExp(pathMatch, "gi");
      if (logger.isLoggingEnabled())
         logger.log("doclist.get.js - will match results using regex: " + pathMatch);
   }
	

   // Ensure folders and folderlinks appear at the top of the list
   var folderNodes = [],
      documentNodes = [];
   
   for each (node in allNodes)
   {
      if (totalItemCount !== 0)
      {
      try
      {
            if (!allSites || node.qnamePath.match(pathRegex))
            {
               totalItemCount--;
         if (node.isContainer || node.typeShort == "app:folderlink")
         {
            folderNodes.push(node);
         }
         else
         {
            documentNodes.push(node);
         }
      }
         }
      catch (e)
      {
         // Possibly an old indexed node - ignore it
      }
      } else break;
   }
   
   // Node type counts
   var folderNodesCount = folderNodes.length,
      documentNodesCount = documentNodes.length,
      nodes, totalRecords;

   if (parsedArgs.type === "documents")
   {
      nodes = documentNodes;
   }
   else
   {
      nodes = folderNodes.concat(documentNodes);
   }
   totalRecords = nodes.length;
   
   if (logger.isLoggingEnabled())
      logger.log("doclist.get.js - totalRecords: " + totalRecords);

   // Pagination
   var pageSize = args.size || nodes.length,
      pagePos = args.pos || "1",
      startIndex = (pagePos - 1) * pageSize;

   // Trim the nodes array down to the page size
   nodes = nodes.slice(startIndex, pagePos * pageSize);

   // Common or variable parent container?
   var parent = null;
   
   if (!filterParams.variablePath)
   {
      // Parent node permissions (and Site role if applicable)
      parent =
      {
         node: parsedArgs.pathNode,
         userAccess: Evaluator.run(parsedArgs.pathNode, true).actionPermissions
      };
   }

   var isThumbnailNameRegistered = thumbnailService.isThumbnailNameRegistered(THUMBNAIL_NAME),
      thumbnail = null,
      locationNode,
      item;
   
   // Loop through and evaluate each node in this result set
   for each (node in nodes)
   {
      // Get evaluated properties.
      item = Evaluator.run(node);
      if (item !== null)
      {
         item.isFavourite = (favourites[item.node.nodeRef] === true);
   
         // Does this collection of nodes have potentially differering paths?
         if (filterParams.variablePath || item.isLink)
         {
            locationNode = item.isLink ? item.linkedNode : item.node;
            // Ensure we have Read permissions on the destination on the link object
            if (!locationNode.hasPermission("Read")) break;
            location = Common.getLocation(locationNode, parsedArgs.libraryRoot);
         }
         else
         {
            location =
            {
               site: parsedArgs.location.site,
               siteTitle: parsedArgs.location.siteTitle,
               container: parsedArgs.location.container,
               path: parsedArgs.location.path,
               file: node.name
            };
         }
         location.parent = {};
         if (node.parent != null && node.parent.hasPermission("Read"))
         {
            location.parent.nodeRef = String(node.parent.nodeRef.toString());  
         }
         
         // Resolved location
         item.location = location;
         
         // Is our thumbnail type registered?
         if (isThumbnailNameRegistered && item.node.isSubType("cm:content"))
         {
            // Make sure we have a thumbnail.
            thumbnail = item.node.getThumbnail(THUMBNAIL_NAME);
            if (thumbnail === null)
            {
               // No thumbnail, so queue creation
               item.node.createThumbnail(THUMBNAIL_NAME, true);
            }
         }
         
         items.push(item);
      }
      else
      {
         totalRecords -= 1;
      }
   }

   // Array Remove - By John Resig (MIT Licensed)
   var fnArrayRemove = function fnArrayRemove(array, from, to)
   {
     var rest = array.slice((to || from) + 1 || array.length);
     array.length = from < 0 ? array.length + from : from;
     return array.push.apply(array, rest);
   };
   
   /**
    * De-duplicate orignals for any existing working copies.
    * This can't be done in evaluator.lib.js as it has no knowledge of the current filter or UI operation.
    * Note: This may result in pages containing less than the configured amount of items (50 by default).
	 */
   for each (item in items)
   {
      if (item.customObj.isWorkingCopy)
      {
         var workingCopyOriginal = String(item.customObj.workingCopyOriginal);
         for (var i = 0, ii = items.length; i < ii; i++)
         {
            if (String(items[i].node.nodeRef) == workingCopyOriginal)
            {
               fnArrayRemove(items, i);
               --totalRecords;
               break;
            }
         }
      }
   }

   return (
   {
      luceneQuery: query,
      paging:
      {
         totalRecords: totalRecords,
         startIndex: startIndex
      },
      container: parsedArgs.rootNode,
      parent: parent,
      onlineEditing: utils.moduleInstalled("org.alfresco.module.vti"),
      itemCount:
      {
         folders: folderNodesCount,
         documents: documentNodesCount
      },
      items: items
   });
}

/**
 * Document List Component: doclist
 */
model.doclist = getDoclist();