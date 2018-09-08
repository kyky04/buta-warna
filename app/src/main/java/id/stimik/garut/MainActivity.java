package id.stimik.garut;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.stimik.garut.activities.PanduanActivity;
import id.stimik.garut.activities.PembuatActivity;
import id.stimik.garut.activities.TentangActivity;
import id.stimik.garut.activities.TesActivity;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_toolbar)
    TextView tvToolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_play)
    LinearLayout btnPlay;
    @BindView(R.id.btn_tentang)
    LinearLayout btnLearn;
    @BindView(R.id.btn_panduan)
    LinearLayout btnSkor;
    @BindView(R.id.btn_about)
    LinearLayout btnAbout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        setSupportActionBar(toolbar);
    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menu_id_logout:
//                finish();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @OnClick({R.id.btn_play, R.id.btn_tentang, R.id.btn_panduan, R.id.btn_about})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_play:
                startActivity(new Intent(MainActivity.this, TesActivity.class));
                break;
            case R.id.btn_tentang:
                startActivity(new Intent(MainActivity.this, TentangActivity.class));
                break;
            case R.id.btn_panduan:
                startActivity(new Intent(MainActivity.this, PanduanActivity.class));
                break;
            case R.id.btn_about:
                startActivity(new Intent(MainActivity.this, PembuatActivity.class));
                break;
        }
    }
}
