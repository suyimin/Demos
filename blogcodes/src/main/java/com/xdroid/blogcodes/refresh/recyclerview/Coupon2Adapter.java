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
 * notifyItemChanged() 定向刷新两个视图
 * 会有白光一闪动画
 */
public class Coupon2Adapter extends RecyclerView.Adapter<Coupon2Adapter.CouponVH> {
    private List<TestBean> mDatas;
    private Context mContext;
    private LayoutInflater mInflater;

    /**
     * 保存当前选中的position
     */
    private int mSelectedPos = -1;

    public Coupon2Adapter(List<TestBean> datas, Context context, RecyclerView rv) {
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
                //如果勾选的不是已经勾选状态的Item
                if (mSelectedPos != position) {
                    //先取消上个item的勾选状态
                    mDatas.get(mSelectedPos).setSelected(false);
                    notifyItemChanged(mSelectedPos);
                    //设置新Item的勾选状态
                    mSelectedPos = position;
                    mDatas.get(mSelectedPos).setSelected(true);
                    notifyItemChanged(mSelectedPos);
                }
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
