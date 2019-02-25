// Generated code from Butter Knife. Do not modify!
package com.hababk.delivery.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hababk.delivery.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NewOrderAdapter$NewOrderHolder_ViewBinding implements Unbinder {
  private NewOrderAdapter.NewOrderHolder target;

  private View view2131361957;

  @UiThread
  public NewOrderAdapter$NewOrderHolder_ViewBinding(final NewOrderAdapter.NewOrderHolder target,
      View source) {
    this.target = target;

    View view;
    target.personNameTv = Utils.findRequiredViewAsType(source, R.id.new_order_person_name_tv, "field 'personNameTv'", TextView.class);
    target.priceTv = Utils.findRequiredViewAsType(source, R.id.new_order_price_tv, "field 'priceTv'", TextView.class);
    target.paymentMethodTv = Utils.findRequiredViewAsType(source, R.id.new_order_payment_method_tv, "field 'paymentMethodTv'", TextView.class);
    target.orderStatusTv = Utils.findRequiredViewAsType(source, R.id.new_order_status_tv, "field 'orderStatusTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.new_order_item_layout, "field 'mItemLayout' and method 'onClickItemLayout'");
    target.mItemLayout = Utils.castView(view, R.id.new_order_item_layout, "field 'mItemLayout'", LinearLayout.class);
    view2131361957 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickItemLayout();
      }
    });
    target.orderNumberTv = Utils.findRequiredViewAsType(source, R.id.new_order_order_text_tv, "field 'orderNumberTv'", TextView.class);
    target.orderDateTv = Utils.findRequiredViewAsType(source, R.id.new_order_dispatch_text_tv, "field 'orderDateTv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    NewOrderAdapter.NewOrderHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.personNameTv = null;
    target.priceTv = null;
    target.paymentMethodTv = null;
    target.orderStatusTv = null;
    target.mItemLayout = null;
    target.orderNumberTv = null;
    target.orderDateTv = null;

    view2131361957.setOnClickListener(null);
    view2131361957 = null;
  }
}
