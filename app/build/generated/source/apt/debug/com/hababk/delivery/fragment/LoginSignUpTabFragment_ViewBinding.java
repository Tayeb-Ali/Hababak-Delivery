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

public class LoginSignUpTabFragment_ViewBinding implements Unbinder {
  private LoginSignUpTabFragment target;

  @UiThread
  public LoginSignUpTabFragment_ViewBinding(LoginSignUpTabFragment target, View source) {
    this.target = target;

    target.mAuthTabs = Utils.findRequiredViewAsType(source, R.id.auth_tabs, "field 'mAuthTabs'", TabLayout.class);
    target.mAuhViewPager = Utils.findRequiredViewAsType(source, R.id.auth_viewpager, "field 'mAuhViewPager'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginSignUpTabFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mAuthTabs = null;
    target.mAuhViewPager = null;
  }
}
