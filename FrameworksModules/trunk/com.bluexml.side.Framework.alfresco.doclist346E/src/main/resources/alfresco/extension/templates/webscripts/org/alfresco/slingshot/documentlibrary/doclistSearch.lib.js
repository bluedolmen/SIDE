const DEFAULT_MAX_RESULTS = 100;
const SITES_SPACE_QNAME_PATH = "/app:company_home/st:sites/";
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