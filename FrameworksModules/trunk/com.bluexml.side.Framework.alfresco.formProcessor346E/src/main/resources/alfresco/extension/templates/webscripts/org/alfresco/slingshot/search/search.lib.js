/**
 * Search Component
 * 
 * Takes the following object as Input: params { siteId: the site identifier to
 * search into, null for all sites containerId: the component the search in,
 * null for all components in the site term: search terms tag: search tag query:
 * advanced search query json sort: sort parameter maxResults: maximum results
 * to return };
 * 
 * Outputs: items - Array of objects containing the search results
 */
const
DEFAULT_MAX_RESULTS = 250;
const
SITES_SPACE_QNAME_PATH = "/app:company_home/st:sites/";
const
DISCUSSION_QNAMEPATH = "/fm:discussion";
const
COMMENT_QNAMEPATH = DISCUSSION_QNAMEPATH + "/cm:Comments/";
const
QUERY_TEMPLATES = [ {
   field : "keywords",
   template : "%(cm:name cm:title cm:description ia:whatEvent ia:descriptionEvent lnk:title lnk:description TEXT TAG)"
} ];

/**
 * Returns site information data structure. { shortName: siteId, title: title }
 * 
 * Caches the data to avoid repeatedly querying the repository.
 */
var siteDataCache = {};
function getSiteData(siteId) {
   if (typeof siteDataCache[siteId] === "object") {
      return siteDataCache[siteId];
   }
   var site = siteService.getSite(siteId);
   var data = {
      shortName : siteId,
      title : (site !== null ? site.title : "unknown")
   };
   siteDataCache[siteId] = data;
   return data;
}

/**
 * Returns person display name string as returned to the user.
 * 
 * Caches the person full name to avoid repeatedly querying the repository.
 */
var personDataCache = {};
function getPersonDisplayName(userId) {
   if (typeof personDataCache[userId] === "object") {
      return personDataCache[userId];
   }

   var displayName = "";
   var person = people.getPerson(userId);
   if (person != null) {
      displayName = person.properties.firstName + " " + person.properties.lastName;
   }
   personDataCache[userId] = displayName;
   return displayName;
}

/**
 * Cache to not display twice the same element (e.g. if two comments of the same
 * blog post match the search criteria
 */
var processedCache = {};
function addToProcessed(category, key) {
   var cat = processedCache[category];
   if (typeof cat !== "object") {
      processedCache[category] = [];
      cat = processedCache[category];
   }
   cat.push(key);
}
function checkProcessed(category, key) {
   var cat = processedCache[category];
   if (typeof cat === "object") {
      for ( var x in cat) {
         if (cat[x] == key) {
            return true;
         }
      }
   }
   return false;
}

/**
 * Returns an item outside of a site in the main repository.
 */
function getRepositoryItem(folderPath, node) {
   // check whether we already processed this document
   var cat = "repository", refkey = "" + node.nodeRef.toString();
   if (checkProcessed(cat, refkey)) {
      return null;
   }
   addToProcessed(cat, refkey);

   // check whether this is a valid folder or a file
   var item = t = null;
   if (node.qnamePath.indexOf(COMMENT_QNAMEPATH) == -1 && !(node.qnamePath.match(DISCUSSION_QNAMEPATH + "$") == DISCUSSION_QNAMEPATH)) {
      if (node.isContainer || node.isDocument) {
         item = {
            nodeRef : node.nodeRef.toString(),
            tags : ((t = node.tags) !== null) ? t : [],
            name : node.name,
            displayName : node.name,
            title : node.properties["cm:title"],
            description : node.properties["cm:description"],
            modifiedOn : node.properties["cm:modified"],
            modifiedByUser : node.properties["cm:modifier"],
            createdOn : node.properties["cm:created"],
            createdByUser : node.properties["cm:creator"],
            path : folderPath.join("/"),
            node : node
         };
         item.modifiedBy = getPersonDisplayName(item.modifiedByUser);
         item.createdBy = getPersonDisplayName(item.createdByUser);
      }
      if (node.isContainer) {
         item.type = "folder";
         item.size = -1;
      } else if (node.isDocument) {
         item.type = "document";
         item.size = node.size;
      }
   }

   return item;
}

/**
 * Returns an item of the document library component.
 */
