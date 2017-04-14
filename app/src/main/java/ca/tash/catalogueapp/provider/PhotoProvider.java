package ca.tash.catalogueapp.provider;

import ca.tash.catalogueapp.R;

/**
 * Created by dounaka on 2017-04-13.
 * mock a photo resource provider
 */

public class PhotoProvider {

    public static int getPhotoRes(long bagid) {

        if (bagid == 101)
            return R.drawable.carrierbag;

        if (bagid == 102)
            return R.drawable.travol;

        if (bagid == 103)
            return R.drawable.urbanito;

        return -1;
    }
}
