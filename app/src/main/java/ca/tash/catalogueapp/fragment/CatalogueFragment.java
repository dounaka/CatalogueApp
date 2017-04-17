package ca.tash.catalogueapp.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.tash.catalogueapp.R;
import ca.tash.catalogueapp.provider.DataStore;
import ca.tash.catalogueapp.store.Bag;
import ca.tash.catalogueapp.ui.EntityAdapter;
import ca.tash.catalogueapp.ui.BagThumbnailView;
import ca.tash.catalogueapp.ui.EntityView;

public class CatalogueFragment extends Fragment implements View.OnClickListener {

    private EntityAdapter<Bag> mAdapterBagList;

    public interface Listener {
        void onBagClick(BagThumbnailView view);
    }

    private Listener mListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_catalogue, container, false);
        RecyclerView recyclerView = (RecyclerView) mainView.findViewById(R.id.recyclerViewCatalogue);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        mAdapterBagList = new EntityAdapter<Bag>() {
            @Override
            protected EntityView<Bag> createEntityView(Context ctx) {
                BagThumbnailView view = new BagThumbnailView(ctx);
                view.setOnClickListener(CatalogueFragment.this);
                return view;
            }
        };
        recyclerView.setAdapter(mAdapterBagList);
        return mainView;
    }


    @Override
    public void onResume() {
        super.onResume();
        mAdapterBagList.setEntities(DataStore.getInstance().getAll(Bag.class));
        mAdapterBagList.notifyDataSetChanged();
    }


    @Override
    public void onClick(View v) {
        mListener.onBagClick(((BagThumbnailView) v));
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
