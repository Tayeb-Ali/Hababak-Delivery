// Generated code from Butter Knife. Do not modify!
package com.hababk.delivery.activity;

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

public class EarningsActivity_ViewBinding implements Unbinder {
  private EarningsActivity target;

  @UiThread
  public EarningsActivity_ViewBinding(EarningsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EarningsActivity_ViewBinding(EarningsActivity target, View source) {
    this.target = target;

    target.mEarningsTabs = Utils.findRequiredViewAsType(source, R.id.earnings_tabs, "field 'mEarningsTabs'", TabLayout.class);
    target.mEarningsViewPager = Utils.findRequiredViewAsType(source, R.id.earnings_viewpager, "field 'mEarningsViewPager'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EarningsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mEarningsTabs = null;
    target.mEarningsViewPager = null;
  }
}
