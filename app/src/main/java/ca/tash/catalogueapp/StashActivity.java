package ca.tash.catalogueapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import ca.tash.catalogueapp.fragment.CatalogueFragment;
import ca.tash.catalogueapp.fragment.WishListFragment;
import ca.tash.catalogueapp.store.Bag;
import ca.tash.catalogueapp.ui.BagThumbnailView;

/*

    Écran 1
    TODO Écran principal de l’application. Contient le catalogue des produits et une liste des objets désirés.
    TODO La liste des objets désirés défile verticalement. La hauteur de la liste est variable selon le nombre d’items ajoutés dans la liste.
    TODO Le bouton « proceed to checkout » affiche un dialogue tel qu’illustré dans l’écran 1-4. Le bouton est sous la liste des produits désirés.

    Écran 1-1
    TODO Lorsqu’on appuie sur le bouton rouge, on revient sur l’écran principal et le produit est ajouté dans la liste des produits désirés.
    Écran 1-2
    TODO Lorsqu’on appuie sur le bouton noir, on revient sur l’écran principal et le produit disparaît de la liste des produits désirés.

    TODO change rating bar color red -> gold

    TODO mettre les operations de lecture data dans un AsyncTask

    TODO put bag name in action for bag detail


    ------------------------------------

    hypothese
    General / les buttons sont au style de material Design, character en majuscule

    Ecran 1-1 / action bar, conforme a Android, le nom du bag n'est pas centre, mais aligne a gauche




 */
public class StashActivity extends FragmentActivity implements CatalogueFragment.Listener{

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

    @Override
    public void onBagDetail(BagThumbnailView bagView) {
        BagDetailActivity.launch(this, bagView.getEntity().id, bagView.getBagImageView());
    }




}
