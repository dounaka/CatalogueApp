package ca.tash.catalogueapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

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

    public ColorListView(Context context, AttributeSet attrs) {
        super(context, attrs);
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


    public void show(List<String> colors) {
        for (String color : colors) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.view_color, null);
            mContainerColorListView.addView(view);
        }


    }
}
