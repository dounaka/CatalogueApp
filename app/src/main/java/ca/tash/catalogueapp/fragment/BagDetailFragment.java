package ca.tash.catalogueapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.tash.catalogueapp.R;
import ca.tash.catalogueapp.provider.DataStore;
import ca.tash.catalogueapp.store.Bag;
import ca.tash.catalogueapp.store.WishList;
import ca.tash.catalogueapp.ui.BagDetailView;

/**
 * Created by dounaka on 2017-04-14.
 */

public class BagDetailFragment extends Fragment implements BagDetailView.Listener{
    private long mBagId = -1;
    private Bag mBag;
    private WishList mWishList;

    private BagDetailView mBagDetailView;

    public static final String PARAM_BAG_ID = "param.bagId";

    public static BagDetailFragment newInstance(long bagId) {
        BagDetailFragment fragment = new BagDetailFragment();
        Bundle args = new Bundle();
        args.putLong(PARAM_BAG_ID, bagId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mBagId = getArguments().getLong(PARAM_BAG_ID);
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
        mWishList = DataStore.getInstance().getUserWishList();
        mBagDetailView.show(mBag);
        mBagDetailView.listener = this;
        if (mWishList.bags.contains(mBag.id))
            mBagDetailView.showRemoveToWishlist();
        else
            mBagDetailView.showAddToWishlist();
    }

    @Override
    public void onAdd(long bagId) {
        mWishList.bags.add(bagId);
        DataStore.getInstance().put(mWishList);
        getActivity().finish();
    }

    @Override
    public void onRemove(long bagId) {
        mWishList.bags.remove(bagId);
        DataStore.getInstance().put(mWishList);
        getActivity().finish();
    }
}
