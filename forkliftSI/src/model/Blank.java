package model;

/**
 * Created by Tomasz on 2015-04-29.
 */
public class Blank implements WorldElement {

    private static final String TYPE = "Blank";

    @Override
    public String getType() {
        return TYPE;
    }
}
