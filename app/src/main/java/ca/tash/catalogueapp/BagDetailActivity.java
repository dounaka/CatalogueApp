package ca.tash.catalogueapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import ca.tash.catalogueapp.fragment.BagDetailFragment;
import ca.tash.catalogueapp.provider.DataStore;
import ca.tash.catalogueapp.store.Bag;

public class BagDetailActivity extends FragmentActivity {


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
        getActionBar().setTitle(DataStore.getInstance().get(Bag.class, mBagId).name);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void launch(Activity activity, long bagid, View transitionView) {
        Intent intent = getLaunchIntent(activity, bagid);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity, transitionView, transitionView.getTransitionName());
            ActivityCompat.startActivity(activity, intent, options.toBundle());
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
