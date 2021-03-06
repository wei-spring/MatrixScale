package com.linsea.matrixscale;

import android.graphics.Matrix;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.VideoView;

import com.danikula.videocache.HttpProxyCacheServer;

public class MainActivity extends AppCompatActivity {


    VideoView videoView0;
    FixedTextureVideoView videoView;

    String url = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView0 = (VideoView) findViewById(R.id.videoView0);
        videoView = (FixedTextureVideoView) findViewById(R.id.videoView);


        HttpProxyCacheServer proxy = App.getProxy(this);
        final String proxyUrl = proxy.getProxyUrl(url);

        videoView.post(new Runnable() {
            @Override
            public void run() {
                videoView.setFixedSize(videoView.getWidth(), videoView.getHeight());
                videoView.invalidate();

                videoView.setVideoPath(proxyUrl);
                videoView.start();
            }
        });

        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_LONG).show();
            }
        });


        videoView0.setVideoPath(proxyUrl);
        videoView0.start();
    }


    /*

    PicView picView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        picView = (PicView) findViewById(R.id.pic);

        picView.post(new Runnable() {
            @Override
            public void run() {
                //图像铺满整个View
                Matrix matrix = new Matrix();

                //图像铺满整个View
//                matrix.postScale(2, 2, 0, 0);
//                picView.setMatrix(matrix);

                //图像右下角显示在View的左上角
//                matrix.postScale(2, 2, picView.getWidth() / 2, picView.getHeight() / 2);
//                picView.setMatrix(matrix);

//                没有头像
//                matrix.preTranslate(picView.getWidth() / 2, picView.getHeight() / 2);
//                matrix.postScale(2, 2);//似乎以左上角为坐标原点
//                picView.setMatrix(matrix);

                //右下角区域显示图片的左上角
//                matrix.postTranslate(picView.getWidth() / 2, picView.getHeight() / 2);
//                matrix.postScale(2, 2, picView.getWidth() / 2, picView.getHeight() / 2);



                //需求:图像等比例放大,直至短边铺满屏幕,再移动到View的正中央,这样长边两边会被裁剪掉同样大小的区域
                int bitmapWidth = picView.bitmap.getWidth();
                int bitmapHeight = picView.bitmap.getHeight();
                int viewWidth = picView.getWidth();
                int viewHeight = picView.getHeight();

                float maxScale = Math.max(viewWidth / (float) bitmapWidth, viewHeight / (float) bitmapHeight);

                matrix.preTranslate((viewWidth - bitmapWidth) / 2, (viewHeight - bitmapHeight) / 2);
                matrix.postScale(maxScale, maxScale, viewWidth / 2, viewHeight / 2);





                //结论:matrix.postScale(2, 2)以最原始的坐标原点为支点,而matrix.postScale(2, 2, x,y)以最原始的坐标轴向右下平移后为支点
                picView.setMatrix(matrix);
                picView.invalidate();
            }
        });

    }
*/

}
