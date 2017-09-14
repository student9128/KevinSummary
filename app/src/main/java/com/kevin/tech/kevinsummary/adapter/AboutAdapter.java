package com.kevin.tech.kevinsummary.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kevin.tech.kevinsummary.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> for Project KevinSummary on 2017/9/12.
 * <h3>Description:</h3>
 * <div>
 * <div/>
 */


public class AboutAdapter extends RecyclerView.Adapter<AboutAdapter.MyViewHolder> {

    private Context context;
    private String[] strings;
    private String[] titles;

    public AboutAdapter(Context context, String[] title, String[] strings) {
        this.context = context;
        this.strings = strings;
        this.titles = title;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_recycler_view_about, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.textTitle.setText(titles[position]);
        SpannableString text = new SpannableString(strings[position]);
        text.setSpan(new URLSpan(strings[position]), 0, text.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.textView.setText(text);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onRecyclerClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return strings.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cv_card_view)
        CardView cvCardView;

        @BindView(R.id.item_text)
        TextView textView;
        @BindView(R.id.item_title)
        TextView textTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onRecyclerClick(int position);
    }

}