function getDocumentItem(siteId, containerId, pathParts, node) {
   // PENDING: how to handle comments? the document should
   // be returned instead

   // check whether we already processed this document
   var cat = siteId + containerId, refkey = "" + node.nodeRef.toString();
   if (checkProcessed(cat, refkey)) {
      return null;
   }
   addToProcessed(cat, refkey);

   // check whether this is a valid folder or a file
   var item = t = null;
   if (node.qnamePath.indexOf(COMMENT_QNAMEPATH) == -1 && !(node.qnamePath.match(DISCUSSION_QNAMEPATH + "$") == DISCUSSION_QNAMEPATH)) {
      if (node.isContainer || node.isDocument) {
         item = {
            site : getSiteData(siteId),
            container : containerId,
            nodeRef : node.nodeRef.toString(),
            tags : ((t = node.tags) !== null) ? t : [],
            name : node.name,
            displayName : node.name,
            title : node.properties["cm:title"],
            description : node.properties["cm:description"],
            modifiedOn : node.properties["cm:modified"],
            modifiedByUser : node.properties["cm:modifier"],
            createdOn : node.properties["cm:created"],
            createdByUser : node.properties["cm:creator"],
            path : pathParts.join("/"),
            node : node
         };
         item.modifiedBy = getPersonDisplayName(item.modifiedByUser);
         item.createdBy = getPersonDisplayName(item.createdByUser);
      }
      if (node.isContainer) {
         item.type = "folder";
         item.size = -1;
      } else if (node.isDocument) {
         item.type = "document";
         item.size = node.size;
      }
   }

   return item;
}

function getBlogPostItem(siteId, containerId, pathParts, node) {
   /**
    * Investigate the rest of the path. the first item is the blog post, ignore
    * everything that follows are replies or folders
    */
   var site = siteService.getSite(siteId);
   var container = site.getContainer(containerId);

   /**
    * Find the direct child of the container Note: this only works for post
    * which are direct children of the blog container
    */
   var child = node;
   var parent = child.parent;
   while ((parent !== null) && (!parent.nodeRef.equals(container.nodeRef))) {
      child = parent;
      parent = parent.parent;
   }

   // check whether we found the container
   if (parent === null) {
      return null;
   }

   // check whether we already added this blog post
   var cat = siteId + containerId, refkey = "" + child.nodeRef.toString();
   if (checkProcessed(cat, refkey)) {
      return null;
   }
   addToProcessed(cat, refkey);

   // child is our blog post
   var item, t = null;
   item = {
      site : getSiteData(siteId),
      container : containerId,
      nodeRef : child.nodeRef.toString(),
      type : "blogpost",
      tags : ((t = child.tags) !== null) ? t : [],
      name : child.name,
      modifiedOn : child.properties["cm:modified"],
      modifiedByUser : child.properties["cm:modifier"],
      createdOn : node.properties["cm:created"],
      createdByUser : node.properties["cm:creator"],
      size : child.size,
      displayName : child.properties["cm:title"]
   };
   item.modifiedBy = getPersonDisplayName(item.modifiedByUser);
   item.createdBy = getPersonDisplayName(item.createdByUser);

   return item;
}

function getForumPostItem(siteId, containerId, pathParts, node) {
   // try to find the first fm:topic node, that's what we return as search
   // result
   var topicNode = node;
   while ((topicNode !== null) && (topicNode.type != "{http://www.alfresco.org/model/forum/1.0}topic")) {
      topicNode = topicNode.parent;
   }
   if (topicNode === null) {
      return null;
   }

   // make sure we haven't already added the post
   var cat = siteId + containerId, refkey = "" + topicNode.nodeRef.toString();
   if (checkProcessed(cat, refkey)) {
      return null;
   }
   addToProcessed(cat, refkey);

   // find the first post, which contains the post title
   // PENDING: error prone
   var postNode = topicNode.childAssocs["cm:contains"][0];

   // child is our forum post
   var item = t = null;
   item = {
      site : getSiteData(siteId),
      container : containerId,
      nodeRef : topicNode.nodeRef.toString(),
      type : "forumpost",
      tags : ((t = topicNode.tags) !== null) ? t : [],
      name : topicNode.name,
      description : topicNode.properties["cm:description"],
      modifiedOn : topicNode.properties["cm:modified"],
      modifiedByUser : topicNode.properties["cm:modifier"],
      createdOn : node.properties["cm:created"],
      createdByUser : node.properties["cm:creator"],
      size : topicNode.size,
      displayName : postNode.properties["cm:title"]
   };
   item.modifiedBy = getPersonDisplayName(item.modifiedByUser);
   item.createdBy = getPersonDisplayName(item.createdByUser);

   return item;
}

