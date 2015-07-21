package com.rxjavaexamples.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.rxjavaexamples.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.widget.OnTextChangeEvent;
import rx.android.widget.WidgetObservable;
import rx.functions.Func2;
import timber.log.Timber;

public class FormValidatorActivity extends AppCompatActivity {
    @Bind(R.id.username_et)
    EditText usernameEt;
    @Bind(R.id.password_et)
    EditText passwordEt;
    private Observable<OnTextChangeEvent> usernameObservable;
    private Observable<OnTextChangeEvent> passwordObservable;
    private Subscription validatorSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_validator);
        ButterKnife.bind(this);

        usernameObservable = WidgetObservable.text(usernameEt);
        passwordObservable = WidgetObservable.text(passwordEt);
        combineAll();
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_form_validator, menu);

        return true;
    }*/

    private void combineAll() {

        validatorSubscription = Observable.combineLatest(usernameObservable, passwordObservable,
                new Func2<OnTextChangeEvent, OnTextChangeEvent, Boolean>() {
                    @Override
                    public Boolean call(OnTextChangeEvent usernameTextChangeEvent, OnTextChangeEvent passwordTextChangeEvent2) {
                        String username = usernameTextChangeEvent.text().toString();
                        boolean usernameValid;
                        /*boolean usernameValid = !TextUtils.isEmpty(username);
                        if (!usernameValid) {
                            usernameEt.setError("Enter Username");
                        }*/
                        usernameValid = username.length() < 6 || username.length() > 10 ? false : true;
                        if (!usernameValid) {
                            usernameEt.setError("Username length should be >6 and < 10");
                        }

                        String password = passwordTextChangeEvent2.text().toString();
                        boolean passwordValid ;//= !TextUtils.isEmpty(password);
                        /*if (!passwordValid) {
                            passwordEt.setError("Enter password");
                        }*/
                        passwordValid = password.length() < 6 ? false : true;
                        if (!passwordValid) {
                            passwordEt.setError("Password length should be > 6");
                        }
                        return usernameValid && passwordValid;
                    }
                }).subscribe(new Observer<Boolean>() {
            @Override
            public void onCompleted() {
                Timber.i("completed");
            }

            @Override
            public void onError(Throwable e) {
                Timber.i("error");
                e.printStackTrace();
            }

            @Override
            public void onNext(Boolean isFormValid) {
                if (isFormValid) {
                    Toast.makeText(FormValidatorActivity.this, "Everything is correct!", Toast.LENGTH_LONG).
                            show();
                }
            }
        });
    }

    /*@Override
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
    }*/
    @Override
    public void onPause() {
        super.onPause();
        if (validatorSubscription != null) {
            validatorSubscription.unsubscribe();
        }
    }
}
