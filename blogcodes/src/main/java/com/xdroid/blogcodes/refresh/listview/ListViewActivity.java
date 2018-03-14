package com.xdroid.blogcodes.refresh.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.xdroid.blogcodes.R;
import com.xdroid.blogcodes.refresh.RefreshActivity;
import com.xdroid.blogcodes.refresh.TestBean;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    private ListView mLv;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mLv = (ListView) findViewById(R.id.lv);
        id = getIntent().getIntExtra(RefreshActivity.ID, 0);
        if (id == 0) {
            mLv.setAdapter(new ListCoupon1Adapter(initDatas(), this, mLv));
        } else {
            mLv.setAdapter(new ListCoupon2Adapter(initDatas(), this, mLv));
        }
        TextView header = new TextView(this);
        header.setText("我是头");
        mLv.addHeaderView(header);
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