function getCalendarItem(siteId, containerId, pathParts, node) {
   // only process nodes of the correct type
   if (node.type != "{http://www.alfresco.org/model/calendar}calendarEvent") {
      return null;
   }

   // make sure we haven't already added the post
   var cat = siteId + containerId, refkey = "" + node.nodeRef.toString();
   if (checkProcessed(cat, refkey)) {
      return null;
   }
   addToProcessed(cat, refkey);

   var item, t = null;
   item = {
      site : getSiteData(siteId),
      container : containerId,
      nodeRef : node.nodeRef.toString(),
      type : "calendarevent",
      tags : ((t = node.tags) !== null) ? t : [],
      name : node.name,
      description : node.properties["ia:descriptionEvent"],
      modifiedOn : node.properties["cm:modified"],
      modifiedByUser : node.properties["cm:modifier"],
      createdOn : node.properties["cm:created"],
      createdByUser : node.properties["cm:creator"],
      size : -1,
      displayName : node.properties["ia:whatEvent"]
   };
   item.modifiedBy = getPersonDisplayName(item.modifiedByUser);
   item.createdBy = getPersonDisplayName(item.createdByUser);

   return item;
}

function getWikiItem(siteId, containerId, pathParts, node) {
   // only process documents
   if (!node.isDocument) {
      return null;
   }

   // make sure we haven't already added the page
   var cat = siteId + containerId, refkey = "" + node.nodeRef.toString();
   if (checkProcessed(cat, refkey)) {
      return null;
   }
   addToProcessed(cat, refkey);

   var item, t = null;
   item = {
      site : getSiteData(siteId),
      container : containerId,
      nodeRef : node.nodeRef.toString(),
      type : "wikipage",
      tags : ((t = node.tags) !== null) ? t : [],
      name : node.name,
      description : node.properties["cm:description"],
      modifiedOn : node.properties["cm:modified"],
      modifiedByUser : node.properties["cm:modifier"],
      createdOn : node.properties["cm:created"],
      createdByUser : node.properties["cm:creator"],
      size : node.size,
      displayName : ("" + node.name).replace(/_/g, " ")
   };
   item.modifiedBy = getPersonDisplayName(item.modifiedByUser);
   item.createdBy = getPersonDisplayName(item.createdByUser);

   return item;
}

function getLinkItem(siteId, containerId, pathParts, node) {
   // only process documents
   if (!node.isDocument) {
      return null;
   }

   // make sure we haven't already added this link
   var cat = siteId + containerId, refkey = "" + node.nodeRef.toString();
   if (checkProcessed(cat, refkey)) {
      return null;
   }
   addToProcessed(cat, refkey);

   var item = t = null;
   if (node.qnamePath.indexOf(COMMENT_QNAMEPATH) == -1 && !(node.qnamePath.match(DISCUSSION_QNAMEPATH + "$") == DISCUSSION_QNAMEPATH)) {
      item = {
         site : getSiteData(siteId),
         container : containerId,
         nodeRef : node.nodeRef.toString(),
         type : "link",
         tags : ((t = node.tags) !== null) ? t : [],
         name : node.name,
         description : node.properties["cm:description"],
         modifiedOn : node.properties["cm:modified"],
         modifiedByUser : node.properties["cm:modifier"],
         createdOn : node.properties["cm:created"],
         createdByUser : node.properties["cm:creator"],
         size : -1,
         displayName : node.properties["lnk:title"]
      };
      item.modifiedBy = getPersonDisplayName(item.modifiedByUser);
      item.createdBy = getPersonDisplayName(item.createdByUser);
   }

   return item;
}

function getDataItem(siteId, containerId, pathParts, node) {
   // make sure we haven't already added this item
   var cat = siteId + containerId, refkey = "" + node.nodeRef.toString();
   if (checkProcessed(cat, refkey)) {
      return null;
   }
   addToProcessed(cat, refkey);

   var item = null;

   // data item can be either ba containing dl:dataList or any dl:dataListItem
   // subtype
   if (node.type == "{http://www.alfresco.org/model/datalist/1.0}dataList") {
      // found a data list
      item = {
         site : getSiteData(siteId),
         container : containerId,
         nodeRef : node.nodeRef.toString(),
         type : "datalist",
         tags : [],
         name : node.name,
         description : node.properties["cm:description"],
         modifiedOn : node.properties["cm:modified"],
         modifiedByUser : node.properties["cm:modifier"],
         createdOn : node.properties["cm:created"],
         createdByUser : node.properties["cm:creator"],
         size : -1,
         displayName : node.properties["cm:title"]
      };
      item.modifiedBy = getPersonDisplayName(item.modifiedByUser);
      item.createdBy = getPersonDisplayName(item.createdByUser);
   } else if (node.isSubType("{http://www.alfresco.org/model/datalist/1.0}dataListItem")) {
      // found a data list item
      item = {
         site : getSiteData(siteId),
         container : containerId,
         nodeRef : node.nodeRef.toString(),
         type : "datalistitem",
         tags : [],
         name : node.parent.name, // used to generate link to parent
         // datalist - not ideal
         modifiedOn : node.properties["cm:modified"],
         modifiedByUser : node.properties["cm:modifier"],
         createdOn : node.properties["cm:created"],
         createdByUser : node.properties["cm:creator"],
         size : -1,
         displayName : node.name
      // unfortunately does not have a common display name property
      };
      item.modifiedBy = getPersonDisplayName(item.modifiedByUser);
      item.createdBy = getPersonDisplayName(item.createdByUser);
   }

   return item;
}

