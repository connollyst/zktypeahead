package org.zkoss.typeahead;

import java.io.IOException;

import org.zkoss.typeahead.data.Dataset;
import org.zkoss.zk.ui.sys.ContentRenderer;
import org.zkoss.zul.Textbox;

/**
 * A <a href="https://github.com/corejavascript/typeahead.js">Typeahead.js</a> component.
 *
 * @author Sean Connolly
 */
public class Typeahead extends Textbox {

	private Boolean highlight = true;
	private Boolean hint = true;
	private int minLength = 1;
	private String placeholder;
	private String dir;
	private Dataset dataset;

	public Boolean getHighlight() {
		return highlight;
	}

	public void setHighlight(Boolean highlight) {
		if(this.highlight != highlight) {
			this.highlight = highlight;
			smartUpdate("highlight", highlight);
		}
	}

	public boolean getHint() {
		return hint;
	}

	public void setHint(boolean hint) {
		if(this.hint != hint) {
			this.hint = hint;
			smartUpdate("hint", hint);
		}
	}

	public int getMinLength() {
		return minLength;
	}

	public void setMinLength(int minLength) {
		if(this.minLength != minLength) {
			this.minLength = minLength;
			smartUpdate("minLength", minLength);
		}
	}

	public String getPlaceholder() {
		return this.placeholder;
	}

	public void setPlaceholder(String placeholder) {
		if(this.placeholder == null || !this.placeholder.equals(placeholder)) {
			this.placeholder = placeholder;
			smartUpdate("placeholder", placeholder);
		}
	}

	public String getDir() {
		return this.placeholder;
	}

	public void setDir(String dir) {
		if(this.dir == null || !this.dir.equals(dir)) {
			this.dir = dir;
			smartUpdate("dir", dir);
		}
	}

	public Dataset getDataset() {
		return dataset;
	}

	public void setDataset(Dataset dataset) {
		if(this.dataset == null || !this.dataset.equals(dataset)) {
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
		render(renderer, "placeholder", placeholder);
		render(renderer, "dir", dir);
		render(renderer, "dataset", dataset);
	}

}
