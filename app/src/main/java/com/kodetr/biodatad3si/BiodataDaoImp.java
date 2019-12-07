package com.kodetr.biodatad3si;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BiodataDaoImp extends SQLiteOpenHelper implements BiodataDao {

    public BiodataDaoImp(Context context) {
        super(context, "db_biodata", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE tbl_biodata (nim TEXT PRIMARY KEY, nama TEXT, alamat TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE tbl_biodata");
    }

    @Override
    public Cursor read() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM tbl_biodata", null);
    }

    @Override
    public boolean create(Biodata bio) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("INSERT INTO tbl_biodata VALUES ('" + bio.getNim() + "','" + bio.getNama() + "','" + bio.getAlamat() + "')");
        return true;
    }

    @Override
    public boolean update(Biodata bio) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("UPDATE tbl_biodata SET nama='" + bio.getNama() + "', alamat='" + bio.getAlamat() + "' WHERE nim='" + bio.getNim() + "'");
        return true;
    }

    @Override
    public boolean delete(String nim) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM tbl_biodata WHERE nim = '" + nim + "'");
        return true;
    }

}
