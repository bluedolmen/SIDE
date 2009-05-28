<%--encoding=ISO-8859-1--%>
<%
metamodel http://www.kerblue.org/class/1.0
import com.bluexml.side.clazz.generator.facetmap.ClassFacetmapGenerator
%>

<%script type="clazz.ClassPackage" name="validatedFilename"%>
	./index.get.js
	
<%script type="clazz.ClassPackage" name="typeDocument"%>
Personnes

<%script type="clazz.ClassPackage" name="cmisRequestGenerator"  file="<%validatedFilename%>" %>
script: {
default xml namespace = 'http://www.cmis.org/2008/05';

var query="
<cmis:query xmlns:cmis='http://www.cmis.org/2008/05' xmlns:p='http://www.w3.org/1999/xhtml' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xsi:schemaLocation='http://www.cmis.org/2008/05 CMIS.xsd '>
	<cmis:statement>
		SELECT * FROM DOCUMENT  WHERE (NOT((CreatedBy = 'System') OR (Name = 'doclib.png') OR (Name = 'webpreview.swf') ) <AND (BaseType = '<%typeDocument()%>'))
	</cmis:statement>
	<cmis:searchAllVersions>false</cmis:searchAllVersions>
	<cmis:pageSize>0</cmis:pageSize>
	<cmis:skipCount>0</cmis:skipCount>
	<cmis:returnAllowableActions>false</cmis:returnAllowableActions>
</cmis:query>
";

var cmisQuery = new XML(query);

    // extract query statement
    model.statement = cmisQuery.statement.toString();
    if (model.statement == null || model.statement.length == 0)
    {
        //status.setCode(status.STATUS_BAD_REQUEST, "Query statement must be provided");
        break script;
    }
    
    // process search all versions (NOTE: not supported)
    var searchAllVersions = cmisQuery.searchAllVersions;
    if (searchAllVersions != null && searchAllVersions === "true")
    {
        //status.setCode(status.STATUS_INTERNAL_SERVER_ERROR, "Search all versions not supported");
        break script;
    }
    
    // process paging
    var skipCount = parseInt(cmisQuery.skipCount);
    var pageSize = parseInt(cmisQuery.pageSize);
    var page = paging.createPageOrWindow(null, null, isNaN(skipCount) ? null : skipCount, isNaN(pageSize) ? null : pageSize);
    
    // perform query
    var paged = cmis.query(model.statement, page);
    model.resultset = paged.result;
    model.cursor = paged.cursor;
    
}