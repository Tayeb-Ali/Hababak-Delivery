// Generated code from Butter Knife. Do not modify!
package com.hababk.delivery.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.hababk.delivery.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OrdersFragment_ViewBinding implements Unbinder {
  private OrdersFragment target;

  @UiThread
  public OrdersFragment_ViewBinding(OrdersFragment target, View source) {
    this.target = target;

    target.mOrderTabs = Utils.findRequiredViewAsType(source, R.id.order_tabs, "field 'mOrderTabs'", TabLayout.class);
    target.mOrderViewPager = Utils.findRequiredViewAsType(source, R.id.order_viewpager, "field 'mOrderViewPager'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OrdersFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mOrderTabs = null;
    target.mOrderViewPager = null;
  }
}
