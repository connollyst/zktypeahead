package org.zkoss.typeahead;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.typeahead.data.Bloodhound;
import org.zkoss.typeahead.data.Dataset;

/**
 * View-Model for the demo of {@link Typeahead} in action.
 *
 * @author Sean Connolly
 */
public class DemoViewModel {

	public Dataset getStates() {
		String[] states = new String[] { "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado",
				"Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa",
				"Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota",
				"Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico",
				"New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania",
				"Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia",
				"Washington", "West Virginia", "Wisconsin", "Wyoming" };
		return Bloodhound
				.builder()
					.withLocal(states)
					.withDatumTokenizers(Bloodhound.Tokenizer.whitespace())
					.withQueryTokenizers(Bloodhound.Tokenizer.whitespace())
					.build();
	}

	public Dataset getCountries() {
		return Bloodhound
				.builder()
					.withPrefetch("data/countries.json")
					.withDatumTokenizers(Bloodhound.Tokenizer.whitespace())
					.withQueryTokenizers(Bloodhound.Tokenizer.whitespace())
					.build();
	}

	public Dataset getBestPictures() {
		Dataset bestPictures = Bloodhound
				.builder()
					.withPrefetch("data/post_1960.json", false)
					// TODO implement web service
					// .withRemote("data/films/queries/%QUERY.json", "%QUERY")
					.withDatumTokenizers(Bloodhound.Tokenizer.whitespace("value"))
					.withQueryTokenizers(Bloodhound.Tokenizer.whitespace())
					.build();
		bestPictures.setName("best-pictures");
		bestPictures.setDisplay("value");
		return bestPictures;
	}

	public Dataset getBestPicturesPretty() {
		Dataset bestPictures = getBestPictures();
		Map<String, String> templates = new HashMap<>();
		templates.put("empty",
				"<div class='empty-message'>unable to find any Best Picture winners that match the current query</div>");
		templates.put("suggestion", "<div><strong>{{value}}</strong> - {{year}}</div>");
		bestPictures.setTemplates(templates);
		return bestPictures;
	}

	public Dataset getNfl() {
		return getTeams("nfl-teams", "data/nfl.json");
	}

	public Dataset getNba() {
		return getTeams("nba-teams", "data/nba.json");
	}

	public Dataset getNhl() {
		return getTeams("nhl-teams", "data/nhl.json");
	}

	private Dataset getTeams(String name, String file) {
		return Bloodhound
				.builder()
					.withPrefetch(file, false)
					.withDatumTokenizers(Bloodhound.Tokenizer.whitespace("team"))
					.withQueryTokenizers(Bloodhound.Tokenizer.whitespace())
					.build(name);
	}

	public Dataset getRtl() {
		return Bloodhound
				.builder()
					.withPrefetch("data/arabic.json")
					.withDatumTokenizers(Bloodhound.Tokenizer.whitespace())
					.withQueryTokenizers(Bloodhound.Tokenizer.whitespace())
					.build();
	}

}
