package ca.tash.catalogueapp.store;

/**
 * Created by dounaka on 2017-04-13.
 */

public class Entity {
    private static final String SEP = "_";
    public long id;

    public String getUid() {
        return this.getClass().getSimpleName() + SEP + id;
    }

    public static String getUid(Class clazz, long id) {
        return clazz.getSimpleName() + SEP + id;
    }


}
