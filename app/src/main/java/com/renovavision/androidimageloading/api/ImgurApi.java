package com.renovavision.androidimageloading.api;

import android.util.Log;

import com.renovavision.androidimageloading.model.Image;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Path;

/**
 * Created by Alexandr Golovach on 21.11.2015.
 */
public class ImgurApi {

    private static final String TAG = ImgurApi.class.getName();

    private static final String BASE_URL = "https://api.imgur.com/";
    private static final String AUTH_HEADER = "Client-ID 0feb0ab092f3d0b";

    private static volatile ImgurApi sInstance;

    public static ImgurApi getInstance() {
        if (sInstance == null) {
            synchronized (ImgurApi.class) {
                if (sInstance == null) {
                    sInstance = new ImgurApi();
                }
            }
        }
        return sInstance;
    }

    private ExecutorService mCallbackExecutor = Executors.newSingleThreadExecutor();
    private ImgurApiService mService;


    private ImgurApi() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .validateEagerly().callbackExecutor(mCallbackExecutor).build();
        mService = retrofit.create(ImgurApiService.class);
    }

    // should run in sync mode
    public List<Image> getImages(long page) {
        Call<ImageData> call = mService.getImages(AUTH_HEADER, page);
        try {
            Response<ImageData> response = call.execute();
            if (response == null) {
                return null;
            }

            final ImageData body = response.body();
            if (body == null) {
                return null;
            }

            return body.getImages();

        } catch (IOException e) {
            Log.e("Can not load images", TAG, e);
            return null;
        }
    }


    private interface ImgurApiService {

        @GET("3/gallery/hot/viral/{page}")
        public Call<ImageData> getImages(@Header("Authorization") String header, @Path("page") long page);
    }
}
