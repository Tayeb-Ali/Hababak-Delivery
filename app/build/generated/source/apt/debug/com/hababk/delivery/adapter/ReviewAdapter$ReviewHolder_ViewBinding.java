// Generated code from Butter Knife. Do not modify!
package com.hababk.delivery.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.hababk.delivery.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ReviewAdapter$ReviewHolder_ViewBinding implements Unbinder {
  private ReviewAdapter.ReviewHolder target;

  @UiThread
  public ReviewAdapter$ReviewHolder_ViewBinding(ReviewAdapter.ReviewHolder target, View source) {
    this.target = target;

    target.nameTv = Utils.findRequiredViewAsType(source, R.id.review_person_name_tv, "field 'nameTv'", TextView.class);
    target.addressTv = Utils.findRequiredViewAsType(source, R.id.review_person_address_tv, "field 'addressTv'", TextView.class);
    target.reviewTextTv = Utils.findRequiredViewAsType(source, R.id.review_text_tv, "field 'reviewTextTv'", TextView.class);
    target.dateTv = Utils.findRequiredViewAsType(source, R.id.review_date_tv, "field 'dateTv'", TextView.class);
    target.ratingValTv = Utils.findRequiredViewAsType(source, R.id.review_rating_val_tv, "field 'ratingValTv'", TextView.class);
    target.ratingBar = Utils.findRequiredViewAsType(source, R.id.review_ratingbar, "field 'ratingBar'", RatingBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ReviewAdapter.ReviewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.nameTv = null;
    target.addressTv = null;
    target.reviewTextTv = null;
    target.dateTv = null;
    target.ratingValTv = null;
    target.ratingBar = null;
  }
}
