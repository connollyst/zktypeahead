package org.zkoss.typeahead.data;

import org.zkoss.json.JSONObject;

/**
 * @author Sean Connolly
 */
public class Bloodhound extends Dataset.Source {

	public static final String CLASS = "_class";

	public static final String LOCAL = "local";

	public static final String PREFETCH = "prefetch";
	public static final String PREFETCH_URL = "url";
	public static final String PREFETCH_CACHE = "cache";

	public static final String REMOTE = "remote";
	public static final String REMOTE_URL = "url";
	public static final String REMOTE_WILDCARD = "wildcard";

	public static final String BLOODHOUND = "bloodhound";
	public static final String DATUM_TOKENIZERS = "datumTokenizers";
	public static final String QUERY_TOKENIZERS = "queryTokenizers";

	private Bloodhound() {
		put(CLASS, BLOODHOUND);
	}

	private JSONObject getJSONObject(String key) {
		if(!containsKey(key)) {
			put(key, new JSONObject());
		}
		return (JSONObject)get(key);
	}

	public void setLocal(String... values) {
		put(LOCAL, values);
	}

	public void setPrefetch(String url) {
		getPrefetch().put(PREFETCH_URL, url);
	}

	public void setPrefetch(String url, boolean cache) {
		getPrefetch().put(PREFETCH_URL, url);
		getPrefetch().put(PREFETCH_CACHE, cache);
	}

	private JSONObject getPrefetch() {
		return getJSONObject(PREFETCH);
	}

	public void setRemote(String url) {
		getPrefetch().put(PREFETCH_URL, url);
	}

	public void setRemote(String url, String wildcard) {
		getRemote().put(REMOTE_URL, url);
		getRemote().put(REMOTE_WILDCARD, wildcard);
	}

	private JSONObject getRemote() {
		return getJSONObject(PREFETCH);
	}

	public void setDatumTokenizers(Tokenizer... tokenizers) {
		put(DATUM_TOKENIZERS, tokenizers);
	}

	public void setQueryTokenizers(Tokenizer... tokenizers) {
		put(QUERY_TOKENIZERS, tokenizers);
	}

	void validate() {
		vertify(CLASS);
		vertify(DATUM_TOKENIZERS);
		vertify(QUERY_TOKENIZERS);
	}

	void vertify(String key) {
		if(!containsKey(key)) {
			throw new IllegalStateException(key + " is required.");
		}
	}

	public Dataset asDataset() {
		return new Dataset(this);
	}

	public static class Tokenizer extends JSONObject {

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

		private Tokenizer(String type) {
			put("type", type);
		}

		/**
		 * @author Sean Connolly
		 */
		private static final class NonwordTokenizer extends Tokenizer {

			private NonwordTokenizer() {
				super("nonword");
			}

			private NonwordTokenizer(String key) {
				this();
				put("key", key);
			}

		}

		/**
		 * @author Sean Connolly
		 */
		private static final class WhitespaceTokenizer extends Tokenizer {

			private WhitespaceTokenizer() {
				super("whitespace");
			}

			private WhitespaceTokenizer(String key) {
				this();
				put("key", key);
			}

		}

	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {

		Bloodhound bloodhound = new Bloodhound();

		public Builder withLocal(String... values) {
			bloodhound.setLocal(values);
			return this;
		}

		public Builder withPrefetch(String url) {
			bloodhound.setPrefetch(url);
			return this;
		}

		public Builder withPrefetch(String url, boolean cache) {
			bloodhound.setPrefetch(url, cache);
			return this;
		}

		public Builder withRemote(String url) {
			bloodhound.setRemote(url);
			return this;
		}

		public Builder withRemote(String url, String wildcard) {
			bloodhound.setRemote(url, wildcard);
			return this;
		}

		public Builder withDatumTokenizers(Tokenizer... tokenizers) {
			bloodhound.setDatumTokenizers(tokenizers);
			return this;
		}

		public Builder withQueryTokenizers(Tokenizer... tokenizers) {
			bloodhound.setQueryTokenizers(tokenizers);
			return this;
		}

		public Dataset build() {
			return bloodhound.asDataset();
		}

		public Dataset build(String name) {
			Dataset dataset = bloodhound.asDataset();
			dataset.setName(name);
			return dataset;
		}

	}

}
