package id.stimik.garut.activities;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.stimik.garut.R;
import id.stimik.garut.algoritma.LevenshteinDistance;
import id.stimik.garut.models.ItemSoal;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SoalActivity extends AppCompatActivity {
    private static final String TAG = "SoalActivity";
    private final int REQ_CODE_SPEECH_INPUT = 100;


    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.tv_progress)
    TextView tvProgress;
    @BindView(R.id.tv_soal)
    TextView tvItemSoal;
    @BindView(R.id.card_soal)
    CardView cardItemSoal;
    @BindView(R.id.tv_jawaban_user)
    TextView tvJawabanUser;
    @BindView(R.id.tv_jawaban_db)
    TextView tvJawabanDb;
    @BindView(R.id.card_jawaban)
    CardView cardJawaban;
    @BindView(R.id.tv_akurasi)
    TextView tvAkurasi;
    @BindView(R.id.btn_voice)
    ImageButton btnVoice;
    @BindView(R.id.btn_next)
    Button btnNext;

    List<ItemSoal> soalList = new ArrayList<>();

    int pos = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal);
        ButterKnife.bind(this);

       soalList = loadData();

        tvItemSoal.setText(soalList.get(pos).getPertanyaan() +"\n"+ soalList.get(pos).getPertanyaanLanjutan());
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @OnClick({R.id.btn_voice, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_voice:
                promptSpeechInput();
                break;
            case R.id.btn_next:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    tvJawabanUser.setText(result.get(0));
                    tvJawabanDb.setText(soalList.get(0).getJawaban());


                    int panjangA = result.get(0).length();
                    int panjangB = soalList.get(0).getJawaban().length();
                    int panjangKarakter = 0;

                    if (panjangA > panjangB) {
                        panjangKarakter = panjangA;
                    } else if (panjangB > panjangA) {
                        panjangKarakter = panjangB;
                    }


                    int distance = LevenshteinDistance.computeLevenshteinDistance(result.get(0), soalList.get(0).getJawaban());

                    float kemiripan = (float) distance / panjangKarakter;
                    kemiripan = 1 - kemiripan;

                    Log.d("DISTANCE", String.valueOf(distance));
                    Log.d("PANJANG KARAKTER", String.valueOf(panjangKarakter));
                    Log.d("KEMIRIPAN", String.valueOf(kemiripan));

                    String hasil;

                    if (kemiripan > 0.5) {
                        hasil = "benar";
                    } else {
                        hasil = "salah";
                    }
                    tvAkurasi.setText(hasil);
                }
                break;
            }

        }
    }

    public ArrayList<ItemSoal> loadData() {
        ArrayList<ItemSoal> locList = new ArrayList<>();
        String json = null;
        try {
            InputStream is = getAssets().open("soal.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray m_jArry = obj.getJSONArray("soal");

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                ItemSoal doa = new ItemSoal();
                doa.setId(jo_inside.getInt("id"));
                doa.setJawaban(jo_inside.getString("jawaban"));
                doa.setPertanyaan(jo_inside.getString("pertanyaan"));
                doa.setPertanyaanLanjutan(jo_inside.getString("pertanyaan_lanjutan"));

                Log.d(TAG, "loadData: " + doa.getPertanyaan() + " " + doa.getPertanyaanLanjutan() + " " + doa.getJawaban() + " ");
                //Add your values in your `ArrayList` as below:
                locList.add(doa);
//                notesBox.put(doa);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return locList;
    }

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        Locale loc = new Locale("ar");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ar-SA");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Silahkan jawab");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "Not supported",
                    Toast.LENGTH_SHORT).show();
        }


    }
}
