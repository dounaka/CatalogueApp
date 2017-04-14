package ca.tash.catalogueapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import ca.tash.catalogueapp.R;
import ca.tash.catalogueapp.provider.PhotoProvider;
import ca.tash.catalogueapp.store.Bag;

/**
 * Created by dounaka on 2017-04-13.
 * very simple fragment
 */

public class BagDetailView extends EntityView<Bag> {
    private ImageView mImgView;
    private TextView mTxtPrice, mTxtDesc;
    private ColorListView mColorListView;
    private DimensionView mDimensionView;
    private RatingBar mRatingBar;


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
    protected void showEntity(final Bag bag) {
        mImgView.setImageResource(PhotoProvider.getPhotoRes(bag.photoId));
        mTxtDesc.setText(bag.longDesc);
        mTxtPrice.setText("$" + bag.price);
        mColorListView.show(bag.colors);
        mDimensionView.show(bag.heightInInches, bag.widthInInches, bag.deepInInches);
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
    }
}

