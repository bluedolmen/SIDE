/*
 * BlueXML SIDE
 */
function _testTagName(/* DOM Node */ element, /* String */value) {
	var refValue = (value) ? value.toLowerCase() : "";
	return (element.nodeName.toLowerCase() == refValue);
}

function _testId(/* DOM Node */ element, /* String */value) {
	if (element.id) {
		return (element.id == value);
	}
	return false;
}

function _trimWhitespace(/* String */ value) {
	var result = "";
	if (value) {
		result = value.replace(/^\s*/, "").replace(/\s*$/, "");
	}
	return result;
}

function _getDivsByClass(/* String */clazz) {
	/* caution when using this function on large documents, especially with old browsers */
	var divs = document.getElementsByTagName('div');
	var results = [];
	var element;
	for ( var i = 0; i < divs.length; i++) {
		element = divs[i];
		if ((" " + element.className + " ").indexOf(" " + clazz + " ") != -1) {
			results.push(element);
		}
	}
	return results;
}

function _hasClass(element, /* String */clazz) { /* from Chiba's xforms-util.js */
	if (!element || !element.className) {
		return false;
	}

	// surround classes with spaces to guarantee non-ambiguous lookups
	if ((" " + element.className + " ").indexOf(" " + clazz + " ") == -1) {
		return false;
	}

	return true;
}

function _addClass(element, /* String */clazz) { /* from Chiba's xforms-util.js */
	if (!element || !element.className) {
		return false;
	}

	// surround classes with spaces to guarantee non-ambiguous lookups
	if ((" " + element.className + " ").indexOf(" " + clazz + " ") == -1) {
		element.className = element.className + " " + clazz;
		return true;
	}

	return false;
}

function _removeClass(element, /* String */clazz) { /* from Chiba's xforms-util.js */
	if (!element || !element.className) {
		return false;
	}

	// surround classes with spaces to guarantee non-ambiguous lookups
	if ((" " + element.className + " ").indexOf(" " + clazz + " ") > -1) {
		var classList = (" " + element.className + " ").replace(new RegExp(" " + clazz + " "), " ");
		element.className = classList.slice(1, classList.length - 1);
		return true;
	}

	return false;
}

function _hideSelectionWidgets() {
	// hides all selection widgets on the form by adding the "ghost" CSS class
	var divs = _getDivsByClass("side_select_widget");
	for ( var i = 0; i < divs.length; i++) {
		var element = divs[i];
		_addClass(element, "ghost");
	}
}

function _getDescendant(/* DOM Node */element, /* String */value, /* function (DOM Node, String) */ fCompare) {
	// returns the first descendant that matches the tag name
	if (!element) {
		return null;
	}

	var children = element.childNodes;

	var idx = 0;
	while (idx < children.length) {
		if (fCompare(children[idx], value)) {
			return children[idx];
		}
		idx++;
	}
	
	// not found amongst the children, so recurse
	idx = 0;
	while (idx < children.length) {
		var result = _getDescendant(children[idx], value, fCompare);
		if (result !== null) {
			return result;
		}
		idx++;
	}
	// nowhere to be found
	return null;
}

function _getDescendantByTag(/* DOM Node */ element, /* String */value) {
	return _getDescendant(element, value, _testTagName);
}

function _getDescendantById(/* DOM Node */ element, /* String */value) {
	return _getDescendant(element, value, _testId);
}

function _getChildByClass(/* DOM Node */element, /* String */tagName, /* String */clazz) {
	// gets the first child, with the tag name, that has the style
	var children = element.childNodes;

	var idx = 0;
	while (idx < children.length) {
		var child = children[idx];
		if (child.nodeName.toLowerCase() == tagName) {
			if (_hasClass(child, clazz)) {
				return child;
			}
		}
		idx++;
	}
}

function _getSelectionWidgetLabelNode(/* DOM Node */element /* root div of the widget */) {
	// returns a new label by cloning the original widget's label

	var originalLabel = _getDescendantByTag(element, "label");
	var labelNode = originalLabel.cloneNode(true);
	return labelNode;
}

function _removeAllChildNodes(/* DOM Node */ node) {
	if (node && node.hasChildNodes && node.removeChild) {
		while (node.hasChildNodes()) {
			node.removeChild(node.firstChild);
		}
	}
}

function _selectionWidgetOpen (/* DOM Node */ element /* the input in "open" button */) {
	// hides the new widget and opens the original widget
	
	var newWidget = element.parentNode.parentNode.parentNode;
	var parent = newWidget.parentNode; // the field div
	var oldWidget = _getChildByClass(parent, "div", "side_select_widget");

	// update visibility
	_addClass(newWidget, "ghost");
	_removeClass(oldWidget, "ghost");
}

