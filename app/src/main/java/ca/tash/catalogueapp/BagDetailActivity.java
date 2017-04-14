package ca.tash.catalogueapp;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        mBagId = getIntent().getLongExtra(Bag.PARAM_BAG_ID, -1);

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
}
