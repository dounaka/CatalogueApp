package ca.tash.catalogueapp.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;

import ca.tash.catalogueapp.R;
import ca.tash.catalogueapp.store.Entity;

/**
 * Created by dounaka on 2017-04-13.
 */

public abstract class BagAdapter<E extends Entity> extends RecyclerView.Adapter<BagAdapter.ViewHolder> {


    public ArrayList<E> entities = new ArrayList<>();

    protected abstract EntityView<E> createEntityView(Context ctx);

    public BagAdapter() {
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        EntityView entityView;
        public ViewHolder(EntityView v) {
            super(v);
            entityView = v;
        }
    }
    @Override
    public BagAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder vh = new ViewHolder(createEntityView(parent.getContext()));
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.entityView.show(entities.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return entities.size();
    }
}



