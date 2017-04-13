package ca.tash.catalogueapp.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.List;

import ca.tash.catalogueapp.R;
import ca.tash.catalogueapp.provider.DataStore;
import ca.tash.catalogueapp.store.Bag;
import ca.tash.catalogueapp.ui.BagAdapter;
import ca.tash.catalogueapp.ui.BagThumbnailView;
import ca.tash.catalogueapp.ui.EntityView;

/**
 * Created by dounaka on 2017-04-13.
 */

public class CatalogueFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private BagAdapter<Bag> mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_catalogue, container, false);
        mRecyclerView = (RecyclerView) mainView.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        return mainView;
    }


    @Override
    public void onResume() {
        super.onResume();
        mAdapter = new BagAdapter<Bag>() {
            @Override
            protected EntityView createEntityView(Context ctx) {
                return new BagThumbnailView(ctx);
            }
        };
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.entities.addAll(
                DataStore.getInstance().getAll(Bag.class));
        mAdapter.notifyDataSetChanged();
    }


}
