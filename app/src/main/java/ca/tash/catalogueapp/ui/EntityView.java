package ca.tash.catalogueapp.ui;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import ca.tash.catalogueapp.store.Entity;

/**
 * Created by dounaka on 2017-04-13.
 */

public abstract class EntityView<E extends Entity> extends FrameLayout {

    protected E entity;

    public EntityView(Context ctx) {
        super(ctx);
        initView(ctx, null);
    }

    public EntityView(Context ctx, ViewGroup parent) {
        super(ctx);
        initView(ctx, parent);
    }

    public EntityView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, null);
    }

    public EntityView(Context context, AttributeSet attrs, int defstyle) {
        super(context, attrs, defstyle);
        initView(context, null);
    }

    public abstract int getViewResourceId();

    public void show(E e) {
        this.entity = e;
        showEntity(this.entity);
    }

    public E getEntity() {return this.entity;}

    protected abstract void showEntity(E entity);

    public abstract void bindControls(Context ctx);

    private void initView(final Context ctx, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(getViewResourceId(), this, true);
        bindControls(ctx);
    }


}
