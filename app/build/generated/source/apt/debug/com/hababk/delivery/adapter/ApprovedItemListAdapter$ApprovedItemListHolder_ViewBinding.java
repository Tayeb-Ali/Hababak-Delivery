// Generated code from Butter Knife. Do not modify!
package com.hababk.delivery.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hababk.delivery.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ApprovedItemListAdapter$ApprovedItemListHolder_ViewBinding implements Unbinder {
  private ApprovedItemListAdapter.ApprovedItemListHolder target;

  private View view2131361836;

  @UiThread
  public ApprovedItemListAdapter$ApprovedItemListHolder_ViewBinding(final ApprovedItemListAdapter.ApprovedItemListHolder target,
      View source) {
    this.target = target;

    View view;
    target.itemNameTv = Utils.findRequiredViewAsType(source, R.id.approved_item_name_tv, "field 'itemNameTv'", TextView.class);
    target.itemTypeTv = Utils.findRequiredViewAsType(source, R.id.approved_item_type_tv, "field 'itemTypeTv'", TextView.class);
    target.itemPriceTv = Utils.findRequiredViewAsType(source, R.id.approved_item_price_val_tv, "field 'itemPriceTv'", TextView.class);
    target.itemSwitch = Utils.findRequiredViewAsType(source, R.id.approved_item_available_switch, "field 'itemSwitch'", SwitchCompat.class);
    target.itemMoreIv = Utils.findRequiredViewAsType(source, R.id.approved_item_more_iv, "field 'itemMoreIv'", ImageView.class);
    target.itemImageView = Utils.findRequiredViewAsType(source, R.id.approved_item_image_view, "field 'itemImageView'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.approved_item_layout, "method 'onClickItemLayout'");
    view2131361836 = view;
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
    ApprovedItemListAdapter.ApprovedItemListHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.itemNameTv = null;
    target.itemTypeTv = null;
    target.itemPriceTv = null;
    target.itemSwitch = null;
    target.itemMoreIv = null;
    target.itemImageView = null;

    view2131361836.setOnClickListener(null);
    view2131361836 = null;
  }
}
