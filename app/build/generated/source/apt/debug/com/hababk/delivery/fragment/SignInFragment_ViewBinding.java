// Generated code from Butter Knife. Do not modify!
package com.hababk.delivery.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hababk.delivery.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SignInFragment_ViewBinding implements Unbinder {
  private SignInFragment target;

  private View view2131362061;

  @UiThread
  public SignInFragment_ViewBinding(final SignInFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.signin_forgot_pass_tv, "method 'onClickForgotPass'");
    view2131362061 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickForgotPass();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view2131362061.setOnClickListener(null);
    view2131362061 = null;
  }
}
