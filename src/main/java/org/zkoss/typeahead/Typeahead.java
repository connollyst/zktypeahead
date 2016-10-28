package org.zkoss.typeahead;

import org.zkoss.typeahead.data.Dataset;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.sys.ComponentCtrl;
import org.zkoss.zk.ui.sys.ContentRenderer;
import org.zkoss.zul.Textbox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * A <a href="https://github.com/corejavascript/typeahead.js">Typeahead.js</a> component.
 *
 * @author Sean Connolly
 */
public class Typeahead extends Textbox {

    static {
        addClientEvent(Typeahead.class, Events.ON_SELECT, ComponentCtrl.CE_DUPLICATE_IGNORE);
        addClientEvent(Typeahead.class, TypeaheadEvents.ACTIVE, ComponentCtrl.CE_IMPORTANT);
        addClientEvent(Typeahead.class, TypeaheadEvents.IDLE, ComponentCtrl.CE_IMPORTANT);
        addClientEvent(Typeahead.class, TypeaheadEvents.OPEN, ComponentCtrl.CE_IMPORTANT);
        addClientEvent(Typeahead.class, TypeaheadEvents.CLOSE, ComponentCtrl.CE_IMPORTANT);
        addClientEvent(Typeahead.class, TypeaheadEvents.CHANGE, ComponentCtrl.CE_IMPORTANT);
        addClientEvent(Typeahead.class, TypeaheadEvents.RENDER, ComponentCtrl.CE_IMPORTANT);
        addClientEvent(Typeahead.class, TypeaheadEvents.SELECT, ComponentCtrl.CE_IMPORTANT);
        addClientEvent(Typeahead.class, TypeaheadEvents.AUTOCOMPLETE, ComponentCtrl.CE_IMPORTANT);
        addClientEvent(Typeahead.class, TypeaheadEvents.CURSOR_CHANGE, ComponentCtrl.CE_IMPORTANT);
        addClientEvent(Typeahead.class, TypeaheadEvents.ASYNC_REQUEST, ComponentCtrl.CE_IMPORTANT);
        addClientEvent(Typeahead.class, TypeaheadEvents.ASYNC_CANCEL, ComponentCtrl.CE_IMPORTANT);
        addClientEvent(Typeahead.class, TypeaheadEvents.ASYNC_RECEIVE, ComponentCtrl.CE_IMPORTANT);
    }

    private Boolean highlight = true;
    private Boolean hint = true;
    private int minLength = 1;
    private String placeholder;
    private String dir;
    private Collection<Dataset> datasets = new ArrayList<>();

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

    public String getPlaceholder() {
        return this.placeholder;
    }

    public void setPlaceholder(String placeholder) {
        if (this.placeholder == null || !this.placeholder.equals(placeholder)) {
            this.placeholder = placeholder;
            smartUpdate("placeholder", placeholder);
        }
    }

    public String getDir() {
        return this.placeholder;
    }

    public void setDir(String dir) {
        if (this.dir == null || !this.dir.equals(dir)) {
            this.dir = dir;
            smartUpdate("dir", dir);
        }
    }

    public Collection<Dataset> getDatasets() {
        return datasets;
    }


    public void setDataset(Dataset dataset) {
        this.datasets.clear();
        this.datasets.add(dataset);
        smartUpdate("datasets", datasets);
    }

    public void setDataset(Collection<Dataset> datasets) {
        this.datasets.clear();
        this.datasets.addAll(datasets);
        smartUpdate("datasets", datasets);
    }

    @Override
    protected void renderProperties(ContentRenderer renderer) throws IOException {
        super.renderProperties(renderer);
        render(renderer, "highlight", highlight);
        render(renderer, "hint", hint);
        render(renderer, "minLength", minLength);
        render(renderer, "placeholder", placeholder);
        render(renderer, "dir", dir);
        render(renderer, "datasets", datasets);
    }

}
