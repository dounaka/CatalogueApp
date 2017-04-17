package ca.tash.catalogueapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import ca.tash.catalogueapp.fragment.CatalogueFragment;
import ca.tash.catalogueapp.fragment.WishListFragment;
import ca.tash.catalogueapp.ui.BagThumbnailView;
import ca.tash.catalogueapp.ui.BagWishlistItemView;

/*


    hypothese
    General / les buttons sont au style de material Design, character en majuscule

    Ecran 1-1 / action bar, conforme a Android, le nom du bag n'est pas centre, mais aligne a gauche




 */
public class StashActivity extends Activity implements CatalogueFragment.Listener, WishListFragment.Listener {

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
    public void onBagClick(BagThumbnailView bagView) {
        BagDetailActivity.launch(this, bagView.getEntity().id, bagView.getBagImageView());
    }

    @Override
    public void onBagClick(BagWishlistItemView view) {
        BagDetailActivity.launch(this, view.getEntity().id, view.getBagImageView());
    }


    public static class CheckoutDialogFragment extends DialogFragment {

        public static CheckoutDialogFragment newInstance() {
            return new CheckoutDialogFragment();
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setIcon(R.drawable.ic_shopping_basket_black_24dp)
                    .setTitle(R.string.proceed_checkout_dialog)
                    .setPositiveButton(R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    // ((FragmentAlertDialog)getActivity()).doPositiveClick();
                                }
                            }
                    )
                    .setNegativeButton(R.string.cancel,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    // ((FragmentAlertDialog)getActivity()).doNegativeClick();
                                }
                            }
                    )
                    .create();
        }
    }

    @Override
    public void onCheckout() {
        CheckoutDialogFragment.newInstance().show(getFragmentManager(), "checkout.dialog");
    }
}
