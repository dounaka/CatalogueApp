package ca.tash.catalogueapp.store;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by dounaka on 2017-04-13.
 */

public class Bag extends Entity {


    public String name, shortDesc, longDesc, pict;

    public long photoId;
    public int price;
    public float rating;
    public List<String> colors = new ArrayList<>();
    public int heightInInches, widthInInches, deepInInches;


}
