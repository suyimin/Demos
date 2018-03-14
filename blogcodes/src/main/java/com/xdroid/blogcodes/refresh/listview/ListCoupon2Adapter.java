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
 * ListView定向刷新
 */
public class ListCoupon2Adapter extends BaseAdapter {
    private List<TestBean> mDatas;
    private Context mContext;
    private LayoutInflater mInflater;
    /**
     * 保存当前选中的position
     */
    private int mSelectedPos = -1;
    /**
     * 需要利用Lv获取某个postion的view
     */
    private ListView mLv;

    public ListCoupon2Adapter(List<TestBean> datas, Context context, ListView listView) {
        mDatas = datas;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        //设置数据集时，找到默认选中的pos
        for (int i = 0; i < mDatas.size(); i++) {
            if (mDatas.get(i).isSelected()) {
                mSelectedPos = i;
            }
        }
        mLv = listView;
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
                //如果当前选中的View在当前屏幕可见,且不是自己，要定向刷新一下之前的View的状态
                if (position != mSelectedPos) {
                    int firstPos = mLv.getFirstVisiblePosition() - mLv.getHeaderViewsCount();//这里考虑了HeaderView的情况
                    int lastPos = mLv.getLastVisiblePosition() - mLv.getHeaderViewsCount();
                    if (mSelectedPos >= firstPos && mSelectedPos <= lastPos) {
                        View lastSelectedView = mLv.getChildAt(mSelectedPos - firstPos);//取出选中的View
                        CouponVH lastVh = (CouponVH) lastSelectedView.getTag();
                        lastVh.ivSelect.setSelected(false);
                    }
                    //不管在屏幕是否可见，都需要改变之前的data
                    mDatas.get(mSelectedPos).setSelected(false);
                    //改变现在的点击的这个View的选中状态
                    couponVH.ivSelect.setSelected(true);
                    mDatas.get(position).setSelected(true);
                    mSelectedPos = position;
                }
            }
        });
        return view;
    }

    public static class CouponVH {
        private ImageView ivSelect;
        private TextView tvCoupon;
    }
}
