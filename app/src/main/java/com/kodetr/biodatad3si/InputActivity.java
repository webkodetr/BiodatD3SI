package com.kodetr.biodatad3si;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputActivity extends AppCompatActivity{

    private EditText etNim, etNama, etAlamat;
    private Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        init();
    }

    private void init() {
        etNim = (EditText) findViewById(R.id.etNim);
        etNama = (EditText) findViewById(R.id.etNama);
        etAlamat = (EditText) findViewById(R.id.etAlamat);

        btnSimpan = (Button) findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                create();
            }
        });
    }

    private void create(){
        BiodataDao dao = new BiodataDaoImp(this);

        Biodata biodata = new Biodata();
        biodata.setNim(etNim.getText().toString());
        biodata.setNama(etNama.getText().toString());
        biodata.setAlamat(etAlamat.getText().toString());

        if(dao.create(biodata)){
            Toast.makeText(this, "Berhasil disimpan", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
