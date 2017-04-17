package ca.tash.catalogueapp.store;

import java.util.ArrayList;
import java.util.List;

public class WishList extends Entity {

    public static final String EVENT_WISHLIST_UPDATE = "event.wishlist.update";

    private List<Long> mBags = new ArrayList<>();

    public long updateTime = 0L;

    public WishList() {
        super();
    }

    public boolean containsBag(long bagId) {
        return mBags.contains(bagId);
    }

    public void addBag(long bagid) {
        mBags.add(bagid);
        updateTime = System.currentTimeMillis();
    }

    public void removeBag(long bagid) {
        mBags.remove(bagid);
        updateTime = System.currentTimeMillis();
    }

}
