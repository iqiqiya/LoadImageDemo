package iqiqiya.lanlana.loadimagedemo;

import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.annotation.GlideType;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;

/**
 * Author: iqiqiya
 * Date: 2019/10/22
 * Time: 10:33
 * Blog: blog.77sec.cn
 * Github: github.com/iqiqiya
 */

@GlideExtension
public class MyGlideExtension {

    private MyGlideExtension(){}

    /**@GlideOption
    public static void injectOptions(RequestOptions options){
        options.placeholder(R.mipmap.loading)
                .error(R.mipmap.loader_error)
                .circleCrop();
    }*/


    @GlideOption
    public static BaseRequestOptions<?> injectOptions(BaseRequestOptions<?> options) {
        return options.placeholder(R.mipmap.loading)
                .error(R.mipmap.loader_error)
                .circleCrop();
    }
}
