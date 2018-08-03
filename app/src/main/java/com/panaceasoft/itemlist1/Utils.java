package com.panaceasoft.itemlist1;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by Panacea-Soft on 3/8/18.
 * Contact Email : teamps.is.cool@gmail.com
 * Website : http://www.panacea-soft.com
 */
public class Utils {


    public static int getDrawableInt(Context context, String name) {
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }


    public static void setImageToImageView(Context context, ImageView imageView, int drawable) {
        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.NONE) // because file name is always same
                .skipMemoryCache(true);

        Glide.with(context)
                .load(drawable)
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

}
