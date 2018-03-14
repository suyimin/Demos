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
 * 利用RecyclerView的 findViewHolderForLayoutPosition()方法，获取某个postion的ViewHolder。
 * 仅刷新单选按钮。
 */
public class Coupon3Adapter extends RecyclerView.Adapter<Coupon3Adapter.CouponVH> {
    private List<TestBean> mDatas;
    private Context mContext;
    private LayoutInflater mInflater;

    /**
     * 保存当前选中的position
     */
    private int mSelectedPos = -1;

    private RecyclerView mRv;

    public Coupon3Adapter(List<TestBean> datas, Context context, RecyclerView rv) {
        mDatas = datas;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mRv = rv;
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
                CouponVH couponVH = (CouponVH) mRv.findViewHolderForLayoutPosition(mSelectedPos);
                //还在屏幕里
                if (couponVH != null) {
                    couponVH.ivSelect.setSelected(false);
                } else {
                    //一些极端情况，holder被缓存在Recycler的cacheView里，
                    //此时拿不到ViewHolder，但是也不会回调onBindViewHolder方法。
                    notifyItemChanged(mSelectedPos);
                }
                //不管在不在屏幕里 都需要改变数据
                mDatas.get(mSelectedPos).setSelected(false);
                //设置新Item的勾选状态
                mSelectedPos = position;
                mDatas.get(mSelectedPos).setSelected(true);
                holder.ivSelect.setSelected(true);
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
