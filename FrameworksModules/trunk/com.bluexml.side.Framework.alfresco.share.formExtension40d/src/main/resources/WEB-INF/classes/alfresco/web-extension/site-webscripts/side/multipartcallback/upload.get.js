if (args.error) {
   model.error = args.error;
}
var params = eval('(' + args.params + ')');
model.params = {
   nodeRef : paramValide(args, "nodeRef", ""),
   successCallback : paramValide(params, "successCallback", "alert('success')"),
   failureCallback : paramValide(params, "failureCallback", "alert('failure')"),
   failureScope : paramValide(params, "failureScope", "this"),
   successScope : paramValide(params, "successScope", "this")
}

function paramValide(map, param, defaultValue) {
   return (map[param] != undefined && map[param] != null && map[param] != "") ? map[param] : defaultValue;
}
