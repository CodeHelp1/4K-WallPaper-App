package com.codewithrahul.wallpaperx.Listeners;

import com.codewithrahul.wallpaperx.Models.SearchApiResponse;

public interface SearchResponseListener {
    void onFetch(SearchApiResponse response, String message);
    void onError(String message);


}
