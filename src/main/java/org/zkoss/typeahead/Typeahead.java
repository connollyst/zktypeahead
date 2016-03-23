package org.zkoss.typeahead;

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
        setDataset(new Dataset(Bloodhound.local(new String[]{
                "Alabama", "Alaska", "Arizona", "Arkansas", "California",
                "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii",
                "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana",
                "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota",
                "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire",
                "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota",
                "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island",
                "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont",
                "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"
        })));
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
