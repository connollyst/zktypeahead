package org.zkoss.typeahead.bloodhound;

/**
 * @author Sean Connolly
 */
public class WhitespaceTokenizer extends Tokenizer {


    public WhitespaceTokenizer() {
        super("whitespace");
    }

    public WhitespaceTokenizer(String key) {
        this();
        put("key", key);
    }

}
