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

public class DeliveryListAdapter$DeliveryListHolder_ViewBinding implements Unbinder {
  private DeliveryListAdapter.DeliveryListHolder target;

  @UiThread
  public DeliveryListAdapter$DeliveryListHolder_ViewBinding(DeliveryListAdapter.DeliveryListHolder target,
      View source) {
    this.target = target;

    target.actionDelivery = Utils.findRequiredViewAsType(source, R.id.statusDelivery, "field 'actionDelivery'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DeliveryListAdapter.DeliveryListHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.actionDelivery = null;
  }
}
