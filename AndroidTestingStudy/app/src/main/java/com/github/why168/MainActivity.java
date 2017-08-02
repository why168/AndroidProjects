package com.github.why168;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * MainActivity
 *
 * @author Edwin.Wu
 * @version 2017/8/1 17:15
 * @since JDK1.8
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.buttonLogin).setOnClickListener(this);
        findViewById(R.id.buttonToast).setOnClickListener(this);
        findViewById(R.id.buttonLong).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonToast:
                Toast.makeText(this, "buttonToast onclick", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonLogin:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.buttonLong:
                startActivity(new Intent(this, LongListActivity.class));
                break;
            default:
                break;
        }
    }
}
