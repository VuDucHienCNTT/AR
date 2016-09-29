package com.vuhienitgmail.btl_clb;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import database.CauThu;
import database.DatabaseHandler;

/**
 * Created by DELL-PC on 9/28/2016.
 */
public class Hienthidanhsach extends Activity {
    List<CauThu> list;
    DatabaseHandler db;
    ListView lstDanhSach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hienthidanhsach);


        db=new DatabaseHandler(this);
        list=new ArrayList<CauThu>();
        list=db.layTatCaCauThu();

        lstDanhSach=(ListView)findViewById(R.id.listViewDanhsach);
        XuLyCustomListView adapter=new XuLyCustomListView(this,R.layout.customs_layout,list);
        lstDanhSach.setAdapter(adapter);
    }
}
