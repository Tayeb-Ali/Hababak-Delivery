// Generated code from Butter Knife. Do not modify!
package com.hababk.delivery.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hababk.delivery.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PendingItemListAdapter$PendingItemListHolder_ViewBinding implements Unbinder {
  private PendingItemListAdapter.PendingItemListHolder target;

  private View view2131361998;

  @UiThread
  public PendingItemListAdapter$PendingItemListHolder_ViewBinding(final PendingItemListAdapter.PendingItemListHolder target,
      View source) {
    this.target = target;

    View view;
    target.itemNameTv = Utils.findRequiredViewAsType(source, R.id.pending_item_name_tv, "field 'itemNameTv'", TextView.class);
    target.itemTypeTv = Utils.findRequiredViewAsType(source, R.id.pending_item_type_tv, "field 'itemTypeTv'", TextView.class);
    target.itemStatusTv = Utils.findRequiredViewAsType(source, R.id.pending_item_status_val_tv, "field 'itemStatusTv'", TextView.class);
    target.itemMoreIv = Utils.findRequiredViewAsType(source, R.id.pending_item_more_iv, "field 'itemMoreIv'", ImageView.class);
    target.itemImageView = Utils.findRequiredViewAsType(source, R.id.pending_item_image_view, "field 'itemImageView'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.pending_item_layout, "method 'onClickItemLayout'");
    view2131361998 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickItemLayout();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    PendingItemListAdapter.PendingItemListHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.itemNameTv = null;
    target.itemTypeTv = null;
    target.itemStatusTv = null;
    target.itemMoreIv = null;
    target.itemImageView = null;

    view2131361998.setOnClickListener(null);
    view2131361998 = null;
  }
}
