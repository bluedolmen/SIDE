= Reverse2 =
 This project aims to produce SIDE data models from alfresco models,
 native models or custom ones including generated with SIDE.

== Configuration ==
 Copy xml file containing alfreco models in /Reverse2/src/models/<your folder>/
 Edit the build.properties files, and set model.dir to relative path from "/Reverse2/src/models/"
== Usage ==
 launch default ant target from this directory
 > ant
  
 reversed files can be found in /Reverse2/build/

== Limitations ==
=== constraints ===
New MMClazz version allow to define constraints, for backward compatibility some constraints are reversed to metainfo
=== mandatory ===
 if mandatory is true, this is reversed but enforced attribute do not have equivalent in SIDE
 so generator always use same mandatory kind

=== index ===
 as mandatory some advanced configuration do not have equivalent in SIDE.
 if index = true, propertySearch key is set in SIDE property model, but :
 <atomic>,<stored> and <tokenised> are lost.
 Generator always use same values for <atomic>,<stored> and <tokenised>
 
=== multi model ===
some bugs exists in SIDE generator for example :
links valueList -> enumeration (other model) are not generated as expected

=== NOT Supported in MMclazz, not generated (or with default value), not reversed ===
see modelSchema_3.4.d.xsd

 type/aspect
-archive
-includesInSuperTypeQuery (for cmis not available in 3.2r2)
-overrides
-propertyOverride


 properties
-protected
-mandatory/@enforced
-index/atomic, stored, tokenised

 child-association
-duplicate
-propagateTimestamps

