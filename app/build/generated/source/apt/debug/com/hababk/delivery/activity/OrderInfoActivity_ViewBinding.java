// Generated code from Butter Knife. Do not modify!
package com.hababk.delivery.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hababk.delivery.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OrderInfoActivity_ViewBinding implements Unbinder {
  private OrderInfoActivity target;

  private View view2131361987;

  @UiThread
  public OrderInfoActivity_ViewBinding(OrderInfoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OrderInfoActivity_ViewBinding(final OrderInfoActivity target, View source) {
    this.target = target;

    View view;
    target.mItemRv = Utils.findRequiredViewAsType(source, R.id.order_info_items_recyclerview, "field 'mItemRv'", RecyclerView.class);
    target.mCancelOrderTv = Utils.findRequiredViewAsType(source, R.id.order_info_cancel_order_tv, "field 'mCancelOrderTv'", TextView.class);
    target.mAcceptOrderTv = Utils.findRequiredViewAsType(source, R.id.order_info_accept_order_tv, "field 'mAcceptOrderTv'", TextView.class);
    target.mNameTv = Utils.findRequiredViewAsType(source, R.id.order_info_name_tv, "field 'mNameTv'", TextView.class);
    target.mPaymentMethod2Tv = Utils.findRequiredViewAsType(source, R.id.order_info_payment_method2_tv, "field 'mPaymentMethod2Tv'", TextView.class);
    target.mAddressTv = Utils.findRequiredViewAsType(source, R.id.order_info_address_tv, "field 'mAddressTv'", TextView.class);
    target.mHomeIv = Utils.findRequiredViewAsType(source, R.id.order_info_home_iv, "field 'mHomeIv'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.order_info_open_map_layout, "method 'onClickOrderDetailMap'");
    view2131361987 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickOrderDetailMap();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    OrderInfoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mItemRv = null;
    target.mCancelOrderTv = null;
    target.mAcceptOrderTv = null;
    target.mNameTv = null;
    target.mPaymentMethod2Tv = null;
    target.mAddressTv = null;
    target.mHomeIv = null;

    view2131361987.setOnClickListener(null);
    view2131361987 = null;
  }
}
