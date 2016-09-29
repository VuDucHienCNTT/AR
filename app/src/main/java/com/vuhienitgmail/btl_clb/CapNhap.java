package com.vuhienitgmail.btl_clb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import database.CauThu;
import database.DatabaseHandler;

/**
 * Created by DELL-PC on 9/28/2016.
 */
public class CapNhap extends Activity {

    EditText edthoten, edtquoctich, edtid;
    Button btnCapNhap, btnXemDanhSach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);

//        edtid = (EditText) findViewById(R.id.editTextIdUpdate);
        edthoten = (EditText) findViewById(R.id.editTextHoTenUpdate);
        edtquoctich = (EditText) findViewById(R.id.editTextQuoctichUpdate);
        btnCapNhap = (Button) findViewById(R.id.buttonCapNhap);
        btnXemDanhSach = (Button) findViewById(R.id.buttonXemDS);

        Bundle blLayDuLieu = new Bundle();
        blLayDuLieu = getIntent().getBundleExtra("GoiDuLieu");
        final String id = blLayDuLieu.getString("id");

//        edtid.setText(id);

        final DatabaseHandler db = new DatabaseHandler(this);
        CauThu ct = new CauThu();
        ct = db.LayMotDuLieu(Integer.parseInt(id));

        edthoten.setText(ct.getHoten());
        edtquoctich.setText(ct.getQuoctich());

        btnCapNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CauThu ct = new CauThu();
                ct.setId(Integer.parseInt(id));
                ct.setHoten(edthoten.getText().toString());
                ct.setQuoctich(edtquoctich.getText().toString());

                db.CapNhap(ct);
            }
        });

        btnXemDanhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CapNhap.this, Hienthidanhsach.class);
                startActivity(intent);
            }
        });


    }
}
