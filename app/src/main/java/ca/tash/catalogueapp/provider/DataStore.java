package ca.tash.catalogueapp.provider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ca.tash.catalogueapp.store.Bag;
import ca.tash.catalogueapp.store.Entity;
import ca.tash.catalogueapp.store.WishList;

/**
 * Created by dounaka on 2017-04-13.
 * <p>
 * Volatile database
 * allows to
 */

public class DataStore implements Serializable {
    private static final Map<String, Entity> mStoreData = new TreeMap<>();
    private static DataStore SINGLE_INSTANCE = null;

    private DataStore() {
    }

    public static synchronized DataStore getInstance() {
        if (SINGLE_INSTANCE == null) {
            SINGLE_INSTANCE = new DataStore();
            initData();
        }
        return SINGLE_INSTANCE;
    }

    public synchronized void put(Entity entity) {
        mStoreData.put(entity.getUid(), entity);
    }

    public synchronized void remove(String uid) {
        mStoreData.remove(uid);
    }


    private static final long DEFAULT_WISHLIST_ID = 13000;

    public synchronized WishList getUserWishList() {
        WishList wishList = get(WishList.class, DEFAULT_WISHLIST_ID);
        if (wishList == null) {
            wishList = new WishList();
            wishList.id = DEFAULT_WISHLIST_ID;
            put(wishList);
        }
        return wishList;
    }

    public synchronized <T extends Entity> T get(Class<T> clazz, long eid) {
        final String key = Entity.getUid(clazz, eid);
        return (T) mStoreData.get(key);
    }

    public synchronized <T extends Entity> List<T> getAll(Class<T> clazz) {
        ArrayList<T> clazzEntities = new ArrayList<>();
        for (Entity entity : mStoreData.values()) {
            if (entity.getClass().getSimpleName().equals(clazz.getSimpleName())) {
                clazzEntities.add((T) entity);
            }
        }
        return clazzEntities;
    }

    public int countAll() {
        return mStoreData.size();
    }


    public static void initData() {
        DataStoreInitializer.initData();
    }
}
