// Generated code from Butter Knife. Do not modify!
package com.hababk.delivery.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hababk.delivery.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ItemsFragment_ViewBinding implements Unbinder {
  private ItemsFragment target;

  private View view2131361936;

  @UiThread
  public ItemsFragment_ViewBinding(final ItemsFragment target, View source) {
    this.target = target;

    View view;
    target.mItemTabs = Utils.findRequiredViewAsType(source, R.id.item_tabs, "field 'mItemTabs'", TabLayout.class);
    target.mItemViewPager = Utils.findRequiredViewAsType(source, R.id.item_viewpager, "field 'mItemViewPager'", ViewPager.class);
    view = Utils.findRequiredView(source, R.id.item_add_fab, "field 'mItemAddFab' and method 'onClickFabOpenAddItemActivity'");
    target.mItemAddFab = Utils.castView(view, R.id.item_add_fab, "field 'mItemAddFab'", FloatingActionButton.class);
    view2131361936 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickFabOpenAddItemActivity();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ItemsFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mItemTabs = null;
    target.mItemViewPager = null;
    target.mItemAddFab = null;

    view2131361936.setOnClickListener(null);
    view2131361936 = null;
  }
}
