package com.rxjavaexamples.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.rxjavaexamples.R;
import com.rxjavaexamples.models.LoggedInUser;
import com.rxjavaexamples.models.LoginRequest;
import com.rxjavaexamples.webservices.WebInterface;
import com.rxjavaexamples.webservices.Webclient;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class RetrofitExampleActivity extends AppCompatActivity {
    @Bind(R.id.username_et)
    EditText editTextUserName;
    @Bind(R.id.password_et)
    EditText editTextPassword;
    Observable<LoggedInUser> loginResponseObservable;
    Subscription subscription;
    @Bind(R.id.login_text)
    TextView loginTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_example);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_retrofit_example, menu);
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

    @OnClick(R.id.login_bt)
    public void login(){
        WebInterface webInterface = Webclient.getApiClient(this);
        LoginRequest loginRequest = new LoginRequest(editTextUserName.getText().toString(),
                editTextPassword.getText().toString());
        loginResponseObservable = webInterface.login(loginRequest);

        subscription = loginResponseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoggedInUser>() {

                    @Override
                    public void onCompleted() {
                        Timber.i("at onCompleted %s", "login completed");
                    }

                    @Override
                    public void onError(Throwable ex) {
                        Timber.i("at error %s ", "login failed " + ex.getMessage());
                    }

                    @Override
                    public void onNext(LoggedInUser loggedInUser) {
                        /*Timber.i("on next user name is %s ", "" + loggedInUser.getName());
                        Timber.i("user id is %s ", "" + loggedInUser.getCustId());
                        Timber.i("login id is %s ", "" + loggedInUser.getLoginID());*/
                        if (loggedInUser != null) {
                            loginTextView.setText(loggedInUser.getJson());
                        }
                    }
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //this will prevent memory leak
        if(subscription!=null){
            subscription.unsubscribe();
        }
    }
}
