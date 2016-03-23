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

    private Boolean hint = true;

    public boolean getHint() {
        return hint;
    }

    public void setHint(boolean hint) {
        if (this.hint != hint) {
            this.hint = hint;
            smartUpdate("hint", hint);
        }
    }

    @Override
    protected void renderProperties(ContentRenderer renderer) throws IOException {
        super.renderProperties(renderer);
        render(renderer, "hint", hint);
    }
}
