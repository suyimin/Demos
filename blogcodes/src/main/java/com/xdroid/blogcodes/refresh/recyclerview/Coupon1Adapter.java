package com.xdroid.blogcodes.refresh.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xdroid.blogcodes.R;
import com.xdroid.blogcodes.refresh.TestBean;

import java.util.List;

/**
 * 常规方法，刷新整屏
 */
public class Coupon1Adapter extends RecyclerView.Adapter<Coupon1Adapter.CouponVH> {
    private List<TestBean> mDatas;
    private Context mContext;
    private LayoutInflater mInflater;

    public Coupon1Adapter(List<TestBean> datas, Context context, RecyclerView rv) {
        mDatas = datas;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public CouponVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CouponVH(mInflater.inflate(R.layout.item_coupon, parent, false));
    }

    @Override
    public void onBindViewHolder(final CouponVH holder, final int position) {
        Log.d("TAG", "holder = [" + holder + "], position = [" + position + "]");
        holder.ivSelect.setSelected(mDatas.get(position).isSelected());
        holder.tvCoupon.setText(mDatas.get(position).getName());

        holder.ivSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //实现单选，第一种方法，十分简单， Lv Rv通用,因为它们都有notifyDataSetChanged()方法
                // 每次点击时，先将所有的selected设为false，并且将当前点击的item 设为true， 刷新整个视图
                for (TestBean data : mDatas) {
                    data.setSelected(false);
                }
                mDatas.get(position).setSelected(true);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return null != mDatas ? mDatas.size() : 0;
    }

    public static class CouponVH extends RecyclerView.ViewHolder {
        private ImageView ivSelect;
        private TextView tvCoupon;

        public CouponVH(View itemView) {
            super(itemView);
            ivSelect = (ImageView) itemView.findViewById(R.id.ivSelect);
            tvCoupon = (TextView) itemView.findViewById(R.id.tvCoupon);
        }
    }
}
