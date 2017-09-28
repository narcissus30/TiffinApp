package com.apkglobal.tiffin7;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class AccountActivity extends AppCompatActivity {

    ImageView ivname, ivphone, ivid;
    TextView tvname, tvphone, tvid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        ImageView ivname = (ImageView) findViewById(R.id.ivname);
        ImageView ivphone = (ImageView) findViewById(R.id.ivphone);
        ImageView ivid = (ImageView) findViewById(R.id.ivid);

        TextView tvname = (TextView) findViewById(R.id.tvname);
        TextView tvphone = (TextView) findViewById(R.id.tvphone);
        TextView tvid = (TextView) findViewById(R.id.tvid);
    }
}
