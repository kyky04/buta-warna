package id.stimik.garut.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.stimik.garut.R;
import id.stimik.garut.models.Tes;
import id.stimik.garut.utils.CommonUtil;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class TesActivity extends AppCompatActivity {
    private static final String TAG = "TesActivity";

    @BindView(R.id.img_tes)
    ImageView imgTes;
    @BindView(R.id.tv_text_jawaban)
    TextView tvTextJawaban;
    @BindView(R.id.tv_jawaban)
    TextView tvJawaban;
    @BindView(R.id.tv_jawaban_benar)
    TextView tvJawabanBenar;
    @BindView(R.id.lay_jawaban)
    LinearLayout layJawaban;
    @BindView(R.id.et_jawaban)
    EditText etJawaban;
    @BindView(R.id.btn_jawab)
    ImageButton btnJawab;
    @BindView(R.id.btn_next)
    Button btnNext;

    List<Tes> tesList = new ArrayList<>();


    String[] jawaban = new String[]{"2", "3", "5", "6", "7", "8", "12", "15", "16", "57", "73"};
    int[] gambar = new int[]{R.drawable.ishihara_2,
            R.drawable.ishihara_3,
            R.drawable.ishihara_5,
            R.drawable.ishihara_6,
            R.drawable.ishihara_7,
            R.drawable.ishihara_8,
            R.drawable.ishihara_12,
            R.drawable.ishihara_15,
            R.drawable.ishihara_57,
            R.drawable.ishihara_73,
    };

    boolean next = false;
    int pos = 0;

    int jawabanBenar, jawabanSalah, jumlahTest = 0;
    @BindView(R.id.tv_jumlah_pertanyaan)
    TextView tvJumlahPertanyaan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        initView();
        generteSoal();

        Glide.with(this).load(gambar[pos]).into(imgTes);
        tvJumlahPertanyaan.setText(String.valueOf(pos + 1) + "/" + tesList.size());
    }

    private void initView() {
        layJawaban.setVisibility(View.GONE);
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @OnClick({R.id.btn_jawab, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_jawab:
                if (next) {
                    CommonUtil.showToast(this, "Tekan Next untuk Melanjutakan Pertanyaan !");
                } else {
                    jawabPertanyaan();
                }
                break;
            case R.id.btn_next:
                if (pos != tesList.size()) {
                    if (next) {
                        hideJawaban();
                    } else {
                        CommonUtil.showToast(this, "Harap Jawab Pertanyaan Terlebih dahulu !");
                    }
                } else {
                    for (int i = 0; i < tesList.size(); i++) {
                        Log.d(TAG, "JAWABAN BETUL: " + tesList.get(i).isBetul());
                        if (tesList.get(i).isBetul()) {
                            jawabanBenar++;
                        } else {
                            jawabanSalah++;
                        }
                    }
                    jumlahTest = tesList.size();

                    openHasil();
                }
                break;
        }
    }

    private void openHasil() {
        Intent intent = new Intent(this, HasilActivity.class);
        intent.putExtra("benar", jawabanBenar);
        intent.putExtra("jumlah", jumlahTest);
        startActivity(intent);
        finish();
    }

    private void hideJawaban() {
        next = false;
        layJawaban.setVisibility(View.GONE);
        etJawaban.setText("");
        Glide.with(this).load(gambar[pos]).into(imgTes);
        btnNext.setVisibility(View.INVISIBLE);
        tvJumlahPertanyaan.setText(String.valueOf(pos + 1) + "/" + tesList.size());
    }

    public void jawabPertanyaan() {
        if (pos != tesList.size()) {
            next = true;
            jawab();
        }
    }

    public void jawab() {
        btnNext.setVisibility(View.VISIBLE);
        layJawaban.setVisibility(View.VISIBLE);
        tvJawabanBenar.setText(String.valueOf(jawaban[pos]));

        if (etJawaban.getText().toString().equals(tesList.get(pos).getJawaban())) {
            tesList.get(pos).setBetul(true);
            tvJawaban.setText("BETUL");
        } else {
            tesList.get(pos).setBetul(false);
            tvJawaban.setText("SALAH");
        }
        pos++;


    }

    public void generteSoal() {
        tesList.add(new Tes(jawaban[0], gambar[0]));
        tesList.add(new Tes(jawaban[1], gambar[1]));
        tesList.add(new Tes(jawaban[2], gambar[2]));
        tesList.add(new Tes(jawaban[3], gambar[3]));
        tesList.add(new Tes(jawaban[4], gambar[4]));
        tesList.add(new Tes(jawaban[5], gambar[5]));


        Collections.shuffle(tesList);
    }

    @Override
    public void onBackPressed() {
        closeDialog();
    }

    void closeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Anda yakin untuk membatalkan Tes Buta Warna ?");
        builder.setPositiveButton("YA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).setNegativeButton("KEMBALI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
