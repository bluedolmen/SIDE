BlueXML SIDE Example : COEPrePRESS Process with Alfresco, Share and Chiba Xforms
==========================================================================

Table of Contents:
------------------
- Introduction
- Prerequisites
- Models Usage with SIDE (COEPrePRESS_models.zip)
- Applications Usage with Alfresco, Share and Chiba
  . through war files deployment (COEPrePRESS_application_war.zip)
  . through generated components deployment (COEPrePRESS_application_comp.zip)
- COEPrePRESS Process Demo Usage
- Pointers & License Notice

Introduction:
-------------
This sample aims to demonstrate an e-procedure to manage document publishing in Alfresco with necessary meta-data.
The synopsis is:
- The COEPrePRESS starts when an autor-service user requests for a publishing and puts his document in a specific 
space of Alfresco ('Sites > coe > documentLibrary > Dépôt').
- then, the communication-service user receives the publishing request. He enters a comment. He can :
   . Accept the request.
   . Refuse the request.
   . Ask for more information.
- If the request is accepted, then the communication-service user will enter an ISBN number and a price. A publishing
 project will be created. 
- If the request is refused, then the communication-service user will enter a reason. The publishing request will be
 archived.
- If the communication-service ask for more information, then the author-service will have to add more information and
 submit the publishing project again to the communication-service user.


Like for all SIDE samples, you can download 3 files: 
  - 1 zip file for the SIDE models to be able to work, generate and deploy the application
  - 2 zip files to get the generated applications: 
    . the first one with the war files, 
    . the second one with only the extension to the original war files.
In the case of COEPrePRESS Process, the 3 files are:
- the "COEPrePRESS_models.zip" file which contains all what is necessary to generate the application using SIDE,
- the "COEPrePRESS_application_war.zip" file which contains the alfresco.war, share.war and xforms.war 
  with the generated components of the COEPrePRESS application. 
  This zip file may be heavy (aroung 100Mo) but the installation procedure is very light and consist only to put the war files
  under alfresco webapps directory. 
  If you want a lighter file, download the following one "COEPrePRESS_application_comp.zip".
- the "COEPrePRESS_application_comp.zip" file which contains the generated components to manually deploy 
  on the targeted frameworks, Alfresco and Chiba.
  This zip file is lighter to download than the previous one but the installation procedure is a little less easy as
  you have to use the Alfresco Module Management tool and unpack files on specific directories. 

Prerequisites:
--------------
In order to deploy the generated components on Alfresco, Share and Chiba or to generate and deploy the models, follow the steps:
- Install Alfresco
- Deploy xforms.war (Chiba XForms web application) on Alfresco:
  copy http://www.bluexml.com/SIDE-Alfresco/2.0/ext/xforms.war under <ALFRESCO_HOME>/tomcat/webapp
