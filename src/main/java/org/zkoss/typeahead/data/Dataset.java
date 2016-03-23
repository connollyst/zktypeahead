package org.zkoss.typeahead.data;

import org.zkoss.json.JSONObject;

import java.util.Map;

/**
 * @author Sean Connolly
 */
public class Dataset extends JSONObject {

    private static final String SOURCE = "source";
    private static final String ASYNC = "async";
    private static final String NAME = "name";
    private static final String LIMIT = "limit";
    private static final String DISPLAY = "display";
    private static final String TEMPLATES = "templates";

    public Dataset(Dataset.Source source) {
        source.validate();
        put(SOURCE, source);
    }

    public void setAsync(boolean async) {
        put(ASYNC, async);
    }

    public void setName(String name) {
        put(NAME, name);
    }

    public void setLimit(int limit) {
        put(LIMIT, limit);
    }

    public void setDisplay(String display) {
        put(DISPLAY, display);
    }

    public void setTemplates(Map<String, String> templates) {
        JSONObject jsonTemplates = new JSONObject();
        jsonTemplates.putAll(templates);
        put(TEMPLATES, jsonTemplates);
    }

    static abstract class Source extends JSONObject {

        abstract void validate();

    }

}
