package org.zkoss.typeahead;

/**
 * @author Sean Connolly
 */
public class TypeaheadEvents {

	/**
	 * Fired when the typeahead moves to active state.
	 */
	public static final String ACTIVE = "onTypeaheadActive";

	/**
	 * Fired when the typeahead moves to idle state.
	 */
	public static final String IDLE = "onTypeaheadIdle";

	/**
	 * Fired when the results container is opened.
	 */
	public static final String OPEN = "onTypeaheadOpen";

	/**
	 * Fired when the results container is closed.
	 */
	public static final String CLOSE = "onTypeaheadClose";

	/**
	 * Normalized version of the native change event. Fired when input loses focus and the value has changed since it
	 * originally received focus.
	 */
	public static final String CHANGE = "onTypeaheadChange";

	/**
	 * Fired when suggestions are rendered for a dataset. The event handler will be invoked with 4 arguments: the jQuery
	 * event object, the suggestions that were rendered, a flag indicating whether the suggestions were fetched
	 * asynchronously, and the name of the dataset the rendering occurred in.
	 */
	public static final String RENDER = "onTypeaheadRender";

	/**
	 * Fired when a suggestion is selected. The event handler will be invoked with 2 arguments: the jQuery event object
	 * and the suggestion object that was selected.
	 */
	public static final String SELECT = "onTypeaheadSelect";
	/**
	 * Fired when a autocompletion occurs. The event handler will be invoked with 2 arguments: the jQuery event object
	 * and the suggestion object that was used for autocompletion.
	 */
	public static final String AUTOCOMPLETE = "onTypeaheadAutocomplete";

	/**
	 * Fired when the results container cursor moves. The event handler will be invoked with 2 arguments: the jQuery
	 * event object and the suggestion object that was moved to.
	 */
	public static final String CURSOR_CHANGE = "onTypeaheadCursorChange";

	/**
	 * Fired when an async request for suggestions is sent. The event handler will be invoked with 3 arguments: the
	 * jQuery event object, the current query, and the name of the dataset the async request belongs to.
	 */
	public static final String ASYNC_REQUEST = "onTypeaheadAsyncRequest";

	/**
	 * Fired when an async request is cancelled. The event handler will be invoked with 3 arguments: the jQuery event
	 * object, the current query, and the name of the dataset the async request belonged to.
	 */
	public static final String ASYNC_CANCEL = "onTypeaheadAsyncCancel";

	/**
	 * Fired when an async request completes. The event handler will be invoked with 3 arguments: the jQuery event
	 * object, the current query, and the name of the dataset the async request belongs to.
	 */
	public static final String ASYNC_RECEIVE = "onTypeaheadAsyncReceive";

}
