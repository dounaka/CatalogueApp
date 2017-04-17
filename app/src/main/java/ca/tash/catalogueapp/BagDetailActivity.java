package ca.tash.catalogueapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import ca.tash.catalogueapp.fragment.BagDetailFragment;
import ca.tash.catalogueapp.provider.DataStore;
import ca.tash.catalogueapp.store.Bag;

public class BagDetailActivity extends Activity {


    private long mBagId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bag_detail);
        mBagId = getIntent().getLongExtra(BagDetailFragment.PARAM_BAG_ID, -1);
        if (mBagId == -1)
            throw new RuntimeException(toString() + " must receive bag id as parameter");
        if (savedInstanceState == null) {
            BagDetailFragment bagDetailFragment = BagDetailFragment.newInstance(mBagId);
            getFragmentManager().beginTransaction().replace(R.id.containerFragmentBagDetail, bagDetailFragment).commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        final String title = DataStore.getInstance().get(Bag.class, mBagId).name;
        if (getActionBar() != null && title != null)
            getActionBar().setTitle(title);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void launch(Activity activity, long bagid, View transitionView) {
        Intent intent = getLaunchIntent(activity, bagid);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                    activity, transitionView, transitionView.getTransitionName());
            activity.startActivity(intent, options.toBundle());
        } else {
            activity.startActivity(intent);
        }
    }

    public static Intent getLaunchIntent(Context context, long bagid) {
        Intent intent = new Intent(context, BagDetailActivity.class);
        intent.putExtra(BagDetailFragment.PARAM_BAG_ID, bagid);
        return intent;
    }
}
