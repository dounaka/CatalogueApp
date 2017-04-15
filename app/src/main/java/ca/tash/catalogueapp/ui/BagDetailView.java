package ca.tash.catalogueapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

import ca.tash.catalogueapp.R;
import ca.tash.catalogueapp.provider.DataStore;
import ca.tash.catalogueapp.provider.PhotoProvider;
import ca.tash.catalogueapp.store.Bag;

/**
 * Created by dounaka on 2017-04-13.
 * very simple fragment
 */

public class BagDetailView extends EntityView<Bag> implements View.OnClickListener {
    private ImageView mImgView;
    private TextView mTxtPrice, mTxtDesc;
    private ColorListView mColorListView;
    private DimensionView mDimensionView;
    private RatingBar mRatingBar;
    private ViewFlipper mViewFlipper;
    private Button mBtnAddWishlist, mBtnRemoveWishlist;


    public BagDetailView(Context ctx) {
        super(ctx);
    }

    public BagDetailView(Context ctx, ViewGroup parent) {
        super(ctx, parent);
    }

    public BagDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BagDetailView(Context context, AttributeSet attrs, int defstyle) {
        super(context, attrs, defstyle);
    }


    @Override
    public int getViewResourceId() {
        return R.layout.view_bag_detail;
    }

    @Override
    public void bindControls(Context ctx) {
        mImgView = (ImageView) this.findViewById(R.id.imgBagDetail);
        mTxtPrice = (TextView) this.findViewById(R.id.txtBagPrice);
        mTxtDesc = (TextView) this.findViewById(R.id.txtBagDesc);
        mColorListView = (ColorListView) this.findViewById(R.id.viewBagColorList);
        mDimensionView = (DimensionView) this.findViewById(R.id.viewBagDimension);
        mViewFlipper = (ViewFlipper) this.findViewById(R.id.viewflipperColor);
        mRatingBar = (RatingBar) this.findViewById(R.id.ratingBarBag);
        mBtnAddWishlist = (Button) this.findViewById(R.id.buttonAddWishlist);
        mBtnRemoveWishlist = (Button) this.findViewById(R.id.buttonRemoveWishlist);
        mBtnAddWishlist.setOnClickListener(this);
        mBtnRemoveWishlist.setOnClickListener(this);
    }

    @Override
    protected void showEntity(final Bag bag) {
        mImgView.setImageResource(PhotoProvider.getPhotoRes(bag.photoId));
        mTxtDesc.setText(bag.longDesc);
        mTxtPrice.setText("$ " + bag.price);
        if (bag.colors == null || bag.colors.size() == 0)
            mViewFlipper.setDisplayedChild(1);
        else
            mColorListView.show(bag.colors);
        mDimensionView.show(bag.heightInInches, bag.widthInInches, bag.deepInInches);
        mRatingBar.setRating(bag.rating);
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                saveRating(rating);
            }
        });

        findViewById(R.id.containerBagInfo).setVisibility(View.VISIBLE);
    }

    public void showAddToWishlist() {
        mBtnAddWishlist.setVisibility(View.VISIBLE);
    }

    public void showRemoveToWishlist() {
        mBtnRemoveWishlist.setVisibility(View.VISIBLE);
    }

    private void saveRating(float newRating) {
        entity.rating = newRating;
        DataStore.getInstance().put(entity);
    }

    @Override
    public void onClick(View v) {
        if (v == mBtnAddWishlist) {
            mBtnAddWishlist.setEnabled(false);
            listener.onAdd(entity.id);
        } else if (v == mBtnRemoveWishlist) {
            mBtnRemoveWishlist.setEnabled(false);
            listener.onRemove(entity.id);
        }
    }

    public Listener listener;

    public interface Listener {
        void onAdd(long bagId);

        void onRemove(long bagId);
    }
}

