package ca.tash.catalogueapp.fragment;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ca.tash.catalogueapp.R;
import ca.tash.catalogueapp.provider.DataStore;
import ca.tash.catalogueapp.store.Bag;
import ca.tash.catalogueapp.store.WishList;
import ca.tash.catalogueapp.ui.BagWishlistItemView;

public class WishListFragment extends Fragment implements View.OnClickListener {
    private LinearLayout mLinearLayoutWishlist, mLinearLayoutTotal;
    private TextView mTxtTopTotal, mTxtBottomTotal, mTxtEmptyWishlist;
    private Button mBtnCheckout;

    public interface Listener {
        void onBagClick(BagWishlistItemView view);

        void onCheckout();
    }

    private Listener mListener;

    public WishListFragment() {
    }

    // refresh the view only if there is a data changed
    private long wishlistVersion = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_wish_list, container, false);
        mLinearLayoutTotal = (LinearLayout) fragmentView.findViewById(R.id.linearLayoutTotal);
        mLinearLayoutWishlist = (LinearLayout) fragmentView.findViewById(R.id.linearLayoutWishlist);
        mTxtTopTotal = (TextView) fragmentView.findViewById(R.id.txtTopTotal);
        mTxtEmptyWishlist = (TextView) fragmentView.findViewById(R.id.txtEmptyWishlist);
        mTxtBottomTotal = (TextView) fragmentView.findViewById(R.id.txtBottomTotal);
        mBtnCheckout = (Button) fragmentView.findViewById(R.id.btnCheckout);
        mBtnCheckout.setOnClickListener(this);
        return fragmentView;
    }

    @Override
    public void onResume() {
        super.onResume();
        new AsyncTask<Void, Void, List<Bag>>() {
            @Override
            protected List<Bag> doInBackground(Void... params) {
                final WishList userWishList = DataStore.getInstance().getUserWishList();
                if (userWishList.updateTime == wishlistVersion)
                    return null;
                final ArrayList<Bag> bags = new ArrayList<>();
                for (Bag bag : DataStore.getInstance().getAll(Bag.class))
                    if (userWishList.containsBag(bag.id))
                        bags.add(bag);
                wishlistVersion = userWishList.updateTime;
                return bags;
            }

            @Override
            protected void onPostExecute(final List<Bag> bagList) {
                if (bagList == null)
                    return; // means no change for the view
                clearWishList();
                if (getView() != null)
                    getView().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            int total = 0;
                            for (Bag bag : bagList) {
                                BagWishlistItemView itemView = new BagWishlistItemView(getActivity());
                                itemView.setOnClickListener(WishListFragment.this);
                                itemView.show(bag);
                                total += bag.price;
                                mLinearLayoutWishlist.addView(itemView);
                                mLinearLayoutWishlist.requestLayout();
                            }
                            if (total != 0) {
                                mTxtBottomTotal.setText(getResources().getString(R.string.price_no_space, total));
                                mLinearLayoutTotal.setVisibility(View.VISIBLE);
                            } else
                                mTxtEmptyWishlist.setVisibility(View.VISIBLE);
                            mTxtTopTotal.setText(getResources().getString(R.string.total, total));
                        }
                    }, 200);

            }
        }.execute();
    }

    @Override
    public void onClick(View v) {
        if (v == mBtnCheckout) {
            mListener.onCheckout();

        } else {
            BagWishlistItemView itemView = (BagWishlistItemView) v;
            mListener.onBagClick(itemView);
        }
    }


    public void clearWishList() {
        mTxtEmptyWishlist.setVisibility(View.GONE);
        mLinearLayoutTotal.setVisibility(View.GONE);
        mLinearLayoutWishlist.removeAllViews();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Listener) {
            mListener = (Listener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement WishListFragment.Listener");
        }
        initBroadcast();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        mBroadcastManager.unregisterReceiver(mBroadCastReceiver);
    }

    /*

        ##### FYI ######
        Broadcasting the event is used to clean the list
        Offer a smoother UX experience
        The list is clean before running the Fragment.onResume();
        Doesnt make a bad effect where list appears and disappears quickly.

     */
    private LocalBroadcastManager mBroadcastManager;
    private BroadcastReceiver mBroadCastReceiver;

    private void initBroadcast() {
        mBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WishList.EVENT_WISHLIST_UPDATE);
        mBroadCastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(WishList.EVENT_WISHLIST_UPDATE))
                    clearWishList();
            }
        };
        mBroadcastManager.registerReceiver(mBroadCastReceiver, intentFilter);
    }

}
