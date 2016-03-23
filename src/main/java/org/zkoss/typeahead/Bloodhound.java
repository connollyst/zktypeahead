package org.zkoss.typeahead;

import org.zkoss.json.JSONObject;
import org.zkoss.typeahead.bloodhound.Tokenizer;

import java.util.Map;

/**
 * @author Sean Connolly
 */
public class Bloodhound extends Datasource {

    public static final String CLASS = "_class";
    public static final String TYPE = "_type";
    public static final String PREFETCH = "prefetch";
    public static final String PREFETCH_URL = "url";
    public static final String PREFETCH_CACHE = "cache";
    public static final String BLOODHOUND = "bloodhound";
    public static final String DATUM_TOKENIZERS = "datumTokenizers";
    public static final String QUERY_TOKENIZERS = "queryTokenizers";


    public static Bloodhound prefetch(String url, boolean cache) {
        Bloodhound bloodhound = new Bloodhound(PREFETCH);
        Map<Object, Object> prefetch = new JSONObject();
        prefetch.put(PREFETCH_URL, url);
        prefetch.put(PREFETCH_CACHE, cache);
        bloodhound.put(PREFETCH, prefetch);
        return bloodhound;
    }

    private Bloodhound(String type) {
        put(CLASS, BLOODHOUND);
        put(TYPE, type);
    }

    public void setDatumTokenizers(Tokenizer... tokenizers) {
        put(DATUM_TOKENIZERS, tokenizers);
    }

    public void setQueryTokenizers(Tokenizer... tokenizers) {
        put(QUERY_TOKENIZERS, tokenizers);
    }

    void validate() {
        vertify(CLASS);
        vertify(TYPE);
        vertify(DATUM_TOKENIZERS);
        vertify(QUERY_TOKENIZERS);
    }

    void vertify(String key) {
        if (!containsKey(key)) {
            throw new IllegalStateException(key + " is required.");
        }
    }
}
