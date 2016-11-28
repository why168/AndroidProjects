package com.github.why168.databinding.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.why168.databinding.R;
import com.github.why168.databinding.databinding.ItemRecyclerViewBinding;
import com.github.why168.databinding.model.Bean;

import java.util.List;

/**
 * RecyclerView
 *
 * @author Edwin.Wu
 * @version 2016/11/28 11:02
 * @since JDK1.8
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<BindingViewHolder<ItemRecyclerViewBinding>> {
    private List<Bean.DataBean> data;
    private LayoutInflater mLayoutInflater;

    private onItemClickLister mLister;

    public interface onItemClickLister {
        void onItemClick(View itemView, Bean.DataBean employee, int position);

    }

    public RecyclerViewAdapter(Context context, List<Bean.DataBean> data) {
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
    }

    @Override
    public BindingViewHolder<ItemRecyclerViewBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemRecyclerViewBinding binding = DataBindingUtil.inflate(mLayoutInflater, R.layout.item_recycler_view, parent, false);

        BindingViewHolder<ItemRecyclerViewBinding> holder = new BindingViewHolder<ItemRecyclerViewBinding>(binding.getRoot());

        holder.setBinding(binding);

        return viewDataBindingBindingViewHolder;
    }

    @Override
    public void onBindViewHolder(final BindingViewHolder<ItemRecyclerViewBinding> holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLister != null) {
                    mLister.onItemClick(holder.itemView, data.get(position), position);
                }
            }
        });
        holder.getBinding().setData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public void setOnItemClickLister(onItemClickLister mLister) {
        if (mLister != null) {
            this.mLister = mLister;
        } else {
            throw new NullPointerException("onItemClickLister is null");
        }
    }


    public void addAll(List<Bean.DataBean> beanList) {
        if (beanList != null) {
            data.addAll(beanList);
        } else {
            throw new NullPointerException("List<Bean.DataBean> is null");
        }
    }


    public void add(int position, Bean.DataBean dataBean) {
        if (dataBean != null) {
            data.add(position, dataBean);
            notifyItemInserted(position);
        } else {
            throw new NullPointerException("dataBean is null");
        }
    }

    public void remove(int position) {
        if (data.size() == 0) {
            return;
        }
        data.remove(position);
        notifyItemRemoved(position);
    }
}
