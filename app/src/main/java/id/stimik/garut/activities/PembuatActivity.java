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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.stimik.garut.R;
import id.stimik.garut.models.Tes;
import id.stimik.garut.utils.CommonUtil;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PembuatActivity extends AppCompatActivity {
    private static final String TAG = "PembuatActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembuat);
        ButterKnife.bind(this);


    }


}
