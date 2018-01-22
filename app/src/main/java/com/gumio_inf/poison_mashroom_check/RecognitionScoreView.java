/* Copyright 2015 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package com.gumio_inf.poison_mashroom_check;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.gumio_inf.poison_mashroom_check.databinding.ResultLayoutBinding;

import java.util.List;

public class RecognitionScoreView extends RelativeLayout implements ResultsView {
  //private static final float TEXT_SIZE_DIP = 24;
  private List<Classifier.Recognition> results;
//  private final float textSizePx;
//  private final Paint fgPaint;
//  private final Paint bgPaint;

  private Boolean detail = false;

  private ResultLayoutBinding mBinding;

  public RecognitionScoreView(final Context context, final AttributeSet set) {
    super(context, set);

    LayoutInflater inflater = LayoutInflater.from(context);
    mBinding = DataBindingUtil.inflate(inflater, R.layout.result_layout, this, true);
//    textSizePx =
//        TypedValue.applyDimension(
//            TypedValue.COMPLEX_UNIT_DIP, TEXT_SIZE_DIP, getResources().getDisplayMetrics());
//    fgPaint = new Paint();
//    fgPaint.setTextSize(textSizePx);
//
//    bgPaint = new Paint();
//    bgPaint.setColor(0xcc4285f4);
    Typeface typeface = Typeface.createFromAsset(getResources().getAssets(), "kin.ttf");
    mBinding.resultText.setTypeface(typeface);
    mBinding.detailText.setTypeface(typeface);
    mBinding.resultView.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        Log.d("detail is ", detail.toString());
        detail = !detail;
      }
    });
  }

  @Override
  public void setResults(final List<Classifier.Recognition> results) {
    this.results = results;

    String label;

    // ここで値取ってこれる
    if (results != null) {
      for (final Classifier.Recognition recog : results) {
        Log.d("this kinoko is " + recog.getTitle() + ":", recog.getConfidence().toString());
        if(recog.getConfidence() >= 0.70) {
          mBinding.resultPicture.setAnimation("warn.json");
          mBinding.resultPicture.playAnimation();
          if(detail) {
            label = recog.getTitle() + "\n" + recog.getConfidence();
            mBinding.detailText.setVisibility(GONE);
          }else {
            label = "毒かも？";
            mBinding.detailText.setVisibility(VISIBLE);
          }
          mBinding.resultText.setText(label);
        } else {
          mBinding.resultPicture.setAnimation("simple_loader.json");
          mBinding.resultPicture.playAnimation();
          mBinding.detailText.setVisibility(GONE);
          mBinding.resultText.setText("このキノコは・・・・・・・");
        }
      }
    }
    postInvalidate();
  }


//  @Override
//  public boolean onTouchEvent(MotionEvent event) {
//    detail = !detail;
//    return true;
//  }
}
