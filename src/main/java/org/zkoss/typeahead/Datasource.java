package org.zkoss.typeahead;

import org.zkoss.json.JSONObject;

/**
 * @author Sean Connolly
 */
public abstract class Datasource extends JSONObject {

    abstract void validate();

}
