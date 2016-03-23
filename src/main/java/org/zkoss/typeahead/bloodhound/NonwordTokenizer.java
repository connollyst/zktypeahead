package org.zkoss.typeahead.bloodhound;

/**
 * @author Sean Connolly
 */
public class NonwordTokenizer extends Tokenizer {

    public NonwordTokenizer() {
        super("nonword");
    }

    public NonwordTokenizer(String key) {
        this();
        put("key", key);
    }

}
