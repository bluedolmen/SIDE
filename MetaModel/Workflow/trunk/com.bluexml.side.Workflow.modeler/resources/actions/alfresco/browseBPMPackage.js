for (var i = 0; i < bpm_package.children.length; i++)
{
  if (!bpm_package.children[i].hasAspect("cm:versionable"))
  {
    logger.log("The content "+bpm_package.children[i].properties["cm:name"]+" is versioned.")
  } else {
    logger.log("The content "+bpm_package.children[i].properties["cm:name"]+" is not versioned.")
  }
}