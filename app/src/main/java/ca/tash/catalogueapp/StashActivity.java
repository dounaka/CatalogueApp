package ca.tash.catalogueapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import ca.tash.catalogueapp.fragment.CatalogueFragment;
import ca.tash.catalogueapp.fragment.WishListFragment;
import ca.tash.catalogueapp.store.Bag;

/*
    TODO L’application supporte le mode portrait seulement.
    TODO Les versions d’Android supportées sont 5 et +.
    TODO Les données peuvent être générées aléatoirement à même le code ou chargées à partir d’un fichier.
    TODO Les données n’ont pas besoin d’être persistées sur le disque.


    Écran 1
    TODO Écran principal de l’application. Contient le catalogue des produits et une liste des objets désirés.
    TODO L’écran défile verticalement.
    TODO Le catalogue des produits est une liste avec défilement horizontal.
    TODO Lorsqu’on clique sur un produit du catalogue, on arrive sur l’écran de détail d’un produit (1-1).
    TODO La liste des objets désirés défile verticalement. La hauteur de la liste est variable selon le nombre d’items ajoutés dans la liste.
    TODO Le bouton « proceed to checkout » affiche un dialogue tel qu’illustré dans l’écran 1-4. Le bouton est sous la liste des produits désirés.


    Écran 1-1
    TODO Détail d’un produit lorsqu’on appuie sur un élément du catalogue sur l’écran principal.
    TODO L’écran défile verticalement.
    TODO Lorsqu’on appuie sur le bouton rouge, on revient sur l’écran principal et le produit est ajouté dans la liste des produits désirés.
    TODO Les étoiles représentent l’évaluation que l’utilisateur donne au produit.  On peut spécifier une note en cliquant sur l’étoile de notre choix. Ce n’est pas nécessaire de persister cette information.

    Écran 1-2
    TODO Détail d’un produit lorsqu’on appuie sur un élément de la liste des produits désirés de l’écran principal.
    TODO Variante de l’écran 1-1 avec un bouton noir au lieu du rouge.
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
    public void onBagDetail(long bagId) {
        Intent bagDetailIntent = new Intent(this, BagDetailActivity.class);
        bagDetailIntent.putExtra(Bag.PARAM_BAG_ID, bagId);
        startActivity(bagDetailIntent);
    }
}
