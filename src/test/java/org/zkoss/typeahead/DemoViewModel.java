package org.zkoss.typeahead;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.typeahead.data.Bloodhound;
import org.zkoss.typeahead.data.Dataset;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * View-Model for the demo of {@link Typeahead} in action.
 *
 * @author Sean Connolly
 */
public class DemoViewModel {

    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireEventListeners(view, this);
    }

	/* EVENT LISTENERS */

    @Listen("onChanging = typeahead")
    public void typeaheadChanging(InputEvent event) {
        Typeahead typeahead = (Typeahead) event.getTarget();
        System.out.println(typeahead.getId() + " is changing value to " + typeahead.getValue());
    }

    @Listen("onChange = typeahead")
    public void typeaheadChanged(InputEvent event) {
        Typeahead typeahead = (Typeahead) event.getTarget();
        System.out.println(typeahead.getId() + " has value changed to " + typeahead.getValue());
    }

    @Listen("onSelect = typeahead")
    public void typeaheadSelected(Event event) {
        Typeahead typeahead = (Typeahead) event.getTarget();
        System.out.println(typeahead.getId() + " has selected a value: " + typeahead.getValue());
    }

    @Listen("onFocus = typeahead")
    public void typeaheadFocused(Event event) {
        System.out.println(event.getTarget().getId() + " has focus.");
    }

    @Listen("onBlur = typeahead")
    public void typeaheadBlurred(Event event) {
        System.out.println(event.getTarget().getId() + " lost focus.");
    }

    @Listen(TypeaheadEvents.ACTIVE + " = typeahead")
    public void typeaheadActive(Event event) {
        System.out.println(event.getTarget().getId() + " (native): " + event.getName());
    }

    @Listen(TypeaheadEvents.IDLE + " = typeahead")
    public void typeaheadIdle(Event event) {
        System.out.println(event.getTarget().getId() + " (native): " + event.getName());
    }

    @Listen(TypeaheadEvents.OPEN + " = typeahead")
    public void typeaheadOpen(Event event) {
        System.out.println(event.getTarget().getId() + " (native): " + event.getName());
    }

    @Listen(TypeaheadEvents.CLOSE + " = typeahead")
    public void typeaheadClose(Event event) {
        System.out.println(event.getTarget().getId() + " (native): " + event.getName());
    }

    @Listen(TypeaheadEvents.CHANGE + " = typeahead")
    public void typeaheadChange(Event event) {
        System.out.println(event.getTarget().getId() + " (native): " + event.getName() + " w/ " + event.getData());
    }

    @Listen(TypeaheadEvents.RENDER + " = typeahead")
    public void typeaheadRender(Event event) {
        System.out.println(event.getTarget().getId() + " (native): " + event.getName() + " w/ " + event.getData());
    }

    @Listen(TypeaheadEvents.SELECT + " = typeahead")
    public void typeaheadSelect(Event event) {
        System.out.println(event.getTarget().getId() + " (native): " + event.getName() + " w/ " + event.getData());
    }

    @Listen(TypeaheadEvents.AUTOCOMPLETE + " = typeahead")
    public void typeaheadAutocomplete(Event event) {
        System.out.println(event.getTarget().getId() + " (native): " + event.getName() + " w/ " + event.getData());
    }

    @Listen(TypeaheadEvents.CURSOR_CHANGE + " = typeahead")
    public void typeaheadCursorChange(Event event) {
        System.out.println(event.getTarget().getId() + " (native): " + event.getName() + " w/ " + event.getData());
    }

    @Listen(TypeaheadEvents.ASYNC_REQUEST + " = typeahead")
    public void typeaheadAsyncRequest(Event event) {
        System.out.println(event.getTarget().getId() + " (native): " + event.getName() + " w/ " + event.getData());
    }

    @Listen(TypeaheadEvents.ASYNC_CANCEL + " = typeahead")
    public void typeaheadAsyncCancel(Event event) {
        System.out.println(event.getTarget().getId() + " (native): " + event.getName() + " w/ " + event.getData());
    }

    @Listen(TypeaheadEvents.ASYNC_RECEIVE + " = typeahead")
    public void typeaheadAsyncReceive(Event event) {
        System.out.println(event.getTarget().getId() + " (native): " + event.getName() + " w/ " + event.getData());
    }

	/* DATASETS */

    public Dataset getStates() {
        String[] states = new String[]{"Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado",
                "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa",
                "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota",
                "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico",
                "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania",
                "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia",
                "Washington", "West Virginia", "Wisconsin", "Wyoming"};
        return Bloodhound
                .local(states)
                .withDatumTokenizers(Bloodhound.Tokenizer.whitespace())
                .withQueryTokenizers(Bloodhound.Tokenizer.whitespace())
                .build();
    }

    public Dataset getCountries() {
        return Bloodhound
                .prefetch("data/countries.json")
                .withDatumTokenizers(Bloodhound.Tokenizer.whitespace())
                .withQueryTokenizers(Bloodhound.Tokenizer.whitespace())
                .build();
    }

    public Dataset getBestPictures() {
        Dataset bestPictures = Bloodhound
                .prefetch("data/post_1960.json")
                // TODO implement as 'remote' webservice
                // .remote("data/films/queries/%QUERY.json", "%QUERY")
                .withCache(false)
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

    public Collection<Dataset> getMultipleTeamDatasets() {
        Dataset nba = getNba();
        nba.addTemplate("header", "<h3 class='league-name'>NBA Teams</h3>");
        Dataset nhl = getNhl();
        nhl.addTemplate("header", "<h3 class='league-name'>NHL Teams</h3>");
        return Arrays.asList(nba, nhl);
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
        Dataset teams = Bloodhound
                .prefetch(file)
                .withCache(false)
                .withDatumTokenizers(Bloodhound.Tokenizer.whitespace("team"))
                .withQueryTokenizers(Bloodhound.Tokenizer.whitespace())
                .build(name);
        teams.setDisplay("team");
        return teams;
    }

    public Dataset getRtl() {
        return Bloodhound
                .prefetch("data/arabic.json")
                .withDatumTokenizers(Bloodhound.Tokenizer.whitespace())
                .withQueryTokenizers(Bloodhound.Tokenizer.whitespace())
                .build();
    }

}