/**
 * Delegates the extraction to the correct extraction function depending on
 * containerId.
 */
function getItem(siteId, containerId, pathParts, node) {
   var item = null;
   if (siteId == null) {
      item = getRepositoryItem(pathParts, node);
   } else {
      switch ("" + containerId) {
      case "documentLibrary":
         item = getDocumentItem(siteId, containerId, pathParts, node);
         break;
      case "blog":
         item = getBlogPostItem(siteId, containerId, pathParts, node);
         break;
      case "discussions":
         item = getForumPostItem(siteId, containerId, pathParts, node);
         break;
      case "calendar":
         item = getCalendarItem(siteId, containerId, pathParts, node);
         break;
      case "wiki":
         item = getWikiItem(siteId, containerId, pathParts, node);
         break;
      case "links":
         item = getLinkItem(siteId, containerId, pathParts, node);
         break;
      case "dataLists":
         item = getDataItem(siteId, containerId, pathParts, node);
         break;
      }
   }
   return item;
}

/**
 * Splits the qname path to a node.
 * 
 * Returns an array with: [0] = site [1] = container or null if the node does
 * not match [2] = remaining part of the cm:name based path to the object - as
 * an array
 */
function splitQNamePath(node) {
   var path = node.qnamePath;
   var displayPath = node.displayPath.split("/");
   var parts = null;

   if (path.match("^" + SITES_SPACE_QNAME_PATH) == SITES_SPACE_QNAME_PATH) {
      var tmp = path.substring(SITES_SPACE_QNAME_PATH.length);
      var pos = tmp.indexOf('/');
      if (pos >= 1) {
         // site id is the cm:name for the site - we cannot use the encoded
         // QName version
         var siteId = displayPath[3];
         tmp = tmp.substring(pos + 1);
         pos = tmp.indexOf('/');
         if (pos >= 1) {
            // strip container id from the path
            var containerId = tmp.substring(0, pos);
            containerId = containerId.substring(containerId.indexOf(":") + 1);

            parts = [ siteId, containerId, displayPath.slice(5, displayPath.length) ];
         }
      }
   }

   return (parts != null ? parts : [ null, null, displayPath ]);
}

/**
 * Processes the search results. Filters out unnecessary nodes
 * 
 * @return the final search results object
 */
function processResults(nodes, maxResults) {
   var results = [], added = 0, parts, item, i, j;

   for (i = 0, j = nodes.length; i < j && added < maxResults; i++) {
      /**
       * For each node we extract the site/container qname path and then let the
       * per-container helper function decide what to do.
       */
      parts = splitQNamePath(nodes[i]);
      if (parts !== null) {
         item = getItem(parts[0], parts[1], parts[2], nodes[i]);
         if (item !== null) {
            results.push(item);
            added++;
         }
      }
   }

   return ({
      items : results
   });
}

/**
 * Helper to escape the QName string so it is valid inside an fts-alfresco
 * query. The language supports the SQL92 identifier standard.
 * 
 * @param qname
 *           The QName string to escape
 * @return escaped string
 */
function escapeQName(qname) {
   var separator = qname.indexOf(':'), namespace = qname.substring(0, separator), localname = qname.substring(separator + 1);

   return escapeString(namespace) + ':' + escapeString(localname);
}

function escapeString(value) {
   var result = "";

   for ( var i = 0, c; i < value.length; i++) {
      c = value.charAt(i);
      if (i == 0) {
         if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_')) {
            result += '\\';
         }
      } else {
         if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_' || c == '$' || c == '#')) {
            result += '\\';
         }
      }
      result += c;
   }
   return result;
}

/**
 * Return Search results with the given search terms. Patched and extended by
 * SIDE to enable advanced search with operator, fix sorting ... "or" is the
 * default operator, AND and NOT are also supported - as is any other valid
 * fts-alfresco elements such as "quoted terms" and (bracket terms) and also
 * propname:propvalue syntax.
 * 
 * @param params
 *           Object containing search parameters - see API description above
 */
