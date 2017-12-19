/* Copyright 2017 Esri
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.esri.arcgisruntime.sample.statisticalquery;

import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

  private List<String> mFields = Collections.emptyList();
  private final LayoutInflater mInflater;
  private int mSelectedPosition = 0;

  public RecyclerViewAdapter(Context context, List<String> data) {
    this.mInflater = LayoutInflater.from(context);
    this.mFields = data;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    String text = mFields.get(position);
    holder.mRowTextView.setText(text);
    // give the selected row a gray background and make all others transparent
    holder.itemView.setBackgroundColor(mSelectedPosition == position ? Color.LTGRAY : Color.TRANSPARENT);
  }

  @Override
  public int getItemCount() {
    return mFields.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public final TextView mRowTextView;

    public ViewHolder(View itemView) {
      super(itemView);
      mRowTextView = itemView.findViewById(R.id.rowTextView);
      itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      // notify change before and after selection so that both previous and current selection have their background
      // color changed
      notifyItemChanged(mSelectedPosition);
      mSelectedPosition = getAdapterPosition();
      notifyItemChanged(mSelectedPosition);
    }
  }

  public int getSelectedPosition() {
    return mSelectedPosition;
  }

  public String getItem(int id) {
    return mFields.get(id);
  }

}
