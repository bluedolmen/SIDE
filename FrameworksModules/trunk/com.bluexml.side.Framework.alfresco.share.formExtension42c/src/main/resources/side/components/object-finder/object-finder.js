/**
 * SIDE root namespace.
 * 
 * @namespace SIDE
 */
// Ensure Alfresco root object exists
if (typeof SIDE == "undefined" || !SIDE) {
	var SIDE = {};
}

/**
 * ObjectFinder component.
 * 
 * @namespace SIDE
 * @class SIDE.ObjectFinder
 */
(function() {
	/**
	 * YUI Library aliases
	 */
	var Dom = YAHOO.util.Dom, Event = YAHOO.util.Event, KeyListener = YAHOO.util.KeyListener;

	/**
	 * Alfresco Slingshot aliases
	 */
	var $html = Alfresco.util.encodeHTML, $hasEventInterest = Alfresco.util.hasEventInterest;

	/**
	 * ObjectFinder constructor.
	 * 
	 * @param {String}
	 *            htmlId The HTML id of the parent element
	 * @param {String}
	 *            currentValueHtmlId The HTML id of the parent element
	 * @return {SIDE.ObjectFinder} The new ObjectFinder instance
	 * @constructor
	 */
	SIDE.ObjectFinder = function(htmlId, currentValueHtmlId) {
		SIDE.ObjectFinder.superclass.constructor.call(this, htmlId, currentValueHtmlId);

		return this;
	};

	YAHOO.extend(SIDE.ObjectFinder, Alfresco.ObjectFinder, {

		/**
		 * Determines whether the picker is in 'authority' mode.
		 * 
		 * @method _inAuthorityMode
		 * @return true if the picker is being used to find authorities i.e.
		 *         users and groups
		 * @private
		 */
		_inAuthorityMode : function ObjectFinder__inAuthorityMode() {
			return (this.options.itemFamily == "authority" || this.options.itemFamily == "search");
		}

	});
})();
