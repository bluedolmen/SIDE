BlueXML SIDE Example : Pre and Post Deployment tasks with Alfresco
==========================================================================

Table of Contents:
------------------
- Introduction
- Prerequisites and Use
- Pointers & License Notice

Introduction:
-------------
This sample aims to demonstrate the use of operations before and after the SIDE deployment.
In our case:
-check if Alfresco is running before deployment; if it is, stop it.
-start Alfresco after deployment, having the possibility of creating the database alf_synchro and loading data test in Alfresco.  


Prerequisites and Use:
----------------------
We suppose you have made at least one Data model with SIDE and unziped the project in a specific directory;
we'll call the path to it <f_u>.

The project's structure contains two directories.
To configure the the tasks that will run before and after the SIDE Deployment, go to src and open the build.properties;
that one will be use to configure environment parameters and give the choice to create alf_synchro and/or load data test.
Just follow the instructions written in but note that with windows os you have to write two anti-slash in path as separator 
and use, in general, alfresco for database user name and password (because ALfresco have its own MySql server but not Linux).
With Linux os, write simple slash in path and use the user name and password of a MySql's user who have enough rigths to create a database
(as said, Linux Alfresco's distribution doesn't have its own MySql server; this one is a service that must be on).  

If you choose to load data test (i.e Data model instances), open data_post.properties:
you first have to indicate your model name as follow:
{http://www.bluexml.com/model/content/<model_name>/1.0}model
Then, fill the number of data instances you want (for all types) and, if there is associations in your Data model with multiplicity 
greater than one, the maximum number of types that can be associated through those associations.
Finally, indicates, with the Alfresco xPath writing, the Alfresco's repository where instances will be stored.  
   
To make SIDE take into account of the project tasks, just tick, in the SIDE Manager Configuration the ANT Deployer in the Déploiement part and fill, 
on the right, the Ant File Path with the path to the build.xml file you have unzip (it must look like: <f_u>/src/build.xml).
Save and launch. 

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


