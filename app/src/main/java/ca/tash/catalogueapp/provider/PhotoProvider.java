package ca.tash.catalogueapp.provider;

import ca.tash.catalogueapp.R;

/**
 * Created by dounaka on 2017-04-13.
 * mock a photo resource provider
 */

public class PhotoProvider {

    public static int getPhotoRes(long bagid) {

        if (bagid == 12001)
            return R.drawable.carrierbag;

        if (bagid == 12002)
            return R.drawable.travol;

        if (bagid == 12003)
            return R.drawable.urbanito;

        return -1;
    }
}
