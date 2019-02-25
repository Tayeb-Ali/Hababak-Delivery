// Generated code from Butter Knife. Do not modify!
package com.hababk.delivery.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hababk.delivery.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SplashActivity_ViewBinding implements Unbinder {
  private SplashActivity target;

  private View view2131362072;

  private View view2131362071;

  @UiThread
  public SplashActivity_ViewBinding(SplashActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SplashActivity_ViewBinding(final SplashActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.splash_signin_btn, "field 'signInBtn' and method 'onCLickSignIn'");
    target.signInBtn = Utils.castView(view, R.id.splash_signin_btn, "field 'signInBtn'", Button.class);
    view2131362072 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCLickSignIn();
      }
    });
    view = Utils.findRequiredView(source, R.id.splash_register_btn, "field 'registerBtn' and method 'onClickRegister'");
    target.registerBtn = Utils.castView(view, R.id.splash_register_btn, "field 'registerBtn'", Button.class);
    view2131362071 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickRegister();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    SplashActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.signInBtn = null;
    target.registerBtn = null;

    view2131362072.setOnClickListener(null);
    view2131362072 = null;
    view2131362071.setOnClickListener(null);
    view2131362071 = null;
  }
}
