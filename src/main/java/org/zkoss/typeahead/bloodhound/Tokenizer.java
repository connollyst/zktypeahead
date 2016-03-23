package org.zkoss.typeahead.bloodhound;

import org.zkoss.json.JSONObject;

/**
 * @author Sean Connolly
 */
public class Tokenizer extends JSONObject {

    public static Tokenizer nonword() {
        return new NonwordTokenizer();
    }

    public static Tokenizer nonword(String key) {
        return new NonwordTokenizer(key);
    }

    public static Tokenizer whitespace() {
        return new WhitespaceTokenizer();
    }

    public static Tokenizer whitespace(String key) {
        return new WhitespaceTokenizer(key);
    }

    Tokenizer(String type) {
        put("type", type);
    }

}
