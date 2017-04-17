package ca.tash.catalogueapp.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

import ca.tash.catalogueapp.R;

/**
 * Created by dounaka on 2017-04-14.
 */

public class ColorListView extends HorizontalScrollView {

    private ViewGroup mContainerColorListView;

    public ColorListView(Context ctx) {
        super(ctx);
        initView();
    }

    public ColorListView(Context ctx, ViewGroup parent) {
        super(ctx);
        initView();
    }

    int mColorSize, mMarginSize;

    public ColorListView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.ColorListView, 0, 0);
        try {
            mColorSize = a.getDimensionPixelSize(R.styleable.ColorListView_imgSize, 0);
            mMarginSize = a.getDimensionPixelSize(R.styleable.ColorListView_imgMargin, 0);
        } finally {
            a.recycle();
        }
        initView();
    }

    public ColorListView(Context context, AttributeSet attrs, int defstyle) {
        super(context, attrs, defstyle);
        initView();
    }


    private void initView() {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.view_color_container, this, true);
        mContainerColorListView = (ViewGroup) findViewById(R.id.containerColorList);
    }


    public int getColorView() {
        return R.layout.view_color;
    }


    public void show(List<String> colors) {
        for (String color : colors) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.view_color, null);
            ImageView img = (ImageView) view.findViewById(R.id.imgColor);
            GradientDrawable drawable = (GradientDrawable) img.getBackground();
            drawable.setColor(Color.parseColor(color));


            ImageView imgColor = new ImageView(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(mColorSize, mColorSize);
            layoutParams.setMargins(0, 0, mMarginSize, 0);
            imgColor.setLayoutParams(layoutParams);
            imgColor.setBackgroundResource(R.drawable.bg_round_border);
            GradientDrawable drawable3 = (GradientDrawable) imgColor.getBackground();
            drawable3.setColor(Color.parseColor(color));

            mContainerColorListView.addView(imgColor);
        }


    }
}