function getSearchResults(params) {
   var nodes = [];

   var queryDef = getSearchDef(params);

   if (queryDef != null) {
      nodes = search.query(queryDef);
   }

   return processResults(nodes, params.maxResults);
}

function buildFormQuery(formJson) {
   // build operator map (group by operator type)
   var operators = {
      "OR" : [],
      "AND" : [],
      "NOT" : [],
      "IGNORE" : []
   };
   var defaultOperator = 'AND';
   var behaviors = [ "naive", "group-and", "group-or" ];
   var defaultBehavior = behaviors[2];
   // naive is to build query in the field order ...
   // group is to group fields by operator and link operator group with
   // "and" or "or"

   // search default operator

   if (formJson["operator-default"]) {
      // this field is the default logical operator
      defaultOperator = formJson["operator-default"];
      if (defaultOperator == 'AND') {
         defaultBehavior = behaviors[1];
      } else if (defaultOperator == 'OR') {
         defaultBehavior = behaviors[2];
      }

   }

   for ( var p in formJson) {
      if (p.indexOf("operator-") == -1) {
         // this field is a logical operator, for a specific field
         var operatorId = getOperatorId(p);
         var op = formJson[operatorId];
         if (operators[op] != null || operators[op] != undefined) {
            operators[op].push(p);
         } else if (p.indexOf("prop_") === 0 || p.indexOf("assoc_") === 0) {
            operators[defaultOperator].push(p);
            if (logger.isLoggingEnabled()) {
               logger.log("search.lib.js use default operator for :" + p);
            }
         }
      }

   }

   // now we can build the query according selected behavior

   if (defaultBehavior == "naive") {
      // iterate fields and use the associated operator
      // extract form data and generate search query
      var first = true;
      for ( var f in formJson) {
         if (f.indexOf("operator-") == -1) {
            formQuery += makeQueryFor(formJson, f, formJson["operator-" + f], first);
            first = false;
         }
      }
   } else {
      if (defaultBehavior == "group-and") {
         formQuery = buildGroupedQuery(formJson, operators, "AND");
      } else if (defaultBehavior == "group-or") {
         formQuery = buildGroupedQuery(formJson, operators, "OR");
      }
   }
   logger.log("buildFormQuery :" + formQuery);
   return formQuery;
}

