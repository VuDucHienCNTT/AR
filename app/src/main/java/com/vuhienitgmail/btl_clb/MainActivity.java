package com.vuhienitgmail.btl_clb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import database.CauThu;
import database.DatabaseHandler;

public class MainActivity extends AppCompatActivity {

    DatabaseHandler db;
    EditText edtHoten, edtid, edtQuoctich;
    Button btnThemmoi, btnDanhsach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        edtid=(EditText) findViewById(R.id.editTextId);
        edtHoten=(EditText) findViewById(R.id.editTextTen);
        edtQuoctich=(EditText) findViewById(R.id.editTextQuoctich);


        btnThemmoi = (Button) findViewById(R.id.buttonThemmoi);
        btnDanhsach = (Button) findViewById(R.id.buttonDanhsach);

        db=new DatabaseHandler(this);
        btnThemmoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CauThu ct=new CauThu();
//                ct.setId(Integer.parseInt(edtid.getText().toString()));
                ct.setHoten(edtHoten.getText().toString());
                ct.setQuoctich(edtQuoctich.getText().toString());
                db.Themmoi(ct);
            }
        });

        btnDanhsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, Hienthidanhsach.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
