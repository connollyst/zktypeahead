package org.zkoss.typeahead;

/**
 * @author Sean Connolly
 */
public class Bloodhound extends Datasource {

    public static Bloodhound local(String[] data) {
        Bloodhound local = new Bloodhound();
        local.put("local", data);
        return local;
    }

    private Bloodhound() {
        put("_class", "bloodhound");
    }

}
