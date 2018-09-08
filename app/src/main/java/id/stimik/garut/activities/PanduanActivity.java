package id.stimik.garut.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import id.stimik.garut.R;

public class PanduanActivity extends AppCompatActivity {
    private static final String TAG = "PembuatActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panduan);
        ButterKnife.bind(this);


    }


}
