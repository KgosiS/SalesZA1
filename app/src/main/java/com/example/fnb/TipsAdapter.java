package com.example.fnb;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.TipViewHolder> {
    private ArrayList<FinancialTip> tips;

    public TipsAdapter(ArrayList<FinancialTip> tips) {
        this.tips = tips;
    }

    @Override
    public TipViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tip_card, parent, false);
        return new TipViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TipViewHolder holder, int position) {
        FinancialTip tip = tips.get(position);
        holder.tipTextView.setText(tip.getTip());
    }

    @Override
    public int getItemCount() {
        return tips.size();
    }

    public static class TipViewHolder extends RecyclerView.ViewHolder {
        TextView tipTextView;

        public TipViewHolder(View itemView) {
            super(itemView);
            tipTextView = itemView.findViewById(R.id.tip_text);
        }
    }
}
