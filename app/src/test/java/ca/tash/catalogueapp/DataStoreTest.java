package ca.tash.catalogueapp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ca.tash.catalogueapp.provider.DataStore;
import ca.tash.catalogueapp.store.Bag;
import ca.tash.catalogueapp.store.WishList;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DataStoreTest {


    @Before
    public void initDataStore() {

    }

    @Test
    public void testInit() throws Exception {
        Assert.assertTrue(DataStore.getInstance().countAll() == 3);
    }

    @Test
    public void testGetEntity() throws Exception {
        Bag travol = DataStore.getInstance().get(Bag.class, 12002);
        travol.rating = 4;
        Assert.assertTrue(DataStore.getInstance().get(Bag.class, 12002).rating == 4);
    }


    @Test
    public void getGetAll() throws Exception {

        Assert.assertTrue(DataStore.getInstance().getAll(Bag.class).size() == 3);
    }

    @Test
    public void testPutWishList() throws Exception {
        WishList whishList = new WishList();
        DataStore.getInstance().put(whishList);
        Assert.assertTrue(DataStore.getInstance().get(WishList.class, WishList.DEFAULT_WISHLIST_ID) != null);

        DataStore.getInstance().remove(whishList.getUid());
        Assert.assertTrue(DataStore.getInstance().get(WishList.class, WishList.DEFAULT_WISHLIST_ID) == null);
    }
}
