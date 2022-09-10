package com.codewithrahul.wallpaperx.Listeners;

import com.codewithrahul.wallpaperx.Models.CuratedApiResponse;

public interface CuratedResponseListener {
    void onFetch(CuratedApiResponse response,String message);
    void onError (String message);

}
