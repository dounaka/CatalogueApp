package ca.tash.catalogueapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import ca.tash.catalogueapp.R;

/**
 * Created by dounaka on 2017-04-14.
 */

public class DimensionView extends FrameLayout {


    public DimensionView(Context ctx) {
        super(ctx);
        initView();
    }

    public DimensionView(Context ctx, ViewGroup parent) {
        super(ctx);
        initView();
    }

    public DimensionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DimensionView(Context context, AttributeSet attrs, int defstyle) {
        super(context, attrs, defstyle);
        initView();
    }

    private void initView() {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.view_dimensions, this, true);
        mTxtHeight = (TextView) findViewById(R.id.txtHeight);
        mTxtWidth = (TextView) findViewById(R.id.txtWidth);
        mTxtDeep = (TextView) findViewById(R.id.txtDeep);
    }

    private TextView mTxtHeight, mTxtWidth, mTxtDeep;

    public void show(int height, int width, int deep) {
        mTxtHeight.setText(getResources().getString(R.string.height_cm_inche, convertToCm(height), height));
        mTxtWidth.setText(getResources().getString(R.string.width_cm_inche, convertToCm(width), width));
        mTxtDeep.setText(getResources().getString(R.string.deep_cm_inche, convertToCm(deep), deep));
    }

    private int convertToCm(int sizeInInches) {
        return (int) (sizeInInches * 2.54);
    }
}
