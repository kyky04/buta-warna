package id.stimik.garut.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.stimik.garut.MainActivity;
import id.stimik.garut.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class HasilActivity extends AppCompatActivity {
    private static final String TAG = "HasilActivity";


    int jawabanBenar, jawabanSalah, jumlahTest = 0;
    @BindView(R.id.img_tes)
    ImageView imgTes;
    @BindView(R.id.tv_hasil)
    TextView tvHasil;
    @BindView(R.id.tv_hasil_hitung)
    TextView tvHasilHitung;
    @BindView(R.id.lay_jawaban)
    LinearLayout layJawaban;
    @BindView(R.id.btn_tes_lagi)
    Button btnTesLagi;
    @BindView(R.id.btn_home)
    Button btnHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);
        ButterKnife.bind(this);
        initView();


        jawabanBenar = getIntent().getIntExtra("benar", 0);
        jumlahTest = getIntent().getIntExtra("jumlah", 0);

        if (jawabanBenar > 8) {
            Glide.with(this).load(R.drawable.happy).into(imgTes);
            tvHasil.setText("NORMAL");
        } else if (jawabanBenar > 1 && jawabanBenar < 8) {
            Glide.with(this).load(R.drawable.smiling).into(imgTes);
            tvHasil.setText("BUTA WARNA PARSIAL");
        } else {
            Glide.with(this).load(R.drawable.unhappy).into(imgTes);
            tvHasil.setText("BUTA WARNA TOTAL");
        }
        tvHasilHitung.setText("Anda Betul " + jawabanBenar + " dari " + jumlahTest + " Pertanyaan");
    }

    private void initView() {
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @OnClick({R.id.btn_tes_lagi, R.id.btn_home})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_tes_lagi:
                Intent intent = new Intent(this, TesActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_home:
                finish();
                break;
        }
    }
}
