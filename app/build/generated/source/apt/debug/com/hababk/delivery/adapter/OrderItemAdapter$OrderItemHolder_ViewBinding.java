// Generated code from Butter Knife. Do not modify!
package com.hababk.delivery.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.hababk.delivery.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OrderItemAdapter$OrderItemHolder_ViewBinding implements Unbinder {
  private OrderItemAdapter.OrderItemHolder target;

  @UiThread
  public OrderItemAdapter$OrderItemHolder_ViewBinding(OrderItemAdapter.OrderItemHolder target,
      View source) {
    this.target = target;

    target.mNameTv = Utils.findRequiredViewAsType(source, R.id.order_item_name_tv, "field 'mNameTv'", TextView.class);
    target.mSubTotalTv = Utils.findRequiredViewAsType(source, R.id.order_item_sub_total_tv, "field 'mSubTotalTv'", TextView.class);
    target.mPriceQuantityTv = Utils.findRequiredViewAsType(source, R.id.order_item_price_quantity_tv, "field 'mPriceQuantityTv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OrderItemAdapter.OrderItemHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mNameTv = null;
    target.mSubTotalTv = null;
    target.mPriceQuantityTv = null;
  }
}
