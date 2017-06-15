package cool.oneshot.rxjavaapp.rx.view;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.util.AttributeSet;

import com.jakewharton.rxbinding2.view.RxView;

/**
 * Created by brettn on 4/21/17.
 */
public class RxCheckedTextView extends AppCompatCheckedTextView {

    public RxCheckedTextView(Context context) {
        super(context);
    }

    public RxCheckedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RxCheckedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        RxView.clicks(this).subscribe(clicked -> toggle());
    }
}
