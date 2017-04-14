package ca.tash.catalogueapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.tash.catalogueapp.R;
import ca.tash.catalogueapp.provider.DataStore;
import ca.tash.catalogueapp.store.Bag;
import ca.tash.catalogueapp.ui.BagDetailView;

/**
 * Created by dounaka on 2017-04-14.
 */

public class BagDetailFragment extends Fragment {
    private long mBagId = -1;
    private Bag mBag;

    private BagDetailView mBagDetailView;

    public static BagDetailFragment newInstance(long bagId) {
        BagDetailFragment fragment = new BagDetailFragment();
        Bundle args = new Bundle();
        args.putLong(Bag.PARAM_BAG_ID, bagId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mBagId = getArguments().getLong(Bag.PARAM_BAG_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_bag_detail, container, false);
        mBagDetailView = (BagDetailView) mainView.findViewById(R.id.viewBagDetail);
        return mainView;
    }


    @Override
    public void onResume() {
        super.onResume();
        mBag = DataStore.getInstance().get(Bag.class, mBagId);
        mBagDetailView.show(mBag);

    }


}
