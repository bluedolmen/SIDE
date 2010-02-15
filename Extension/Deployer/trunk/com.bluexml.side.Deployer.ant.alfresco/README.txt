BlueXML SIDE Example : Pre and Post Deployment tasks with Alfresco
==========================================================================

Table of Contents:
------------------
- Introduction
- Prerequisites
- Use
- Pointers & License Notice

Introduction:
-------------
This sample aims to demonstrate the use of a SIDE ant deployer.operations before and after the SIDE deployment.
This kind of deployer is used to perform processing before (pre-build task) and after (post-build task) the other deployers you define in your application model.

This example of ant deployer performs the following tasks:
- pre-build task:
  . check if Alfresco is running
  . if Alfresco is running, stop it
  . if you choose in build.properties to re-create alfresco and alf_data,
    re-start MySQL
    delete alfresco database and alf_data content store
    create alfresco database
    stop MySQL
- post-build task:
  . start Alfresco
  . if you choose in build.properties to load test data, load test data in Alfresco relatively to the setting of data_post.properties
  . if you choose in build.properties to create alf_synchro database, create alf_synchro database.

Note: the alf_synchro database is a relational schema image of all the data inserted in alfresco database relatively to the model extension you create.

The ant process of this sample ant deployer is useful when you want to tune data or workflow model and deploy on Alfresco: update of Alfresco model requires to re-start Alfresco in order to take accoung changes.
If your data and workflow models are ok and you start modeling form, view, portal or security, you do not need anymore restart alfresco but only webapps like share or xforms: in this case, you can design other application configurations with other ant build scripts.

Prerequisites:
--------------
We suppose you have made at least one Data model with SIDE and unzipped the current project in a specific directory;
let's call the path to this directory <sideAntDeployerPath>.

Use:
----
The project's structure contains two directories.
To configure the tasks that will run before and after the SIDE Deployment, go to src and open the build.properties file.
This file contains the build configuration parameters you can update.
Just follow the instructions written in this file
Important: 
- For Windows os, write path with two anti-slash "\\" characters as separator; just single slash "/" for Linux. 
- in order to delete and create alfresco and alf_synchro database, you need the database connection information to MySQL.

If you choose to load data test (i.e Data model instances), open data_post.properties:
1/ "model": indicate your model name as follow: {http://www.bluexml.com/model/content/<model_name>/1.0}model
2/ "numOfInstances": this parameter allows limiting the overall number of data you want the webscript stores in ALfresco. 
   It is important to note that this number is for all the content types defined in the Alfresco data model you choose through the previous parameter: if you give a
value inferior to the number of content types in the model, some content type may not be generated.
3/ "numOfOutputArcs": this parameter allows limiting the number of associations between nodes; 
   this limitation is achieved per node (content type instance).
4/ "pathToDocuments": this parameters points to a folder where you can store files in order they are associated as content to the newly Alfresco nodes. If you do not set this parameters, the node will be generated
without content but only metadata.
5/ "alfrescoRepository": this parameters allows to define the Alfresco path to store the generated nodes under /app:company_home; 
   this path must be expressed using an Xpath representation like app:guest_home/cm:testData. If you want to generate under Alfresco Share, 
   you must indicate a path like /app:company_home/st:sites/cm:mySite/cm:documentLibrary/cm:SIDELoadedTestData.
   It is important to note that you must be connected to Alfresco under an account having write permission on this Path.
6/ "scenario": this parameter allows to index the types instances and its attributes in an incremental way or fully randomly.
7/ "indexes": this parameter is used for the incremental scenario; it assures unicity of attributes if necessary (for the first generation, you can fill it with 0).
8/ "folders": this parameter allows the content instances to be grouped by type into folders.
9/ "login": Alfresco login.
10/ "password": Alfresco password.
  
In order to declare these pre and post-build task in your deployment process, under the deployer tab of application configuration window of the SIDE Graphical environment, 
select the ANT Deployer and set the "Ant file path" parameter to  "<sideAntDeployerPath>/src/build.xml".

Save the application configuration and launch.

During deployment, the pre-build and post-build tasks will be performed. Look at the side-report to get results of the MDA process.

Note: only executed ant tasks have logs during the loading data option; so if you don't see any, it means that the corresponding task is not executed (except for isMySQLRunning and is AlfrescoRunning
	  wich don't have logs).   

Pointers & License notices :
----------------------------
For support, refer to: http://www.side-labs.org/forum
More information at http://www.side-labs.org and www.bluexml.com

Copyright (C) 2007-2009  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.


