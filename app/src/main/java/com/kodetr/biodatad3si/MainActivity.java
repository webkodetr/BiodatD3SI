package com.kodetr.biodatad3si;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lview;
    private List<HashMap<String, String>> listmap;
    private BiodataDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        read();

    }

    private void init() {
        lview = (ListView) findViewById(R.id.lvBiodata);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, InputActivity.class));
            }
        });

    }

    private void read() {
        listmap = new ArrayList<>();
        dao = new BiodataDaoImp(MainActivity.this);

        Cursor cu = dao.read();

        if (cu.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("nim", cu.getString(0));
                map.put("nama", cu.getString(1));
                map.put("alamat", cu.getString(2));
                listmap.add(map);
            } while (cu.moveToNext());
        }


        String[] from = {"nim", "nama", "alamat"};
        int[] to = {R.id.tvNim, R.id.tvNama, R.id.tvAlamat};

        SimpleAdapter adapter = new SimpleAdapter(
                MainActivity.this,
                listmap,
                R.layout.items,
                from,
                to
        );

        lview.setAdapter(adapter);
        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posisi, long l) {
                leartDialog(posisi);
            }
        });
    }

    private void leartDialog(final int posisi) {
        String[] pilihan = {"Ubah", "Hapus"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        final AlertDialog.Builder builder_ubah_hapus = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Pilihan");
        builder.setItems(pilihan, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                TODO : PROSES UBAH
                if (i == 0) {
                    builder_ubah_hapus.setMessage("Yakin ingin ubah!");
                    builder_ubah_hapus.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(MainActivity.this, InputActivity.class);
                            intent.putExtra("nim", listmap.get(posisi).get("nim"));
                            intent.putExtra("nama", listmap.get(posisi).get("nama"));
                            intent.putExtra("alamat", listmap.get(posisi).get("alamat"));
                            startActivity(intent);
                        }
                    });
                    builder_ubah_hapus.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder_ubah_hapus.show();

                } else {
//                 TODO : PROSES HAPUS


                }
            }
        });

        builder.show();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        read();
    }
}
