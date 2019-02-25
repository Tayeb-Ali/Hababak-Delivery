// Generated code from Butter Knife. Do not modify!
package com.hababk.delivery.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hababk.delivery.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddItemActivity_ViewBinding implements Unbinder {
  private AddItemActivity target;

  private View view2131361822;

  @UiThread
  public AddItemActivity_ViewBinding(AddItemActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddItemActivity_ViewBinding(final AddItemActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.add_item_camera_iv, "field 'mItemImageView' and method 'onClickCameraIv'");
    target.mItemImageView = Utils.castView(view, R.id.add_item_camera_iv, "field 'mItemImageView'", ImageView.class);
    view2131361822 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickCameraIv();
      }
    });
    target.mItemTypeSpinner = Utils.findRequiredViewAsType(source, R.id.add_item_spinner_tv, "field 'mItemTypeSpinner'", Spinner.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AddItemActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mItemImageView = null;
    target.mItemTypeSpinner = null;

    view2131361822.setOnClickListener(null);
    view2131361822 = null;
  }
}
