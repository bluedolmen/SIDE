<import resource="classpath:/alfresco/templates/webscripts/org/alfresco/slingshot/documentlibrary/evaluator.lib.js">
<import resource="classpath:/alfresco/extension/templates/webscripts/org/alfresco/slingshot/documentlibrary/filters.lib.js">
<import resource="classpath:/alfresco/templates/webscripts/org/alfresco/slingshot/documentlibrary/parse-args.lib.js">

const
REQUEST_MAX = 1000;

/**
 * Main entry point: Create collection of documents and folders in the given
 * space
 * 
 * @method getDoclist
 */
function getDoclist() {
   // Use helper function to get the arguments
   var parsedArgs = ParseArgs.getParsedArgs();
   if (parsedArgs === null)
   {
      return;
   }

   // SIDE
   parsedArgs.args = args;

   var filter = args.filter, items = [];

   // Try to find a filter query based on the passed-in arguments
   var allNodes = [], totalRecords = 0, requestTotalCountMax = 0, paged = false, favourites = Common.getFavourites(), filterParams = Filters.getFilterParams(filter, parsedArgs,
   {
      favourites : favourites
      }),
      query = filterParams.query,
      allSites = (parsedArgs.nodeRef == "alfresco://sites/home");

   if (logger.isLoggingEnabled())
      logger.log("doclist.get.js - NodeRef: " + parsedArgs.nodeRef + " Query: " + query);

   var totalItemCount = filterParams.limitResults ? parseInt(filterParams.limitResults, 10) : -1;
   // For all sites documentLibrary query we pull in all available results and post filter
   if (totalItemCount === 0) totalItemCount = -1;
   else if (allSites) totalItemCount = (totalItemCount > 0 ? totalItemCount * 10 : 500);
   
   if ((filter || "path") == "path")
   {
      // TODO also add DB filter by "node" (in addition to "path")

      var parentNode = parsedArgs.pathNode;
      if (parentNode !== null)
      {
         var ignoreTypes = Filters.IGNORED_TYPES;
         skip = -1, max = -1;

         if (args.size != null)
         {
            max = args.size;

            if (args.pos > 0)
            {
               skip = (args.pos - 1) * max;
            }
         }

         var sortField = (args.sortField == null ? "cm:name" : args.sortField),
            sortAsc = (((args.sortAsc == null) || (args.sortAsc == "true")) ? true : false);

         // Get paged set
         requestTotalCountMax = skip + REQUEST_MAX;
         var pagedResult = parentNode.childFileFolders(true, true, ignoreTypes, skip, max, requestTotalCountMax, sortField, sortAsc, "TODO");

         allNodes = pagedResult.page;
         totalRecords = pagedResult.totalResultCountUpper;
         paged = true;
      }
   } else
   {

      // Query the nodes - passing in sort and result limit parameters
      if (query !== "" || filter == "savedSearch")
      {
         // SIDE override sort parameters using sort parameter (see share
         // config
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
               } else if (sort.indexOf(":") != -1)
               {
                  // handle attribute field sort
                  column = "@" + utils.longQName(sort);
               } else
               {
                  // other sort types e.g. TYPE
                  column = sort;
               }
               sortColumns.push(
               {
                  column : column,
                  ascending : asc
               });
            }

            filterParams.sort = sortColumns;
         }

         var queryDef = null;
         if (filter == "savedSearch")
         {
            queryDef = filterParams;
         } else
         {
            queryDef =
            {
               query : query,
               language : filterParams.language,
               page :
               {
                  maxItems : (filterParams.limitResults ? parseInt(filterParams.limitResults, 10) : 0)
               },
               sort : filterParams.sort,
               templates : filterParams.templates,
               namespace : (filterParams.namespace ? filterParams.namespace : null)
            };

         }
         logger.log("doclist queryDef :" + queryDef.toSource());
         allNodes = search.query(queryDef);
      }
   }
   // Ensure folders and folderlinks appear at the top of the list
   var folderNodes = [], documentNodes = [];

   for each(node in allNodes)
   {
      try
      {
         if (node.isContainer || node.isLinkToContainer)
         {
            folderNodes.push(node);
         } else
         {
            documentNodes.push(node);
         }
      } catch (e)
      {
         // Possibly an old indexed node - ignore it
      }
   }

   // Node type counts
   var folderNodesCount = folderNodes.length, documentNodesCount = documentNodes.length, nodes;

   if (parsedArgs.type === "documents")
   {
      nodes = documentNodes;
      totalRecords -= folderNodesCount;
   } else
   {
      // TODO: Sorting with folders at end -- swap order of concat()
      nodes = folderNodes.concat(documentNodes);
   }

   // Pagination
   var pageSize = args.size || nodes.length, pagePos = args.pos || "1", startIndex = (pagePos - 1) * pageSize;

   if (!paged)
   {
      // Trim the nodes array down to the page size
      nodes = nodes.slice(startIndex, pagePos * pageSize);
   }

   // Common or variable parent container?
   var parent = null;

   if (!filterParams.variablePath)
   {
      // Parent node permissions (and Site role if applicable)
      parent =
      {
         node : parsedArgs.pathNode,
         userAccess : Evaluator.run(parsedArgs.pathNode, true).actionPermissions
      };
   }

   var isThumbnailNameRegistered = thumbnailService.isThumbnailNameRegistered(THUMBNAIL_NAME), thumbnail = null, locationNode, item;

   // Loop through and evaluate each node in this result set
   for each(node in nodes)
   {
      // Get evaluated properties.
      item = Evaluator.run(node);
      if (item !== null)
      {
         item.isFavourite = (favourites[item.node.nodeRef] === true);
         item.likes = Common.getLikes(node);

         // Does this collection of nodes have potentially differering paths?
         if (filterParams.variablePath || item.isLink)
         {
            locationNode = item.isLink ? item.linkedNode : item.node;
            // Ensure we have Read permissions on the destination on the link
            // object
            if (!locationNode.hasPermission("Read"))
               break;
            location = Common.getLocation(locationNode, parsedArgs.libraryRoot);
         } else
         {
            location =
            {
               site : parsedArgs.location.site,
               siteTitle : parsedArgs.location.siteTitle,
               container : parsedArgs.location.container,
               path : parsedArgs.location.path,
               file : node.name
            };
         }
         location.parent =
         {};
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
      } else
      {
         --totalRecords;
      }
   }

   // Array Remove - By John Resig (MIT Licensed)
   var fnArrayRemove = function fnArrayRemove(array, from, to) {
      var rest = array.slice((to || from) + 1 || array.length);
      array.length = from < 0 ? array.length + from : from;
      return array.push.apply(array, rest);
   };

   /**
    * De-duplicate orignals for any existing working copies. This can't be done
    * in evaluator.lib.js as it has no knowledge of the current filter or UI
    * operation. Note: This may result in pages containing less than the
    * configured amount of items (50 by default).
    */
   for each(item in items)
   {
      if (item.customObj.isWorkingCopy)
      {
         var workingCopyOriginal = String(item.customObj.workingCopyOriginal);
         for ( var i = 0, ii = items.length; i < ii; i++)
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

   var paging =
   {
      totalRecords : totalRecords,
      startIndex : startIndex
   };

   if (paged && (totalRecords == requestTotalCountMax))
   {
      paging.totalRecordsUpper = requestTotalCountMax;
   }

   return (
   {
      luceneQuery : query,
      paging : paging,
      container : parsedArgs.rootNode,
      parent : parent,
      onlineEditing : utils.moduleInstalled("org.alfresco.module.vti"),
      itemCount :
      {
         folders : folderNodesCount,
         documents : documentNodesCount
      },
      items : items
   });
}

/**
 * Document List Component: doclist
 */
model.doclist = getDoclist();