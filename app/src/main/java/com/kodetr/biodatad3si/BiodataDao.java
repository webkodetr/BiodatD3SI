package com.kodetr.biodatad3si;

import android.database.Cursor;

public interface BiodataDao {

    Cursor read();

    boolean create(Biodata bio);

    boolean update(Biodata bio);

    boolean delete(String nim);

}
