package cool.oneshot.rxjavaapp.constants;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by brettn on 6/1/17.
 */
public class PhotoApiConstants {

    @IntDef({IMAGE_SIZE_CROPPED_70, IMAGE_SIZE_CROPPED_140, IMAGE_SIZE_CROPPED_280, IMAGE_SIZE_CROPPED_100, IMAGE_SIZE_CROPPED_200, IMAGE_SIZE_CROPPED_440, IMAGE_SIZE_CROPPED_600})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ImageSizeCroppedIds {
    }

    public static final int IMAGE_SIZE_CROPPED_70 = 1;
    public static final int IMAGE_SIZE_CROPPED_140 = 2;
    public static final int IMAGE_SIZE_CROPPED_280 = 3;
    public static final int IMAGE_SIZE_CROPPED_100 = 100;
    public static final int IMAGE_SIZE_CROPPED_200 = 200;
    public static final int IMAGE_SIZE_CROPPED_440 = 440;
    public static final int IMAGE_SIZE_CROPPED_600 = 600;

}
