package com.xdroid.blogcodes.refresh.recyclerview;

import android.content.Context;
import android.os.Bundle;
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
 * 利用payloads实现部分刷新
 */
public class Coupon4Adapter extends RecyclerView.Adapter<Coupon4Adapter.CouponVH> {
    public static final String KEY_BOOLEAN = "KEY_BOOLEAN";
    private List<TestBean> mDatas;
    private Context mContext;
    private LayoutInflater mInflater;
    /**
     * 保存当前选中的position
     */
    private int mSelectedPos = -1;

    public Coupon4Adapter(List<TestBean> datas, Context context, RecyclerView rv) {
        mDatas = datas;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        //设置数据集时，找到默认选中的pos
        for (int i = 0; i < mDatas.size(); i++) {
            if (mDatas.get(i).isSelected()) {
                mSelectedPos = i;
            }
        }
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
                if (mSelectedPos != position) {
                    //先取消上个item的勾选状态
                    mDatas.get(mSelectedPos).setSelected(false);
                    //传递一个payload
                    Bundle payloadOld = new Bundle();
                    payloadOld.putBoolean(KEY_BOOLEAN, false);
                    notifyItemChanged(mSelectedPos, payloadOld);
                    //设置新Item的勾选状态
                    mSelectedPos = position;
                    mDatas.get(mSelectedPos).setSelected(true);
                    Bundle payloadNew = new Bundle();
                    payloadNew.putBoolean(KEY_BOOLEAN, true);
                    notifyItemChanged(mSelectedPos, payloadNew);
                }
            }
        });
    }

    @Override
    public void onBindViewHolder(CouponVH holder, int position, List<Object> payloads) {
        Log.d("TAG", "holder = [" + holder + "], position = [" + position + "], payloads = [" + payloads + "]");

        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            Bundle payload = (Bundle) payloads.get(0);
            if (payload.containsKey(KEY_BOOLEAN)) {
                boolean aBoolean = payload.getBoolean(KEY_BOOLEAN);
                holder.ivSelect.setSelected(aBoolean);
            }
        }
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
