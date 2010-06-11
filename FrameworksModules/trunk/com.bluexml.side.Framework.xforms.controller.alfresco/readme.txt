This project (controller.entreprise) defines the XForms controller for Alfresco that is available in SIDE Entreprise.

Historically, there was only one controller. But we decided to offer more features in the Entreprise version of SIDE.
From there, some code is specific to the Entreprise version and some code is specific to the Labs version. So basically, we need three projects:
- the project for the common part
- the project for the Labs-specific part
- the project for the Entreprise-specific part

But, the build system needs two distinct modules for each version because it's not able to choose which specific part to include.
So we need to duplicate the common part into two distinct projects, which leads to this architecture:
1- the project for the common part (will stand as the Labs module) 
2- the project for the common part (will stand as the Entreprise module)
3- the project for the Labs-specific part (not a module, but set as a Maven project for convenience)
4- the project for the Entreprise-specific part (idem)

The projects are named as follows on the SVN repository:
1- com.bluexml.side.Framework.xforms.alfresco.controller.labs (Labs controller)
2- com.bluexml.side.Framework.xforms.alfresco.controller.entreprise (Entreprise controller)
3- com.bluexml.side.Framework.xforms.alfresco.controller.labs.hook (Labs-specific code)
4- com.bluexml.side.Framework.xforms.alfresco.controller.entreprise.hook (Entreprise-specific code)


Notes:
- Each controller imports the specific code via an svn:externals properties
- There's no Maven dependency from a controller version to its specific code
- The Labs controller has no code per se: all code is imported via several svn:externals properties to either labs.hook or the controller.entreprise.

Amenel@2010-06-11