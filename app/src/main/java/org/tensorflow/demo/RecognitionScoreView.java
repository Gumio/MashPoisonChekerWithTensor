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

package org.tensorflow.demo;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import org.tensorflow.demo.Classifier.Recognition;
import org.tensorflow.demo.databinding.ResultLayoutBinding;

import java.util.List;

public class RecognitionScoreView extends RelativeLayout implements ResultsView {
  //private static final float TEXT_SIZE_DIP = 24;
  private List<Recognition> results;
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
    mBinding.resultText.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        Log.d("detail is ", detail.toString());
        detail = !detail;
      }
    });
  }

  @Override
  public void setResults(final List<Recognition> results) {
    this.results = results;

    String label;

    // ここで値取ってこれる
    if (results != null) {
      for (final Recognition recog : results) {
        if(detail) {
          label = recog.getTitle() + ": " + recog.getConfidence();
        }else {
          label = "毒かも？";
        }
        if(recog.getConfidence() > 0.7) {
          mBinding.resultPicture.setImageResource(R.drawable.m_f_mushroom370);
          mBinding.resultText.setText(label);
        } else {
          mBinding.resultPicture.setImageResource(R.drawable.question_head_boy);
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
