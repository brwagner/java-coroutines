package com.brajswagner.coroutines.examples;

import com.brajswagner.coroutines.lib.Coroutine;
import okhttp3.*;

import java.io.IOException;

public class WWW extends Coroutine<Result<String, IOException>> {
    private String url;
    private Result<String, IOException> result;
    public WWW(String url) {
        this.url = url;
    }
    @Override
    public Result<String, IOException> run() throws InterruptedException {
        final OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                WWW.this.result = Result.error(e);
            }
            @Override public void onResponse(Call call, Response response) throws IOException {
                WWW.this.result = Result.success(response.body().string());
            }
        });
        while (result == null) {
            yield(null);
        }
        return result;
    }
}
