package ca.tash.catalogueapp.fragment;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ca.tash.catalogueapp.R;
import ca.tash.catalogueapp.provider.DataStore;
import ca.tash.catalogueapp.store.Bag;
import ca.tash.catalogueapp.ui.EntityAdapter;
import ca.tash.catalogueapp.ui.BagThumbnailView;
import ca.tash.catalogueapp.ui.EntityView;

/**
 * Created by dounaka on 2017-04-13.
 */

public class CatalogueFragment extends Fragment implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private EntityAdapter<Bag> mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    public interface Listener {
        void onBagDetail(BagThumbnailView view);
    }

    private Listener mListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_catalogue, container, false);
        mRecyclerView = (RecyclerView) mainView.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new EntityAdapter<Bag>() {
            @Override
            protected EntityView createEntityView(Context ctx) {
                BagThumbnailView view = new BagThumbnailView(ctx);
                view.setOnClickListener(CatalogueFragment.this);
                return view;
            }
        };
        mRecyclerView.setAdapter(mAdapter);
        return mainView;
    }


    @Override
    public void onResume() {
        super.onResume();
        mAdapter.setEntities(DataStore.getInstance().getAll(Bag.class));
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View v) {
        mListener.onBagDetail(((BagThumbnailView) v));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Listener) {
            mListener = (Listener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement CatalogueFragment.Listener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
