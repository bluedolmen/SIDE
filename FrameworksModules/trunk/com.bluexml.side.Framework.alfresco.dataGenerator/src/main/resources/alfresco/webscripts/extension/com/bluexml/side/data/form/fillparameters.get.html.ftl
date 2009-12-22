<html>
    <style type="text/css">
	    div#property {margin: 2 0 2 0;}
	    div#label {width: 500px; margin-right: 5px; text-align: right; font-weight: bold; display: block; float: left}
	    div#field {width:300px; text-align: left; display: inline;}
	    div#button {width:800px; padding: 2 400 2 400;}               
    </style>

	<body>
		<h2>Random Data Model Generator</h2>
	   <form method="post" action="/alfresco/service/data/generate/generate">
	            <div id="property">
	                    <div id="label">Model: </div>
	                    <div id="field">
	                            <select name="model">
	                            <#list qnameModels as qnameModel>
	                                    <option value="${qnameModel}">${qnameModel}</option>
	                            </#list> 
	                            </select>
	                    </div>
	            </div>
	            <div id="property">
	                    <div id="label">Number of generated model contents: </div>        
	                    <div id="field">
	                            <input name="numOfInstances" type="text" />
	                    </div>
	            </div>
	            <div id="property">
	                    <div id="label">Maximum number of output associations per content: </div>
	                    <div id="field">
	                            <input name="numOfOutputArcs" type="text" />
	                    </div>
	            </div>
	            <div id="property">
	                    <div id="label">Path to associates documents folder : </div>
	                    <div id="field">
	                            <input name="pathToDocuments" type="text" />
	                    </div>
	            </div>
	            <div id="property">
                        <div id="label">Alfresco path repository to store ACP and its deployement (use XPath representation): </div>
                        <div id="field">
                                /app:company_home/       
                                <input name="alfrescoRepository" type="text" value="app:guest_home/"/>
                        </div>                        
	            </div>
	            <div id="button">
	                    <input name="submit" type="Submit" value="Generate" />
	            </div>
	    </form>
	</body>
</html>