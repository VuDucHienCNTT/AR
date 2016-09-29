package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL-PC on 9/27/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "QLCauThu";
    private static int DATABASE_VERSION = 1;

    public static String TABLE_NAME = "CauThu";
    public static String KEY_ID = "id";
    public static String KEY_HOTEN = "hoten";
    public static String KEY_QUOCTICH = "quoctich";

    Context context;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public CauThu LayMotDuLieu(int id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql = "select * from " + TABLE_NAME + " where " + KEY_ID + " =" + id;
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        CauThu ct = new CauThu();
        if (cursor.moveToFirst()) {
            ct.setHoten(cursor.getString(cursor.getColumnIndex(KEY_HOTEN)));
            ct.setQuoctich(cursor.getString(cursor.getColumnIndex(KEY_QUOCTICH)));
        }

        return ct;
    }

    public List<CauThu> layTatCaCauThu() {
        List<CauThu> list = new ArrayList<CauThu>();
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "select * from " + TABLE_NAME;

        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            CauThu ct = new CauThu();
            ct.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))));
            ct.setHoten(cursor.getString(cursor.getColumnIndex(KEY_HOTEN)));
            ct.setQuoctich(cursor.getString(cursor.getColumnIndex(KEY_QUOCTICH)));
            list.add(ct);
            cursor.moveToNext();
        }
        return list;
    }

    public void Themmoi(CauThu ct) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, ct.getId());
        values.put(KEY_HOTEN, ct.getHoten());
        values.put(KEY_QUOCTICH, ct.getQuoctich());

        if (sqLiteDatabase.insert(TABLE_NAME, null, values) != -1) {
            Toast.makeText(context, "Thêm thành công!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
        }
        sqLiteDatabase.close();
    }

    public int CapNhap(CauThu ct) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_HOTEN, ct.getHoten());
        values.put(KEY_QUOCTICH, ct.getQuoctich());
        if (sqLiteDatabase.update(TABLE_NAME, values, KEY_ID + "=?", new String[]{String.valueOf(ct.getId())}) != -1) {
            Toast.makeText(context, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Cập nhật thất bại!", Toast.LENGTH_SHORT).show();
        }
        return sqLiteDatabase.update(TABLE_NAME, values, KEY_ID + "=?", new String[]{String.valueOf(ct.getId())});
    }

    public void Xoa(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, KEY_ID + "=?", new String[]{String.valueOf(id)});
        if (sqLiteDatabase.delete(TABLE_NAME, KEY_ID + "=?", new String[]{String.valueOf(id)}) != -1) {
            Toast.makeText(context, "Xóa thành công!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Xóa thất bại!", Toast.LENGTH_SHORT).show();
        }
        sqLiteDatabase.close();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "Create table " + TABLE_NAME + " ( "
                + KEY_ID + " Integer primary key autoincrement, "
                + KEY_HOTEN + " text, "
                + KEY_QUOCTICH + " text " + ")";
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