function _selectionWidgetClose (/* DOM Node */ element /* the input in "close" button */) {
	// updates the new widget's output with the selection in the original widget

	// get the baseId from the button's id
	var baseId = element.id.replace("-closeInput", "");

	// clear the new widget's output
	var oldWidget = element.parentNode.parentNode.parentNode;
	var parent = oldWidget.parentNode; // the field div
	var newWidget = _getDescendantById(parent, baseId + "-root");
	var outputContainer = _getDescendantById(newWidget, baseId + "-output");
	_removeAllChildNodes(outputContainer);

	// read the selection
	var selectedItems = [];
	var firstSpan;
	var itemNode;
	var itemText;
	var len;
	var itemContainer = _getChildByClass(oldWidget, "div", "side_select_selected_item");
	if (itemContainer) { // Nx1 widget
		firstSpan = itemContainer.childNodes[1];
		itemNode = _getDescendantByTag(firstSpan, "span");
		itemText = (itemNode.textContent ? itemNode.textContent : itemNode.innerText);
		itemText = _trimWhitespace(itemText);
		len = itemText.length;
		if (len > 0) {
			selectedItems.push(itemText);
		}
	} else { // NxN widget
		itemContainer = _getChildByClass(oldWidget, "div", "side_select_selected_items");
		var repeater = _getChildByClass(itemContainer, "div", "repeat");
		var items = repeater.childNodes;
		for (var iItems = 0; iItems < items.length; iItems++) {
			var item = items[iItems];
			firstSpan = _getDescendantByTag(item, "span");
			itemNode = _getDescendantByTag(firstSpan, "span");
			itemText = (itemNode.textContent ? itemNode.textContent : itemNode.innerText);
			itemText = _trimWhitespace(itemText);
			len = itemText.length;
			if (len > 0) {
				selectedItems.push(itemText);
			}
		}
	}

	// for each selected item, create a div>span in the output container
	for (var i = 0; i < selectedItems.length; i++) {
		var outputDiv = document.createElement('div'); // container
		var outputSpan = document.createElement('span');
		var textNode = document.createTextNode(selectedItems[i]);
		outputSpan.appendChild(textNode);
		outputSpan.className = "output xsd-string enabled readwrite optional valid";
		outputDiv.appendChild(outputSpan);
		outputContainer.appendChild(outputDiv);
	}

	// update visibility
	_addClass(oldWidget, "ghost");
	_removeClass(newWidget, "ghost");

}

function _replaceSelectionWidgets() {
	// replaces all **extended** selection widgets with outputs
	// WARNING: the purpose of this function (and all others directly or indirectly called) is to
	// illustrate a possibility of replacing a generated widget with a custom one. It will make the 
	// form not work if run on associations that are rendered as simple select lists.

	var widgets = _getDivsByClass("side_select_widget");
	for ( var i = 0; i < widgets.length; i++) {
		var element = widgets[i]; // the original widget

		// make sure it has not been already "replaced"
		if (!element.getAttribute("customWidget")) {

			var parent = element.parentNode;
			var baseId = parent.id + "-customWidget";
	
			var div = document.createElement('div'); // topmost container for the new widget
			div.className = "customWidget";
	
			// container for showing the selected items
			var outputContainer = document.createElement('div'); 
			outputContainer.className = "customWidgetOutput";
	
			var openDiv = document.createElement('div'); 
			var openSpan = document.createElement('span');
			var openInput = document.createElement('input');
	
			_addClass(openSpan, 'submit');
			openDiv.id = baseId + "-openDiv";
			openDiv.className = "customWidgetOpen";
	
			openSpan.id = baseId + "-openSpan";
			openInput.id = baseId + "-openInput";
			openInput.setAttribute("type", "button");
			openInput.setAttribute("value", "Choose items");
			openInput.setAttribute("onclick" , "_selectionWidgetOpen(this);");
	
			openDiv.appendChild(openSpan);
			openSpan.appendChild(openInput);
	
			// get a new label based on the one in the original selection widget
			var labelNode = _getSelectionWidgetLabelNode(element);
	
			div.appendChild(labelNode);
			div.appendChild(outputContainer);
			div.appendChild(openDiv);
	
			// set ids
			div.id = baseId + "-root";
			labelNode.id = baseId + "-label";
			outputContainer.id = baseId + "-output";
	
			// modify the original widget so as to have a "close" button
			var closeDiv = document.createElement('div'); 
			var closeSpan = document.createElement('span');
			var closeInput = document.createElement('input');
			_addClass(closeSpan, 'submit');
			closeDiv.id = baseId + "-closeDiv";
			closeDiv.className = "customWidgetClose";
			closeSpan.id = baseId + "-closeSpan";
			closeInput.id = baseId + "-closeInput";
			closeInput.setAttribute("type", "button");
			closeInput.setAttribute("value", "Close");
	
			closeDiv.appendChild(closeSpan);
			closeSpan.appendChild(closeInput);
	
			var clearDiv = _getChildByClass(element, "div", "xformstdclear");
			element.insertBefore(closeDiv, clearDiv);
			element.setAttribute("customWidget", div.id);

			// attach a click handler to the "close" button for updating the output & hiding the
			// widget
			closeInput.setAttribute("onclick" , "_selectionWidgetClose(this);");
	
			// insert the replacement widget before the original widget
			parent.insertBefore(div, element);
	
			// set visibility
			_addClass(element, "ghost");
		}
	}
}

/*
 * PLEASE LEAVE THE 'side' NAMESPACE STRUCTURE UNTOUCHED
 * 
 * 
 * You may comment out lines but the function names in 'side' must remain as defined.
 */
var side = {};

side.init = function() {
	// called on the DOMReady event

	// _replaceSelectionWidgets();
};

side.updateUI = function() {
	// called by the 'add' trigger for inline multiple subforms

};
