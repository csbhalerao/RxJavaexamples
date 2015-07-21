package com.rxjavaexamples.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.rxjavaexamples.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.retrofit_example)
    public void retrofitExample(){
        Intent retrofitIntent = new Intent(this,RetrofitExampleActivity.class);
        startActivity(retrofitIntent);
    }

    @OnClick(R.id.observable_bt)
    public void observableExample(){
        Intent observableIntent = new Intent(this,ObservableExampleActivity.class);
        startActivity(observableIntent);
    }

    @OnClick(R.id.validator_bt)
    public void validatorExample(){
        Intent validatorIntent = new Intent(this,FormValidatorActivity.class);
        startActivity(validatorIntent);
    }
}
