package com.codewithrahul.wallpaperx;

import android.content.Context;
import android.widget.Toast;

import com.codewithrahul.wallpaperx.Listeners.CuratedResponseListener;
import com.codewithrahul.wallpaperx.Listeners.SearchResponseListener;
import com.codewithrahul.wallpaperx.Models.CuratedApiResponse;
import com.codewithrahul.wallpaperx.Models.SearchApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.pexels.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void searchCuratedWallpapers(SearchResponseListener listener, String page, String query) {
        CallWallpaperListSearch CallWallpaperListSearch = retrofit.create(CallWallpaperListSearch.class);
        Call<SearchApiResponse> call = CallWallpaperListSearch.searchWallpapers(query,page, "80");

        call.enqueue(new Callback<SearchApiResponse>() {
            @Override
            public void onResponse(Call<SearchApiResponse> call, Response<SearchApiResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, "An Error Occurred!", Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.onFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<SearchApiResponse> call, Throwable t) {
                listener.onError(t.getMessage());

            }
        });
    }



    public void getCuratedWallpapers(CuratedResponseListener listener, String page) {
        CallWallpaperList callWallpaperList = retrofit.create(CallWallpaperList.class);
        Call<CuratedApiResponse> call = callWallpaperList.getWallpapers(page, "80");

        call.enqueue(new Callback<CuratedApiResponse>() {
            @Override
            public void onResponse(Call<CuratedApiResponse> call, Response<CuratedApiResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, "An Error Occurred!", Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.onFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<CuratedApiResponse> call, Throwable t) {
                listener.onError(t.getMessage());

            }
        });
    }



    private interface CallWallpaperList {
        @Headers({
                "Accept: application/json",
                "Authorization: 563492ad6f91700001000001baf5ccd49eba484f8aa43b0e07c7ff6c"
        })
        @GET("curated/")
        Call<CuratedApiResponse> getWallpapers(
                @Query("page") String page,
                @Query("per_page") String per_page

        );
    }

    private interface CallWallpaperListSearch {
        @Headers({
                "Accept: application/json",
                "Authorization: 563492ad6f91700001000001baf5ccd49eba484f8aa43b0e07c7ff6c"
        })

        @GET("search")
        Call<SearchApiResponse> searchWallpapers(
                @Query("query") String query,
                @Query("page") String page,
                @Query("per_page") String per_page
        );
    }
}


