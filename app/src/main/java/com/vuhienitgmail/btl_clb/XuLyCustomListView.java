package com.vuhienitgmail.btl_clb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import database.CauThu;
import database.DatabaseHandler;

/**
 * Created by DELL-PC on 9/28/2016.
 */
public class XuLyCustomListView extends ArrayAdapter<CauThu> {
    Context context;
    int resource;
    List<CauThu> ct;

    public XuLyCustomListView(Context context, int resource, List<CauThu> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.ct = objects;
    }

    @Override
    public View getView(final int vitri, View v, ViewGroup viewcha) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View viewrow = inflater.inflate(R.layout.customs_layout, viewcha, false);

//        TextView txtid = (TextView) viewrow.findViewById(R.id.textViewidad);
        TextView txttencauthu = (TextView) viewrow.findViewById(R.id.textViewTen);
        TextView txtquoctich = (TextView) viewrow.findViewById(R.id.textViewQuoctich);

        final Button btnSua = (Button) viewrow.findViewById(R.id.buttonSua);
        final Button btnXoa = (Button) viewrow.findViewById(R.id.buttonXoa);

        CauThu getCauThu = ct.get(vitri);

//        txtid.setText(String.valueOf(getCauThu.getId()));
        txttencauthu.setText(getCauThu.getHoten());
        txtquoctich.setText(getCauThu.getQuoctich());

        //gán Tag thành id riêng
        btnXoa.setTag(getCauThu.getId());
        btnSua.setTag(getCauThu.getId());

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CapNhap.class);
                Bundle blTruyendulieu=new Bundle();

                blTruyendulieu.putString("id",btnSua.getTag().toString());
                intent.putExtra("GoiDuLieu",blTruyendulieu);
                context.startActivity(intent);
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler db = new DatabaseHandler(context);
                int id = Integer.parseInt(btnXoa.getTag().toString());
                db.Xoa(id);

                // hiệu ứng xóa api 16 trở lên
//                viewrow.animate().alpha(0).setDuration(1000).withEndAction(new Runnable() {
//                    @Override
//                    public void run() {
//                        ct.remove(vitri);
//                        notifyDataSetChanged();
//                        viewrow.setAlpha(1);
//                    }
//                });

                ct.remove(vitri);
                notifyDataSetChanged();
            }
        });

        return viewrow;
    }
}


