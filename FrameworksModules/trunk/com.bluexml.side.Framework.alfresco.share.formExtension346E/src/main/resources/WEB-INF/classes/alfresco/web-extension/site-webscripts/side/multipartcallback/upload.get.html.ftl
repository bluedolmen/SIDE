<html>
<head>
   <title>Upload success</title>
</head>
<body>
<p>${args.params!"KO"}</p>

<p>${params.nodeRef!"KO"}</p>
<p>${params.failureCallback!"KO"}</p>
<p>${params.failureScope!"KO"}</p>
<p>${params.successScope!"KO"}</p>

<p></p>
<#if (!error?exists)>
<#if (params.successCallback?exists)>
   <script type="text/javascript">
      ${params.successCallback}.call(${params.successScope},
      {
         nodeRef: "${params.nodeRef}"
      });
   </script>
</#if>
<#else>
<#if (params.failureCallback?exists)>
   <script type="text/javascript">
      ${params.failureCallback}.call(${params.failureScope}, {message : "${error!"error"}"});
   </script>
</#if>
</#if>

</body>
</html>