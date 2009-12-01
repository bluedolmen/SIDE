/* Written by Chris Pietschmann (http://pietschsoft.com), 3/1/2007 */

/*
This method uses a couple different methods of instantiating the
XMLHttpRequest object. The reason we do this is so we can support
multiple browser (I've only tested in IE and Firefox).
*/
function GET_XMLHTTPRequest()
{
    var request;
    
    // Lets try using ActiveX to instantiate the XMLHttpRequest object
    try{
        request = new ActiveXObject("Microsoft.XMLHTTP");
    }catch(ex1){
        try{
            request = new ActiveXObject("Msxml2.XMLHTTP");
        }catch(ex2){
            request = null;
        }
    }

    // If the previous didn't work, lets check if the browser natively support XMLHttpRequest 
    if(!request && typeof XMLHttpRequest != "undefined"){
        //The browser does, so lets instantiate the object
        request = new XMLHttpRequest();
    }

    return request;
}

function FireOffAJAXRequest(/*String:*/ nameFile)
{
    //Instantiate a new XMLHttpRequest object
    var myXMLHttpRequest = GET_XMLHTTPRequest();
    
    //Make sure the XMLHttpRequest object was instantiated
    if (myXMLHttpRequest)
    {
        //Tell the XMLHttpRequest object what we want it to do.
        //In the first parameter we're telling it to use HTTP GET for the request
        //In the second parameter we're telling it what page to request
        //In the third parameter we're telling it to do the request asychronously
        myXMLHttpRequest.open("GET", nameFile, false);
	    //Lets fire off the request
        myXMLHttpRequest.send(null);
        return myXMLHttpRequest.responseText;
    }
    else
    {
        //Oh no, the XMLHttpRequest object couldn't be instantiated.
        return "A problem occurred instantiating the XMLHttpRequest object.";
    }
}
