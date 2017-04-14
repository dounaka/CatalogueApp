package ca.tash.catalogueapp.provider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ca.tash.catalogueapp.store.Bag;
import ca.tash.catalogueapp.store.Entity;

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


    public synchronized <T extends Entity> List<T> getAll(Class<T> clazz) {
        ArrayList<T> clazzEntities = new ArrayList<>();
        for (Entity entity : mStoreData.values()) {
            if (entity.getClass().getSimpleName().equals(clazz.getSimpleName()))
                clazzEntities.add((T) entity);
        }
        return clazzEntities;
    }


    public synchronized <T extends Entity> T get(Class<T> clazz, long eid) {
        final String key = Entity.getUid(clazz, eid);
        return (T) mStoreData.get(key);

    }

    public int countAll() {
        return mStoreData.size();
    }


    public static void initData() {
        if (DataStore.getInstance().countAll() > 0) return;
        DataStore dataStore = DataStore.getInstance();
        Bag bag = new Bag();
        // Carrier bag
        bag.id = 12001l;
        bag.name = "Carrier Bag";
        bag.shortDesc = "Navy Blue and Brown soft leather";
        bag.longDesc = "Our Mountain Briefcase is the ultimate shape changing, go to work, bum around town, travel the world, do it every day carry.";
        bag.colors.add("#1E293A");
        bag.colors.add("#A36348");
        dataStore.put(bag);


        // Ubarnito
        bag = new Bag();
        bag.id = 12002l;
        bag.name = "Urbanito";
        bag.shortDesc = "Biker basics";
        bag.longDesc = "Our Urbanito is the ultimate shape changing, go to work, bum around town, travel the world, do it every day carry.";
        bag.colors.add("#1E293A");
        bag.colors.add("#CBC340");
        bag.colors.add("#F3A03A");
        dataStore.put(bag);

        // Travol
        bag = new Bag();
        bag.id = 12003l;
        bag.name = "Travol";
        bag.shortDesc = "Soft leather bag";
        bag.longDesc = "Our Travol is the ultimate shape changing, go to work, bum around town, travel the world, do it every day carry.";
        // no colors so not available
        dataStore.put(bag);

    }
}
