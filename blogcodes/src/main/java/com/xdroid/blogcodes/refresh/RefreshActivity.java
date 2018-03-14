package com.xdroid.blogcodes.refresh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.xdroid.blogcodes.R;
import com.xdroid.blogcodes.refresh.listview.ListViewActivity;
import com.xdroid.blogcodes.refresh.recyclerview.RecyclerViewActivity;

import java.util.ArrayList;
import java.util.List;

public class RefreshActivity extends AppCompatActivity {

    public static final String ID = "id";

    private ListView lv;

    private List<String> titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        lv = (ListView) findViewById(R.id.lv);
        titles = new ArrayList<>();
        titles.add("ListView 刷新整个视图");
        titles.add("ListView 定向刷新");
        titles.add("RecyclerView 刷新整个视图");
        titles.add("RecyclerView notifyItemChanged() 定向刷新两个视图");
        titles.add("RecyclerView 仅刷新单选按钮");
        titles.add("RecyclerView 利用payloads实现部分刷新");
        lv.setAdapter(new ArrayAdapter<String>(RefreshActivity.this, -1, titles) {
                          @Override
                          public View getView(int position, View convertView, ViewGroup parent) {
                              String title = getItem(position);
                              if (convertView == null) {
                                  convertView = LayoutInflater.from(RefreshActivity.this).inflate(R.layout.item_category, parent, false);
                              }
                              TextView tv = (TextView) convertView.findViewById(R.id.id_title);
                              tv.setText(title);
                              return convertView;
                          }
                      }
        );
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (position) {
                    case 0:
                    case 1:
                        intent = new Intent(RefreshActivity.this, ListViewActivity.class);
                        break;
                    default:
                        intent = new Intent(RefreshActivity.this, RecyclerViewActivity.class);
                        break;
                }
                intent.putExtra(ID, position);
                startActivity(intent);
            }
        });
    }
}
