package com.kevin.tech.kevinsummary.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kevin.tech.kevinsummary.R;
import com.kevin.tech.kevinsummary.listener.OnItemClickListener;
import com.kevin.tech.kevinsummary.listener.OnShowEffectListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> for Project KevinSummary on 2017/9/12.
 * <h3>Description:</h3>
 * <div>
 * <div/>
 */


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private String[] strings;
    private boolean showAction;

    public RecyclerViewAdapter(Context context, String[] strings, boolean showAction) {
        this.context = context;
        this.strings = strings;
        this.showAction = showAction;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_recycler_view, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.textView.setText(strings[position]);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onRecyclerClick(position);
                }
            }
        });
        holder.textAction.setText("查看效果");
        holder.textAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showEffectListener != null) {
                    showEffectListener.showEffectActivity(position);
                }
            }
        });
        if (showAction) {
            holder.viewLine.setVisibility(View.VISIBLE);
            holder.textAction.setVisibility(View.VISIBLE);
        } else {
            holder.viewLine.setVisibility(View.GONE);
            holder.textAction.setVisibility(View.GONE);
        }
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
        @BindView(R.id.item_action)
        TextView textAction;

        @BindView(R.id.view_line)
        View viewLine;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    private OnShowEffectListener showEffectListener;

    public void setOnShowEffectListener(OnShowEffectListener l) {
        this.showEffectListener = l;
    }


}
