package ca.tash.catalogueapp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import ca.tash.catalogueapp.fragment.CatalogueFragment;
import ca.tash.catalogueapp.fragment.WishListFragment;

public class StashActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stash);
        if (savedInstanceState == null) {

            CatalogueFragment catalogueFragment = new CatalogueFragment();
            getFragmentManager().beginTransaction().replace(R.id.containerCatalogue, catalogueFragment).commit();

            WishListFragment wishListFragment = new WishListFragment();
            getFragmentManager().beginTransaction().replace(R.id.containerWishlist, wishListFragment).commit();
        }
    }


}