function makeQueryFor(formJson, p, operator, first) {

   // retrieve value and check there is someting to search for
   // currently all values are returned as strings

   var formQuery = "";
   if (operator != 'IGNORE') {
      var propValue = formJson[p];
      if (propValue != null && propValue.length !== 0) {
         if (p.indexOf("prop_") === 0) {
            // found a property - is it namespace_propertyname or pseudo
            // property format?
            var propName = p.substr(5);
            if (propName.indexOf("_") !== -1) {
               // property name - convert to DD property name format
               propName = propName.replace("_", ":");

               // special case for range packed properties
               if (propName.match("-range$") == "-range") {
                  // currently support text based ranges (usually numbers)
                  // or date ranges
                  // range value is packed with a | character separator

                  // if neither value is specified then there is no need
                  // to add the term
                  if (propValue.length > 1) {
                     var from, to, sepindex = propValue.indexOf("|");
                     if (propName.match("-date-range$") == "-date-range") {
                        // date range found
                        propName = propName.substr(0, propName.length - "-date-range".length);

                        // work out if "from" and/or "to" are specified
                        // -
                        // use MIN and MAX otherwise;
                        // we only want the "YYYY-MM-DD" part of the ISO
                        // date value - so crop the strings
                        from = (sepindex === 0 ? "MIN" : propValue.substr(0, 10));
                        to = (sepindex === propValue.length - 1 ? "MAX" : propValue.substr(sepindex + 1, sepindex + 10));
                     } else {
                        // simple range found
                        propName = propName.substr(0, propName.length - "-range".length);

                        // work out if "min" and/or "max" are specified
                        // -
                        // use MIN and MAX otherwise
                        from = (sepindex === 0 ? "MIN" : propValue.substr(0, sepindex));
                        to = (sepindex === propValue.length - 1 ? "MAX" : propValue.substr(sepindex + 1));
                     }
                     formQuery += (first ? '' : ' ' + operator + ' ') + propName + ':"' + from + '".."' + to + '"';
                  }
               } else {
                  var queryTerm = "";
                  var orString = msg.get("advsearch.inner.operator.or");
                  var andString = msg.get("advsearch.inner.operator.and");
                  var notString = msg.get("advsearch.inner.operator.not");
                  if (propValue.indexOf(" " + orString + " ") != -1 || propValue.indexOf(" " + andString + " ") != -1 || propValue.indexOf(notString + " ") != -1 || propValue.indexOf("(") != -1
                        || propValue.indexOf(")") != -1) {
                     // prop value contains sub query so interpret them to
                     // fts-alfresco query
                     queryTerm = propValue;
                     var split = queryTerm.split(/\s+/);

                     for ( var c = 0; c < split.length; c++) {
                        var item = split[c];

                        if (item != andString && item != orString && item != notString) {
                           var tokenClean = item.replace("(", "");
                           tokenClean = tokenClean.replace(")", "");

                           var regex = new RegExp(tokenClean, "g");
                           queryTerm = queryTerm.replace(regex, propName + ':"' + tokenClean + '"');
                        }
                     }

                     queryTerm = queryTerm.replace(new RegExp(orString, "g"), "OR");
                     queryTerm = queryTerm.replace(new RegExp(andString, "g"), "AND");
                     queryTerm = queryTerm.replace(new RegExp(notString, "g"), "NOT");

                  } else {
                     // normal propValue
                     // special case of enumeration value like v1,v2,v3 must be
                     // converted in composed request
                     // we make the assumption that field other than cm:content
                     // with pattern like v1,v2 is enumeration
                     // the only sure test is to request the dictionary service
                     // about the field type this will degrade reponse time

                     // so if field is not cm:content (cm:content is not an enum
                     // and can contains ',')
                     // value contains ',' but never match the regExp : "(, )|(
                     // ,)|( , )", we make the assumption that the field is a
                     // Enum value list

                     var isNotEnum = new RegExp("(, )|( ,)|( , )", "g");
                     var m = propValue.match(isNotEnum);
                     if (propValue.indexOf(",") != -1 && propName != "cm:content" && m == null) {
                        var terms = propValue.split(",");
                        for ( var tc = 0; tc < terms.length; tc++) {
                           var cterm = terms[tc];
                           var enumterm = propName + ':"' + cterm + '"';
                           queryTerm += (tc == 0 ? '' : ' ' + operator + ' ') + enumterm;
                        }
                        queryTerm = addSubQueryParenthesis(queryTerm);
                     } else {
                        queryTerm = propName + ':"' + propValue + '"';
                     }

                  }
                  formQuery += (first ? '' : ' ' + operator + ' ') + queryTerm;

               }
            } else {
               // pseudo cm:content property - e.g. mimetype, size or
               // encoding
               formQuery += (first ? '' : ' ' + operator + ' ') + 'cm:content.' + propName + ':"' + propValue + '"';
            }
         } else if (p.indexOf("assoc_") === 0 && p.match("_added$") == "_added") {
            var regexp = new RegExp("assoc_([^_]+)_(.*)_added");
            var propName = '@' + p.replace(regexp, "$1:$2") + "search";
            if (propValue.indexOf(',') != -1) {
               var values = propValue.split(',');
               formQuery += (first ? '' : ' ' + operator + ' ');
               for ( var c = 0; c < values.length; c++) {
                  var value = values[c];
                  formQuery += propName + ':"' + values[c] + '"' + (c == values.length - 1 ? '' : ' ' + operator + ' ');
               }
            } else {
               formQuery += (first ? '' : ' ' + operator + ' ') + propName + ':"' + propValue + '"';
            }
         }
      }
   }
   logger.log("makeQueryFor return " + formQuery);
   return formQuery;
}

/**
 * get fields grouped by operator and compute the query Note : grouping NOT
 * operator must be done according to boolean rules ( not(A) AND not(B) -> not(A
 * OR B)) ...
 * 
 * @param formJson
 * @param operators
 * @param group_operator
 * @returns {String}
 */
function buildGroupedQuery(formJson, operators, group_operator) {
   var formQuery = "";
   var firstOp = true;
   for ( var operator in operators) {
      var fields = operators[operator];

      var operatorIsNot = false;
      if (operator == 'NOT') {
         operatorIsNot = true;
         if (group_operator == 'OR') {
            operator = 'AND';
         } else {
            operator = 'OR';
         }
      }
      var firstField = true;
      var groupedQuery = "";
      for ( var i = 0; i < fields.length; i++) {
         var field = fields[i];
         var formFieldId = getFormFieldIdFor(formJson, field);
         groupedQuery += makeQueryFor(formJson, formFieldId, operator, firstField);
         if (groupedQuery != '') {
            firstField = false;
         }
      }

      if (groupedQuery != '') {
         if (!firstOp) {
            formQuery += ' ' + group_operator + ' ';
         }
         firstOp = false;
         if (operatorIsNot) {
            formQuery += 'NOT ';
         }
         formQuery += addSubQueryParenthesis(groupedQuery);
      }
   }
   logger.log("buildGroupedQuery return :" + formQuery);
   return formQuery;
}

