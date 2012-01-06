<html>
    <style type="text/css">
    	body {font-family: Tahoma;}
    	h2 {text-align: center; color: white;  background-color: blue;}
    	p#intro {text-align: center;}
    	p#note {font-style:italic; font-size: 8pt;}
    	p#link {text-align: center; color: blue;}
	    div#property {margin: 2 0 2 0;}
	    div#label {width: 600px; margin-right: 5px; text-align: right; font-weight: bold; display: block; float: left;}
	    div#field {width:300px; text-align: left; display: inline;}
	    div#button {width:800px; padding: 2 200 2 600;}               
    </style>

	<body>
	   <h2>SIDE-Alfresco: Random Metadata and document Loading for Test</h2>
	   <p id="intro">The following input form allows to give the parameters to load test metadata and documents in the Alfresco repository in conformance with the customized content types you model, generate and deploy through SIDE-Alfresco MDA environment:</p>
	   <form method="post" action="/alfresco/service/data/generate/generate">
	            <div id="property">
	                    <div id="label">SIDE Data Model: </div>
	                    <div id="field">
	                            <select name="model">
	                            <#list qnameModels as qnameModel>
	                                    <option value="${qnameModel}">${qnameModel}</option>
	                            </#list> 
	                            </select>
	                    </div>
	            </div>
	            <div id="property">
	                    <div id="label">Number of content type instances to load: </div>        
	                    <div id="field">
	                            <input name="numOfInstances" type="text" />
	                    </div>
	            </div>
	            <div id="property">
	                    <div id="label">Maximum Number of output associations per content type instance: </div>
	                    <div id="field">
	                            <input name="numOfOutputArcs" type="text" />
	                    </div>
	            </div>
	            <div id="property">
	                    <div id="label">Path to the documents to load with generated metadata: </div>
	                    <div id="field">
	                            <input name="pathToDocuments" type="text" />
	                    </div>
	            </div>
	            <div id="property">
                        <div id="label">Path to store documents in Alfresco: </div>
                        <div id="field">
                                /app:company_home/       
                                <input name="alfrescoRepository" type="text" value="app:guest_home/"/>
                        </div>                        
	            </div>
	            <div id="property">
	                    <div id="label">Scenario: </div>
	                    <div id="field">
	                            <select name="scenario">
	                            	<option value="random">Random</option>
	                            	<option value="incremental">Incremental</option>
	                            </select>
	                    </div>
	            </div>
	            <div id="property">
                        <div id="label">Index: </div>
                        <div id="field">      
                                <input name="indexes" type="text" />
                        </div>                        
	            </div>
	            <div id="property">
                        <div id="label">Folders structure: </div>
                        <div id="field">      
                                <input name="folders" type="checkbox" />
                        </div>                        
	            </div>
	            <div id="property">
	                    <div id="label">Alfresco login: </div>        
	                    <div id="field">
	                            <input name="login" type="text" value="admin"/>
	                    </div>
	            </div>
	            <div id="property">
	                    <div id="label">Alfresco password: </div>        
	                    <div id="field">
	                            <input name="password" type="text" value="admin"/>
	                    </div>
	            </div>
	            <div id="button">
	                    <input name="submit" type="Submit" value="Generate" />
	            </div>
	    </form>
	    <p id="note">Note:</p> 
	    <table>
	    	<td>
	    		<tr>
	    			<p id="note">- SIDE Data model: The select list gives you the valid Alfresco data model extensions you can use to generated new content types.</p>
	    		</tr>
	    		<tr>
	    			<p id="note">- Number of content type instances to load: this parameter allows limiting the overall number of data you want the webscript stores in ALfresco. It is important to note that this number is for all the content types defined in the Alfresco data model you choose through the previous parameter: if you give a value inferior to the number of content types in the model, some content type may not be generated.</p> 
	    		</tr>
	    		<tr>
	    			<p id="note">- Maximum Number of output associations per content type instance: this parameter allows limiting the number of associations between nodes; this limitation is achieved per node (content type instance).</p>
	    		</tr>
	    		<tr>
	    			<p id="note">- Path to store documents in Alfresco: this parameters points to a folder where you can store files in order they are associated as content to the newly Alfresco nodes. If you do not set this parameters, the node will be generated without content but only metadata.</p>
	    		</tr> 
	    		<tr>
	    			<p id="note">- Path to store documents in Alfresco: this parameters allows to define the Alfresco path to store the generated nodes under /app:company_home; this path must be expressed using an Xpath representation like app:guest_home/cm:testData; 
	    						   If you want to generate under Alfresco Share, you must indicate a path like /app:company_home/st:sites/cm:mySite/cm:documentLibrary/cm:SIDELoadedTestData.
	    						   It is important to note that you must be connected to Alfresco under an account having write permission on this Path.</p>
	    		</tr>
	    		<tr>
	    			<p id="note">-Scenario: this parameter allows to index the types instances and its attributes in an incremental way or fully randomly.</p>
	    		</tr>
	    		<tr>
	    			<p id="note">-Index: this parameter is used for the incremental scenario; it assures unicity of attributes if necessary (for the first generation, you can fill it with 0).
	    						 Note that its filled value is not take into account if no unique property is defined in the selected model.
	    			</p>
	    		</tr>
	    		<tr>
	    			<p id="note">-Folders Structure: if checked, this parameter allows the content instances to be grouped by type into folders.</p>
	    		</tr>
	    		<tr>
	    			<p id="link"><a href="http://www.bluexml.com">BlueXML</a></p>
	    		</tr>
	    </table>
	</body>
</html>