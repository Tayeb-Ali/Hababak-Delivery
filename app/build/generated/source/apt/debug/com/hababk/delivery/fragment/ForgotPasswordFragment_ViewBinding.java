// Generated code from Butter Knife. Do not modify!
package com.hababk.delivery.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hababk.delivery.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ForgotPasswordFragment_ViewBinding implements Unbinder {
  private ForgotPasswordFragment target;

  private View view2131361922;

  private View view2131361920;

  @UiThread
  public ForgotPasswordFragment_ViewBinding(final ForgotPasswordFragment target, View source) {
    this.target = target;

    View view;
    target.mEmailTv = Utils.findRequiredViewAsType(source, R.id.forgot_pass_email_tv, "field 'mEmailTv'", EditText.class);
    target.mSendMailBtn = Utils.findRequiredViewAsType(source, R.id.forgot_pass_send_mail_btn, "field 'mSendMailBtn'", Button.class);
    view = Utils.findRequiredView(source, R.id.forgot_pass_login_tv, "field 'mLoginTv' and method 'onClickBack'");
    target.mLoginTv = Utils.castView(view, R.id.forgot_pass_login_tv, "field 'mLoginTv'", TextView.class);
    view2131361922 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickBack();
      }
    });
    view = Utils.findRequiredView(source, R.id.forgot_pass_back_iv, "field 'mBackIv' and method 'onClickBack'");
    target.mBackIv = Utils.castView(view, R.id.forgot_pass_back_iv, "field 'mBackIv'", ImageView.class);
    view2131361920 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickBack();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ForgotPasswordFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mEmailTv = null;
    target.mSendMailBtn = null;
    target.mLoginTv = null;
    target.mBackIv = null;

    view2131361922.setOnClickListener(null);
    view2131361922 = null;
    view2131361920.setOnClickListener(null);
    view2131361920 = null;
  }
}
