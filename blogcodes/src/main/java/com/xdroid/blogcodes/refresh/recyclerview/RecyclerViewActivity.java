package com.xdroid.blogcodes.refresh.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xdroid.blogcodes.R;
import com.xdroid.blogcodes.refresh.RefreshActivity;
import com.xdroid.blogcodes.refresh.TestBean;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    private RecyclerView mRv;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        id = getIntent().getIntExtra(RefreshActivity.ID, 4);
        switch (id) {
            case 2:
                mRv.setAdapter(new Coupon1Adapter(initDatas(), this, mRv));
                break;
            case 3:
                mRv.setAdapter(new Coupon2Adapter(initDatas(), this, mRv));
                break;
            case 4:
                mRv.setAdapter(new Coupon3Adapter(initDatas(), this, mRv));
                break;
            default:
                mRv.setAdapter(new Coupon4Adapter(initDatas(), this, mRv));
                break;
        }
    }

    public List<TestBean> initDatas() {
        List<TestBean> datas = new ArrayList<>();
        datas.add(new TestBean("满100减99"));
        datas.add(new TestBean("满100减98", true));
        datas.add(new TestBean("满100减97"));
        datas.add(new TestBean("满100减96"));
        datas.add(new TestBean("满100减95"));
        datas.add(new TestBean("满100减94"));
        datas.add(new TestBean("满100减93"));
        datas.add(new TestBean("满100减92"));
        datas.add(new TestBean("满100减91"));
        datas.add(new TestBean("满100减90"));
        datas.add(new TestBean("满100减89"));
        datas.add(new TestBean("满100减88"));
        datas.add(new TestBean("满100减87"));
        datas.add(new TestBean("满100减86"));
        return datas;
    }
}
