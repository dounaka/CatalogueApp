package ca.tash.catalogueapp.store;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by dounaka on 2017-04-13.
 */

public class Bag extends Entity {
    public String name, shortDesc, longDesc, pict;
    public int priceInCents;
    public float rating;
    public Set<String> colors = new HashSet<>();
    public String height, width, deep;


}