/**
 * seem that fts-alfresco do not support as expected expression with useless
 * parenthesis so we try to limit them
 * 
 * @param query
 * @returns
 */
function addSubQueryParenthesis(query) {
   if (query.indexOf(" OR ") != -1 || query.indexOf(" AND ") != -1) {
      query = ' ( ' + query + ' ) ';
   }
   return query;
}

function getFormFieldIdFor(formFields, id) {
   var formId = null;
   for ( var f in formFields) {
      if (f == id) {
         formId = f;
      } else if (f.match("-range$") == "-range") {
         var formIdRange = null;
         if (f.match("-date-range$") == "-date-range") {
            formIdRange = f.substring(0, f.indexOf("-date-range"));
         } else {
            formIdRange = f.substring(0, f.indexOf("-range"));
         }
         if (formIdRange == id) {
            formId = f;
         }
      }
   }
   return formId;
}

function getOperatorId(f) {
   var id = f;
   if (f.match("-range$") == "-range") {
      var formIdRange = null;
      if (f.match("-date-range$") == "-date-range") {
         id = f.substring(0, f.indexOf("-date-range"));
      } else {
         id = f.substring(0, f.indexOf("-range"));
      }
   } else if (f.indexOf("assoc_") === 0) {
      id = f.substring(0, f.indexOf("_added"));
   }
   return "operator-" + id;
}

function getSearchPath(formJson) {
   var pathFieldId = "path_added";
   var searchPath = formJson[pathFieldId];
   if (searchPath != null && searchPath != undefined && searchPath != '') {
      if (searchPath.match("^[^:]+://[^/]+/.+$") != null) {
         // is a nodeRef need to be converted to xpath
         return search.findNode(searchPath).qnamePath;
      } else {
         // assume that the parameter is a xpath
         return searchPath;
      }

   }
   return null;
}

function getSearchSubdirectories(formJson) {
   var f = formJson["path_subdirectories"];
   var sub = f != undefined && f == 'on';
   if (logger.isLoggingEnabled()) {
      logger.log("search in subdirectories :" + sub);
   }
   return sub;
}

/**
 * Return Search results with the given search terms Terms are split on
 * whitespace characters.
 * 
 * AND, OR and NOT are supported - as their Lucene equivalent.
 */
