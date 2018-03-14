package com.xdroid.blogcodes.refresh.listview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.xdroid.blogcodes.R;
import com.xdroid.blogcodes.refresh.TestBean;

import java.util.List;


/**
 * 每次点击时，先将所有的selected设为false，并且将当前点击的item 设为true， 刷新整个视图
 */
public class ListCoupon1Adapter extends BaseAdapter {
    private List<TestBean> mDatas;
    private Context mContext;
    private LayoutInflater mInflater;

    public ListCoupon1Adapter(List<TestBean> datas, Context context, ListView listView) {
        mDatas = datas;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return null != mDatas ? mDatas.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        Log.d("TAG", " position = [" + position + "]");
        final CouponVH couponVH;
        if (view == null) {
            view = mInflater.inflate(R.layout.item_coupon, viewGroup, false);
            couponVH = new CouponVH();
            couponVH.ivSelect = (ImageView) view.findViewById(R.id.ivSelect);
            couponVH.tvCoupon = (TextView) view.findViewById(R.id.tvCoupon);
            view.setTag(couponVH);
        } else {
            couponVH = (CouponVH) view.getTag();
        }
        couponVH.ivSelect.setSelected(mDatas.get(position).isSelected());
        couponVH.tvCoupon.setText(mDatas.get(position).getName());
        couponVH.ivSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (TestBean data : mDatas) {
                    data.setSelected(false);
                }
                mDatas.get(position).setSelected(true);
                notifyDataSetChanged();
            }
        });

        return view;
    }


    public static class CouponVH {
        private ImageView ivSelect;
        private TextView tvCoupon;
    }
}
