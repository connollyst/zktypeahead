package org.zkoss.typeahead.data;

import org.zkoss.json.JSONObject;

/**
 * @author Sean Connolly
 */
public class Bloodhound extends Dataset.Source {

	public static final String CLASS = "_class";

	public static final String LOCAL = "local";
	public static final String PREFETCH = "prefetch";
	public static final String REMOTE = "remote";

	public static final String URL = "url";
	public static final String CACHE = "cache";
	public static final String WILDCARD = "wildcard";
	public static final String PREPARE = "prepare";
	public static final String TRANSFORM = "transform";

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

	private void setLocal(String... values) {
		put(LOCAL, values);
	}

	private void setPrefetch(String url) {
		getPrefetch().put(URL, url);
	}

	private void setPrefetchCache(boolean cache) {
		getPrefetch().put(CACHE, cache);
	}

	private void setPrefetchPrepare(String javascript) {
		getPrefetch().put(PREPARE, javascript);
	}

	public void setPrefetchTransform(String javascript) {
		getPrefetch().put(TRANSFORM, javascript);
	}

	private JSONObject getPrefetch() {
		return getJSONObject(PREFETCH);
	}

	private void setRemote(String url) {
		getRemote().put(URL, url);
	}

	private void setRemote(String url, String wildcard) {
		getRemote().put(URL, url);
		getRemote().put(WILDCARD, wildcard);
	}

	public void setRemoteWildcard(String wildcard) {
		getRemote().put(WILDCARD, wildcard);
	}

	public void setRemoteCache(boolean cache) {
		getRemote().put(CACHE, cache);
	}

	private void setRemotePrepare(String javascript) {
		getRemote().put(PREPARE, javascript);
	}

	private void setRemoteTransform(String javascript) {
		getRemote().put(TRANSFORM, javascript);
	}

	private JSONObject getRemote() {
		return getJSONObject(REMOTE);
	}

	private void setDatumTokenizers(Tokenizer... tokenizers) {
		put(DATUM_TOKENIZERS, tokenizers);
	}

	private void setQueryTokenizers(Tokenizer... tokenizers) {
		put(QUERY_TOKENIZERS, tokenizers);
	}

	void validate() {
		verify(CLASS);
		verify(DATUM_TOKENIZERS);
		verify(QUERY_TOKENIZERS);
	}

	void verify(String key) {
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

	public static Builder local(String... values) {
		return new Builder(Type.LOCAL).withLocal(values);
	}

	public static Builder prefetch(String url) {
		return new Builder(Type.PREFETCH).withPrefetch(url);
	}

	public static Builder remote(String url) {
		return new Builder(Type.REMOTE).withRemote(url);
	}

	public static Builder remote(String url, String wildcard) {
		return new Builder(Type.REMOTE).withRemote(url, wildcard);
	}

	private enum Type {
		LOCAL, PREFETCH, REMOTE
	}

	public static final class Builder {

		private final Type type;

		private Builder(Type type) {
			this.type = type;
		}

		Bloodhound bloodhound = new Bloodhound();

		public Builder withLocal(String... values) {
			bloodhound.setLocal(values);
			return this;
		}

		public Builder withPrefetch(String url) {
			bloodhound.setPrefetch(url);
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

		public Builder withWildcard(String wildcard) {
			if(type == Type.REMOTE) {
				bloodhound.setRemoteWildcard(wildcard);
			} else {
				throw new IllegalArgumentException("'wildcard' is only applicable to remote data.");
			}
			return this;
		}

		public Builder withCache(boolean cache) {
			switch (type) {
			case PREFETCH:
				bloodhound.setPrefetchCache(cache);
				return this;
			case REMOTE:
				bloodhound.setRemoteCache(cache);
				return this;
			default:
				throw new IllegalArgumentException("'cache' is only applicable to prefetch and remote data.");
			}
		}

		public Builder withDatumTokenizers(Tokenizer... tokenizers) {
			bloodhound.setDatumTokenizers(tokenizers);
			return this;
		}

		public Builder withQueryTokenizers(Tokenizer... tokenizers) {
			bloodhound.setQueryTokenizers(tokenizers);
			return this;
		}

		/**
		 * <p>
		 * A function that provides a hook to allow you to prepare the settings object passed to {@code transport} when
		 * a request is about to be made. The function signature should be {@code prepare(query, settings)}, where
		 * {@code query} is the query {@code #search} was called with and {@code settings} is the default settings
		 * object created internally by the Bloodhound instance. The {@code prepare} function should return a settings
		 * object. Defaults to the identity function.
		 * </p>
		 * <p>
		 * The function is passed to the browser as a String and is evaluated there.
		 * </p>
		 * 
		 * @param javascript
		 * @return
		 */
		public Builder withPrepare(String javascript) {
			switch (type) {
			case PREFETCH:
				bloodhound.setPrefetchPrepare(javascript);
				return this;
			case REMOTE:
				bloodhound.setRemotePrepare(javascript);
				return this;
			default:
				throw new IllegalArgumentException("'prepare' is only applicable to prefetch and remote data.");
			}
		}

		public Builder withTransform(String javascript) {
			switch (type) {
			case PREFETCH:
				bloodhound.setPrefetchTransform(javascript);
				return this;
			case REMOTE:
				bloodhound.setRemoteTransform(javascript);
				return this;
			default:
				throw new IllegalArgumentException("'transform' is only applicable to prefetch and remote data.");
			}
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
