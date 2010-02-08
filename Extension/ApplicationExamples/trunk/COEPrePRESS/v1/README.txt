BlueXML SIDE Example : Digitization Process with Alfresco and Chiba Xforms
==========================================================================

Table of Contents:
------------------
- Introduction
- Prerequisites
- Models Usage with SIDE (digitizationProcess_models.zip)
- Applications Usage with Alfresco and Chiba
  . through war files deployment (digitizationProcess_application_war.zip)
  . through generated components deployment (digitizationProcess_application_comp.zip)
- Digitization Process Demo Usage
- Pointers & License Notice

Introduction:
-------------
This sample aims to demonstrate an e-procedure to load a scanned document in Alfresco with necessary meta-data.
The synopsis is:
- a first user specialized in digitization scanned a document 
  and put this document in a specific space of Alfresco ('<one of your space>/inProgress')
- then, the Digitization process is automatically started and a task is affected to another user in charge of 
  validating that this new document must or must not enter in the document repository.
- If the user validates, a new task is affected to the same user to choose the content type of the document.
- According to the chosen type of content, a task is affected to another user who is in charge of setting the 
  meta-data of this kind of content type.
  In this demo procedure, the managed content type have been limited to "mail" and "vehicle".
- According to the content type,
  . The "mail"-aware user enters the meta-data and asks for approbation. 
    A new task is affected to a manager for approval.
    Upon approbation, the document is moved under a target space ('<one of your space>/achieved/mail')
- The "vehicle"-aware user enters the meta-data and 
  the document is moved under a target space ('<one of your space>/achieved/vehicle')


Like for all SIDE samples, you can download 3 files: 
  - 1 zip file for the SIDE models to be able to work, generate and deploy the application
  - 2 zip files to get the generated applications: 
    . the first one with the war files, 
    . the second one with only the extension to the original war files.
In the case of Digitization Process, the 3 files are:
- the "digitizationProcess_models.zip" file which contains all what is necessary to generate the application using SIDE,
- the "digitizationProcess_application_war.zip" file which contains the alfresco.war, share.war and xforms.war 
  with the generated components of the DigitizationProcess application. 
  This zip file may be heavy (aroung 100Mo) but the installation procedure is very light and consist only to put the war files
  under alfresco webapps directory. 
  If you want a lighter file, download the following one "digitizationProcess_application_comp.zip".
- the "digitizationProcess_application_comp.zip" file which contains the generated components to manually deploy 
  on the targeted frameworks, Alfresco and Chiba.
  This zip file is lighter to download than the previous one but the installation procedure is a little less easy as
  you have to use the Alfresco Module Management tool and unpack files on specific directories. 

Prerequisites:
--------------
In order to deploy the generated components on Alfresco and Chiba or to generate and deploy the models, follow the steps:
- Install Alfresco
- Deploy xforms.war (Chiba XForms web application) on Alfresco:
  copy http://www.bluexml.com/SIDE-Alfresco/2.0/ext/xforms.war under <ALFRESCO_HOME>/tomcat/webapp
- Start ALfresco and create the following element under http://localhost:8080/alfresco :
  . System users : digi 
  . Users groups : Digitization, Manager, Vehicle, Mail, Quotation
  . Associate the groups to 'digi' and potentially admin if you want to test with admin
    Note: we associate all the groups to digi (or admin) in order to avoid having to connect and disconnet for each task of the demo procedure.
          For a production e-procedure, you use several users having their own groups and permissions. 
          You may experience a problem to see the first task through the demo webapp using digi with Alfresco 3.2 community. In this case, use 'admin'.
  . Under a space of your choice '<one of your space>', create the space inProgress, achieved, achieved/vehicle, achieved/mail, achieved/quotation
    This space '<one of your space>' may obviously be in an Alfresco Share site. 
  . On the space 'inProgress', add the content rule corresponding to the script 'launchWorkflow.js' 
    that you will find in each distribution under extension.
- Stop Alfresco

Models Usage with SIDE (digitizationProcess_models.zip):
--------------------------------------------------------
In order to generate and deploy models on Alfresco, follow the steps:
- Start SIDE
- Unzip the digitizationProcess_models.zip in your SIDE workspace under the "DigitizationProcess" folder
- Import the new digitization process project using File -> Import -> existing Project -> select the "DigitizationProcess" folder
- Update the myLocal.application to your settings using click droit on the file and 'Manage Configuration':
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
  through war files deployment (digitizationProcess_application_war.zip) :
  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
In order to directly deploy the war files on your Alfresco and Chiba instances, follow the steps:
- Unzip the digitizationProcess_application_war.zip in a temporary directory <TEMP>
- Stop Alfresco
- Copy the <your Alfresco Install Directory>/webapps/alfresco.war (resp. xforms.war and share.war) under 
  <your Alfresco Install Directory>/webapps/alfresco.war.orig (resp. xforms.war.orig and share.war.orig)
  to keep a backup
- Copy the unzipped <TEMP>/alfresco.war, xforms.war and share.war files under
  <your Alfresco Install Directory>/webapps
- Restart ALfresco 

  through generated components deployment (digitizationProcess_application_comp.zip) :
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

Digitization Process Demo Usage :
---------------------------------
Having performed the Prerequisite steps and with Alfresco running, 
- Go to http://localhost:8080/alfresco
  . Connect as 'digi' 
  . Upload a pdf file under '<one of your space>/inProgress'
  This will start the Digitization Process e-procedure for this document.
  You may also upload the file using webdav or share. 
- Go to http://localhost:8080/xforms/demo  to access the demo where you can access the Digitization Process running e-procedure 
  . First thing to do is to click on 'Change parameters' and to set up Chiba URL: for example, "http://localhost:8080/xforms/"
  . Then click on the new 'Open task' link under Pooled Tasks and have fun with the e-procedure.
    Note: for task 'Assign the type of the document', enter for the type one of the following value: mail, vehicle, quotation.
          a "mail" type will go through an approval supplementary step before integration of the document in Alfresco.
          At the end, the documents are automatically moved, according to their type, from 'inProgress' to 'achieved/<type>'.
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


