package com.rxjavaexamples.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.rxjavaexamples.R;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class ObservableExampleActivity extends AppCompatActivity {
    private Observable<Company> justObservable;
    private Subscription justsubscription;
    private Subscription createsubscription;
    private Observable<Long> justObservableNew;
    private Subscription timerSubscribption;
    @Bind(R.id.test_tv)
    TextView testTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observable_example);
        ButterKnife.bind(this);
    }

    /* @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         // Inflate the menu; this adds items to the action bar if it is present.
         getMenuInflater().inflate(R.menu.menu_observable_example, menu);
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
     }*/
    @OnClick(R.id.create_bt)
    public void createObservale() {
        Timber.i("inside create Observable");
          testingCreate();
    }
    @OnClick(R.id.just_bt)
    public void justObservale() {
        Timber.i("inside just Observable");
        testingJust();
    }
    @OnClick(R.id.timer_bt)
    public void timerObservale() {
        Timber.i("inside timer Observable");
        testingTimer();
    }
    private void testingJust() {
        //.timer(1000,3000, TimeUnit.MILLISECONDS)

        justObservable = Observable.just("Leftshift").map(new Func1<String, Company>() {
            @Override
            public Company call(String s) {
                return new Company(s);
            }
        });
        //justObservableNew = justObservable.timer(1000,3000, TimeUnit.MILLISECONDS);
        justsubscription = justObservable.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Subscriber<Company>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Company company) {
                        testTv.setText("Company:" + company.companyName);
                    }
                });
    }

    private void testingTimer() {

        timerSubscribption = Observable.timer(1000, 2000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        doWork();
                    }

                });
    }

    int i = 0;

    private void doWork() {
        //Timber.i("Timer:"+i);
        testTv.setText("Count:" + i);
        i++;
    }

    private void testingCreate() {

        Observable<String> myObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        for (int i = 0; i < 10; i++) {
                            sub.onNext("Hello, world!" + i);
                        }
                        sub.onCompleted();
                    }
                }
        );

        createsubscription = myObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onNext(String s) {
                        testTv.setText(testTv.getText()+s+"\n");
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                })

        ;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (justsubscription != null) {
            justsubscription.unsubscribe();
        }
        if (createsubscription != null) {
            createsubscription.unsubscribe();
        }
        if (timerSubscribption != null) {
            timerSubscribption.unsubscribe();
        }
    }

    private class Company {
        String companyName;

        public Company(String companyName) {
            this.companyName = companyName;

        }
    }
}
