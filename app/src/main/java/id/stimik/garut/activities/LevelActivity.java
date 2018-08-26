package id.stimik.garut.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.stimik.garut.R;
import id.stimik.garut.adapters.LevelAdapter;
import id.stimik.garut.models.ItemLevel;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LevelActivity extends AppCompatActivity {
    private static final String TAG = "LevelActivity";
    @BindView(R.id.tv_toolbar)
    TextView tvToolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    LevelAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        ButterKnife.bind(this);

        adapter = new LevelAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.add(new ItemLevel("Level 1"));
        adapter.add(new ItemLevel("Level 2"));
        adapter.add(new ItemLevel("Level 3"));

        adapter.setOnItemClickListener(new LevelAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(new Intent(LevelActivity.this,SoalActivity.class));
            }

            @Override
            public void onLongClick(int position) {

            }
        });
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
