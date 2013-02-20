if (typeof SIDE == "undefined" || !SIDE) {
   var SIDE = {};
}
SIDE.args = {};
SIDE.args.paramValide = function (param, defaultValue) {
   return (args[param] != undefined && args[param] != null && args[param] != "") ? args[param] : defaultValue;
}