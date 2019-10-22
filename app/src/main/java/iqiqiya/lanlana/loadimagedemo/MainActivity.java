package iqiqiya.lanlana.loadimagedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ImageView mIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIv = findViewById(R.id.iv);
    }

    public void onLoadImageClick(View v) {
        //普通方式 loadUrlImage("https://avatars2.githubusercontent.com/u/43666095?s=460&v=4");
        glideLoadImage("http://p7.qhimg.com/bdm/2560_1600_100/t0116c6a290615250b7.jpg");
    }

    /**
     * 加载网络图片
     * @param img
     */
    private void loadUrlImage(final String img){

        mIv.setImageResource(R.mipmap.loading);

        /**
         * 1.先找图片地址
         * 2.根据图片地址，把图片转化为可被加载的对象
         * 3.通过imageView来把对象展示出来
         */
        new Thread(){
            @Override
            public void run() {
                super.run();

                Message message = new Message();

                try {
                    URL url = new URL(img);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    int code = urlConnection.getResponseCode();
                    if (code == 200){
                        // 获取输入流
                        InputStream inputStream = urlConnection.getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        message.obj = bitmap;
                        message.what = 200;
                    } else {
                        message.what = code;
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    message.what = -1;
                }catch (IOException e) {
                    e.printStackTrace();
                    message.what = -1;
                } finally {
                    handler.sendMessage(message);
                }
            }
        }.start();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case 200:
                    Bitmap bitmap = (Bitmap) msg.obj;
                    mIv.setImageBitmap(bitmap);
                    break;
                    default:
                        mIv.setImageResource(R.mipmap.loader_error);
                        break;
            }
        }
    };

    /**
     * 通过Glide加载网络图片
     * @param img
     */
    private void glideLoadImage(String img){
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.loading)
                .error(R.mipmap.loader_error)
                .circleCrop();

        Glide.with(this)
                .load(img)
                .apply(options)
                .into(mIv);
    }

}
