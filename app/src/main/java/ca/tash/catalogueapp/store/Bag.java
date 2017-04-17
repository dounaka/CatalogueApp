package ca.tash.catalogueapp.store;

import java.util.ArrayList;
import java.util.List;

public class Bag extends Entity {

    public String name, shortDesc, longDesc;
    public long photoId;
    public int price;
    public float rating;
    public List<String> colors = new ArrayList<>();
    public int heightInInches, widthInInches, deepInInches;


}
