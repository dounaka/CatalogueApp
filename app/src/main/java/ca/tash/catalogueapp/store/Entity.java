package ca.tash.catalogueapp.store;

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
