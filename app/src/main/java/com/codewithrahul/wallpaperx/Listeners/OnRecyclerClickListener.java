package com.codewithrahul.wallpaperx.Listeners;

import com.codewithrahul.wallpaperx.Models.Photo;

public interface OnRecyclerClickListener {
    void onClick();

    void onClick(Photo photo);
}
