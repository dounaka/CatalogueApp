package ca.tash.catalogueapp.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ca.tash.catalogueapp.store.Entity;

/**
 * Created by dounaka on 2017-04-13.
 * Generic adapter for entities
 * bind with an Entity view
 */

public abstract class EntityAdapter<E extends Entity> extends RecyclerView.Adapter<EntityAdapter.ViewHolder> {


    private ArrayList<E> entities = new ArrayList<>();

    protected abstract EntityView<E> createEntityView(Context ctx);

    public EntityAdapter() {
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        EntityView entityView;
        public ViewHolder(EntityView v) {
            super(v);
            entityView = v;
        }
    }
    @Override
    public EntityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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


    public void setEntities(List<E> entties) {
        this.entities.clear();
        this.entities.addAll(entties);
    }



}



