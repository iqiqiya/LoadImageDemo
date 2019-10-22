package iqiqiya.lanlana.loadimagedemo;

import com.bumptech.glide.request.RequestOptions;

/**
 * Author: iqiqiya
 * Date: 2019/10/22
 * Time: 10:18
 * Blog: blog.77sec.cn
 * Github: github.com/iqiqiya
 */
public class GlideOptionsUtils {

    public static RequestOptions baseOptions() {
        return new RequestOptions()
                .placeholder(R.mipmap.loading)
                .error(R.mipmap.loader_error);
    }

    public static RequestOptions circleCropOption() {
        return baseOptions().circleCrop();
    }
}
