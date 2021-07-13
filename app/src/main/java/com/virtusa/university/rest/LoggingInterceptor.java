package com.virtusa.university.rest;

import android.util.Log;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import okio.Buffer;

public class LoggingInterceptor implements Interceptor {

    @Override public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        logRequest(request);
        return logResponse(chain.proceed(request));
    }

    protected void logRequest(Request request) throws IOException {

        if (request.body() != null) {
            Buffer buffer = new Buffer();
            request.body().writeTo(buffer);
            String requestBody = buffer.readUtf8();
            Log.d("University Request:", requestBody);

        }
    }

    protected Response logResponse(Response response) throws IOException {
        ResponseBody body = response.body();
        if (body == null) return response;

        String bodyString = body.string();
        Log.d("University Response:", bodyString);

        return response.newBuilder()
                .body(ResponseBody.create(body.contentType(), bodyString))
                .build();
    }
}

