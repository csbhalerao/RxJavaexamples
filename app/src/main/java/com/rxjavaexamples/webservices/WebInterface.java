package com.rxjavaexamples.webservices;

import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;
import com.rxjavaexamples.models.LoggedInUser;
import com.rxjavaexamples.models.LoginRequest;

/**
 * Created by chetan on 12/07/15.
 */

public interface WebInterface {
    @POST("/Login")
    Observable<LoggedInUser> login(@Body LoginRequest loginRequest);
}