function getSearchQuery(term, maxResults, siteId, containerId) {
   var path = SITES_SPACE_QNAME_PATH;
   if (siteId !== null && siteId.length > 0) {
      path += "cm:" + search.ISO9075Encode(siteId) + "/";
   } else {
      path += "*/";
   }
   if (containerId !== null && containerId.length > 0) {
      path += "cm:" + search.ISO9075Encode(containerId) + "/";
   } else {
      path += "*/";
   }

   var luceneQuery = "";
   if (term !== null && term.length !== 0) {
      // TODO: Perform smarter term processing. For now we simply split on
      // whitespace
      // which ignores quoted phrases that may be present.
      var terms = term.split(/\s/), i, j, t;

      for (i = 0, j = terms.length; i < j; i++) {
         t = terms[i];
         // remove quotes - TODO: add support for quoted terms later
         t = t.replace(/\"/g, "");
         if (t.length !== 0) {
            switch (t.toLowerCase()) {
            case "and":
               if (i < j - 1 && terms[i + 1].length !== 0) {
                  luceneQuery += "AND ";
               }
               break;

            case "or":
               break;

            case "not":
               if (i < j - 1 && terms[i + 1].length !== 0) {
                  luceneQuery += "NOT ";
               }
               break;

            default:
               luceneQuery += "(TEXT:\"" + t + "\"" + // full text
               " @cm\\:name:\"" + t + "\"" + // name
               // property
               " @lnk\\:title:\"" + t + "\"" + // link title
               " @lnk\\:description:\"" + t + "\"" + // link
               // description
               " PATH:\"/cm:taggable/cm:" + search.ISO9075Encode(t) + "/member\"" + // tags
               ") ";
            }
         }
      }
   }

   // if we processed the search terms, then suffix the PATH query
   if (luceneQuery.length !== 0) {
      luceneQuery = "+PATH:\"" + path + "/*\" +(" + luceneQuery + ")";
      luceneQuery += " -TYPE:\"{http://www.alfresco.org/model/content/1.0}thumbnail\"";

   } else {
      return null;
   }

   return luceneQuery;
}

function getSavedSearchQueryDef(nodeRef) {
   var node = search.findNode(nodeRef);
   var queryString = node.content;

   logger.log("getSavedSearchQuery " + queryString);

   var savedSearch = eval('(' + queryString + ')');

   return getSearchDef(savedSearch);
}

function getSearchDef(params) {
   var ftsQuery = "", term = params.term, tag = params.tag, formData = params.query;

   // Simple keyword search and tag specific search
   if (term !== null && term.length !== 0) {
      // TAG is now part of the default macro
      ftsQuery = term + " ";
   } else if (tag !== null && tag.length !== 0) {
      // Just look for tag
      ftsQuery = "TAG:" + tag + " ";
   }

   // Advanced search form data search.
   // Supplied as json in the standard Alfresco Forms data structure:
   // prop_<name>:value|assoc_<name>:value
   // name = namespace_propertyname|pseudopropertyname
   // value = string value - comma separated for multi-value, no escaping yet!
   // - underscore represents colon character in name
   // - pseudo property is one of any cm:content url property:
   // mimetype|encoding|size
   // - always string values - interogate DD for type data
   if (formData !== null && formData.length !== 0) {
      var formQuery = "", formJson = jsonUtils.toObject(formData);

      var repoSearch = params.repo;
      var searchPath = getSearchPath(formJson);
      if (searchPath != null) {
         repoSearch = true;
      }

      formQuery = buildFormQuery(formJson);

      if (formQuery.length !== 0 || ftsQuery.length !== 0 || repoSearch) {
         // extract data type for this search - advanced search query is type
         // specific
         ftsQuery = 'TYPE:"' + formJson.datatype + '"' + (formQuery.length !== 0 ? ' AND ' + addSubQueryParenthesis(formQuery) : '')
               + (ftsQuery.length !== 0 ? ' AND ' + addSubQueryParenthesis(ftsQuery) : '');
      }
   }

   if (ftsQuery.length !== 0 || repoSearch) {
      // we processed the search terms, so suffix the PATH query
      var path = null;
      if (!repoSearch) {
         path = SITES_SPACE_QNAME_PATH;
         if (params.siteId !== null && params.siteId.length > 0) {
            path += "cm:" + search.ISO9075Encode(params.siteId) + "/";
         } else {
            path += "*/";
         }
         if (params.containerId !== null && params.containerId.length > 0) {
            path += "cm:" + search.ISO9075Encode(params.containerId) + "/";
         } else {
            path += "*/";
         }
      } else if (searchPath != undefined && searchPath != '') {
         path = searchPath;
         // interprate {site} token
         if (params.siteId !== null && params.siteId.length > 0) {
            path = path.replace(/\{site\}/, "cm:" + search.ISO9075Encode(params.siteId));
         }

         if (getSearchSubdirectories(formJson)) {
            path += "/";
         }
      }

      if (path != null) {
         ftsQuery = 'PATH:"' + path + '/*" AND ' + addSubQueryParenthesis(ftsQuery);
      }
      ftsQuery = addSubQueryParenthesis(ftsQuery) + ' AND -TYPE:"cm:thumbnail"';

      // sort field - expecting field to in one of the following formats:
      // - short QName form such as: cm:name
      // - pseudo cm:content field starting with "." such as: .size
      // - any other directly supported search field such as: TYPE
      var sortColumns = [];
      var sortParam = params.sort;
      if (sortParam != null && sortParam.length != 0) {
         var sort = sortParam;
         var asc = true;
         var separator = sort.indexOf("|");
         if (separator != -1) {
            sort = sort.substring(0, separator);
            asc = (sortParam.substring(separator + 1) == "true");
         }
         var column;
         if (sort.charAt(0) == '.') {
            // handle pseudo cm:content fields
            column = "@{http://www.alfresco.org/model/content/1.0}content" + sort;
         } else if (sort.indexOf(":") != -1) {
            // handle attribute field sort
            column = "@" + utils.longQName(sort);
         } else {
            // other sort types e.g. TYPE
            column = sort;
         }
         sortColumns.push({
            column : column,
            ascending : asc
         });
      }

      // perform fts-alfresco language query
      var queryDef = {
         query : ftsQuery,
         language : "fts-alfresco",
         page : {
            maxItems : params.maxResults
         },
         templates : QUERY_TEMPLATES,
         defaultField : "keywords",
         onerror : "no-results",
         sort : sortColumns
      };
      if (logger.isLoggingEnabled()) {
         logger.log("getSearchResults ftsQuery :" + ftsQuery);
         logger.log("search.lib.js queryDef:" + queryDef.toSource());
      }

      return queryDef;

   }
   return null;
}
