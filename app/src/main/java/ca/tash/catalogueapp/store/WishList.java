package ca.tash.catalogueapp.store;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dounaka on 2017-04-13.
 */

public class WishList extends Entity {

    public List<Bag> bags = new ArrayList<>();

    public static final long DEFAULT_WISHLIST_ID = 13000;

    public WishList() {
        super();
        this.id = DEFAULT_WISHLIST_ID;
    }
}
