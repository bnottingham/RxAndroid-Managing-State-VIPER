package cool.oneshot.rxjavaapp.feature.photolist.presentation.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import cool.oneshot.rxjavaapp.R;
import cool.oneshot.rxjavaapp.feature.photolist.presentation.view.fragment.PhotoListFragment;
import cool.oneshot.rxjavaapp.presentation.view.activity.BaseActivity;

/**
 * Created by brettn on 4/27/17.
 */
public class PhotoListActivity extends BaseActivity {
    private static final String TAG = PhotoListActivity.class.getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.main_fragment_container);
        if (fragment == null) {
            fragment = new PhotoListFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_fragment_container, fragment)
                    .commit();
        }
    }
}
