package ca.tash.catalogueapp.ui;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ca.tash.catalogueapp.R;
import ca.tash.catalogueapp.provider.PhotoProvider;
import ca.tash.catalogueapp.store.Bag;

/**
 * Created by dounaka on 2017-04-13.
 * display info of bag with the short desc
 */

public class BagWishlistItemView extends EntityView<Bag> {
    private ImageView mImgBag;
    private TextView mtxtName, mTxtShortDesc, mTxtPrice, mTxtOutOfStock;
    private ColorListView mColorListView;

    public BagWishlistItemView(Context ctx) {
        super(ctx);
    }

    @Override
    public int getViewResourceId() {
        return R.layout.view_bag_wishlist;
    }

    @Override
    public void bindControls(Context ctx) {
        mImgBag = (ImageView) findViewById(R.id.imgBagWishlist);
        mtxtName = (TextView) findViewById(R.id.txtBagName);
        mTxtPrice = (TextView) findViewById(R.id.txtBagPrice);
        mTxtShortDesc = (TextView) findViewById(R.id.txtBagShortDesc);
        mTxtOutOfStock = (TextView) findViewById(R.id.txtOutOfStock);
        mColorListView = (ColorListView) findViewById(R.id.viewColorList);
    }

    @Override
    protected void showEntity(Bag bag) {
        mtxtName.setText(bag.name);
        mTxtPrice.setText(getResources().getString(R.string.price, bag.price));
        mTxtShortDesc.setText(bag.shortDesc);
        if (bag.colors != null && bag.colors.size() > 0) {
            mColorListView.show(bag.colors);
            mColorListView.setVisibility(View.VISIBLE);
            mTxtOutOfStock.setVisibility(View.GONE);
        } else {
            mColorListView.setVisibility(View.GONE);
            mTxtOutOfStock.setVisibility(View.VISIBLE);
        }

        mImgBag.setImageResource(PhotoProvider.getPhotoRes(bag.photoId));
    }

    public ImageView getBagImageView() {
        return mImgBag;
    }
}

