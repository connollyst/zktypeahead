package org.zkoss.typeahead;

import org.zkoss.typeahead.bloodhound.Tokenizer;
import org.zkoss.zk.ui.sys.ContentRenderer;
import org.zkoss.zul.impl.XulElement;

import java.io.IOException;

/**
 * A <a href="https://github.com/corejavascript/typeahead.js">Typeahead.js</a> component.
 *
 * @author Sean Connolly
 */
public class Typeahead extends XulElement {

    private Boolean highlight = true;
    private Boolean hint = true;
    private int minLength = 1;
    private Dataset dataset;

    public Typeahead() {
        Bloodhound bloodhound = Bloodhound.prefetch("http://localhost:8080/zktypeahead/users.json", false);
        bloodhound.setDatumTokenizers(Tokenizer.whitespace("login"), Tokenizer.whitespace("first"), Tokenizer.whitespace("last"));
        bloodhound.setQueryTokenizers(Tokenizer.whitespace());
        System.out.println("Bloodhound: " + bloodhound);
        Dataset ds = new Dataset(bloodhound);
        ds.setName("alps-people");
        ds.setDisplay("login");
        setDataset(ds);
    }

    public Boolean getHighlight() {
        return highlight;
    }

    public void setHighlight(Boolean highlight) {
        if (this.highlight != highlight) {
            this.highlight = highlight;
            smartUpdate("highlight", highlight);
        }
    }

    public boolean getHint() {
        return hint;
    }

    public void setHint(boolean hint) {
        if (this.hint != hint) {
            this.hint = hint;
            smartUpdate("hint", hint);
        }
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        if (this.minLength != minLength) {
            this.minLength = minLength;
            smartUpdate("minLength", minLength);
        }
    }

    public Dataset getDataset() {
        return dataset;
    }

    public void setDataset(Dataset dataset) {
        if (this.dataset != dataset) {
            this.dataset = dataset;
            smartUpdate("dataset", dataset);
        }
    }

    @Override
    protected void renderProperties(ContentRenderer renderer) throws IOException {
        super.renderProperties(renderer);
        render(renderer, "highlight", highlight);
        render(renderer, "hint", hint);
        render(renderer, "minLength", minLength);
        render(renderer, "dataset", dataset);
    }

}