- Start ALfresco and create the following element under http://localhost:8080/alfresco :
  . System users : coepre 
  . Users groups : auteur, communication, preprod, daf
  . Associate the groups to 'coepre' and potentially admin if you want to test with admin
    Note: we associate all the groups to coepre (or admin) in order to avoid having to connect and disconnet for each task of the demo procedure.
          For a production e-procedure, you use several users having their own groups and permissions. 
          You may experience a problem to see the first task through the demo webapp using coepre with Alfresco 3.2 community. In this case, use 'admin'.
  . In Share (http://localhost:8080/share), create a new site called 'coe'.
  . In Alfresco (http://localhost:8080/alfresco), add the script 'launchWorkflow.js' into the space 'Company Home > Data Dictionary > Scripts'.
  . In Alfresco (http://localhost:8080/alfresco), go in the space 'Sites > coe > documentLibrary' and import the file 'guest.acp'. It will create the spaces 'Demande de publication', 'Dépôt' and 'Projet de publication'.
  . On the space 'Dépôt', add the content rule corresponding to the script 'launchWorkflow.js' 
    that you will find in each distribution under extension.
- Stop Alfresco

Models Usage with SIDE (COEPrePRESS_models.zip):
--------------------------------------------------------
In order to generate and deploy models on Alfresco, follow the steps:
- Start SIDE
- Unzip the COEPrePRESS_models.zip in your SIDE workspace under the "COEPrePRESS" folder
- Import the new COEPrePRESS process project using File -> Import -> existing Project -> select the "COEPrePRESS" folder
- Update the my.application to your settings using right click on the file and 'Manage Configuration':
  you have mainly to update:
  . In the generation tab, according to your environment, update the following parameters: 
    Alfresco URL, Alfresco share URL
  . In the deployment tab, according to your environment, update the following parameter: 
    Tomcat installation directory
  . The first time you will generate, uncheck the outline mode of the generation tab in order to get the up-to-date SIDE modules from the Maven repository.
    As this update increase the duration of the generation, the next time checks this mode to avoid a new update from the Maven repository.
- Stop Alfresco if it's running
- Generate and deploy the models using SIDE
- Restart Alfresco

Note: to avoid starting and stopping Alfresco for each generation, you can use the Alfresco ant deployer provided in the
      extension/Alfresco/antDeployeralfresco.zip file.
      Look at the README file for usage.

Applications Usage with Alfresco and Chiba :
--------------------------------------------
  through war files deployment (COEPrePRESS_application_war.zip) :
  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
In order to directly deploy the war files on your Alfresco and Chiba instances, follow the steps:
- Unzip the COEPrePRESS_application_war.zip in a temporary directory <TEMP>
- Stop Alfresco
- Copy the <your Alfresco Install Directory>/webapps/alfresco.war (resp. xforms.war and share.war) under 
  <your Alfresco Install Directory>/webapps/alfresco.war.orig (resp. xforms.war.orig and share.war.orig)
  to keep a backup
- Copy the unzipped <TEMP>/alfresco.war, xforms.war and share.war files under
  <your Alfresco Install Directory>/webapps
- Restart ALfresco 

  through generated components deployment (COEPrePRESS_application_comp.zip) :
  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
In order to directly deploy the generated components on your Alfresco and Chiba instances, follow the steps:
- Unzip the digitizationProcess_application_comp.zip in a temporary directory <TEMP>/SIDE
- Stop Alfresco
- Copy the <your Alfresco Install Directory>/webapps/alfresco.war (resp. xforms.war and share.war) under 
  <your Alfresco Install Directory>/webapps/alfresco.war.orig (resp. xforms.war.orig and share.war.orig)
  to keep a backup
- Copy the <TEMP>/SIDE/alfresco_3.x/*.amp files under <your Alfresco Install Directory>/amps
- Unzip all the <TEMP>SIDE/alfresco_3.x/*.zip files under <your Alfresco Install Directory>/webapps/share
- Unzip all the <TEMP>/SIDE/chiba_1_5.x/*.zip files under <your Alfresco Install Directory>/webapps/xforms
- Run the <your Alfresco Install Directory>/apply_amps.[bat or sh] script to apply the amps.
- Restart ALfresco 

COEPrePRESS Process Demo Usage :
---------------------------------
Having performed the Prerequisite steps and with Alfresco running, 
- Go to http://localhost:8080/alfresco
  . Connect as 'coepre' 
  . Upload a pdf file under 'Sites > coe > documentLibrary > Dépôt'
  This will start the COEPrePRESS Process e-procedure for this document.
  You may also upload the file using webdav or share. 
- Go to http://localhost:8080/xforms/demo  to access the demo where you can access the COEPrePRESS Process running e-procedure 
  . First thing to do enter your alfresco username and password and click on the button 'Login'.
  . Then click on the new 'Open task' link under Pooled Tasks and have fun with the e-procedure.
- Go to http://localhost:8080/share to see the meta-data of the loaded documents.
- Go to http://localhost:8080/xforms to see all the generated forms to handle documents meta-data.


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


